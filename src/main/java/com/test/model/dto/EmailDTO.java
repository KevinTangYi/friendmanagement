package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="EmailDTO", description="Email Object")

public class EmailDTO {
    private String email;
}
