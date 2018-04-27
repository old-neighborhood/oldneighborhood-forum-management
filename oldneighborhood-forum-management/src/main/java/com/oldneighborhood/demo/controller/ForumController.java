package com.oldneighborhood.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldneighborhood.demo.entity.Collect;
import com.oldneighborhood.demo.entity.Forum;
import com.oldneighborhood.demo.entity.PageDto;
import com.oldneighborhood.demo.entity.Post;
import com.oldneighborhood.demo.service.ForumService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
@SuppressWarnings("restriction")
@RestController
@RequestMapping(path= {"/forum"})
public class ForumController {
	
	@Autowired
	private ForumService forumService;
	
	@RequestMapping("/totalrows")
	public String totalrows() {
		return forumService.count().toString();
	}
	
	@RequestMapping("/postImg")
	public String postImg(@RequestBody Map<String, Object> reqMap ) {
		String imgName = UUID.randomUUID().toString().replace("-", "");
		String imgStr = reqMap.get("src").toString();
		String path = "C:\\pc\\workspace\\oldneighborhood-frontUI\\src\\main\\resources\\img\\post\\"+imgName+".jpg";
		imgStr = imgStr.replaceAll("data:image/jpeg;base64,", ""); 
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			System.out.println(path);
			 File imageFile = new File(path);
	            imageFile.createNewFile();
	               if(!imageFile.exists()){
	                   imageFile.createNewFile();
	                }
	                OutputStream imageStream = new FileOutputStream(imageFile);
	                imageStream.write(b);
	                imageStream.flush();
	                imageStream.close();           
			} catch (Exception e) {
			}
		
		return imgName+".jpg";
	}
	
	@RequestMapping(path= {"/forumlist"})
	public String forumlist(@RequestBody Map<String, Object> reqMap) {
		long total_rows = forumService.count();
		PageDto pagedto = new PageDto(total_rows, 
				Integer.parseInt(reqMap.get("current_page").toString()), 
				Integer.parseInt(reqMap.get("page_size").toString()), 
				reqMap.get("sort_term").toString());
		List<Forum> page = forumService.pageforum(pagedto);
		JSONArray json = JSONArray.fromObject(page);
		for(int i=0;i<json.size();i++) {
			long posts = forumService.countPost(json.getJSONObject(i).get("f_ID").toString());
			json.getJSONObject(i).put("posts", posts);
		}
		return json.toString();
	}
	
	@RequestMapping(path= {"/totalposts"})
	public String totalposts(@RequestBody String f_ID) {
		return forumService.countPost(f_ID).toString();
	}
	
	@RequestMapping(path= {"/postlist"})
	public String postlist(@RequestBody Map<String, Object> reqMap) {
		long total_rows = forumService.countPost(reqMap.get("f_ID").toString());
		PageDto pagedto = new PageDto(total_rows, 
				Integer.parseInt(reqMap.get("current_page").toString()), 
				Integer.parseInt(reqMap.get("page_size").toString()), 
				reqMap.get("sort_term").toString());
		List<Post> page = forumService.listpost(pagedto,reqMap.get("f_ID").toString());
		JSONArray json = JSONArray.fromObject(page);
		return json.toString();
	}
	
	@RequestMapping(path= {"/forumdetail"})
	public String forumdetail(@RequestBody String f_ID) {
		Forum forum = forumService.forumdetail(f_ID);
		JSONObject json = JSONObject.fromObject(forum);
		return json.toString();
	}
	
	@RequestMapping(path= {"/newforum"})
	public String newforum(@RequestBody Map<String, Object> reqMap) {
		Forum forum = new Forum(
				reqMap.get("f_title").toString(), 
				reqMap.get("f_content").toString(),
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
				reqMap.get("f_content").toString(),
				reqMap.get("f_image").toString(),
				Integer.parseInt(reqMap.get("f_view").toString())
				);
		return forumService.editforum(forum) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/stickforum"})
	public String stickforum(@RequestBody String f_ID) {
		return forumService.stickforum(f_ID) ? "\"result\":\"success\"" : "\"result\":\"error\"";
		
	}
	
	@RequestMapping(path= {"/unstickforum"})
	public String unstickforum(@RequestBody String f_ID) {
		return forumService.unstickforum(f_ID) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}
	
	@RequestMapping(path= {"/deleteforum"})
	public String deleteforum(@RequestBody String f_ID) {
		return forumService.deleteforum(f_ID) ? "\"result\":\"success\"" : "\"result\":\"error\"";
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
	public String deletepost(@RequestBody String p_ID) {
		return forumService.deletepost(p_ID) ? "\"result\":\"success\"" : "\"result\":\"error\"";
	}

	
	@RequestMapping(path= {"/totalCollect"})
	public int totalCollect(@RequestBody String f_ID) {
		return forumService.totalCollect(f_ID);
	}
	
	@RequestMapping(path= {"/isCollect"})
	public boolean isCollect(@RequestBody Map<String, Object> reqMap) {
		return forumService.isCollect(
				reqMap.get("userID").toString(),
				reqMap.get("userType").toString(),
				reqMap.get("f_ID").toString()
				);
	}
	
	@RequestMapping(path= {"/newCollect"})
	public boolean newCollect(@RequestBody Map<String, Object> reqMap) {
		Collect collect = new Collect(
				reqMap.get("userID").toString(),
				reqMap.get("userType").toString(),
				reqMap.get("f_ID").toString()
				);
		return forumService.newCollect(collect);
	}
	
	@RequestMapping(path= {"/deleteCollect"})
	public boolean deleteCollect(@RequestBody Map<String, Object> reqMap) {
		Collect collect = new Collect(
				reqMap.get("userID").toString(),
				reqMap.get("userType").toString(),
				reqMap.get("f_ID").toString()
				);
		return forumService.deleteCollect(collect);
	}
}
