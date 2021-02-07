package com.gec.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gec.pojo.DocumentInf;
import com.gec.service.DocumentService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/document")
public class DocumentController {
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/list.action")
	public String list(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
					   DocumentInf document,Model model){
		model.addAttribute("document",document);
		PageInfo<DocumentInf> info = documentService.findPageEntity(pageNum, document);
		model.addAttribute("pageModel", info);
		return "document/documentlist";
	}
	
	@RequestMapping("/add.do")
	public String addView(){
		return "document/showUpdateDocument";
	}
	
	@RequestMapping("/update.do")
	public String editView(Integer id,Model model){
		DocumentInf document = documentService.findById(id);
		model.addAttribute("document",document);
		return "document/showUpdateDocument";
	}
	
	@RequestMapping("/saveorupdate.action")
	public String addOrUpdate(DocumentInf document,Integer userId,
							  @RequestParam(value="file",required=false)MultipartFile fileFile,
							  HttpSession session){
		document.setUserId(userId);
		String fileName = fileFile.getOriginalFilename();
		document.setFilename(fileName);
		String filetype = fileName.substring(fileName.lastIndexOf(".")+1);
		document.setFiletype(filetype);
		String fileUrl = "";
		if ("jpg".equals(filetype)||"png".equals(filetype)) {
			fileUrl = "/ueditor/jsp/upload/image";
		} else {
			fileUrl = "/ueditor/jsp/upload/file";
		}
		document.setFileurl(fileUrl);
		String path = session.getServletContext().getRealPath(fileUrl);
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			File targFile = new File(file,fileName);
			fileFile.transferTo(targFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		if (document.getId()==null) {
			document.setCreateDate(new Date());
			documentService.save(document);
		} else {
			documentService.update(document);
		}
		return "redirect:/document/list.action";
	}
	
	@RequestMapping("/download.action")
	public ResponseEntity<byte[]> download(Integer id,HttpServletRequest request) throws IOException{
		ResponseEntity<byte[]> response = null;
		
		DocumentInf document = documentService.findById(id);
		
		String fileName = document.getFilename();
		String path = request.getSession().getServletContext().getRealPath(document.getFileurl());
		try {
			InputStream in = new FileInputStream(path+File.separator+fileName);
			byte[] b = new byte[in.available()];	//返回下一次读取字节大小
			in.read(b);	//读取为数组中
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
			HttpStatus statusCode = HttpStatus.OK;	//响应成功
			response = new ResponseEntity<byte[]>(b,headers,statusCode);	//返回响应对象
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping("/del.action")
	public String del(Integer[] ids){
		for (Integer id : ids) {
			documentService.delete(id);
		}
		return "redirect:/document/list.action";
	}
	
	@RequestMapping("/viewFile.action")
	public void viewFile(DocumentInf document,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String fileName = document.getFilename();
		String path = request.getServletContext().getRealPath(document.getFileurl());
		//File.separator自适应斜线
		InputStream in = new FileInputStream(path+File.separator+fileName);
		byte[] b = new byte[1024];
		ServletOutputStream out = response.getOutputStream();
		int len = 0;
		while ((len=in.read(b))>0) {
			out.write(b,0,len);
		}
		in.close();
		out.flush();
		out.close();
	}
}
