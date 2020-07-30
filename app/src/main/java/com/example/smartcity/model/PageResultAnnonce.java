package com.example.smartcity.model;

import java.util.ArrayList;

public class PageResultAnnonce {
	private int totalCount;
	private int pageSize;
	private int pageindex;
	private ArrayList<Annonce> items;

	public PageResultAnnonce() {
	}

	public PageResultAnnonce(int totalCount, int pageSize, int pageindex, ArrayList<Annonce> annonces) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageindex = pageindex;
		this.items = annonces;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public ArrayList<Annonce> getAnnonces() {
		return items;
	}

	public void setAnnonces(ArrayList<Annonce> annonces) {
		this.items = annonces;
	}
}
