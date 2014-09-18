package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報Logicクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/19 TECS 新規作成。
 */
public class StudentMonthlyFeePurchaseInformationLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentMonthlyFeePurchaseInformationLogic(Connection con) {
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
        int dataCount = dao.rowCount(NaikaraTalkConstants.POINT_OWNERSHIP_TRN, setConditions(dto));

        // 戻り値
        return dataCount;

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
        orderBy.addCondition(PointOwnershipTrnDao.COND_POINT_CD, OrderCondition.ASC);
        orderBy.addCondition(PointOwnershipTrnDao.COND_OWNERSHIP_ID, OrderCondition.ASC);

        // 一覧データを取得する
        list = dao.searchWithGroupBy(dto, orderBy);

        // 戻り値
        return list;

    }

    /**
     * データの存在チェック。<br>
     * <br>
     * @param PointOwnershipTrnDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getDataExist(PointOwnershipTrnDto dto) throws NaiException {

        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        ArrayList<PointOwnershipTrnDto> resultDto = dao.search(setConditionsIsExist(dto), orderBy);

        return resultDto.get(0).getReturnCode();

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
        conditions.addCondition(PointOwnershipTrnDao.COND_FEE_KBN, ConditionMapper.OPERATOR_EQUAL, dto.getFeeKbn());

        // 戻り値
        return conditions;

    }

    /**
     * データの存在チェック検索条件の設定。<br>
     * <br>
      * @param PointOwnershipTrnDto
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditionsIsExist(PointOwnershipTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 所有ID
        conditions.addCondition(PointOwnershipTrnDao.COND_OWNERSHIP_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getOwnershipId());

        // 戻り値
        return conditions;

    }
}
