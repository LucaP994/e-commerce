package it.trinakria.ecommerce.config;

import it.trinakria.ecommerce.config.props.CorsProps;
import it.trinakria.ecommerce.config.props.PageProps;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@ConfigurationProperties(prefix = "backend")
@Setter
@Getter
public class AppConfiguration {
	public final static String API_BASE = "api/v1";
	private PageProps page;
	private CorsProps cors;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		var filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}
}
