package telran.microservices.probes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import telran.microservices.probes.dto.EmailData;
import telran.microservices.probes.service.DataProvider;
@WebMvcTest
class DataProviderControllerTest {
@Autowired
	MockMvc mockMvc;
@MockBean
DataProvider dataProvider;
EmailData emailData = new EmailData(new String[] {"vasya@gmail.com", "petya@gmail.com"},
		new String[] {"vasya", "petya"});
	@Test
	void dataExistTest() {
		when(dataProvider.getEmailData(123)).thenReturn(emailData);
		
		
	}

}
