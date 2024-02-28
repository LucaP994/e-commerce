package it.trinakria.ecommerce.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Getter
@Setter
public class CorsProps {

	private List<String> allowedOriginPatterns;
	private boolean allowCredentials;
	private List<String> allowedMethods;
	private List<String> allowedHeaders;

	public CorsConfiguration toCorsConfiguration() {

		CorsConfiguration res = new CorsConfiguration();
		res.setAllowedHeaders(allowedHeaders);
		res.setAllowedMethods(allowedMethods);
		res.setAllowedOriginPatterns(allowedOriginPatterns);
		res.setAllowCredentials(allowCredentials);

		return res;
	}
}
