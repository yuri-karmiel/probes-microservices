import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TimestampsChecker {
@Value("${app.check.timeout:1000}")
	long checkTimeout;
Instant timestamp;
@Value("${app.time.period.poll.seconds:3600}")
private long timePeriodSensorsDataPoll;
//List<Sensor> sensors = new ArrayList<>();
//final SensorDataProviderClient client; //interface with only method getSensorData returning a list 
	@PostConstruct
	void startPeriodicalCheck() {
		Thread checkerThread = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(checkTimeout);
				} catch (InterruptedException e) {
					
				}
				checkSensors();
			}
		});
		checkerThread.setDaemon(true);
		checkerThread.start();
	}
	private void checkSensors() {
		if(timestamp == null || ChronoUnit.SECONDS.between(timestamp, Instant.now()) >
		timePeriodSensorsDataPoll) {
			//communicating with synchronous service for sensors data 
			//sensors = client.getSensorsData()
			timestamp = Instant.now();
		}
		//.............................
		
		
	}
}
