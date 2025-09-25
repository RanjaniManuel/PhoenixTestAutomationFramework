package com.api.pojos;

public class Problem {

	private int id;
	private String remark;
	public Problem(int id, String remark) {
		super();
		this.id = id;
		this.remark = remark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Problem [id=" + id + ", remark=" + remark + "]";
	}
	
}
