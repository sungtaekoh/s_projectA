package com.care.root.member.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;
	
	BCryptPasswordEncoder encoder;
	
	public MemberServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	public int userCheck(String id,String pw) {
		MemberDTO dto = mapper.userCheck(id);
		if(dto != null) {
			//if(pw.equals(dto.getPw())) {
			if(encoder.matches(pw, dto.getPw()) || pw.equals(dto.getPw()) ) {
				return 0;
			}
		}
		return 1;
	}
	public void memberInfo(Model model) {
		model.addAttribute("memberList", mapper.memberInfo() );
	}
	public void info(Model model, String id) {
		model.addAttribute( "info",mapper.userCheck(id) );
	}
	public int register(MemberDTO dto) {
		//System.out.println("비번 변경 전 : "+dto.getPw());
		String securePw = encoder.encode(dto.getPw());
		//System.out.println("비번 변경 후 : "+securePw);
		
		dto.setPw(securePw);
		
		int result = 0;
		try {
			result = mapper.register(dto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void keepLogin(String sessionId, Date limitDate, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("id", id);
		mapper.keepLogin(map);
	}
	public MemberDTO getUserSessionId(String sessionId) {
		return mapper.getUserSessionId(sessionId);
	}
}








