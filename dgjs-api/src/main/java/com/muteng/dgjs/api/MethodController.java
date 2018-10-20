package com.muteng.dgjs.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.muteng.dgjs.common.system.BaseResponse;
import com.muteng.dgjs.common.system.ResponseEntity;
import com.muteng.dgjs.service.GetPictureCapService;
import com.muteng.dgjs.service.SendMessageService;

@RestController
@RequestMapping(value="/api",produces = {"application/json;charset=utf-8"})
@Api(value="method001到method010",description="method001到method010")
public class MethodController {

	@Resource
	private GetPictureCapService getPictureCapService;
	@Resource
	private SendMessageService sendMessageService;
	
	@GetMapping("/method002")
	@ApiOperation(value="获取图片验证码",httpMethod="GET")
	public ResponseEntity method002(@RequestParam("phone") String phone,@ApiIgnore HttpServletRequest request){
		
		String picture = this.getPictureCapService.getPicture();
		request.getSession().setAttribute(phone, picture);
		return BaseResponse.buildSuccess(picture, "200", "success");
	}
	
	@GetMapping("/method003")
	@ApiOperation(value="获取短信验证码",httpMethod="GET")
	public ResponseEntity method003(@RequestParam("phone") String phone,@ApiIgnore HttpServletRequest request){
		
		this.sendMessageService.sendMessage(phone);
		return BaseResponse.buildSuccess("200", "success");
	}
}
