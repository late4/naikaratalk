package com.naikara_talk.sessiondata;

/**
 * ユーザセッション情報クラス。
 * @author nos
 *
 */
public class SessionUser implements SessionData {

	private static final String KEY = "naikara_talk.SessionData.SessionUser";

	/** 現在のロール　システム管理者 */
	public static final String ROLE_SYSADMIN = "A";
	/** 現在のロール　管理者 */
	public static final String ROLE_ADMIN = "M";
	/** 現在のロール　スタッフ */
	public static final String ROLE_STAFF = "S";
	/** 現在のロール　講師 */
	public static final String ROLE_TEACHER = "T";
	/** 現在のロール　受講者 */
	public static final String ROLE_STUDENT = "C";
	/** 現在のロール　法人責任者 */
	public static final String ROLE_ORGANIZATION = "B";
	/** 現在のロール　ゲスト(ログインしていない状態￥) */
	public static final String ROLE_GUEST = "GUEST";


	/** ログイン中のユーザID */
	private String userId;
	/** ログイン中のユーザ表示名 */
	private String userNm;
	/** ログイン中のユーザロール */
	private String role;
	/** ログイン中の顧客区分(受講者の場合のみ設定される) */
	private String customerKbn;
	/** ログイン中の組織IDの連番(組織の場合のみ設定される) */
	private String organizationSeq;
	/** ログイン中の組織名(組織の場合のみ設定される) */
	private String organizationNm;

	/**
	 * このセッションデータのキーを返却する
	 */
	public static String getKey(){
		return KEY;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return userNm
	 */
	public String getUserNm() {
		return userNm;
	}


	/**
	 * @param userNm セットする userNm
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}


	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role セットする role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return customerKbn
	 */
	public String getCustomerKbn() {
		return customerKbn;
	}

	/**
	 * @param customerKbn セットする customerKbn
	 */
	public void setCustomerKbn(String customerKbn) {
		this.customerKbn = customerKbn;
	}

	/**
	 * @return organizationSeq
	 */
	public String getOrganizationSeq() {
		return organizationSeq;
	}

	/**
	 * @param organizationSeq セットする organizationSeq
	 */
	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}

	/**
	 * @return organizationNm
	 */
	public String getOrganizationNm() {
		return organizationNm;
	}

	/**
	 * @param organizationNm セットする organizationNm
	 */
	public void setOrganizationNm(String organizationNm) {
		this.organizationNm = organizationNm;
	}

}
