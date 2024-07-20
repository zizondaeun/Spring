package com.dada.app.user.service;

import lombok.Data;

@Data
public class UserVO {
	private Integer userNo; //유저번호
	private String userName; //유저아이디
	private String password; //유저패스워드
	private String nickname; //유저닉네임
}
