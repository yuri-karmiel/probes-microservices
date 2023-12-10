package telran.microservices.probes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.microservices.probes.dto.EmailData;
import telran.microservices.probes.entities.Person;
import telran.microservices.probes.repo.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataProviderImpl implements DataProvider {
	final PersonRepo personRepo;
	final SensorRepo sensorRepo;
	@Override
	public EmailData getEmailData(long sensorId) {
		List<Person> persons = personRepo.findBySensorId(sensorId);
		if (persons.isEmpty()) {
			throw new IllegalArgumentException("no person for sensor " + sensorId);
		}
		String[] emailAddresses = persons.stream().map(Person::getEmail)
				.toArray(String[]::new);
		String[] names = persons.stream().map(Person::getName)
				.toArray(String[]::new);
		return new EmailData(emailAddresses, names);
	}
	
	
}
