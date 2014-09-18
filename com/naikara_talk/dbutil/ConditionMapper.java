package com.naikara_talk.dbutil;

import java.util.ArrayList;
import java.util.List;


/**
 * SQL発行時の検索条件を構築します。
 * AND条件で条件区を構築します。
 *
 * @author nos
 *
 */
public class ConditionMapper {

	public static final String OPERATOR_EQUAL = " = ";
	public static final String OPERATOR_LARGE_EQUAL = " >= ";
	public static final String OPERATOR_LARGER_THAN = " > ";
	public static final String OPERATOR_LESS_EQUAL = " <= ";
	public static final String OPERATOR_LESS_THAN = " < ";
	public static final String OPERATOR_NOT_EQUAL = " <> ";
	public static final String OPERATOR_IN = " in ";
	public static final String OPERATOR_LIKE = " like ";
	/** is nullの演算用	 */
	public static final String OPERATOR_IS = " is ";

	private static final String SPACE  = " ";

	private List<QueryCondition> conditions = new ArrayList<QueryCondition>();

	/**
	 * 検索条件を追加する
	 * @param item
	 * @param condition
	 * @param value
	 */
	public void addCondition(String item, String operator, String value){

		QueryCondition cond = new QueryCondition();
		cond.setItem(item);
		cond.setOperator(operator);
		cond.setValue(value);

		this.conditions.add(cond);
	}

	/**
	 * 検索条件を追加する
	 * @param item
	 * @param condition
	 * @param value
	 */
	public void addCondition(String item, String operator, int value) {
		QueryCondition cond = new QueryCondition();
		cond.setItem(item);
		cond.setOperator(operator);
		cond.setValue(String.valueOf(value));

		this.conditions.add(cond);

	}


	/**
	 * IN演算子の検索条件を追加する
	 * @param item
	 * @param condition
	 * @param value
	 */
	public void addCondition(String item,  String[] values) {
		QueryCondition cond = new QueryCondition();
		cond.setItem(item);
		cond.setOperator(ConditionMapper.OPERATOR_IN);
		cond.setValues(values);

		this.conditions.add(cond);

	}

	/**
	 * 条件区を返却します。
	 * preparedSQLnoの条件区を返却します。
	 * @return
	 */
	public String getConditionString() {

		StringBuilder wb = new StringBuilder();

		for(int i = 0; i < this.conditions.size(); i++) {

			if(i > 0){
				wb.append("and ");
			}

			QueryCondition cond = this.conditions.get(i);

			wb.append(cond.getCondition());

		}

		return wb.toString();
	}

	/**
	 * 条件配列を返却する
	 * @return
	 */
	public List<QueryCondition> getConditions(){
		return this.conditions;
	}


}
