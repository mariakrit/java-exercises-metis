package exercises.metis.weather.api;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherServiceTest {

	@RegisterExtension
	static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
			.withConfiguration(GreenMailConfiguration.aConfig().withUser("maria", "testPass"))
			.withPerMethodLifecycle(false);

	@InjectMocks
	private WeatherService weatherService;

	@Mock
	private OpenWeatherMapProperties openWeatherMapProperties;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private JavaMailSender javaMailSender;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		greenMail.start();
	}

	@After
	public void stopMailServer() {
		greenMail.stop();
	}

	@Test
	public void testFetchWeatherData() throws Exception {
//		// Mock OpenWeatherMap properties
//		when(openWeatherMapProperties.getApiUrl()).thenReturn("http://localhost:8080/weather");
//		when(openWeatherMapProperties.getScheme()).thenReturn("http");
//		when(openWeatherMapProperties.getLatitude()).thenReturn("123.45");
//		when(openWeatherMapProperties.getLongitude()).thenReturn("67.89");
//		when(openWeatherMapProperties.getApiKey()).thenReturn("test-api-key");
//		when(openWeatherMapProperties.getUnitCelsius()).thenReturn("metric");

		// Mock ResponseEntity for the REST call
//		String jsonResponse = "{\"main\": {\"temp\": 21.0, \"humidity\": 50}, \"wind\": {\"speed\": 5.0}, \"weather\": [{\"description\": \"Partly cloudy\"}]}";
//		ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResponse, HttpStatus.OK);
//		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntity);
//
//		MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
//
//		// Call the fetchWeatherData method
//		weatherService.fetchWeatherData();
//
//		assertEquals(1, receivedMessages.length);
//
//		// Verify that the sendEmail method is called
////		verify(weatherService, times(1)).sendEmail(21.0, 50, 5.0, "Partly cloudy");
	}
}
