package com.oldneighborhood.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.oldneighborhood.demo.entity.Forum;

public interface ForumDao extends JpaRepository<Forum, String>{
	
	
	@Query(value = "select * from forum order by isSticky desc, f_date desc limit ?, ? ", nativeQuery = true)
	public List<Forum> listByDate(int offset_row, int page_size);
	
	//当前只使用f_view一个指标
	@Query(value = "select * from forum order by isSticky desc, f_view desc limit ?, ? ", nativeQuery = true)
	public List<Forum> listByHot(int offset_row, int page_size);
	
	@Modifying
	@Query(value = "update forum set f_title=?, f_content=?, f_image=? where f_ID=? ", nativeQuery = true)
	public void editforum(String f_title, String f_context, String f_image, String f_ID);
	
	@Modifying
	@Query(value = "update forum set isSticky = 1 where f_ID=? ", nativeQuery = true)
	public void stickforum(String f_ID);
	
	@Modifying
	@Query(value = "update forum set isSticky = 0 where f_ID=? ", nativeQuery = true)
	public void unstickforum(String f_ID);

}
