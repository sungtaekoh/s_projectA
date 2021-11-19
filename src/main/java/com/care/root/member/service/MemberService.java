package com.care.root.member.service;

import java.sql.Date;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	public int userCheck(String id,String pw);
	public void memberInfo(Model model);
	public void info(Model model, String id);
	public int register(MemberDTO dto);
	public void keepLogin(String sessionId, Date limitDate, String id);
	public MemberDTO getUserSessionId(String sessionId);
}











