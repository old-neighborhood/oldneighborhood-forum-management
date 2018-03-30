package com.oldneighborhood.demo.service;

import java.util.List;

import com.oldneighborhood.demo.entity.Forum;
import com.oldneighborhood.demo.entity.PageDto;
import com.oldneighborhood.demo.entity.Post;

public interface ForumService {
	
	public Long count();
	
	public List<Forum> pageforum(PageDto pagedto);
	
	public Forum forumdetail(String f_ID);
	
	public List<Post> listpost(PageDto pagedto, String f_ID);
	
	public boolean newforum(Forum forum);
	
	public boolean editforum(Forum forum);
	
	public boolean stickforum(String f_ID);
	
	public boolean unstickforum(String f_ID);
	
	//连同回帖一并删除？
	public boolean deleteforum(String f_ID);
	
	public boolean newpost(Post post);
	
	public boolean editpost(Post post);
	
	public boolean deletepost(String p_ID);

}
