package ro.ubb.catalog.core.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Transaction;
import ro.ubb.catalog.core.dto.TransactionDto;

@Component
public class TransactionConverter extends BaseConverter<Transaction, TransactionDto>{
    @Override
    public Transaction convertDtoToModel(TransactionDto dto) {
        return new Transaction();
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