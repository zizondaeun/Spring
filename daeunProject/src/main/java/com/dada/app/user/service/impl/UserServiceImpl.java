package com.dada.app.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dada.app.user.mapper.UserMapper;
import com.dada.app.user.service.CalendarVO;
import com.dada.app.user.service.UserService;
import com.dada.app.user.service.UserVO;

@Service
public class UserServiceImpl implements UserService{

	private UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public List<UserVO> userList() {
		return userMapper.selectUserAll();
	}

	@Override
	public UserVO userInfo(int userNo) {
		return userMapper.selectUserInfo(userNo);
	}

	@Override
	public int userInsert(UserVO userVO) {
		int result = userMapper.insertUserInfo(userVO);
		return result == 1 ? userVO.getUserNo() : -1;
	}

	@Override
	public boolean userUpdate(UserVO userVO) {
		boolean isSuccessed = true;
		int result = userMapper.updateUserInfo(userVO);
		if(result == 0) {
			isSuccessed = false;
		}
		return isSuccessed;
	}
	
	@Override
	public int userDelete(int userNo) {
		return userMapper.deleteUserInfo(userNo);
	}

	@Override
	public boolean userNameCheck(String userName) {
		String result = userMapper.selectUserName(userName);
		//System.out.println(result); /있으니까 user1반환했고 없으니까 null반환함
		if(result == null) {
			return true; //사용가능한 id -> true반환
		}
		return false;
	}

	@Override
	public List<CalendarVO> dayList() {
		return userMapper.selectDayAll();
	}

}
