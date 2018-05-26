package com.test.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value="FriendsListDTO", description="Friends List Object")
public class FriendsListDTO {
    private boolean success;

    private List<String> friends;

    private int count;


}
