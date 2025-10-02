package com.example.board.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDTO {
    @Email(message = "이메일형식에 맞지 않습니다.")
    private String email;
    private String name;
    private String pw;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
