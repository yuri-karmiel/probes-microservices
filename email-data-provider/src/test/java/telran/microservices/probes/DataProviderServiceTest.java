package telran.microservices.probes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import telran.microservices.probes.dto.EmailData;
import telran.microservices.probes.service.DataProvider;
import telran.spring.exceptions.NotFoundException;

@SpringBootTest
@Sql(scripts = "db-test-script.sql")
public class DataProviderServiceTest {
	private static final long SENSOR_ID_EXIST = 100000;
	private static final long SENSOR_ID_NOT_EXIST = 100001;
	private static final String ERROR_MESSAGE = "no person for sensor " + SENSOR_ID_NOT_EXIST;
	
	@Autowired
     DataProvider dataProvider;
	@Test
	void emailDataExistTest() {
		EmailData expected = new EmailData(new String[] {"vasya@gmail.com", "sara@gmail.com"},
				new String[] {"Vasya", "Sara"});
		EmailData actual = dataProvider.getEmailData(SENSOR_ID_EXIST);
		assertArrayEquals(expected.emailAddress(), actual.emailAddress());
		assertArrayEquals(expected.name(), actual.name());
	}
	@Test
	void emailDataNotExistTest() {
		assertThrowsExactly(NotFoundException.class,
				() -> dataProvider.getEmailData(SENSOR_ID_NOT_EXIST), ERROR_MESSAGE);
	}
}
