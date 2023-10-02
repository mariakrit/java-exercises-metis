package exercises.metis.weather.api;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

	Logger log = LoggerFactory.getLogger(WeatherService.class);

	private OpenWeatherMapProperties openWeatherMapProperties;

	private RestTemplate restTemplate;

	private JavaMailSender javaMailSender;

	@Autowired
	public WeatherService(OpenWeatherMapProperties openWeatherMapProperties, RestTemplate restTemplate,
			JavaMailSender javaMailSender) {
		super();
		this.openWeatherMapProperties = openWeatherMapProperties;
		this.restTemplate = restTemplate;
		this.javaMailSender = javaMailSender;
	}

	@Scheduled(fixedRate = 60000) // Run every 1 minute (60,000 milliseconds)
	public void fetchWeatherData() {
		try {

			// Build the URL
			URIBuilder builder = new URIBuilder();
			builder.setHost(openWeatherMapProperties.getApiUrl());
			builder.setScheme(openWeatherMapProperties.getScheme());
			builder.setParameter("lat", openWeatherMapProperties.getLatitude());
			builder.setParameter("lon", openWeatherMapProperties.getLongitude());
			builder.setParameter("appid", openWeatherMapProperties.getApiKey());
			builder.setParameter("units", openWeatherMapProperties.getUnitCelsius());

			// Make the HTTP GET request
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.toString(), String.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				String jsonResponse = responseEntity.getBody();

				// Parse the JSON response
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(jsonResponse);
				int temp = jsonNode.get("main").get("temp").asInt();

				if (temp > 20.0) {
					sendEmail(temp, jsonNode.get("main").get("humidity").asInt(),
							jsonNode.get("wind").get("speed").asDouble(),
							jsonNode.get("weather").get(0).get("description").asText());
				}
			} else {
				throw new Exception("Request to api.openweathermap.org has been failed");
			}
		} catch (Exception e) {
			log.error("WeatherService failed to execute ", e);
		}
	}

	// Send email notification
	void sendEmail(int temperature, int humidity, double windSpeed, String weatherDescription) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();

			message.setFrom("admin@test.com");
			message.setTo("mariaskrit@gmail.com");
			message.setSubject("Temperature is greater than 20°C in Athens: " + temperature + "°C");
			message.setText("Temperature: " + temperature + "°C\n" + "Humidity: " + humidity + "\n" + "Wind speed: "
					+ windSpeed + "\n" + "Weather description: " + weatherDescription);

			javaMailSender.send(message);
		} catch (Exception e) {
			log.error("Send email failed ", e);
		}
	}

}