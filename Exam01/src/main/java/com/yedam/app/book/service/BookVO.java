package com.yedam.app.book.service;

import java.util.Date;

import lombok.Data;

@Data
public class BookVO {
	//book_tbl_06
	private Integer bookNo;
	private String bookName;
	private String bookCoverImg;
	private Date bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
	
	//rent_tbl_06
	private Integer rentNo;
	private int rentPrice;
	private Date rentDate;
	private String rentStatus;
}
