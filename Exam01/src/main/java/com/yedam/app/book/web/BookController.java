package com.yedam.app.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Controller
public class BookController {
	
	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	//전체조회
	@GetMapping("bookList")
	public String bookList(Model model) {
		List<BookVO> list = bookService.bookList();
		model.addAttribute("books", list);
		return "book/bookList";
	}
	
	//단건조회
	@GetMapping("bookInfo")
	public String bookInfo(BookVO bookVO, Model model) {
		BookVO findVO = bookService.bookInfo(bookVO);
		model.addAttribute("book", findVO);
		return "book/bookInfo";
	}
	
	//등록
	@GetMapping("bookInsert")
	public String bookInsertForm() {
		return "book/bookInsert";
	}
	
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {
		int bno = bookService.insertBook(bookVO);
		return "redirect:bookList";
	}
	
	//수정
	
	//삭제
	
	//대여조회
	@GetMapping("rentList")
	public String rentList(Model model) {
		List<BookVO> list = bookService.rentList();
		model.addAttribute("rents", list);
		return "book/rentList";
	}
	
}
