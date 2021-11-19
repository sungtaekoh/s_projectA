package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired BoardService bs;
	
	@GetMapping("boardAllList")
	public String selectAllBoardList(Model model,
			@RequestParam(required = false, defaultValue = "1" ) int num) {
		
		bs.selectAllBoardList(model, num);
		
		return "board/boardAllList";
	}
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	@PostMapping("writeSave")
	public void writeSave(MultipartHttpServletRequest mul,
				HttpServletResponse response,
				HttpServletRequest request) throws IOException {
		System.out.println("read : "+mul.getParameter("id"));
		String message = bs.writeSave(mul, request);
		PrintWriter out=null;
		response.setContentType("text/html; charset=utf-8");
		out = response.getWriter();
		out.println(message);
	}
	@GetMapping("contentView")
	public String contentView(@RequestParam int writeNo, Model model) {
		bs.contentView(writeNo, model);
		return "board/contentView";
	}
	@GetMapping("download")
	public void download(@RequestParam("imageFileName") String imageFileName,
				HttpServletResponse response) throws IOException {
	    response.addHeader(
		"Content-disposition","attachment;fileName="+imageFileName);
	    File file = new File(BoardFileService.IMAGE_REPO+"/"+imageFileName);
	    FileInputStream in = new FileInputStream(file);
	    FileCopyUtils.copy(in, response.getOutputStream());
	    in.close();
	}
	@GetMapping("delete")
	public void boardDelete(@RequestParam("writeNo") int write_no,
			@RequestParam("imageFileName") String imageFileName,
			HttpServletResponse response, 
			HttpServletRequest request) throws IOException {
		
		String message = bs.boardDelete(write_no,imageFileName,request);
		
		PrintWriter out=null;
		response.setContentType("text/html; charset=utf-8");
		out = response.getWriter();
		out.println(message);
	}
	@GetMapping("modify_form")
	public String modify_form(@RequestParam int writeNo, Model model) {
		bs.getData(writeNo, model);
		return "board/modify_form";
	}
	@PostMapping("modify")
	public void modify(MultipartHttpServletRequest mul,
				HttpServletResponse response,
				HttpServletRequest request) throws IOException {
		String message = bs.modify(mul, request);
		
		PrintWriter out=null;
		response.setContentType("text/html; charset=utf-8");
		out = response.getWriter();
		out.println(message);
	}

}





