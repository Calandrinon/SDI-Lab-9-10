package ro.ubb.catalog.core.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.dto.RecordDto;
import ro.ubb.catalog.core.model.RecordType;
import ro.ubb.catalog.core.model.Transaction;
import ro.ubb.catalog.core.dto.TransactionDto;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.model.Record;

import java.util.Date;

@Component
public class TransactionConverter extends BaseConverter<Transaction, TransactionDto>{
    @Override
    public Transaction convertDtoToModel(TransactionDto dto) {
        User user = new User("", "", 0);
        user.setId(dto.getUserID());
        Record record = new Record(0, "", 0, RecordType.CD);
        record.setId(dto.getRecordID());
        return new Transaction(user, record, new Date(), dto.getQuantity());
    }

    @Override
    public TransactionDto convertModelToDto(Transaction transaction) {
        TransactionDto transactionDto = TransactionDto.builder()
                .recordID(transaction.getRecord().getId())
                .userID(transaction.getUser().getId())
                .quantity(transaction.getQuantity())
                .build();
        transactionDto.setId(transaction.getId());
        return transactionDto;
    }
}