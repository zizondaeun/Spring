package com.yedam.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	private Integer boardNo; //번호
	private String boardTitle; //제목
	private String boardContent; //내용
	private String boardWriter; //작성자
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate; //작성일
	private Date updatedate; //수정일
	private String image; //첨부이미지
}
