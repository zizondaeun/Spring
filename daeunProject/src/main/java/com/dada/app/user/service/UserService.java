package com.dada.app.user.service;

import java.util.List;

public interface UserService {
	//회원전체조회
	public List<UserVO> userList();
	
	//회원단건조회
	public UserVO userInfo(int userNo);
	
	//아이디중복체크
	public boolean userNameCheck(String userName);
	
	//회원등록
	public int userInsert(UserVO userVO);
	
	//회원수정
	public boolean userUpdate(UserVO userVO);
	
	//회원삭제
	public int userDelete(int userNo);
	
	public List<CalendarVO> dayList();
}
