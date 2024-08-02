package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //선언
public class SpringSecurityConfig {
	
	//1. 암호화/복호화에 사용하는 Bean 등록 /암호화에 사용하는 인코더가 필요(무조건 암호화된것만 사용해야함 - version변경으로 인해)
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//2. 인증 및 인가 /인증할, 인가를 확인할 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //보안정보 담당 : HttpSecurity에게 알려줘야함.
		//1-경로 설정
		http
			.authorizeHttpRequests() //Security가 체크하는 경로 및 각 경로별 권한
		//2-그 경로가 확인해야하는 권한 
			.antMatchers("/", "/all") 		//경로 /특정 경로에 대해 처리하겠다
				.permitAll() 				//권한 /permitAll는 모든 사람(페이지) 다 허용
			.antMatchers("/admin/**") 		//경로 
				.hasRole("ADMIN")  			//권한 /hasRole는 empList빼고 전부 다 허용/특정한 권한일때
				// = ROLE_ADMIN
			.antMatchers("/user/**") 		//경로
				//.hasAuthority("ROLE_USER") 	//권한 /권한 그 자체 100%
				.hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") //접근권한을 admin에게도 주기 위해 
			.anyRequest() 					//.어떠한 요청
				.authenticated() //위에 다 처리하고(필터 순서대로/포괄적인 부분은 아래에 작은 -> 큰 ) .누군지만 알고있다면 권한부여(접근을 허용하는 것)
		.and()
		.formLogin()
			.defaultSuccessUrl("/all") //로그인을 했을때 어디로 넘어가는지(기본 /)
		.and()
		.logout()
			.logoutSuccessUrl("/all"); //로그아웃을 했을때 어디로 넘어가는지(기본 /)
		
		http.csrf().disable();
		
		return http.build();
	}
	
	//테스트용) 메모리 인증 방식
	//@Bean
	InMemoryUserDetailsManager inMemorUserDetailsService() {
		UserDetails user = User.builder()
									.username("user1")
									.password(passwordEncoder().encode("1234"))
									//.authorities("ROLE_USER")
									.roles("USER") //ROLE_USER 알아서 앞에 ROLE이 붙음
									.build();
		
		UserDetails admin = User.builder()
									.username("admin")
									.password(passwordEncoder().encode("1234"))
									.authorities("ROLE_ADMIN")
									//.roles("ADMIN") //ROLE_ADMIN 
									.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
}
