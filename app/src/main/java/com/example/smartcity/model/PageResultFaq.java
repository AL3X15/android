package com.example.smartcity.model;

import java.util.ArrayList;

public class PageResultFaq {
	private int totalCount;
	private int pageSize;
	private int pageindex;
	private ArrayList<Faq> faqs;


	public PageResultFaq() {
	}

	public PageResultFaq(int totalCount, int pageSize, int pageindex, ArrayList<Faq> faqs) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageindex = pageindex;
		this.faqs = faqs;
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

	public ArrayList<Faq> getFaqs() {
		return faqs;
	}

	public void setFaqs(ArrayList<Faq> faqs) {
		this.faqs = faqs;
	}
}

