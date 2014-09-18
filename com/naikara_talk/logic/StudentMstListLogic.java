package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.StuMPoiOwnTDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.StuMPoiOwnTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMstListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】Logicクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMstListLogic {

    // 受講者マスタテーブル名
    private static String STUDENT_MST_TABLE_NM = "SM.";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentMstListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索処理。<br>
     * <br>
     * @param StuMPoiOwnTDto
     *            画面のパラメータ
     * @return List<StuMPoiOwnTDto>
     * @throws NaiException
     */
    public List<StuMPoiOwnTDto> selectList(StuMPoiOwnTDto dto) throws NaiException {

        // 初期化処理

        List<StuMPoiOwnTDto> list = new ArrayList<StuMPoiOwnTDto>();

        StuMPoiOwnTDao dao = new StuMPoiOwnTDao(this.conn);

        // 並び順:受講者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StuMPoiOwnTDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.search(setConditions(dto), orderBy);

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param StuMPoiOwnTDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditions(StuMPoiOwnTDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // お客様区分!＝全てのコード の場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getCustomerKbn())) {
            conditions.addCondition(STUDENT_MST_TABLE_NM + StuMPoiOwnTDao.COND_CUSTOMER_KBN,
                    ConditionMapper.OPERATOR_EQUAL, dto.getCustomerKbn());
        }

        // 条件キー条件１
        if (!StringUtils.equals(dto.getItemNm1_sel(), NaikaraTalkConstants.BRANK)) {
            // 範囲
            if (StringUtils.equals(dto.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm1_sel(),
                        ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getItemFrom1_txt());

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm1_sel(),
                        ConditionMapper.OPERATOR_LESS_EQUAL, dto.getItemTo1_txt());

                // 前方一致
            } else if (StringUtils.equals(dto.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm1_sel(), ConditionMapper.OPERATOR_LIKE,
                        dto.getItemFrom1_txt() + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 曖昧
            } else if (StringUtils.equals(dto.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm1_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom1_txt()
                                + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 後方一致
            } else if (StringUtils.equals(dto.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm1_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom1_txt());

                // 完全一致
            } else {
                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm1_sel(), ConditionMapper.OPERATOR_EQUAL,
                        dto.getItemFrom1_txt());
            }
        }

        // 条件キー条件２
        if (!StringUtils.equals(dto.getItemNm2_sel(), NaikaraTalkConstants.BRANK)) {
            // 範囲
            if (StringUtils.equals(dto.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm2_sel(),
                        ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getItemFrom2_txt());

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm2_sel(),
                        ConditionMapper.OPERATOR_LESS_EQUAL, dto.getItemTo2_txt());

                // 前方一致
            } else if (StringUtils.equals(dto.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm2_sel(), ConditionMapper.OPERATOR_LIKE,
                        dto.getItemFrom2_txt() + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 曖昧
            } else if (StringUtils.equals(dto.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm2_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom2_txt()
                                + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 後方一致
            } else if (StringUtils.equals(dto.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm2_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom2_txt());

                // 完全一致
            } else {
                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm2_sel(), ConditionMapper.OPERATOR_EQUAL,
                        dto.getItemFrom2_txt());
            }
        }

        // 条件キー条件３
        if (!StringUtils.equals(dto.getItemNm3_sel(), NaikaraTalkConstants.BRANK)) {
            // 範囲
            if (StringUtils.equals(dto.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm3_sel(),
                        ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getItemFrom3_txt());

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm3_sel(),
                        ConditionMapper.OPERATOR_LESS_EQUAL, dto.getItemTo3_txt());

                // 前方一致
            } else if (StringUtils.equals(dto.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm3_sel(), ConditionMapper.OPERATOR_LIKE,
                        dto.getItemFrom3_txt() + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 曖昧
            } else if (StringUtils.equals(dto.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm3_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom3_txt()
                                + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 後方一致
            } else if (StringUtils.equals(dto.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm3_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom3_txt());

                // 完全一致
            } else {
                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm3_sel(), ConditionMapper.OPERATOR_EQUAL,
                        dto.getItemFrom3_txt());
            }
        }

        // 条件キー条件４
        if (!StringUtils.equals(dto.getItemNm4_sel(), NaikaraTalkConstants.BRANK)) {
            // 範囲
            if (StringUtils.equals(dto.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm4_sel(),
                        ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getItemFrom4_txt());

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm4_sel(),
                        ConditionMapper.OPERATOR_LESS_EQUAL, dto.getItemTo4_txt());

                // 前方一致
            } else if (StringUtils.equals(dto.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm4_sel(), ConditionMapper.OPERATOR_LIKE,
                        dto.getItemFrom4_txt() + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 曖昧
            } else if (StringUtils.equals(dto.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm4_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom4_txt()
                                + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 後方一致
            } else if (StringUtils.equals(dto.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm4_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom4_txt());

                // 完全一致
            } else {
                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm4_sel(), ConditionMapper.OPERATOR_EQUAL,
                        dto.getItemFrom4_txt());
            }
        }

        // 条件キー条件５
        if (!StringUtils.equals(dto.getItemNm5_sel(), NaikaraTalkConstants.BRANK)) {
            // 範囲
            if (StringUtils.equals(dto.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm5_sel(),
                        ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getItemFrom5_txt());

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm5_sel(),
                        ConditionMapper.OPERATOR_LESS_EQUAL, dto.getItemTo5_txt());

                // 前方一致
            } else if (StringUtils.equals(dto.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm5_sel(), ConditionMapper.OPERATOR_LIKE,
                        dto.getItemFrom5_txt() + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 曖昧
            } else if (StringUtils.equals(dto.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm5_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom5_txt()
                                + NaikaraTalkConstants.OPERATOR_PERCENT);

                // 後方一致
            } else if (StringUtils.equals(dto.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU)) {

                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm5_sel(), ConditionMapper.OPERATOR_LIKE,
                        NaikaraTalkConstants.OPERATOR_PERCENT + dto.getItemFrom5_txt());

                // 完全一致
            } else {
                conditions.addCondition(STUDENT_MST_TABLE_NM + dto.getItemNm5_sel(), ConditionMapper.OPERATOR_EQUAL,
                        dto.getItemFrom5_txt());
            }
        }

        // 戻り値
        return conditions;

    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> list = cache.getList(category);

        Iterator<String> iter = list.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            CodeManagMstDto dto = list.get(key);
            hashMap.put(dto.getManagerCd(), dto.getManagerNm());
        }

        return hashMap;
    }
}
