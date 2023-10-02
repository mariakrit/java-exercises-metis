package exercises.metis.weather.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private WeatherService weatherService;

	@Mock
	private OpenWeatherMapProperties openWeatherMapProperties;

	@Mock
	private JavaMailSender javaMailSender;

	@Test
	public void testFetchWeatherData() throws Exception {

		// Mock OpenWeatherMap properties
		when(openWeatherMapProperties.getApiUrl()).thenReturn("localhost:8080/weather");
		when(openWeatherMapProperties.getScheme()).thenReturn("http");
		when(openWeatherMapProperties.getLatitude()).thenReturn("123.45");
		when(openWeatherMapProperties.getLongitude()).thenReturn("67.89");
		when(openWeatherMapProperties.getApiKey()).thenReturn("test-api-key");
		when(openWeatherMapProperties.getUnitCelsius()).thenReturn("metric");

		String jsonResponse = "{\"main\": {\"temp\": 21.0, \"humidity\": 50}, "
				+ "\"wind\": {\"speed\": 5.0}, \"weather\": [{\"description\": \"Partly cloudy\"}]}";
		ResponseEntity<String> mockResponseEntity = new ResponseEntity<>(jsonResponse, HttpStatus.OK);

		Mockito.when(restTemplate.getForEntity(
				"http://localhost:8080/weather?lat=123.45&lon=67.89&appid=test-api-key&units=metric", String.class))
				.thenReturn(mockResponseEntity);

		weatherService.fetchWeatherData();

		Mockito.verify(javaMailSender, Mockito.times(1)).send(any(SimpleMailMessage.class));
	}
}
