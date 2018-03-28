package com.oldneighborhood.demo.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oldneighborhood.demo.dao.ForumDao;
import com.oldneighborhood.demo.dao.PostDao;
import com.oldneighborhood.demo.entity.Forum;
import com.oldneighborhood.demo.entity.PageDto;
import com.oldneighborhood.demo.entity.Post;
import com.oldneighborhood.demo.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService{
	
	@Autowired
	private ForumDao forumDao;
	
	@Autowired
	private PostDao postDao;

	@Override
	public Long count() {
		return forumDao.count();
	}

	@Override
	public Page<Forum> pageforum(PageDto pagedto) {
		//排序sort......
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		orders.add(new Sort.Order(Direction.DESC, "isSticky"));
		orders.add(new Sort.Order(Direction.DESC, pagedto.getSort_term()));
		Sort sort = new Sort(orders);
		Pageable pageable = new PageRequest(
				pagedto.getOffset_row(), pagedto.getPage_size(), sort);
		
		return forumDao.findAll(pageable);
	}

	@Override
	public Forum forumdetail(String f_ID) {
		Forum forum = forumDao.findOne(f_ID);
		return forum;
	}
	/**
	 * 同一主题帖的回帖list
	 */
	@Override
	public List<Post> listpost(PageDto page, String f_ID) {
		return postDao.listByPage(page.getOffset_row(), page.getPage_size(), f_ID);
	}
	
	@Override
	@Transactional
	public boolean newforum(Forum forum) {
		boolean flag = false;
		try {
			forumDao.saveAndFlush(forum);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean editforum(Forum forum) {
		boolean flag = false;
		try {
			forumDao.editforum(forum.getF_title(), forum.getF_context(), 
					forum.getF_image(), forum.getF_ID());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean stickforum(String f_ID) {
		boolean flag = false;
		try {
			forumDao.stickforum(f_ID);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean unstickforum(String f_ID) {
		boolean flag = false;
		try {
			forumDao.unstickforum(f_ID);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteforum(String f_ID) {
		boolean flag = false;
		try {
			forumDao.delete(forumDao.findOne(f_ID));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean newpost(Post post) {
		boolean flag = false;
		try {
			postDao.save(post); 
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@Transactional
	public boolean editpost(Post post) {
		boolean flag = false;
		try {
			postDao.editpost(post.getP_content(), 
					post.getP_image(), post.getP_ID());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deletepost(String p_ID) {
		boolean flag = false;
		try {
			postDao.delete(postDao.findOne(p_ID));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
