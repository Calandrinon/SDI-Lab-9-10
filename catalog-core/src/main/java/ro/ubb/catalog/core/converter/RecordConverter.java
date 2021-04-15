package ro.ubb.catalog.core.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.dto.RecordDto;

@Component
public class RecordConverter extends BaseConverter<Record, RecordDto> {
    @Override
    public Record convertDtoToModel(RecordDto dto) {
        var model = new Record();
        model.setId(dto.getId());
        model.setAlbumName(dto.getAlbumName());
        model.setInStock(dto.getInStock());
        model.setPrice(dto.getPrice());
        model.setTypeOfRecord(dto.getTypeOfRecord());
        return model;
    }

    @Override
    public RecordDto convertModelToDto(Record record) {
        RecordDto recordDto = new RecordDto(record.getPrice(), record.getAlbumName(), record.getInStock(), record.getTypeOfRecord());
        recordDto.setId(record.getId());
        return recordDto;
    }
}
