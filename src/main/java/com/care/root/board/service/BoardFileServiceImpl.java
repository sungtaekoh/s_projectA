package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService{
	public String saveFile(MultipartFile file) {
		SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar calendar = Calendar.getInstance();
		String sysFileName = 
				simpl.format(calendar.getTime()) + file.getOriginalFilename();
		File saveFile = new File(IMAGE_REPO+"/"+sysFileName);
		try {
			file.transferTo(saveFile);//해당 위치에 파일 저장
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}
	public String getMessage(int num, HttpServletRequest request) {
		String message = null;
		if(num == 1) {
			message = "<script>alert('새글을 추가하였습니다.');";
			message += "location.href='"+request.getContextPath()+
					"/board/boardAllList'</script>";
		}else {
			message = "<script>alert('문제가 발생하였습니다.');";
			message += "location.href='"+request.getContextPath()+
					"/board/writeForm'</script>";
		}
		return message;
	}
	public void deleteImage(String originFileName) {
		File deleteFile = new File(IMAGE_REPO+"/"+originFileName);
		deleteFile.delete();
	}
	public String getMessage(HttpServletRequest request,
							String msg, String url) {
	    String message = null;
	    String path = request.getContextPath();
	  
		message = "<script>alert('"+msg+"');";
		message += "location.href='"+path+ url +"'</script>";
	  
	    return message;
	}

}







