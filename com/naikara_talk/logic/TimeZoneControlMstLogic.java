package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

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
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【単票】Logicクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【単票】Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/10 TECS 新規作成。
 */
public class TimeZoneControlMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TimeZoneControlMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return TimeManagMstDto
     * @throws NaiException
     */
    public TimeManagMstDto select(TimeManagMstDto dto) throws NaiException {

        TimeManagMstDao dao = new TimeManagMstDao(this.conn);

        // 並び順:国コード&時差管理No の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(TimeManagMstDao.COND_COUNTRY_CD, OrderCondition.ASC);
        orderBy.addCondition(TimeManagMstDao.COND_AREA_NO_CD, OrderCondition.ASC);

        ArrayList<TimeManagMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        TimeManagMstDto dtoResult = new TimeManagMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 登録処理。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int insert(TimeManagMstDto dto) throws NaiException {
        // DAOの初期化
        TimeManagMstDao dao = new TimeManagMstDao(this.conn);

        // 登録を行う
        return dao.insert(dto);
    }

    /**
     * 更新処理。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int update(TimeManagMstDto dto) throws NaiException {
        // DAOの初期化
        TimeManagMstDao dao = new TimeManagMstDao(this.conn);

        // 更新を行う
        return dao.update(dto);

    }

    /**
     * 削除処理。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int delete(TimeManagMstDto dto) throws NaiException {
        // DAOの初期化
        TimeManagMstDao dao = new TimeManagMstDao(this.conn);

        // 削除を行う
        return dao.delete(dto);
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getExist(TimeManagMstDto dto) throws NaiException {

        TimeManagMstDao dao = new TimeManagMstDao(this.conn);

        // 並び順:国コード の昇順、時差地域No の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(TimeManagMstDao.COND_COUNTRY_CD, OrderCondition.ASC);
        orderBy.addCondition(TimeManagMstDao.COND_AREA_NO_CD, OrderCondition.ASC);

        ArrayList<TimeManagMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();

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

        // 国コードを入力されている場合
        conditions.addCondition(TimeManagMstDao.COND_COUNTRY_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCountryCd());
        // 時差地域Noを入力されている場合
        conditions.addCondition(TimeManagMstDao.COND_AREA_NO_CD, ConditionMapper.OPERATOR_EQUAL, dto.getAreaNoCd());

        // 戻り値
        return conditions;

    }
}
