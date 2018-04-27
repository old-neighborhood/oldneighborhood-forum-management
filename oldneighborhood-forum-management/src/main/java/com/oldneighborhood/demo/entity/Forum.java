package com.oldneighborhood.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name="forum")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Forum implements Serializable{
	private static final long serialVersionUID = -4890851566412674939L;
	
	@Id
	private String f_ID;
	@NonNull
	private String f_title;
	@NonNull
	private String f_content;
	@NonNull
	@Column(columnDefinition="varchar(500) not null default '//'")
	private String f_image;
	@Column(columnDefinition="int not null default 0")
	private Integer f_view;
	@Column(columnDefinition="int not null default 0")
	private Integer f_like;
	@Column(columnDefinition="tinyint not null default 0")
	private Boolean isSticky;
	@Column(columnDefinition="timpstamp not null default now()", updatable=false)
	private Timestamp f_date;
	//修改为用户ID&类型
	@NonNull
	private String user_ID;
	@NonNull
	private String user_type;
	/**
	 * 用于修改时候. 
	 * @param f_ID 
	 * @param f_title
	 * @param f_content
	 * @param f_image
	 */
	public Forum(String f_ID, String f_title, String f_content, String f_image) {
		super();
		this.f_ID = f_ID;
		this.f_title = f_title;
		this.f_content = f_content;
		this.f_image = f_image;
	}
	  
	    /**  
	     * 创建一个新的实例 Forum.  
	     *  
	     * @param f_ID
	     * @param f_title
	     * @param f_content
	     * @param f_image
	     * @param f_view  
	     */  
	    
	public Forum(String f_ID, String f_title, String f_content, String f_image, Integer f_view) {
		super();
		this.f_ID = f_ID;
		this.f_title = f_title;
		this.f_content = f_content;
		this.f_image = f_image;
		this.f_view = f_view;
	}

}
