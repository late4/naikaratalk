package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.TimeManagMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【一覧】Logicクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【一覧】Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成。
 */
public class TimeZoneControlMstListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TimeZoneControlMstListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param TimeManagMstDto
     * @return int:DataCount
     * @throws NaiException
     */
    public int getRowCount(TimeManagMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.TIME_MANAG_MST, SetConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 検索処理<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return List<TimeManagMstDto>
     * @throws Exception
     */
    public List<TimeManagMstDto> selectList(TimeManagMstDto dto) throws Exception {

        // 初期化処理
        List<TimeManagMstDto> list = new ArrayList<TimeManagMstDto>();
        TimeManagMstDao dao = new TimeManagMstDao(this.conn);

        // 並び順:国コード&時差管理No の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(TimeManagMstDao.COND_COUNTRY_CD, OrderCondition.ASC);
        orderBy.addCondition(TimeManagMstDao.COND_AREA_NO_CD, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.search(SetConditions(dto), orderBy);

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param TimeManagMstDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper SetConditions(TimeManagMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 国コード＝全て(のコード) の場合
        if (!StringUtils.equals(dto.getCountryCd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            conditions
                    .addCondition(TimeManagMstDao.COND_COUNTRY_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCountryCd());
        }

        // 時差地域NO＝全て(のコード) の場合
        if (!StringUtils.equals(dto.getAreaNoCd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            conditions.addCondition(TimeManagMstDao.COND_AREA_NO_CD, ConditionMapper.OPERATOR_EQUAL, dto.getAreaNoCd());
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
