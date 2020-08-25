/**
 * Project Name:erp
 * File Name:FileController.java
 * Package Name:com.briup.apps.erp.web.controller
 * Date:2018年9月12日下午10:24:48
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.cms.web.controller.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.briup.apps.cms.bean.CMSFile;
import com.briup.apps.cms.service.ICMSFileService;
import com.briup.apps.cms.util.FastDFS;
import com.briup.apps.cms.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:FileController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月12日 下午10:24:48 <br/>
 * @author   lichunyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Api(description="文件管理")
@RestController
@RequestMapping("/manager/file")
public class FileController {
	@Autowired
	private ICMSFileService cmsFileService ;
	/*
	@ApiOperation(value="清理")
	@GetMapping("clear")
	public MsgResponse clear() {
		List<CMSFile> cfs = cmsFileService.findAll();
		for(CMSFile cf : cfs) {
			System.out.println(cf.getId());
			//批量删除
			delete(cf.getId());
		}
		
		return MsgResponse.success("清理成功", cfs);
	}
	*/
	
	@ApiOperation(value="远程文件删除")
	@GetMapping("delete")
	public MsgResponse delete(String id) {
		try {
			int code = FastDFS.delete(id);
			if(code == 0) {
				cmsFileService.deleteById(id);
				return MsgResponse.success("删除成功", code);
			} else {
				return MsgResponse.success("删除失败", code);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="视频音频上传",notes="文件大小限制为80M,文件服务器地址：http://134.175.154.93:8888，图片地址为 服务器地址/groupName/fileName")
	@PostMapping("uploadMedia")
	public MsgResponse uploadMedia(@RequestParam("file") MultipartFile file,HttpServletRequest req) {
		try {
			String fileName = file.getOriginalFilename();
		    String ext_name = fileName.substring(fileName.lastIndexOf(".") + 1);
		    long fileSize = file.getSize();
		    if(fileSize > 83886080) {
		    		return MsgResponse.error("文件大小不能超过了80M");
		    }
		    
			String[] result = FastDFS.upload(file.getBytes(),ext_name);
			if(result!=null && result.length >1) {
				String erpGroupName = result[0];
				String erpFileName = result[1];
				CMSFile erpFile = new CMSFile();
				erpFile.setFilename(fileName); 
				erpFile.setId(erpFileName);
				erpFile.setGroupname(erpGroupName);
				erpFile.setUploadtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				erpFile.setFilesize(fileSize);
				
				cmsFileService.saveOrUpdate(erpFile);
				return MsgResponse.success("上传成功",erpFile);
			} 
			return MsgResponse.error("上传失败");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="文件上传",notes="文件大小限制为3M,文件服务器地址：http://134.175.154.93:8888，图片地址为 服务器地址/groupName/fileName eg:http://134.175.154.93:8888/group1/M00/00/2D/rBAACV5KTQuAblhXAAAdqc_OKKk776.png")
	@PostMapping("upload")
	public MsgResponse upload(@RequestParam("file") MultipartFile file,HttpServletRequest req) {
		try {
			String fileName = file.getOriginalFilename();
		    String ext_name = fileName.substring(fileName.lastIndexOf(".") + 1);
		    long fileSize = file.getSize();
		    if(fileSize > 3145728) {
		    		return MsgResponse.error("文件大小不能超过了3M");
		    }
		    
			String[] result = FastDFS.upload(file.getBytes(),ext_name);
			if(result!=null && result.length >1) {
				String erpGroupName = result[0];
				String erpFileName = result[1];
				CMSFile erpFile = new CMSFile();
				erpFile.setFilename(fileName); 
				erpFile.setId(erpFileName);
				erpFile.setGroupname(erpGroupName);
				erpFile.setUploadtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				erpFile.setFilesize(fileSize);
				
				cmsFileService.saveOrUpdate(erpFile);
				return MsgResponse.success("上传成功",erpFile);
			} 
			return MsgResponse.error("上传失败");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	
}

