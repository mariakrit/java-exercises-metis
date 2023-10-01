package exercises.metis.weather.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Configuration
@RequiredArgsConstructor
public class OpenWeatherMapProperties {

	@Value("${openweathermapForAthens.scheme}")
	private String scheme;

	@Value("${openweathermapForAthens.apiUrl}")
	private String apiUrl;

	@Value("${openweathermapForAthens.paramLatitude}")
	private String latitude;

	@Value("${openweathermapForAthens.paramLongitude}")
	private String longitude;

	@Value("${openweathermapForAthens.apiKey}")
	private String apiKey;

	@Value("${openweathermapForAthens.unitCelsius}")
	private String unitCelsius;
}
