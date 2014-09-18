package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstModel;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンスLogicクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンスLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public class CodeControlMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CodeControlMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * @param CodeManagMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int Exists(CodeManagMstDto dto) throws NaiException {

        //TableCountDaoクラスのオブジェクトを生成
        TableCountDao dao = new TableCountDao(this.conn);

        // データ件数の取得処理
        return dao.rowCount(NaikaraTalkConstants.CODE_MANAG_MST, SetConditions(dto));

    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * @param CodeManagMstDto
     *            画面のパラメータ
     * @return CodeManagMstDto
     * @throws NaiException
	 */
    public CodeManagMstDto select(CodeManagMstDto dto) throws NaiException {

        CodeManagMstDao dao = new CodeManagMstDao(this.conn);
        CodeManagMstDto dtoResult = new CodeManagMstDto();

        // 並び順 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(CodeManagMstDao.COND_ORDER_BY, OrderCondition.ASC);

        // データ取得処理
        List<CodeManagMstDto> resultDto = dao.search(SetConditions(dto), orderBy);

        // 戻り値へ値の設定
        if (resultDto.size() > 0) {
            // 1件分
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }


    /**
     * 登録処理。<br>
     * <br>
     * @param CodeManagMstDto
     *            画面のパラメータ
     * @return なし
     * @throws NaiException
     */
    public int insert(CodeManagMstDto dto) throws NaiException {
        CodeManagMstDao dao = new CodeManagMstDao(this.conn);
        try {
            dao.insert(dto);
        } catch (Exception e) {

        }
        return CodeControlMstModel.PROCESS_NORMAL;
    }


    /**
     * 更新処理。<br>
     * <br>
     * @param CodeManagMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int update(CodeManagMstDto dto) throws NaiException {
        CodeManagMstDao dao = new CodeManagMstDao(this.conn);
        try {
            return dao.update(dto);

        } catch (Exception e) {

        }
        return CodeControlMstModel.PROCESS_NORMAL;
    }


    /**
     * 削除処理。<br>
     * <br>
     * @param CodeManagMstDto
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int delete(CodeManagMstDto dto) throws NaiException {
        CodeManagMstDao dao = new CodeManagMstDao(this.conn);
        try {
            return dao.delete(dto);

        } catch (Exception e) {

        }
        return CodeControlMstModel.PROCESS_NORMAL;
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * @param CodeManagMstDto
     * @return ConditionMapper:conditions
     * @throws NaiException
     */
    private ConditionMapper SetConditions(CodeManagMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper  conditions = new ConditionMapper();

        // コード種別を入力されている場合
        if (!StringUtils.isEmpty(dto.getCdCategory())) {
            conditions.addCondition(CodeManagMstDao.COND_CD_CATEGORY,
                ConditionMapper.OPERATOR_EQUAL, dto.getCdCategory());
        }
        // 汎用フィールドを入力されている場合
        if (!StringUtils.isEmpty(dto.getManagerCd())) {
            conditions.addCondition(CodeManagMstDao.COND_MANAGER_CD,
                ConditionMapper.OPERATOR_EQUAL, dto.getManagerCd());
        }

        // 戻り値
        return conditions;

    }

}
