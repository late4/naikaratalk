package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス 特別無償ポイント設定Logicクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス 特別無償ポイント設定Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentSpecialFreePointMstLogic {

    private static int BALANCE_POINT = 0;

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentSpecialFreePointMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param PointOwnershipTrnDto
     * @return int:DataCount
     * @throws NaiException
     */
    public int getRowCount(PointOwnershipTrnDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.POINT_OWNERSHIP_TRN, setConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 検索処理<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return List<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public List<PointOwnershipTrnDto> selectList(PointOwnershipTrnDto dto) throws NaiException {

        // 初期化処理
        List<PointOwnershipTrnDto> list = new ArrayList<PointOwnershipTrnDto>();
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);

        // ポイントコード、購入ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_STR_DT, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.search(setConditions(dto), orderBy);
        if (list.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            return new ArrayList<PointOwnershipTrnDto>();
        }

        // 戻り値
        return list;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param PointOwnershipTrnDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditions(PointOwnershipTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(PointOwnershipTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 有償無償区分
        conditions.addCondition(PointOwnershipTrnDao.COND_COMPENSATION_FREE_KBN, ConditionMapper.OPERATOR_EQUAL,
                dto.getFeeKbn());

        // ポイント残高
        conditions.addCondition(PointOwnershipTrnDao.COND_BALANCE_POINT, ConditionMapper.OPERATOR_LARGER_THAN,
                BALANCE_POINT);

        // 有効終了日
        conditions.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());

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
