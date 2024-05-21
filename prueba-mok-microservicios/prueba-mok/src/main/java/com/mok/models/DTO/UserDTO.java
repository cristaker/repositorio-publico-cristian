package com.mok.models.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private List<String> postTitles;
}
