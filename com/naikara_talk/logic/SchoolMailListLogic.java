package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.SmaAccHisTSmaAccHisDetTStuMDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SmaAccHisTSmaAccHisDetTStuMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>顧客管理<br>
 * <b>クラス名称       :</b>スクールのメール送信・受信履歴照会<br>
 * <b>クラス概要       :</b>スクールのメール送信・受信履歴照会Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/12/16 TECS 新規作成
 */
public class SchoolMailListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SchoolMailListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param SmaAccHisTSmaAccHisDetTStuMDto<br>
     * @return int:dataCount<br>
     * @exception NaiException
     */
    public int getRowCount(SmaAccHisTSmaAccHisDetTStuMDto dto) throws NaiException {

        // 初期化処理
        SmaAccHisTSmaAccHisDetTStuMDao dao = new SmaAccHisTSmaAccHisDetTStuMDao(this.conn);

        // 一覧のデータ件数を取得する
        int dataCount = dao.rowCount(setConditions(dto), dto);

        // 戻り値
        return dataCount;

    }

    /**
     * 検索処理<br>
     * <br>
     * 検索処理<br>
     * <br>
     * @param SmaAccHisTSmaAccHisDetTStuMDto 画面のパラメータ<br>
     * @return List<SmaAccHisTSmaAccHisDetTStuMDto> 一覧データ <br>
     * @exception NaiException
     */
    public List<SmaAccHisTSmaAccHisDetTStuMDto> selectList(SmaAccHisTSmaAccHisDetTStuMDto dto) throws NaiException {

        // 初期化処理
        List<SmaAccHisTSmaAccHisDetTStuMDto> list = new ArrayList<SmaAccHisTSmaAccHisDetTStuMDto>();
        SmaAccHisTSmaAccHisDetTStuMDao dao = new SmaAccHisTSmaAccHisDetTStuMDao(this.conn);

        // 並び順:名前(姓)の昇順、名前(名)の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_ORGANIZATION_ID, OrderCondition.ASC);
        orderBy.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_STUDENT_ID, OrderCondition.ASC);
        orderBy.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_SEND_DTTM, OrderCondition.ASC);
        orderBy.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_SEND_DTTM_SEQ, OrderCondition.ASC);
        orderBy.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_MAIL_PATTERN_CD, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.search(setConditions(dto), orderBy, dto);

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param SmaAccHisTSmaAccHisDetTStuMDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(SmaAccHisTSmaAccHisDetTStuMDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // メールパターンコード選択されている場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getMailPatternCd())) {
            conditions.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_MAIL_PATTERN_CD,
                    ConditionMapper.OPERATOR_EQUAL, dto.getMailPatternCd());
        }

        // 受講者IDを入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentId())) {
            StringBuffer work = new StringBuffer();
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentId())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_LIKE,
                    work.toString());
        }

        // 画面の｢顧客区分｣＝全て を除く場合
        if (!StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getCustomerKbn())) {
            if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION, dto.getCustomerKbn())
                || StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_PERSON, dto.getCustomerKbn())) {
                // 法人、個人の場合(対象：受講者)
                conditions.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_CUSTOMER_KBN, ConditionMapper.OPERATOR_EQUAL,
                        dto.getCustomerKbn());
            }
        }

        // 受講者名(ニックネーム)を入力されている場合
        if (!StringUtils.isEmpty(dto.getNickNm())) {
            StringBuffer work = new StringBuffer();
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getNickNm())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_NICK_NM, ConditionMapper.OPERATOR_LIKE,
                    work.toString());
        }

        // 組織名を入力されている場合
        if (!StringUtils.isEmpty(dto.getOrganizationNm())) {
            StringBuffer work = new StringBuffer();
            work.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getOrganizationNm())
                    .append(NaikaraTalkConstants.OPERATOR_PERCENT);
            conditions.addCondition(SmaAccHisTSmaAccHisDetTStuMDao.COND_ORGANIZATION_NM, ConditionMapper.OPERATOR_LIKE,
                    work.toString());
        }

        // 戻り値
        return conditions;

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

}
