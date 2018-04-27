  
    /**    
    * @Title: Collect.java  
    * @Package com.oldneighborhood.demo.entity  
    * @Description: TODO(用一句话描述该文件做什么)  
    * @author 彭冲 
    * @date 2018年4月23日  
    * @version V1.0    
    */  
    
package com.oldneighborhood.demo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.NonNull;

/**  
    * @ClassName: Collect  
    * @Description: TODO(这里用一句话描述这个类的作用)  
    * @author 彭冲 
    * @date 2018年4月23日  
    *    
    */
@Entity
@Table(name="collect")
@Data
@Embeddable
@Builder
public class Collect implements Serializable{

	  
	    /**  
	    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	    */  
	    
	private static final long serialVersionUID = 2133340525395900430L;
	@Id
	private int c_ID;
	@NonNull
	private String userID;
	@NonNull
	private String userType;
	@NonNull
	private String f_ID;
	  
	    /**  
	     * 创建一个新的实例 Collect.  
	     *  
	     * @param userID
	     * @param userType
	     * @param f_ID  
	     */  
	    
	public Collect(String userID, String userType, String f_ID) {
		super();
		this.userID = userID;
		this.userType = userType;
		this.f_ID = f_ID;
	}

		  
		    /**  
		     * 创建一个新的实例 Collect.  
		     *    
		     */  
		    
		public Collect() {
			super();
			// TODO Auto-generated constructor stub
		}
	
}
