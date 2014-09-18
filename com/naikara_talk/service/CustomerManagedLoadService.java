package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CustomerManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CustomerManagedLogic;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.model.CustomerManagedModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ詳細<br>
 * <b>クラス名称　　　:</b>お客様管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ詳細LoadService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedLoadService implements ActionService {

	/**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();

            CustomerManagedLogic logic = new CustomerManagedLogic(conn);
            // コード管理マスタ検索
            return logic.selectCodeMst(category);

        } catch ( SQLException se ) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * 対象期間を取得する
     * @return LinkedHashMap<String, String>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectObjectPeriod() throws NaiException {

    	LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

    	// 対象期間：過去３ヶ月を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_ONE, CustomerManagedListModel.OBJECT_PERIOD_ONE_NM);

    	// 対象期間：過去６ヶ月を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_TWO, CustomerManagedListModel.OBJECT_PERIOD_TWO_NM);

    	// 対象期間：過去１２ヶ月を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_THREE, CustomerManagedListModel.OBJECT_PERIOD_THREE_NM);

    	// 対象期間：期間指定を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_ZERO, CustomerManagedListModel.OBJECT_PERIOD_ZERO_NM);

        return hashMap;

    }

	/**
     * 画面データの取得処理<br>
     * <br>
     * @param CustomerManagedModel
     *            画面のパラメータ
     * @return List<CustomerManagedDto>
     * @throws Exception
     */
    public List<CustomerManagedDto> selectList(CustomerManagedModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            // CustomerManagedLogicクラスの生成
            CustomerManagedLogic logic = new CustomerManagedLogic(conn);

            // CustomerManagedDtoクラスの生成
            CustomerManagedDto dto = new CustomerManagedDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return logic.search(dto);

        } catch ( SQLException se ) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           CustomerManagedModel
     * @return CustomerManagedListDto
     * @throws Exception
	 */
    private CustomerManagedDto modelToDto(CustomerManagedModel model) throws NaiException {

        // DTOの初期化
    	CustomerManagedDto prmDto = new CustomerManagedDto();

        prmDto.setStudentId(model.getStudentId());                      // 受講者ID
        prmDto.setObjectPeriod(model.getObjectPeriod());                // 対象期間

        // 画面の｢対象期間｣＝”0” (期間指定) の場合
        if (StringUtils.equals(CustomerManagedModel.OBJECT_PERIOD_ZERO, model.getObjectPeriod())){

        	prmDto.setPeriodFrom(NaikaraStringUtil.delSlash(model.getPeriodFrom()));  // 開始日
        	prmDto.setPeriodTo(NaikaraStringUtil.delSlash(model.getPeriodTo()));      // 終了日

        } else {
        	// 画面の｢対象期間｣≠”0” (期間指定) の場合
        	// 対象期間：過去３ヶ月の場合
            if (StringUtils.equals(CustomerManagedModel.OBJECT_PERIOD_ONE, model.getObjectPeriod())){

            	prmDto.setObjectPeriodFrom(DateUtil.getSysDateAddMonth1(CustomerManagedModel.OBJECT_PERIOD_INT_ONE));
            	prmDto.setObjectPeriodTo(DateUtil.getSysDateAddMonth1(CustomerManagedModel.OBJECT_PERIOD_INT_MONE));

            } else if (StringUtils.equals(CustomerManagedModel.OBJECT_PERIOD_TWO, model.getObjectPeriod())){

            	// 対象期間：過去６ヶ月の場合
            	prmDto.setObjectPeriodFrom(DateUtil.getSysDateAddMonth1(CustomerManagedModel.OBJECT_PERIOD_INT_TWO));
            	prmDto.setObjectPeriodTo(DateUtil.getSysDateAddMonth1(CustomerManagedModel.OBJECT_PERIOD_INT_MONE));

            } else if (StringUtils.equals(CustomerManagedModel.OBJECT_PERIOD_THREE, model.getObjectPeriod())){

            	// 対象期間：過去１２ヶ月の場合
            	prmDto.setObjectPeriodFrom(DateUtil.getSysDateAddMonth1(CustomerManagedModel.OBJECT_PERIOD_INT_THREE));
            	prmDto.setObjectPeriodTo(DateUtil.getSysDateAddMonth1(CustomerManagedModel.OBJECT_PERIOD_INT_MONE));

            }

            StringBuffer sb = new StringBuffer();
            sb.append(DateUtil.getSysDateYMNoSplit()).append("01");
            prmDto.setPeriodFrom(sb.toString());            // 開始日
        	prmDto.setPeriodTo(DateUtil.getSysDate());      // 終了日
        }

        return prmDto;

    }

}
