package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CdManagMstCdClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CodeControlMstListLogic;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス登録/選択Serviceクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス登録/選択Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public class CodeControlMstListMoveService implements ActionService {


    /**
     * 単票へ画面遷移する前のチェック<br>
     * <br>
     * @param model
     * @param cdCategorySelected
     * @param select_rdl
     * @param hasSearchFlg
     * @return SaleGoodsMstListModel
     * @throws NaiException
     */
    public int nextPageRequest(CodeControlMstListModel model, String cdCategorySelected,
            String select_rdl, String hasSearchFlg) throws NaiException{

        int rturnCd;    // 初期値の設定

        Connection conn = null;
        try {

            conn = DbUtil.getConnection();    // DBの接続

            // ◆◆◆処理区分のチェック◆◆◆
            // (処理区分が[新規]を除く場合) 必須選択のチェック
            if (!StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, model.getProcessKbn())
               && StringUtils.isEmpty(select_rdl)) {
               // 処理区分が[新規]を除く場合、一覧部の項目が選択されていない場合は、メッセージ情報を設定する
               if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
                   // エラー場合の再検索判断
                   return CodeControlMstListModel.ERR_NO_SELECT;

               }
               // 一覧画面戻る
               return CodeControlMstListModel.ERR_LIST_BACK;
            }

            // CodeControlMstListLogicクラスの生成
            CodeControlMstListLogic logic = new CodeControlMstListLogic(conn);

            // チェックデータの取得処理
            CdManagMstCdClassMstDto dto = this.select(model, select_rdl);

            // 処理区分毎のチェック
            if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, model.getProcessKbn())
                    && StringUtils.equals(NaikaraTalkConstants.MANAGER_INSERT_NG_FLG_YES, dto.getManagerInsertNgFlgC()) ) {
                // 処理区分が[新規]の場合 且つ (コード種別マスタ)管理コード登録不可フラグ＝”1”(登録不可)の場合
                return CodeControlMstListModel.ERR_MANAGER_NO_INS;
            }

            if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_DEL, model.getProcessKbn())
                && StringUtils.equals(NaikaraTalkConstants.SYSTEM_DEL_NG_FLG_YES, dto.getSystemDelNgFlg()) ) {
                // 処理区分[削除]の場合 且つ (コード管理マスタ)システム削除不可フラグ＝”1”(削除不可)の場合
                return CodeControlMstListModel.ERR_MANAGER_NO_DEL;
            }

            // ◆◆◆権限チェック◆◆◆
            rturnCd = logic.checkRole(model);

            // 返却値の設定
            return rturnCd;

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
     * チェックで必要な情報の取得処理。<br>
     * <br>
     * @param CodeControlMstListModel
     *            画面のパラメータ
     * @param select_rdl
     *            画面の一覧の選択値
     * @return なし
     * @throws NaiException
     */
    public CdManagMstCdClassMstDto select(CodeControlMstListModel model, String select_rdl) throws NaiException {

        Connection conn = null;
        try {

            conn = DbUtil.getConnection();    // DBの接続

            // CodeControlMstListLogicクラスの生成
            CodeControlMstListLogic logic = new CodeControlMstListLogic(conn);

            // CdManagMstCdClassMstDtoクラスの生成
            CdManagMstCdClassMstDto prmDto = new CdManagMstCdClassMstDto();

            // CdManagMstCdClassMstDtoクラスの生成
            CdManagMstCdClassMstDto dtoResult = new CdManagMstCdClassMstDto();

            // Model値をDTOに設定
            prmDto = this.modelToDto(model, select_rdl);

            // 検索処理
            List<CdManagMstCdClassMstDto> list = logic.select(prmDto);

            // 返却値へ値の設定
            if (list.size() > 0) {
                // 1件分
                dtoResult = list.get(0);
            }

            return dtoResult;

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
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           CodeControlMstModel
     * @param select_rdl
     *            画面の一覧の選択値
     * @return CdManagMstCdClassMstDto
     * @throws NaiException
     */
    private CdManagMstCdClassMstDto modelToDto(CodeControlMstListModel model, String select_rdl)
            throws NaiException {

        // CdManagMstCdClassMstDtoクラスの生成
        CdManagMstCdClassMstDto prmDto = new CdManagMstCdClassMstDto();

        prmDto.setCdCategory(model.getCdCategorySelected());    // コード種別(選択された明細の値)
        prmDto.setManagerCd(select_rdl);                        // 汎用コード(選択された明細の値)

        return prmDto;

    }

    /**
     * 画面データの取得処理。(エラー発生時に既に一覧表示している場合は一覧表示する)<br>
     * <br>
     * @param CdManagMstCdClassMstDto
     *            画面のパラメータ
     * @return List<GoodsMstDto>
     * @throws Exception
	 */
    public List<CdManagMstCdClassMstDto> selectList(CodeControlMstListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();    // DBの接続

            // CodeControlMstListLogicクラスの生成
            CodeControlMstListLogic logic = new  CodeControlMstListLogic(conn);

            // CdManagMstCdClassMstDtoクラスの生成
            CdManagMstCdClassMstDto dto = new CdManagMstCdClassMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return logic.select(dto);

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
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           CodeControlMstListModel
     * @return CdManagMstCdClassMstDto
     * @throws Exception
	 */
    private CdManagMstCdClassMstDto modelToDto(CodeControlMstListModel model)
            throws NaiException {

        // DTOの初期化
        CdManagMstCdClassMstDto prmDto = new CdManagMstCdClassMstDto();

        prmDto.setCdCategory(model.getCdCategorySelected());    // コード種別(選択された値)
        prmDto.setManagerCd(model.getManagerCd());              // 汎用コード
        prmDto.setManagerNm(model.getManagerNm());              // 汎用フィールド

        return prmDto;

    }


}
