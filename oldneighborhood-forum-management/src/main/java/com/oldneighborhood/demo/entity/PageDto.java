package com.oldneighborhood.demo.entity;

import java.io.Serializable;

import lombok.Data;

/**
* @ClassName: Page  
* @Description: 分頁輔助類
* @author user005  
* @date 2018年3月27日  
 */
@Data
public class PageDto implements Serializable{
	private static final long serialVersionUID = -7787741269036291824L;
	
	private long total_rows;
	private int current_page;
	private int page_size;
	
	private int total_pages;
	private int offset_row;
	private boolean hasPre;
	private boolean hasNext;
	
	private String sort_term;
//	private boolean desc;
	/**
	 * @param total_rows
	 * @param current_page
	 * @param page_size
	 * @param sortterm 排序使用的项
	 */
	public PageDto(long total_rows, int current_page, int page_size, String sort_term) {
		super();
		this.total_rows = total_rows;
		this.current_page = current_page;
		this.page_size = page_size;
		
		this.sort_term = sort_term;//排序项
		
		this.total_pages = (int) ((this.total_rows - 1) / this.page_size + 1);
		this.offset_row = (this.current_page - 1) * this.page_size;
		this.hasPre = this.current_page == 1 ? false : true;
		this.hasNext = this.current_page == this.total_pages ? false : true;
	}
	
//	public PageDto(int total_rows, int current_pages, int page_size) {
//		super();
//		this.total_rows = total_rows;
//		this.current_page = current_pages;
//		this.page_size = page_size;
//		
//		this.total_pages = (this.total_rows - 1) / this.page_size + 1;
//		this.offset_row = (this.current_page - 1) * this.page_size;
//		this.hasPre = this.current_page == 1 ? false : true;
//		this.hasNext = this.current_page == this.total_pages ? false : true;
//	}


}
