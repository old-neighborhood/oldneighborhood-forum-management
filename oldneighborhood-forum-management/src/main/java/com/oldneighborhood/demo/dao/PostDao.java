package com.oldneighborhood.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.oldneighborhood.demo.entity.Post;

public interface PostDao extends JpaRepository<Post, String>{
	
	@Query(value = "select * from post order by p_date desc limit ?, ? where f_ID=? ", nativeQuery = true)
	public List<Post> listByPage(int offset_row, int page_size, String forum_ID);
	
	@Modifying
	@Query(value = "update post set p_content = ?, p_image = ? where p_ID = ? ", nativeQuery = true)
	public void editpost(String p_content, String p_image, String p_ID);

}
