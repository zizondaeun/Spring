package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookVO {
	//book_tbl_06
	private Integer bookNo;
	private String bookName;
	private String bookCoverImg;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
	
	//rent_tbl_06
	private Integer rentNo; //null값이어도 숫자로 인식하려고
	private int rentPrice;
	private Date rentDate;
	private int rentStatus;
}
