package com.example.board.controller;

import com.example.board.dto.MemberDTO;
import com.example.board.dto.MemoRequestDTO;
import com.example.board.entity.Memo;
import com.example.board.service.MemoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    // 메모 등록
    @PostMapping("/api/memos")
    public ResponseEntity<?> saveMemo(@Valid @RequestBody MemoRequestDTO requestDTO, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Memo savedMemo = memoService.createMemo(requestDTO, member);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMemo);
    }

    // ✅ 본인 메모 불러오기
    @GetMapping("/api/memos")
    public ResponseEntity<?> getMemos(HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        // 서비스에서 로그인한 사용자 이메일 기준으로 메모 조회
        return ResponseEntity.ok(memoService.findMemosByMember(member));
    }


    // ✅ 메모 삭제
    @DeleteMapping("/api/memos/{id}")
    public ResponseEntity<?> deleteMemo(@PathVariable Long id, HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        boolean deleted = memoService.deleteMemo(id, member);

        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("메모를 찾을 수 없거나 삭제 권한이 없습니다.");
        }
    }
}
