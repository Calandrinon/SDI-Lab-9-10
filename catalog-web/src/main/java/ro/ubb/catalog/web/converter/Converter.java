package ro.ubb.catalog.web.converter;

import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.web.dto.BaseDto;
import ro.ubb.catalog.web.dto.BaseEntityDto;

/**
 * Created by radu.
 */

public interface Converter<Model extends BaseEntity<Integer>, Dto extends BaseEntityDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

