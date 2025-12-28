package net.calmarti.banking_app.dto;

//@Data
//@AllArgsConstructor
//public class UpdateBalanceDTO {
//    private Double amount;
//}

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public record UpdateBalanceDto(Double amount){}