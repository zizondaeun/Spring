package com.dada.app.user.mapper;

import java.util.List;

import com.dada.app.user.service.UserVO;

public interface UserMapper {
	//회원전체조회
	public List<UserVO> selectUserAll();
	
	//회원단건조회
	public UserVO selectUserInfo(int userNo);
	
	//아이디중복체크
	public String selectUserName(String userName);
	
	//회원등록
	public int insertUserInfo(UserVO userVO);
	
	//회원수정
	public int updateUserInfo(UserVO userVO);
	
	//회원삭제
	public int deleteUserInfo(int userNo);
	
}
