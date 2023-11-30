package telran.microservices.probes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.microservices.probes.dto.Probe;
import telran.microservices.probes.entities.ListProbeValues;
import telran.microservices.probes.repo.ListProbeRepo;
import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class AvgReducerImpl implements AvgReducer {
	final ListProbeRepo listProbeRepo;
	@Value("${app.reducing.size:100}")
	int reducingSize;
	@Override
	public Integer avgReduce(Probe probe) {
		long id = probe.id();
		Integer res = null;
		ListProbeValues listProbeValues =
				listProbeRepo.findById(id).orElse(null);
		List<Integer> values = null;
		
		if(listProbeValues == null) {
			log.debug("sensor {} not found in Redis", id);
			listProbeValues = new ListProbeValues(id);
			
		} 
		values = listProbeValues.getValues();
		values.add(probe.value());
		if(values.size() == reducingSize) {
			res = values.stream().collect(Collectors.averagingInt(v -> v)).intValue();
			values.clear();
			log.debug("computed avg value {} for sensor {}", res, id);
		} else {
			log.trace("no avg value for sensor {}", id);
		}
		listProbeRepo.save(listProbeValues);
		log.trace("Redis updated for sensor {}",id);
		return res;
	}

}
