package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="BooleanResponseDTO", description="Boolean Data Response Object")
public class BooleanResponseDTO {
    private Boolean success;
}
