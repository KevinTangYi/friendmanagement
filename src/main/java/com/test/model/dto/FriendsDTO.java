package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="FriendsDTO", description="Create friends connection Object")
public class FriendsDTO {

    private String[] friends;

}
