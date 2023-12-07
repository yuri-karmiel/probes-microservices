package telran.microservices.probes.service;

import telran.microservices.probes.dto.EmailData;

public interface DataProvider {
EmailData getEmailData(long sensorId);
}
