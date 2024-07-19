package com.yedam.app.book.service;

import java.util.List;
import java.util.Map;

public interface BookService {
	//전체조회
	public List<BookVO> bookList();
	
	//단건조회
	public BookVO bookInfo(BookVO bookVO);
	
	//등록
	public int insertBook(BookVO bookVO);
	
	//마지막 도서번호 조회 +1
	public int lastBookNo();
	
	//수정
	public Map<String, Object> updateBook(BookVO bookVO);
	
	//삭제
	public int deleteBook(int bookNo);
	
	//대여조회
	public List<BookVO> rentList();
	
}
