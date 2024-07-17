package com.yedam.app.aaa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aaa.mapper.AaaMapper;
import com.yedam.app.aaa.service.AaaService;

@Service
public class AaaServiceImpl implements AaaService {
	@Autowired
	AaaMapper aaaMapper;
	
	@Transactional
	@Override
	public void insert() {
		aaaMapper.aaaInsert("101");
		aaaMapper.aaaInsert("a101");
	}
}
