package com.dada.app.user.service;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Integer boardNo; //게시글번호
	private String boardTitle; //제목
	private String boardContent; //내용
	private Date createdAt; //작성일
	private Date updatedAt; //수정일
	private Integer userNo; //유저번호
}
