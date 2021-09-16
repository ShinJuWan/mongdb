package com.cos.mongoapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mongoapp.domain.Board;
import com.cos.mongoapp.domain.BoardRepository;
import com.cos.mongoapp.web.dto.BoardSaveDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // data 리턴 서버
public class BoardController {

	private final BoardRepository boardRepository;
	
	@GetMapping("/board")
	public List<Board> findAll() { // 리턴을 java오브젝트로 하면 스프링 내부적으로 JSON으로 자동변환해준다.
		return boardRepository.findAll();
	}
	
	@PostMapping("/board")
	public Board save(@RequestBody BoardSaveDto dto) { // {"title":"제목3", "content":"내용3"})
		return boardRepository.save(dto.toEntity());
	}
}
