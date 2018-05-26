package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="FriendsCreationDTO", description="Create friends connection Object")
public class FriendsCreationDTO {

    private String[] friends;

}
