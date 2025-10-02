package com.example.board.service;

import com.example.board.dto.MemberDTO;
import com.example.board.dto.MemoRequestDTO;
import com.example.board.entity.Memo;
import com.example.board.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // ✅ 등록
    @Transactional
    public Memo createMemo(MemoRequestDTO requestDTO, MemberDTO member) {
        Memo memo = new Memo();
        memo.setName(member.getName());
        memo.setMemberEmail(member.getEmail());   // ✅ 카멜케이스 필드 사용
        memo.setTitle(requestDTO.getTitle());
        memo.setContent(requestDTO.getContent());
        memo.setIsPublic(requestDTO.getIsPublic()); // ✅ getter/setter 이름 맞춤
        return memoRepository.save(memo);
    }

    // ✅ 삭제 (본인 글만)
    @Transactional
    public boolean deleteMemo(Long id, MemberDTO member) {
        return memoRepository.findById(id)
                .filter(memo -> memo.getMemberEmail().equals(member.getEmail())) // ✅ 본인 확인
                .map(memo -> {
                    memoRepository.delete(memo);
                    return true;
                })
                .orElse(false);
    }

    // ✅ 조회: 로그인한 사용자의 메모 전체
    @Transactional(readOnly = true)
    public List<Memo> findMemosByMember(MemberDTO member) {
        return memoRepository.findByMemberEmail(member.getEmail());
    }
}
