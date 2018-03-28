package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.oldneighborhood.demo.entity.Forum;

public interface ForumDao extends JpaRepository<Forum, String>{
	
	@Modifying
	@Query(value = "update forum set f_title=?, f_context=?, f_image=? where f_ID=? ", nativeQuery = true)
	public void editforum(String f_title, String f_context, String f_image, String f_ID);
	
	@Modifying
	@Query(value = "update forum set isSticky = 1 where f_ID=? ", nativeQuery = true)
	public void stickforum(String f_ID);
	
	@Modifying
	@Query(value = "update forum set isSticky = 0 where f_ID=? ", nativeQuery = true)
	public void unstickforum(String f_ID);

}
