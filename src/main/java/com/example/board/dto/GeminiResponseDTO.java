package com.example.board.dto;

public record GeminiResponseDTO(Candidate[] candidates) {
    public record Candidate(Content content) {}
    public record Content(Part[] parts, String role) {}
    public record Part(String text) {}
}