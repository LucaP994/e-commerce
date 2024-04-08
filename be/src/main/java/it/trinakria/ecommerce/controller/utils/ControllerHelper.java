package it.trinakria.ecommerce.controller.utils;

import it.trinakria.ecommerce.utily.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class ControllerHelper {

	public void isMandatory(Object data, String name) {

		if (isNullOrVoid(data)) {
			throw new ApiException(
							HttpStatus.BAD_REQUEST,
							"%s is mandatory".formatted(name),
							"Il campo %s è obbligatorio".formatted(name));
		}
	}


	private boolean isNullOrVoid(Object data) {
		if (data instanceof String) {
			return !StringUtils.hasLength((String) data);
		} else {
			return data == null;
		}
	}

	public Pageable buildPageable(int pageNumber, Integer pageSize, Sort.Direction direction, String sortField) {
		Pageable page = null;
		if (sortField != null) {
			if (direction == null) {
				direction = Sort.Direction.ASC;
			}

			Sort sorter = Sort.by(direction, sortField);
			page = PageRequest.of(pageNumber, pageSize, sorter);
		} else {
			page = PageRequest.of(pageNumber, pageSize);
		}

		return page;
	}
}
