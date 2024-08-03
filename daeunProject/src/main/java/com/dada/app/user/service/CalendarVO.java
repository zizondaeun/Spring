package com.dada.app.user.service;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarVO {
	private Integer id;
	private String title;
	private Date startDay;
	private Date endDay;
    private boolean allday;
	private String textColor;
	private String backgroundColor;
	private String borderColor;
}
