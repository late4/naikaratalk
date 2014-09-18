package com.naikara_talk.sessiondata;

import java.util.List;
import java.util.Map;

import com.naikara_talk.model.TeacherIntroductionModel;

public class TeacherIntorductionData implements SessionData {

	private String[] introArr;
	private String selectedIdtem;
	/**
	 * @return introArr
	 */
	public String[] getIntroArr() {
		return introArr;
	}
	/**
	 * @param introArr セットする introArr
	 */
	public void setIntroArr(String[] introArr) {
		this.introArr = introArr;
	}
	/**
	 * @return selectedIdtem
	 */
	public String getSelectedIdtem() {
		return selectedIdtem;
	}
	/**
	 * @param selectedIdtem セットする selectedIdtem
	 */
	public void setSelectedIdtem(String selectedIdtem) {
		this.selectedIdtem = selectedIdtem;
	};

}