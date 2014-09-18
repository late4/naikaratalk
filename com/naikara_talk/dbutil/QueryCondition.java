package com.naikara_talk.dbutil;

import org.apache.commons.lang3.StringUtils;

/**
 * SQLの条件句の要素を表現します。
 * @author nos
 *
 */
public class QueryCondition {

	private static final String SPACE  = " ";

	private String item;
	private String operator;
	private String value;
	private String[] values;

	public String getCondition() {
		StringBuilder sb = new StringBuilder();

		sb.append(getItem());
		sb.append(getOperator());

		if(StringUtils.equals(getOperator(), ConditionMapper.OPERATOR_IN )) {
			sb.append("( ");

			for(int i = 0 ; i < values.length; i++){

				if(i > 0){
					sb.append(",");
				}
				sb.append("?");
			}
			sb.append(")");
		}else{
			sb.append("?");
		}
		sb.append(SPACE);
		return sb.toString();
	}

	/**
	 * @return item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item セットする item
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @return operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator セットする operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value セットする value
	 */
	public void setValue(String value) {
		this.value = value;
		this.values = new String[1];
		this.values[0] = value;
	}
	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

}
