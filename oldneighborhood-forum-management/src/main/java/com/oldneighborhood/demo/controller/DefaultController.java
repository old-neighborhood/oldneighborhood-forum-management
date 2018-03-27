package com.oldneighborhood.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path= {"/forum"})
public class DefaultController {
	
	public String forumindex() {
		return "/forum";
	}

}
