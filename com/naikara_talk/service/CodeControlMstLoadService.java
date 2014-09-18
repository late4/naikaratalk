package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CodeControlMstListLogic;
import com.naikara_talk.logic.CodeControlMstLogic;
import com.naikara_talk.model.CodeControlMstModel;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class CodeControlMstLoadService implements ActionService {


    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @return CodeControlMstModel
     */
    public CodeControlMstModel initLoad(CodeControlMstModel model){
        CodeControlMstModel workModel=new CodeControlMstModel();

        // 前画面から処理区分を画面に設定
        workModel.setProcessKbn_Rdl(model.getProcessKbn_Rdl());

        // 前画面から処理区分を基に、処理区分の名称を設定
        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_ADD, model.getProcessKbn_Rdl())) {
            // 追加処理
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_INS);
        } else if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_UPD, model.getProcessKbn_Rdl())) {
            // 修正処理
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_UPD);
        } else if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_DEL, model.getProcessKbn_Rdl())) {
            // 削除処理
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_DEL);
        } else {
            // 照会処理
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_REF);
        }

        // 前画面のコード種別名(ドロップダウンリスト)の検索ボタン押下時の選択値を設定
        workModel.setDefaultCdCategory(model.getDefaultCdCategory());

        return workModel;
    }


    /**
     * データの存在チェック。<br>
     * <br>
     * @param CodeControlMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int Exists(CodeControlMstModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            //CodeControlMstLogicクラスの生成
            CodeControlMstLogic logic = new CodeControlMstLogic(conn);

            //CodeManagMstDtoクラスの生成
            CodeManagMstDto prmDto = new CodeManagMstDto();

            // Model値をDTOに設定
            prmDto = this.modelToDto(model);

            // 検索実行
            return logic.Exists(prmDto);

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
     * 画面初期表示。<br>
     * <br>
     * @param CodeControlMstModel
     *            画面のパラメータ
     * @return なし
     * @throws Exception
     */
    public CodeControlMstModel select(CodeControlMstModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            //CodeControlMstLogicクラスの生成
            CodeControlMstLogic logic = new CodeControlMstLogic(conn);

            //CodeManagMstDtoクラスの生成
            CodeManagMstDto prmDto = new CodeManagMstDto();

            // Model値をDTOに設定
            prmDto = this.modelToDto(model);

            // 検索実行
            CodeManagMstDto resultDto = logic.select(prmDto);

            // DTO値をModelに設定
            model = this.dtoToModel(resultDto, model);

            return model;

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
     * @return CodeManagMstDto
     * @throws Exception
     */
    private CodeManagMstDto modelToDto(CodeControlMstModel model)
            throws NaiException {

        //CodeManagMstDtoクラスの生成
        CodeManagMstDto prmDto = new CodeManagMstDto();

        prmDto.setCdCategory(model.getCdCategorySelected());                // コード種別
        prmDto.setManagerCd(model.getManagerCd());                          // 汎用コード
        prmDto.setManagerNm(model.getManagerNm());                          // 汎用フィールド

        try {
            if (model.getOrderBy() == null) {
                prmDto.setOrderBy(CodeControlMstListLogic.ORDER_BY_INIT);                                       // 並び順
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

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * @param prmDto
     *           CodeManagMstDto
     * @param model
     *           CodeControlMstModel
     * @return CodeControlMstModel
     * @throws Exception
     */
    private CodeControlMstModel dtoToModel(CodeManagMstDto prmDto, CodeControlMstModel model)
            throws NaiException {

        // 表示・変更不可項目
        model.setProcessKbn_Rdl(model.getProcessKbn_Rdl());               // 処理区分
        model.setProcessKbn_Txt( model.getProcessKbn_Txt());              // 処理区分名
        model.setCdCategorySelected(prmDto.getCdCategory());              // コード種別

        // 入力項目
        model.setManagerCd(prmDto.getManagerCd());                        // 汎用コード
        model.setManagerNm(prmDto.getManagerNm());                        // 汎用フィールド

        try {
            if (prmDto.getOrderBy() == CodeControlMstListLogic.ORDER_BY_INIT) {
                model.setOrderBy(NaikaraTalkConstants.BRANK);             // 並び順
            } else {
                model.setOrderBy(String.valueOf(prmDto.getOrderBy()));    // 並び順
            }
        } catch (Exception e) {
             throw new NaiException(e);
        }

        model.setRemark(prmDto.getRemark());                              // 備考
        model.setStudentMstEnm(prmDto.getStudentMstEnm());                // 受講者マスタ英語項目名
        model.setMark(prmDto.getMark());                                  // 表示用点数
        model.setSystemDelNgFlg(prmDto.getSystemDelNgFlg());              // システム削除不可フラグ
        model.setRecordVerNo(prmDto.getRecordVerNo());                    // レコードバージョン番号

        return model;

    }

}
