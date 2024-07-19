package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;

public interface BookMapper {
	//전체조회
	public List<BookVO> selectBookAll();
	
	//단건조회
	public BookVO selectBookInfo(BookVO bookVO);
	
	//등록
	public int insertBookInfo(BookVO bookVO);
	
	//수정
	public int updateBookInfo(BookVO bookVO);
	
	//삭제
	public int deleteBookInfo(int bookNo);
	
	//대여조회
	public List<BookVO> selectRentAll();
}
