package com.example.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDTO {
    private String name;
    private String title;
    private String content;

    @JsonProperty("is_public")
    private Boolean isPublic;
}