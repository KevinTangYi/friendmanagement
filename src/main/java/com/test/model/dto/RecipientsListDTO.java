package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value="RecipientsListDTO", description="Recipients List Object")
public class RecipientsListDTO {
    private boolean success;

    private List<String> recipients;

}
