package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserVO implements UserDetails{
	
	private UserVO userVO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //컬렉션<권한을 상속받은 애만 가능하다고 제한한것>
		List<GrantedAuthority> auths = new ArrayList<>(); //Map 타입은 안됨
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		return auths;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}
	
	//boolean 타입 4가지 - 계정 여부
	@Override
	public boolean isAccountNonExpired() { //계정 만료 여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //계정 잠금 여부***(신고 몇회 -> 계정을 정지시킬 경우 이 메소드 사용(false는 잠금상태))
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //계정 패스워드 만료 여부
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 사용 여부
		return true;
	}
	
}
