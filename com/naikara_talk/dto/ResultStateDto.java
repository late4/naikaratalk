package com.naikara_talk.dto;

public class ResultStateDto extends AbstractDto {

	private int recCount;

	public ResultStateDto() {
		this.recCount = 0;
	}

	/**
	 * @return recCount
	 */
	public int getRecCount() {
		return recCount;
	}

	/**
	 * @param recCount セットする recCount
	 */
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}



}
