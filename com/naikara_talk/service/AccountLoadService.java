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
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.AccountLogic;
import com.naikara_talk.model.AccountModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様_初期処理。<br>
 * <b>クラス名称　　　:</b>アカウント取得処理初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>アカウント取得処理初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/08 TECS 新規作成。
 */
public class AccountLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    public AccountModel select(AccountModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            // Model値をDTOにセット
            StudentMstDto prmDto = this.modelToDto(model);

            // 検索を実行
            StudentMstDto resultDto = accountLogic.select(prmDto);

            conn.commit();

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;

        } catch (SQLException se) {
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
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int isExists(AccountModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            // Model値をDTOにセット
            StudentMstDto prmDto = this.modelToDto(model);

            // 検索を実行
            int existsFlg = accountLogic.isExists(prmDto);

            conn.commit();
            return existsFlg;

        } catch (SQLException se) {
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
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            AccountLogic accountLogic = new AccountLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> codeMap = accountLogic.selectCodeMst(category);

            conn.commit();
            return codeMap;

        } catch (SQLException se) {
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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private StudentMstDto modelToDto(AccountModel model) throws NaiException {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();

        // 受講者ID
        prmDto.setStudentId(model.getStudentId_lbl());

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
     * @exception NaiException
     */
    private AccountModel dtoToModel(StudentMstDto prmDto, AccountModel model) throws NaiException {

        // 受講者ID
        model.setStudentId_lbl(prmDto.getStudentId());
        // 受講者所属部署
        model.setStudentPosi_txt(prmDto.getStudentPosition());
        // パスワード
        model.setPassword_txt(prmDto.getPassword());
        // パスワード確認
        model.setPasswordConf_txt(prmDto.getPassword());
        // お名前(姓)
        model.setFamilyJnm_txt(prmDto.getFamilyJnm());
        // お名前(名)
        model.setFirstJnm_txt(prmDto.getFirstJnm());
        // フリガナ(姓)
        model.setFamilyKnm_txt(prmDto.getFamilyKnm());
        // フリガナ(名)
        model.setFirstKnm_txt(prmDto.getFirstKnm());
        // ローマ字(姓)
        model.setFamilyRomaji_txt(prmDto.getFamilyRomaji());
        // ローマ字(名)
        model.setFirstRomaji_txt(prmDto.getFirstRomaji());
        // ニックネーム
        model.setNickNm_txt(prmDto.getNickNm());
        // 電話番号
        model.setTel1_txt(prmDto.getTel1());
        // 携帯電話
        model.setTel2_txt(prmDto.getTel2());
        // 生年月日年
        model.setBirthYy_txt(prmDto.getBirthYyyy());
        // 生年月日月
        model.setBirthMm_txt(prmDto.getBirthMm());
        // 生年月日日
        model.setBirthDd_txt(prmDto.getBirthDd());
        // 郵便番号
        model.setZipCd_txt(prmDto.getZipCd());
        // 住所(地域)
        model.setArea_sel(prmDto.getAddressAreaCd());
        // 住所(都道府県)
        model.setPrefecture_sel(prmDto.getAddressPrefectureCd());
        // 住所(市区町村 等)
        model.setCity_txt(prmDto.getAddressCity());
        // 住所(ビル、マンション名)
        model.setOther_txt(prmDto.getAddressOthers());
        // 性別
        model.setGender_rdl(prmDto.getGenderKbn());
        // メールアドレス1
        model.setMailAdd1_txt(prmDto.getMailAddress1());
        // メールアドレス2
        model.setMailAdd2_txt(prmDto.getMailAddress2());
        // メールアドレス3
        model.setMailAdd3_txt(prmDto.getMailAddress3());
        // ご職業
        model.setOccupa_sel(prmDto.getOccupationCd());
        // ご利用規約に同意する
        model.setUseAgreeFlg_chk(StringUtils.equals(prmDto.getUseAgreementFlg(),
                NaikaraTalkConstants.USE_AGREEMENT_FLG_NO) ? false : true);
        // ご利用の動機1
        model.setMotiveFlg1_chk(StringUtils.equals(prmDto.getUseMotiveFlg1(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true);
        // ご利用の動機2
        model.setMotiveFlg2_chk(StringUtils.equals(prmDto.getUseMotiveFlg2(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true);
        // ご利用の動機3
        model.setMotiveFlg3_chk(StringUtils.equals(prmDto.getUseMotiveFlg3(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true);


        // ご利用の動機4
//        model.setMotiveFlg4_chk(StringUtils.equals(prmDto.getUseMotiveFlg4(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
//                : true);


        // ご利用の動機5
        model.setMotiveFlg5_chk(StringUtils.equals(prmDto.getUseMotiveFlg5(), NaikaraTalkConstants.USE_MOTIVE_FLG_NO) ? false
                : true);
        // ご利用の動機備考
        model.setMotive_txt(prmDto.getUseMotive());
        // 達成したい目標があればご記入ください
        model.setAchieve_txt(prmDto.getAchievement());
        // 個人情報の同意
        model.setIndivAgreeFlg_chk(StringUtils.equals(prmDto.getIndividualAgreementFlg(),
                NaikaraTalkConstants.INDIVIDUAL_AGREEMENT_FLG_NO) ? false : true);
        // 保護者お名前(姓)
        model.setGuardFamilyJnm_txt(prmDto.getGuardianFamilyJnm());
        // 保護者お名前(名)
        model.setGuardFirstJnm_txt(prmDto.getGuardianFirstJnm());
        // 保護者フリガナ(姓)
        model.setGuardFamilyKnm_txt(prmDto.getGuardianFamilyKnm());
        // 保護者フリガナ(名)
        model.setGuardFirstKnm_txt(prmDto.getGuardianFirstKnm());
        // あなたとの続柄
        model.setGuardRelat_txt(prmDto.getGuardianFamilyRelationship());
        // 保護者電話番号
        model.setGuardTel1_txt(prmDto.getGuardianTel1());
        // 保護者携帯電話
        model.setGuardTel2_txt(prmDto.getGuardianTel2());
        // 保護者生年月日年
        model.setGuardBirthYy_txt(prmDto.getGuardianBirthYyyy());
        // 保護者生年月日月
        model.setGuardBirthMm_txt(prmDto.getGuardianBirthMm());
        // 保護者生年月日日
        model.setGuardBirthDd_txt(prmDto.getGuardianBirthDd());
        // 保護者郵便番号
        model.setGuardZipCd_txt(prmDto.getGuardianZipCd());
        // 保護者住所(地域)
        model.setGuardArea_sel(prmDto.getGuardianAddressAreaCd());
        // 保護者住所(都道府県)
        model.setGuardprefecture_sel(prmDto.getGuardianAddressPrefectureCd());
        // 保護者住所(市区町村 等)
        model.setGuardCity_txt(prmDto.getGuardianAddressCity());
        // 保護者住所(ビル、マンション名 等)
        model.setGuardOther_txt(prmDto.getGuardianAddressOthers());
        // 保護者性別
        model.setGuardGender_rdl(prmDto.getGuardianGenderKbn());
        // 保護者メールアドレス1
        model.setGuardMailAdd1_txt(prmDto.getGuardianMailAddress1());
        // 保護者メールアドレス2
        model.setGuardMailAdd2_txt(prmDto.getGuardianMailAddress2());
        // 保護者メールアドレス3
        model.setGuardMailAdd3_txt(prmDto.getGuardianMailAddress3());
        // 顧客区分
        model.setCustomerKbn(prmDto.getCustomerKbn());
        // 組織名
        model.setOrganizationNm(prmDto.getOrganizationNm());
        // 組織ID
        model.setOrganizationId(prmDto.getOrganizationId());
        // 所属組織内ID
        model.setPositionOrganizationId(prmDto.getPositionOrganizationId());
        // 自己負担率
        model.setBurdenNum(prmDto.getBurdenNum());
        // ログイン回数
        model.setLoginNum(prmDto.getLoginNum());
        // 最終ログイン日
        model.setEndLoginDt(prmDto.getEndLoginDt());
        // 備考(生徒非公開)
        model.setRemark(prmDto.getRemark());
        // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        model.setSchoolCmt(prmDto.getSchoolCmt());
        // 排他用レコードバージョン
        model.setRecordVerNo(prmDto.getRecordVerNo());

        return model;
    }
}