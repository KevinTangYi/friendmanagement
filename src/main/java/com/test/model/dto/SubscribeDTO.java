package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="SubscribeDTO", description="Create subscribe connection Object")
public class SubscribeDTO {
    private String requestor;

    private String target;
}
