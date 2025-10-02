package com.example.board.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "member")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1500)
    private String content;
    @Builder.Default
    private int hit = 0;
    private String url;
    private String name;
    private String email;

    @Column(name = "is_public") // ✅ DB 컬럼명과 매핑
    private Boolean checkBox;

    @ManyToOne(fetch = FetchType.EAGER)  //FetchType.LAZY (지연로딩)
    @JoinColumn(name = "member_email", referencedColumnName = "email", insertable = false, updatable = false)


    private Member member;
}
