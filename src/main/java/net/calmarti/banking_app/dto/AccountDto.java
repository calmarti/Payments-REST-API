package net.calmarti.banking_app.dto;

//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data
//@AllArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String accountHolderName;
//    private Double balance;
//}

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@JsonIgnoreProperties(ignoreUnknown = false)
public record AccountDto(
        Long id,
        @NotBlank(message = "Account holder name is required")
        String accountHolderName,
        @PositiveOrZero(message = "Balance must be zero or positive")
        Double balance){};
