package com.dada.app.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dada.app.user.service.CalendarVO;
import com.dada.app.user.service.UserService;

@RestController
public class UserRestController {
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	//아이디중복확인
	@GetMapping("checkId") //rest는 페이지가 아니라 데이터를 반환
	public boolean userNameCheck(@RequestParam("userName") String userName) {
		return userService.userNameCheck(userName); //impl에서 타입이 string -> boolean으로 타입이 바꼈고 true를 반환했기때문에
	}
	
	//캘린더
	@GetMapping("calendar")
	public List<CalendarVO> dayList() {
		return userService.dayList();
	}
}
