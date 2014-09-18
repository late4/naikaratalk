package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.PointManagMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public PointControlLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return PointManagMstDto<br>
     * @exception NaiException
     */
    public PointManagMstDto select(PointManagMstDto dto) throws NaiException {

        PointManagMstDao dao = new PointManagMstDao(this.conn);

        // 並び順:通常月謝区分 の昇順、金額 の昇順、有効ポイント期限 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(PointManagMstDao.COND_FEE_KBN, OrderCondition.ASC);
        orderBy.addCondition(PointManagMstDao.COND_MONEY_YEN, OrderCondition.ASC);
        orderBy.addCondition(PointManagMstDao.COND_PAYMENT_POINT_TIM, OrderCondition.ASC);

        List<PointManagMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        PointManagMstDto dtoResult = new PointManagMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int insert(PointManagMstDto dto) throws NaiException {
        // DAOの初期化
        PointManagMstDao dao = new PointManagMstDao(this.conn);

        // 登録を行う
        return dao.insert(dto);
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int update(PointManagMstDto dto) throws NaiException {
        // DAOの初期化
        PointManagMstDao dao = new PointManagMstDao(this.conn);

        // 更新を行う
        return dao.update(dto);
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(PointManagMstDto dto) throws NaiException {

        PointManagMstDao dao = new PointManagMstDao(this.conn);

        // 並び順:通常月謝区分 の昇順、金額 の昇順、有効ポイント期限 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(PointManagMstDao.COND_FEE_KBN, OrderCondition.ASC);
        orderBy.addCondition(PointManagMstDao.COND_MONEY_YEN, OrderCondition.ASC);
        orderBy.addCondition(PointManagMstDao.COND_PAYMENT_POINT_TIM, OrderCondition.ASC);

        List<PointManagMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();

    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
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
     * 検索条件の設定。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(PointManagMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // ポイントコードを入力されている場合
        if (!StringUtils.isEmpty(dto.getPointCd())) {
            conditions.addCondition(PointManagMstDao.COND_POINT_CD, ConditionMapper.OPERATOR_EQUAL, dto.getPointCd());
        }

        // 戻り値
        return conditions;

    }

}
