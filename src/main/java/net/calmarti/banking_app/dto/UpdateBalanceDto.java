package net.calmarti.banking_app.dto;

//@Data
//@AllArgsConstructor
//public class UpdateBalanceDTO {
//    private Double amount;
//}

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = false)
public record UpdateBalanceDto(
        @NotNull(message = "Transaction type must be either DEPOSIT or WITHDRAW")
        UpdateBalanceType type,
        @PositiveOrZero(message = "Amount must not be netgative")
        BigDecimal amount
){}