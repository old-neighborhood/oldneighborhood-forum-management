  
    /**    
    * @Title: CollectDao.java  
    * @Package com.oldneighborhood.demo.dao  
    * @Description: TODO(用一句话描述该文件做什么)  
    * @author 彭冲 
    * @date 2018年4月23日  
    * @version V1.0    
    */  
    
package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.oldneighborhood.demo.entity.Collect;

/**  
    * @ClassName: CollectDao  
    * @Description: TODO(这里用一句话描述这个类的作用)  
    * @author 彭冲 
    * @date 2018年4月23日  
    *    
    */

public interface CollectDao extends JpaRepository<Collect, String> {
	@Query(value = "select count(1) from collect where f_ID=? ", nativeQuery = true)
	int count(String f_ID);
	@Query(value = "select count(1) from collect where userID=? and userType=? and f_ID=? ", nativeQuery = true)
	int findCollect(String userID, String userType, String f_ID);
	
	@Modifying
	@Transactional 
	@Query(value = "insert into collect(userID,userType,f_ID) values(?,?,?)", nativeQuery = true)
	void insert(String userID, String userType, String f_ID);
	
	@Modifying
	@Transactional 
	@Query(value = "delete from collect where userID=? and f_ID=?", nativeQuery = true)
	void deleteCollect(String userID, String f_ID);

}
