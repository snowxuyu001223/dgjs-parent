package com.muteng.dgjs.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.muteng.dgjs.common.utils.HttpRequest;
import com.muteng.dgjs.service.SendMessageService;

@Service
public class SendMessageServiceImpl implements SendMessageService{

	private final static String account = "lanzhipei-tz"; 
	private final static String password = "FD51016E3995E633A710F9334D042F90";
	
	
	@Override
	public void sendMessage(String phone) {
		
		String content = "";
		content = random()+"【畅卓科技】";
		
		try {
			String url = "http://api.chanzor.com/send?account="+account+"&password="+password+"&mobile="+phone+"&content="+URLEncoder.encode(content, "UTF-8");
			HttpRequest.sendGet(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

	private String random(){
		String str="0123456789";
		StringBuilder sb=new StringBuilder(4);
		for(int i=0;i<4;i++)
		{
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
		}
		return sb.toString();
	}
}
