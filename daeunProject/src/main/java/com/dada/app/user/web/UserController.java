package com.dada.app.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dada.app.user.service.UserService;
import com.dada.app.user.service.UserVO;

@Controller
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//회원전체조회
	@GetMapping("userList")
	public String userList(Model model) {
		List<UserVO> list = userService.userList();
		model.addAttribute("users", list);
		return "user/userList";
	}
	
	//회원단건조회
	@GetMapping("userInfo")
	public String userInfo(@RequestParam int userNo, Model model) {
		UserVO findVO = userService.userInfo(userNo);
		model.addAttribute("user", findVO);
		return "user/userInfo";
	}
	
	//회원가입 - 페이지
	@GetMapping("sign-up")
	public String userSignUpForm() {
		return "user/signUp";
	}
	
	//가입 - 기능
	@PostMapping("sign-up")
	public String userSignUp(@ModelAttribute UserVO userVO) {
		int userNo = userService.userInsert(userVO);
		return "user/login";
	}
	
	//회원수정 - 페이지
	
	//수정 - 기능
	
	//회원삭제
	
}
