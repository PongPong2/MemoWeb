package com.example.board.repository;

import com.example.board.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMemberEmail(String memberEmail); // ✅ 카멜케이스
}