package com.yedam.app.book.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.book.mapper.BookMapper;
import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Service
public class BookServiceImpl implements BookService{
	
	private BookMapper bookMapper;
	
	@Autowired
	public BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}	
	
	@Override
	public List<BookVO> bookList() {
		return bookMapper.selectBookAll();
	}

	@Override
	public BookVO bookInfo(BookVO bookVO) {
		return bookMapper.selectBookInfo(bookVO);
	}

	@Override
	public int insertBook(BookVO bookVO) {
		int result = bookMapper.insertBookInfo(bookVO);
		return result == 1 ? bookVO.getBookNo() : -1;
	}

	@Override
	public Map<String, Object> updateBook(BookVO bookVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = bookMapper.updateBookInfo(bookVO);
		if(result > 0) { //수정되었으면 
			isSuccessed = true; //성공
		}
		
		map.put("result", isSuccessed); //수정되었을때 true,실패하면 false 반환
		map.put("target", bookVO); //수정된 내용
		
		return map;
	}

	@Override
	public int deleteBook(int bookNo) {
		return bookMapper.deleteBookInfo(bookNo);
	}

	@Override
	public List<BookVO> rentList() {
		return bookMapper.selectRentAll();
	}

	@Override
	public int lastBookNo() {
		return bookMapper.selectBookNo(); 
	}
	
}
