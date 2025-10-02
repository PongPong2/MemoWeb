package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ DB 컬럼은 member_email, Java에서는 memberEmail
    @Column(name = "member_email", nullable = false)
    private String memberEmail;

    // ✅ DB 컬럼은 is_public, Java에서는 isPublic
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    private String name;   // 작성자 이름
    private String title;  // 메모 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 메모 내용

    @PrePersist
    public void prePersist() {
        this.modDate = LocalDateTime.now();
    }
}
