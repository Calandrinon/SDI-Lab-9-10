package ro.ubb.catalog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsDto {
    private Collection<TransactionDto> transactions;
}
