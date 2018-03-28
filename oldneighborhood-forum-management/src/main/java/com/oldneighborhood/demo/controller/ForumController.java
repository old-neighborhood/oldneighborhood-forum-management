package com.oldneighborhood.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldneighborhood.demo.entity.Forum;
import com.oldneighborhood.demo.entity.PageDto;
import com.oldneighborhood.demo.entity.Post;
import com.oldneighborhood.demo.service.ForumService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(path= {"/forum"})
public class ForumController {
	
	@Autowired
	private ForumService forumService;
	
	@RequestMapping(path= {"/forumlist"})
	public String forumlist(@RequestBody Map<String, Object> reqMap) {
		long total_rows = forumService.count();
		PageDto pagedto = new PageDto(total_rows, 
				Integer.parseInt(reqMap.get("current_page").toString()), 
				Integer.parseInt(reqMap.get("page_size").toString()), 
				reqMap.get("sort_term").toString());
		Page<Forum> page = forumService.pageforum(pagedto);
		JSONObject json = JSONObject.fromObject(page);
		return json.get("content").toString();
	}
	
	@RequestMapping(path= {"/formdetail"})
	public String forumdetail(@RequestBody Map<String, Object> reqMap) {
		Forum forum = forumService.forumdetail(reqMap.get("f_ID").toString());
		JSONObject json = JSONObject.fromObject(forum);
		return json.toString();
	}
	
	@RequestMapping(path= {"/newforum"})
	public String newforum(@RequestBody Map<String, Object> reqMap) {
		Forum forum = new Forum(
				reqMap.get("f_title").toString(), 
				reqMap.get("f_context").toString(),
				reqMap.get("f_image").toString(),
				reqMap.get("user_ID").toString(),
				reqMap.get("user_type").toString());
		return forumService.newforum(forum) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/editforum"})
	public String editforum(@RequestBody Map<String, Object> reqMap) {
		Forum forum = new Forum(
				reqMap.get("f_ID").toString(),
				reqMap.get("f_title").toString(), 
				reqMap.get("f_context").toString(),
				reqMap.get("f_image").toString());
		return forumService.editforum(forum) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/stickforum"})
	public String stickforum(@RequestBody Map<String, Object> reqMap) {
		return forumService.stickforum(reqMap.get("f_ID").toString()) ? "\"result\":\"success\"" : "\"result\":\"error\"";
		
	}
	
	@RequestMapping(path= {"/unstickforum"})
	public String unstickforum(@RequestBody Map<String, Object> reqMap) {
		return forumService.unstickforum(reqMap.get("f_ID").toString()) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/deleteforum"})
	public String deleteforum(@RequestBody Map<String, Object> reqMap) {
		return forumService.deleteforum(reqMap.get("f_ID").toString()) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/newpost"})
	public String newpost(@RequestBody Map<String, Object> reqMap) {
		Post post = new Post(
				reqMap.get("p_content").toString(), 
				reqMap.get("p_image").toString(),
				reqMap.get("f_ID").toString(), 
				reqMap.get("user_ID").toString(), 
				reqMap.get("user_type").toString());
		return forumService.newpost(post) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/editpost"})
	public String editpost(@RequestBody Map<String, Object> reqMap) {
		Post post = new Post(
				reqMap.get("p_ID").toString(),
				reqMap.get("p_content").toString(),
				reqMap.get("p_image").toString()
				);
		return forumService.editpost(post) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/deletepost"})
	public String deletepost(@RequestBody Map<String, Object> reqMap) {
		return forumService.deletepost(reqMap.get("p_ID").toString()) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}

}
