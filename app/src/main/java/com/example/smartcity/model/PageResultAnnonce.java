package com.example.smartcity.model;

import java.util.ArrayList;

public class PageResultAnnonce {
	private int totalCount;
	private int pageSize;
	private int pageIndex;
	private ArrayList<Annonce> items;

	public PageResultAnnonce() {
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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageindex) {
		this.pageIndex = pageindex;
	}

	public ArrayList<Annonce> getAnnonces() {
		return items;
	}

	public void setAnnonces(ArrayList<Annonce> annonces) {
		this.items = annonces;
	}
}
