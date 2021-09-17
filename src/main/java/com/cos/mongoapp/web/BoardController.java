package com.cos.mongoapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/board/{id}")
	public Board update(@RequestBody BoardSaveDto dto, @PathVariable String id) {
		
		Board board = dto.toEntity();
		board.set_id(id);
		
		return boardRepository.save(board);
	}
	
	@DeleteMapping("/board/{id}")
	public int deleteById(@PathVariable String id) {
		boardRepository.deleteById(id); // 
		return 1;
	}
	
	
	@GetMapping("/board/{id}")
	public Board findById(@PathVariable String id) {
		return boardRepository.findById(id).get();
	}
	
	@GetMapping("/board")
	public List<Board> findAll() { // 리턴을 java오브젝트로 하면 스프링 내부적으로 JSON으로 자동변환해준다.
		return boardRepository.findAll();
	}
	
	@PostMapping("/board")
	public Board save(@RequestBody BoardSaveDto dto) { // {"title":"제목3", "content":"내용3"})
		
		Board boardEntity = boardRepository.save(dto.toEntity());
		
		return boardEntity;  // MessageConverter발동 -> 자바오브젝트를 json 변환해서 응답함.
	}
}
