package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="RecipientsRequestDTO", description="Recipients Request Object")
public class RecipientsRequestDTO {
    private String sender;

    private String text;
}
