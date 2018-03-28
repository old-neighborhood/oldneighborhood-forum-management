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
	private String f_context;
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
	 * @param f_title
	 * @param f_context
	 * @param f_image
	 */
	public Forum(String f_title, String f_context, String f_image) {
		super();
		this.f_title = f_title;
		this.f_context = f_context;
		this.f_image = f_image;
	}

}
