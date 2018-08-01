package com.palmble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//删除微信主机上的多余图片
@Controller
public class RemoveController {
	@RequestMapping("remove")
	public int remove() {
	
		return 0;
	}
}
