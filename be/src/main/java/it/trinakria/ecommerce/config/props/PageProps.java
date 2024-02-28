package it.trinakria.ecommerce.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageProps {

	private Integer pageSize;
	private Sort.Direction defaultDirection;
}
