package telran.microservices.probes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.microservices.probes.dto.EmailData;
import telran.microservices.probes.service.DataProvider;

@RestController
@RequiredArgsConstructor
public class DataProviderController {
	final DataProvider dataProvider;
	@GetMapping("email/data/{id}")
	ResponseEntity<?> getEmailData(@PathVariable long id) {
		ResponseEntity<?> result = null;
		try {
			EmailData emailData = dataProvider.getEmailData(id);
			result = new ResponseEntity<EmailData>(emailData, HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return result;
	}
}
