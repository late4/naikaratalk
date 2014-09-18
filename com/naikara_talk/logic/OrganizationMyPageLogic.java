package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.naikara_talk.dao.GooPurTStuMDao;
import com.naikara_talk.dao.LesResPerTStuMDao;
import com.naikara_talk.dao.OrganizationMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.GooPurTStuMDto;
import com.naikara_talk.dto.LesResPerTStuMDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人_初期処理<br>
 * <b>クラス名称　　　:</b>組織マイページLogicクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/01 TECS 新規作成。
 */
public class OrganizationMyPageLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public OrganizationMyPageLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ<br>
     * @return OrganizationMstDto<br>
     * @exception NaiException
     */
    public OrganizationMstDto select(OrganizationMstDto dto) throws NaiException {

        OrganizationMstDao dao = new OrganizationMstDao(this.conn);

        // 並び順:組織ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, OrderCondition.ASC);
        ArrayList<OrganizationMstDto> resultDto = (ArrayList<OrganizationMstDto>) dao.searchWithNm(SetConditions(dto),
                orderBy);
        OrganizationMstDto dtoResult = new OrganizationMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES < resultDto.size()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

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
     * @param OrganizationMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper SetConditions(OrganizationMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 組織ID
        conditions.addCondition(OrganizationMstDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOrganizationId());

        // 連番
        conditions.addCondition(OrganizationMstDao.COND_CONS_SEQ, ConditionMapper.OPERATOR_EQUAL, dto.getConsSeq());

        // 戻り値
        return conditions;

    }

    /**
     * 商品購入の有償利用ポイント算出処理<br>
     * <br>
     * 商品購入の有償利用ポイントを集計して、返却する<br>
     * <br>
     * @param organizationId       組織ID
     * @param purchaseDate         購入日の年月
     * @return BigDecimal:SumPoint 商品購入の有償利用ポイント
     * @throws NaiException
     */
    public GooPurTStuMDto getPoint(String organizationId, String purchaseDate) throws NaiException {

        // 戻り値とリターンコードの初期化
        GooPurTStuMDto dto = null;

        // データ取得処理
        GooPurTStuMDao dao = new GooPurTStuMDao(this.conn);
        dto = dao.search(organizationId, purchaseDate);

        // 戻り値
        return dto;

    }

    /**
     * レッスン予実の有償利用ポイント算出処理<br>
     * <br>
     * レッスン予実の有償利用ポイントを集計して、返却する<br>
     * <br>
     * @param organizationId       組織ID
     * @param lessonDate           レッスン日の年月
     * @return BigDecimal:SumPoint レッスン予実の有償利用ポイント
     * @throws NaiException
     */
    public LesResPerTStuMDto getLesPoint(String organizationId, String lessonDate) throws NaiException {

        // 戻り値とリターンコードの初期化
        LesResPerTStuMDto dto = null;

        // データ取得処理
        LesResPerTStuMDao dao = new LesResPerTStuMDao(this.conn);
        dto = dao.search(organizationId, lessonDate);

        // 戻り値
        return dto;

    }

}
