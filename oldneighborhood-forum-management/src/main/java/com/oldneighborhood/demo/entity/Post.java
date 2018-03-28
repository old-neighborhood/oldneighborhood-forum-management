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
@Table(name="post")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Post implements Serializable{
	private static final long serialVersionUID = -3735371224755762281L;
	@Id
	private String p_ID;
	@NonNull
	private String p_content;
	@NonNull
	@Column(columnDefinition="timestamp not null default ''")
	private String p_image;
	@Column(columnDefinition="timestamp not null default now()", updatable = false)
	private Timestamp p_date;
	//主题帖ID
	@NonNull
	private String f_ID;
	@NonNull
	private String user_ID;
	@NonNull
	private String user_type;
	public Post(String p_content, String p_image) {
		super();
		this.p_content = p_content;
		this.p_image = p_image;
	}

}
