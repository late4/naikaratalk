package com.naikara_talk.service;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CodeControlMstListLogic;
import com.naikara_talk.logic.CodeControlMstLogic;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.model.CodeControlMstModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.CheckUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス登録更新Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public class CodeControlMstUpdateService implements ActionService {

    /* 更新前チェック：コード種別(ドロップダウンリスト)の必須チェックエラー */
    public static final int ERR_REQUIRED_CD_CATEGORY = 1;

    /* 更新前チェック：汎用フィールドの桁数チェックエラー */
    public static final int ERR_BYTE_LENGTH_MANAGER_NM = 2;

    /* 更新前チェック：並び順の下限チェックエラー */
    public static final int ERR_ORDER_BY_MIN = 3;

    /* 更新前チェック：エラーなし */
    public static final int ERR_NO = 0;

    /* 汎用フィールドの桁数(キャンペーン、お知らせ) */
    private static int BYTE_LENGTH_MANAGER_NM_CAMPAIGN_NEWS = 378;

    /* 汎用フィールドの桁数(基本) 15*3 */
    private static int BYTE_LENGTH_MANAGER_NM_BASIC = 45;


    /**
     * 登録、更新のチェック。<br>
     * <br>
     * @param CodeControlMstModel
     * @param processKbn
     * @return int
     * @throws Exception
     */
    public int errorCheck(CodeControlMstModel model, String processKbn) throws NaiException {

        String cdCategorySelected = model.getCdCategorySelected();    // コード種別
        String managerNm = model.getManagerNm();                      // 汎用フィールド
        int managerNmByteLength = BYTE_LENGTH_MANAGER_NM_BASIC;       // 汎用フィールドのバイト数

        // コード種別(ドロップダウンリスト)の必須チェック
        if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, cdCategorySelected)) {
            return ERR_REQUIRED_CD_CATEGORY;
        }

        // 汎用フィールドの桁数チェック(基本は30バイト。下記のコード種別は255バイト)
        // *** J24 個人：スクールからのキャンペーン
        // *** J25 個人：スクールからのお知らせ
        // *** J26 法人：スクールからのキャンペーン
        // *** J27 法人：スクールからのお知らせ
        // *** J28 講師：スクールからのお知らせ
        // *** E28 講師：スクールからのお知らせ
        if (StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_CAMPAIGN_FROM_SCOOL_TO_PERS, cdCategorySelected)
                || StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_PERS, cdCategorySelected)
                || StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_CAMPAIGN_FROM_SCOOL_TO_CORP, cdCategorySelected)
                || StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_CORP, cdCategorySelected)
                || StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_TEAC_J, cdCategorySelected)
                || StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_TEAC_E, cdCategorySelected)
                ) {
            managerNmByteLength = BYTE_LENGTH_MANAGER_NM_CAMPAIGN_NEWS;
        }

        // 汎用フィールドの桁数チェックのメッセージ用
        model.setManagerNmMaxLength(managerNmByteLength);

        // 汎用フィールドの桁数チェック
        if (!CheckUtil.validateStrByByte(managerNm, managerNmByteLength)) {
            return ERR_BYTE_LENGTH_MANAGER_NM;
        }

        // 並び順の1以上のチェック
        if (model.getOrderBy() != null) {
            // 数値チェックはvalidaterでチェック済
            if (CodeControlMstListLogic.ORDER_BY_INIT  >= Integer.parseInt(model.getOrderBy())) {
                return ERR_ORDER_BY_MIN;
            }
        }

        // CodeControlMstListLoadServiceクラスの生成
        CodeControlMstListLoadService service = new CodeControlMstListLoadService();

        // CodeClassMstDtoクラスの生成
        CodeClassMstDto dto = new CodeClassMstDto();
        dto.setCdCategory(cdCategorySelected);          // 選択されたコード種別

        // コード種別マスタのデータの取得
        List<CodeClassMstDto> list = service.selectCodeClassMst(dto);

        String managerInsertNgFlg = NaikaraTalkConstants.MANAGER_INSERT_NG_FLG_YES; // 初期値の設定
        if (list.size() > 0) {
            managerInsertNgFlg = list.get(0).getManagerInsertNgFlg();    // 管理コード登録不可フラグ
        }

        // 処理区分毎のチェック
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, processKbn)
                && StringUtils.equals(NaikaraTalkConstants.MANAGER_INSERT_NG_FLG_YES, managerInsertNgFlg) ) {
            // 処理区分が[新規]の場合 且つ (コード種別マスタ)管理コード登録不可フラグ＝”1”(登録不可)の場合
            return CodeControlMstListModel.ERR_MANAGER_NO_INS;
        }


        // CodeControlMstUpdateServiceクラスの生成
        CodeControlMstUpdateService serviceUp = new CodeControlMstUpdateService();

        // CodeManagMstDtoクラスの生成
        CodeManagMstDto dtoM = new CodeManagMstDto();

        // データの取得処理
        dtoM = serviceUp.select(model);

        if (dtoM != null) {
            if (dtoM.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // 正常の場合
                if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_DEL, processKbn)
                        && StringUtils.equals(NaikaraTalkConstants.SYSTEM_DEL_NG_FLG_YES, dtoM.getSystemDelNgFlg()) ) {
                        // 処理区分[削除]の場合 且つ (コード管理マスタ)システム削除不可フラグ＝”1”(削除不可)の場合
                        return CodeControlMstListModel.ERR_MANAGER_NO_DEL;
                    }
            }
        }

        return ERR_NO;
    }



    /**
	 * データの取得処理。<br>
	 * <br>
     * @param CodeControlMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
	 */
    public CodeManagMstDto select(CodeControlMstModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();

            CodeControlMstLogic logic = new CodeControlMstLogic(conn);

            // DTOの初期化
            CodeManagMstDto prmDto = new CodeManagMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            return logic.select(prmDto);

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
     * 登録処理。<br>
     * <br>
     * @param CodeControlMstModel
     *            画面のパラメータ
     * @return なし
     * @throws Exception
     */
    public int insert(CodeControlMstModel model) throws NaiException {
        int cnt = 0;  // 初期値の設定

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CodeControlMstLogic logic = new CodeControlMstLogic(conn);
            // DTOの初期化
            CodeManagMstDto prmDto = new CodeManagMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 登録実行
            cnt = logic.insert(prmDto);

            // コミット処理
            conn.commit();

            return cnt;

        } catch (Exception e) {
            try {
                // ロールバック処理
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }


    /**
     * 更新処理。<br>
     * <br>
     * @param CodeControlMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int update(CodeControlMstModel model) throws NaiException {
        int cnt = 0;  // 初期値の設定

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CodeControlMstLogic logic = new CodeControlMstLogic(conn);

            // DTOの初期化
            CodeManagMstDto prmDto = new CodeManagMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = logic.update(prmDto);

            // コミット処理
            conn.commit();

            return cnt;

        } catch (Exception e) {
            try {
                // ロールバック処理
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }


	/**
	 * 削除処理。<br>
	 * <br>
     * @param CodeControlMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
	 */
    public int delete(CodeControlMstModel model) throws NaiException {
        int cnt = 0;    // 初期値の設定

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            CodeControlMstLogic logic = new CodeControlMstLogic(conn);

            // DTOの初期化
            CodeManagMstDto prmDto = new CodeManagMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = logic.delete(prmDto);

            // コミット処理
            conn.commit();

            return cnt;

        } catch (Exception e) {
            try {
                // ロールバック処理
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
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
     *           CodeControlMstModel
     * @return CodeManagMstDto
     * @throws Exception
     */
    private CodeManagMstDto modelToDto(CodeControlMstModel model)
            throws NaiException {

        // DTOの初期化
        CodeManagMstDto prmDto = new CodeManagMstDto();

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        prmDto.setCdCategory(model.getCdCategorySelected());                // コード種別
        prmDto.setManagerCd(model.getManagerCd());                          // 汎用コード
        prmDto.setManagerNm(model.getManagerNm());                          // 汎用フィールド

        try {
            if (model.getOrderBy() == null) {
                prmDto.setOrderBy(CodeControlMstListLogic.ORDER_BY_INIT);   // 並び順
            } else {
                prmDto.setOrderBy(Integer.parseInt(model.getOrderBy()));    // 並び順
            }
        } catch (Exception e) {
             throw new NaiException(e);
        }

        prmDto.setRemark(model.getRemark());                                // 備考
        prmDto.setStudentMstEnm(model.getStudentMstEnm());                  // 受講者マスタ英語項目名
        prmDto.setMark(model.getMark());                                    // 表示用点数
        prmDto.setSystemDelNgFlg(model.getSystemDelNgFlg());                // システム削除不可フラグ
        prmDto.setRecordVerNo(model.getRecordVerNo());                      // レコードバージョン番号
        prmDto.setInsertCd(userId);                                         // 登録者コード
        prmDto.setUpdateCd(userId);                                         // 更新者コード

        return prmDto;

    }

}
