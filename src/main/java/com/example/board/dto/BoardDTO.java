package com.example.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDTO {
    private Long id;
    private String name;
    private String email;
    private String title;
    private String content;
    private boolean checkBox;  // 공개/비공개 (true=공개, false=비공개)

    private LocalDateTime regDate;
    private LocalDateTime modDate;





}
