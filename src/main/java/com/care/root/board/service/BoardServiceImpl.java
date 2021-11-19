package com.care.root.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.common.MemberSessionName;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;

	public void selectAllBoardList(Model model, int num) {
		int pageLetter = 3;
		int allCount = mapper.selectBoardCount();
		int repeat = allCount / pageLetter;
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		model.addAttribute("repeat", repeat);
		model.addAttribute("boardList", mapper.selectAllBoardList(start, end) );
	}
	public String writeSave(MultipartHttpServletRequest mul, 
			HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setTitle( mul.getParameter("title") );
		dto.setContent( mul.getParameter("content") );
		dto.setId( mul.getParameter("id") );
		//HttpSession session = request.getSession();
		//dto.setId((String)session.getAttribute(MemberSessionName.LOGIN));

		MultipartFile file = mul.getFile("image_file_name");
		//BoardFileService bfs = new BoardFileServiceImpl();
		if(file.getSize() != 0) {
			//이미지 있을경우 처리
			dto.setImageFileName( bfs.saveFile(file) );
		}else {
			dto.setImageFileName("nan");
		}
		int result = 0;
		try {
			result = mapper.writeSave(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return bfs.getMessage(result, request);
	}
	public void contentView(int writeNo, Model model) {
		model.addAttribute("personalData", mapper.contentView(writeNo) );
		upHit(writeNo);
	}
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	public String boardDelete(int writeNo,String imageFileName, 
			HttpServletRequest request) {

		//BoardFileService bfs = new BoardFileServiceImpl();
		int result = mapper.delete(writeNo);
		//MessageDTO mDTO = new MessageDTO();
		String message=null;
		if(result == 1) { 
			bfs.deleteImage(imageFileName); 
			message = bfs.getMessage(request, "삭제 성공", 
					"/board/boardAllList" );
		}else{
			message = bfs.getMessage(request, "삭제 실패", 
					"/board/contentView" );
		}
		return message;
	}
	public void getData(int writeNo, Model model) {
		model.addAttribute("personalData", mapper.contentView(writeNo) );
	}
	public String modify(MultipartHttpServletRequest mul, 
									HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo( Integer.parseInt(mul.getParameter("writeNo")) );
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));

		MultipartFile file = mul.getFile("imageFileName");
		if(file.getSize() != 0 ) {
			//이미지 변경시
			dto.setImageFileName( bfs.saveFile(file) );
			bfs.deleteImage( mul.getParameter("originFileName") );
		}else {
			dto.setImageFileName(mul.getParameter("originFileName"));
		}
		int result = mapper.modify(dto);
		String msg, url;
		if(result == 1) {
			msg = "성공적으로 수정되었습니다";
			url = "/board/boardAllList";
		}else {
			msg = "수정 중 문제가 발생하였습니다";
			url = "/board/modify_form";
		}
		String message = bfs.getMessage(request, msg, url);
		return message;
	}
	public String addReply(BoardRepDTO dto) {
		int result = mapper.addReply(dto);
		String msg = null;
		if(result == 1) {
			msg = "{\"result\" : true}";
		}else {
			msg = "{\"result\" : false}";
		}
		return msg;
	}
	public List<BoardRepDTO> getRepList(int write_group){
		return mapper.getRepList(write_group);
	}
}






















