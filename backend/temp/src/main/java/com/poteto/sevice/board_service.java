package com.poteto.sevice;

import com.poteto.entity.Board;
import com.poteto.entity.MemberEntity;
import com.poteto.repository.Board_repository;
import com.poteto.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class board_service {
	
	private final Board_repository board_repository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long register(String title, String content, Long userId) {
        User user = memberRepository.findByMemberId(Long memberId).orElseThrow();

        Board board = Board.createBoard(title, content, user);
        board_repository.save(board);
        return board.getId();
    }

    /**
     * 게시판 전체 조회
     */
    public List<Board> findAll() {
        return board_repository.findAll();
    }

    /**
     * 게시글 단건 조회
     */
    public Optional<Board> findOne(Long id) {
        return board_repository.findById(id);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void updateBoard(Long id, String title, String content) {
        Board board = board_repository.findById(id).orElseThrow();
        board.update(title, content);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteById(Long id) {
    	board_repository.deleteById(id);
    }

}
