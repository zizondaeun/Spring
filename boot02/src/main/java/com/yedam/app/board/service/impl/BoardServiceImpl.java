package com.yedam.app.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service //=> @Transactional, AOP
public class BoardServiceImpl implements BoardService{
	//@Autowired //필드 주입 방식(은 권장하지 않음 BoardService를 참조하게 될수도 있어서)
	private BoardMapper boardMapper;
	
	@Autowired //생성자 주입 방식(추천)
	BoardServiceImpl(BoardMapper boardMapper){
		this.boardMapper = boardMapper;
	}
	
	@Override
	public List<BoardVO> boardList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		int result = boardMapper.insertBoardInfo(boardVO);
		return result == 1 ? boardVO.getBoardNo() : -1;
	}

	@Override
	public Map<String, Object> updateBoard(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = boardMapper.updateBoardInfo(boardVO);
		if(result > 0) { //수정되었으면 
			isSuccessed = true; //성공
		}
		
		map.put("result", isSuccessed); //수정되었을때 true,실패하면 false 반환
		map.put("target", boardVO); //수정된 내용
		
		return map;
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardMapper.deleteBoardInfo(boardNo);
	}

}
