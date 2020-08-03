package com.example.smartcity.model;

import java.util.ArrayList;

public class PageResultFaq {
	private int totalCount;
	private int pageSize;
	private int pageIndex;
	private ArrayList<Faq> items;


	public PageResultFaq() {
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
		return pageIndex;
	}

	public void setPageindex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public ArrayList<Faq> getFaqs() {
		return items;
	}

	public void setFaqs(ArrayList<Faq> faqs) {
		this.items = faqs;
	}
}

