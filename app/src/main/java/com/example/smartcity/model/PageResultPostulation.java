package com.example.smartcity.model;

import java.util.ArrayList;

public class PageResultPostulation {
	private int totalCount;
	private int pageSize;
	private int pageIndex;
	private ArrayList<Postulation> items;

	public PageResultPostulation() {
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

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public ArrayList<Postulation> getItems() {
		return items;
	}

	public void setItems(ArrayList<Postulation> items) {
		this.items = items;
	}
}
