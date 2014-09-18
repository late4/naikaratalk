package com.naikara_talk.service;

//import java.io.File;
//import java.math.BigDecimal;
//import java.sql.Blob;
//import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.UserMstLogic;
import com.naikara_talk.model.UserMstListModel;
import com.naikara_talk.model.UserMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return workModel 新画面パラメータ<br>
     * @exception なし
     */
    public UserMstModel initLoad(UserMstModel model) {

        // モデルの初期化
        UserMstModel workModel = new UserMstModel();

        // 処理区分(前画面情報)
        workModel.setProcessKbn_rdl(model.getProcessKbn_rdl());

        if (StringUtils.equals(UserMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {

            // 画面表示処理区分
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_INS);

        } else if (StringUtils.equals(UserMstListModel.PROS_KBN_UPD, model.getProcessKbn_rdl())) {

            // 画面表示処理区分
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_UPD);

        } else {

            // 画面表示処理区分
            workModel.setProcessKbn_txt(NaikaraTalkConstants.PROCESSKBN_REF);

        }

        // 利用期間開始日
        workModel.setUtilizationStart_txt(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));

        // 利用期間終了日
        workModel.setUtilizationEnd_txt(NaikaraTalkConstants.MAX_END_DT);

        return workModel;
    }

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception Exception
     */
    public UserMstModel select(UserMstModel model) throws Exception {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstLogic userMstLogic = new UserMstLogic(conn);

            // Model値をDTOにセット
            UserMstDto prmDto = this.modelToDto(model);

            // 検索を実行
            UserMstDto resultDto = userMstLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int getExists(UserMstModel model) throws Exception {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstLogic userMstLogic = new UserMstLogic(conn);

            // Model値をDTOにセット
            UserMstDto prmDto = this.modelToDto(model);

            // 検索を実行
            return userMstLogic.getExists(prmDto);

        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws Exception {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstLogic userMstLogic = new UserMstLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> codeMap = userMstLogic.selectCodeMst(category);

            return codeMap;

        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception Exception
     */
    private UserMstDto modelToDto(UserMstModel model) throws Exception {

        // DTOの初期化
        UserMstDto prmDto = new UserMstDto();

        prmDto.setUserId(model.getUserId_lbl());                // 利用者ID
        prmDto.setPassword(model.getPassword_txt());            // パスワード
        prmDto.setFamilyJnm(model.getNameFamiy_txt());          // 名前(姓)
        prmDto.setFirstJnm(model.getNameFirst_txt());           // 名前(名)
        prmDto.setFamilyKnm(model.getFuriganaFamiy_txt());      // フリガナ(姓)
        prmDto.setFirstKnm(model.getFuriganaFirst_txt());       // フリガナ(名）
        prmDto.setFamilyRomaji(model.getRomajiFamiy_txt());     // ローマ字(姓)
        prmDto.setFirstRomaji(model.getRomajiFirst_txt());      // ローマ字(名)
        prmDto.setTel1(model.getTelephone1_txt());              // 電話番号1
        prmDto.setTel2(model.getTelephone2_txt());              // 電話番号2
        prmDto.setBirthYyyy(model.getBirthdayYy_txt());         // 生年月日：年
        prmDto.setBirthMm(model.getBirthdayMm_txt());           // 生年月日：月
        prmDto.setBirthDd(model.getBirthdayDd_txt());           // 生年月日：日
        prmDto.setZipCd(model.getZipCode_txt());                // 郵便番号
        prmDto.setAddressAreaCd(model.getAddress1_sel());       // 住所(地域)コード
        prmDto.setAddressPrefectureCd(model.getAddress2_sel()); // 住所(都道府県)コード
        prmDto.setAddressCity(model.getAddress3_txt());         // 住所(市区町村)
        prmDto.setAddressOthers(model.getAddress4_txt());       // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        prmDto.setGenderKbn(model.getSex_rdl());                // 性別区分
        prmDto.setMailAddress1(model.getMailAdd1_txt());        // メールアドレス1
        prmDto.setMailAddress2(model.getMailAdd2_txt());        // メールアドレス2
        prmDto.setMailAddress3(model.getMailAdd3_txt());        // メールアドレス3
        prmDto.setUseStartDt(model.getUtilizationStart_txt());  // 利用開始日
        prmDto.setUseEndDt(model.getUtilizationEnd_txt());      // 利用終了日
        prmDto.setCityTown(model.getContract_txt());            // 勤務拠点
        prmDto.setClassificationKbn(model.getUserKbn_rdl());    // 種別区分
        prmDto.setRemark(model.getRemarks_txa());               // 備考
        prmDto.setRecordVerNo(model.getRecordVerNo());          // レコードバージョン番号

        return prmDto;
    }

    /**
     * DTO値をセット<br>
     * <br>
     * DTO値をModelにセット<br>
     * <br>
     * @param prmDto 変換前DTO<br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception Exception
     */
    private UserMstModel dtoToModel(UserMstDto prmDto, UserMstModel model) throws Exception {

        model.setProcessKbn_rdl(model.getProcessKbn_rdl());     // 処理区分(前画面の引き継ぎ情報)
        model.setProcessKbn_txt(model.getProcessKbn_txt());     // 画面表示処理区分
        model.setUserId_lbl(prmDto.getUserId());                // 利用者ID
        model.setPassword_txt(prmDto.getPassword());            // パスワード
        model.setNameFamiy_txt(prmDto.getFamilyJnm());          // 名前(姓)
        model.setNameFirst_txt(prmDto.getFirstJnm());           // 名前(名)
        model.setFuriganaFamiy_txt(prmDto.getFamilyKnm());      // フリガナ(姓)
        model.setFuriganaFirst_txt(prmDto.getFirstKnm());       // フリガナ(名)
        model.setRomajiFamiy_txt(prmDto.getFamilyRomaji());     // ローマ字(姓)
        model.setRomajiFirst_txt(prmDto.getFirstRomaji());      // ローマ字(名)
        model.setTelephone1_txt(prmDto.getTel1());              // 電話番号1
        model.setTelephone2_txt(prmDto.getTel2());              // 電話番号2
        model.setBirthdayYy_txt(prmDto.getBirthYyyy());         // 生年月日の年
        model.setBirthdayMm_txt(prmDto.getBirthMm());           // 生年月日の月
        model.setBirthdayDd_txt(prmDto.getBirthDd());           // 生年月日の日
        model.setZipCode_txt(prmDto.getZipCd());                // 郵便番号
        model.setAddress1_sel(prmDto.getAddressAreaCd());       // 住所(地域)
        model.setAddress2_sel(prmDto.getAddressPrefectureCd()); // 住所(都道府県)
        model.setAddress3_txt(prmDto.getAddressCity());         // 住所(市区町村 等)
        model.setAddress4_txt(prmDto.getAddressOthers());       // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        model.setSex_rdl(prmDto.getGenderKbn());                // 性別
        model.setMailAdd1_txt(prmDto.getMailAddress1());        // メールアドレス1
        model.setMailAdd2_txt(prmDto.getMailAddress2());        // メールアドレス2
        model.setMailAdd3_txt(prmDto.getMailAddress3());        // メールアドレス3
        model.setUtilizationStart_txt(prmDto.getUseStartDt());  // 利用期間開始日
        model.setUtilizationEnd_txt(prmDto.getUseEndDt());      // 利用期間終了日
        model.setContract_txt(prmDto.getCityTown());            // 勤務拠点
        model.setUserKbn_rdl(prmDto.getClassificationKbn());    // 種別
        model.setRemarks_txa(prmDto.getRemark());               // 備考
        model.setRecordVerNo(prmDto.getRecordVerNo());          // 排他用レコードバージョン
        model.setUserKbn_chk(prmDto.getClassificationKbn());    // チェック用種別

        return model;
    }
}