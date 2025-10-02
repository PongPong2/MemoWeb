package com.example.board.dto;

public record GeminiRequestDTO(Content[] contents) {
    public record Content(Part[] parts) {}
    public record Part(String text) {}
}

