package exercises.metis.weather.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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

	@Autowired
	private OpenWeatherMapProperties openWeatherMapProperties;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JavaMailSender javaMailSender;

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
				double temp = jsonNode.get("main").get("temp").asDouble();

				if (temp > 20.0) {
					sendEmail(temp, jsonNode.get("main").get("humidity").asInt(),
							jsonNode.get("wind").get("speed").asDouble(),
							jsonNode.get("weather").get(0).get("description").asText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Send email notification
	void sendEmail(double temperature, int humidity, double windSpeed, String weatherDescription) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();

			// Set email details
			message.setFrom("admin@test.com");
//			message.setTo("mariaskrit@yahoo.gr");
			message.setSubject("Temperature is greater than 20°C in Athens: " + temperature + "°C");
			message.setText("Temperature: " + temperature + "°C\n" + "Humidity: " + humidity + "\n" + "Wind speed: "
					+ windSpeed + "\n" + "Weather description: " + weatherDescription);

			// Send the email
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}