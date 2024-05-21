package com.mok.models.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private UserDTO user;
    private CategoryDTO category;
    private List<CommentDTO> comments;
}

