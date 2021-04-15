package ro.ubb.catalog.core.converter;

import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.dto.BaseDto;
import ro.ubb.catalog.core.dto.BaseEntityDto;

public interface Converter<Model extends BaseEntity<Integer>, Dto extends BaseEntityDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

