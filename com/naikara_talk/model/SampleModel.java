package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.SampleDto;

public class SampleModel implements Model {

	private int lowCount;
	private List<SampleDto> list = null;

	public int getLowCount() {
		return lowCount;
	}
	public void setLowCount(int lowCount) {
		this.lowCount = lowCount;
	}
	public List<SampleDto> getList() {
		return list;
	}
	public void setList(List<SampleDto> list) {
		this.list = list;
	}

}
