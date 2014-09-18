package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.AccountModel;
import com.naikara_talk.service.AccountLoadService;
import com.naikara_talk.sessiondata.SessionAccount;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様_初期処理。<br>
 * <b>クラス名称　　　:</b>アカウント取得処理初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>アカウント取得処理初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/08 TECS 新規作成。
 *                     </b>2014/01/03 TECS 変更 スクールのメール送信・受信履歴照会に伴う対応
 */
public class AccountLoadAction extends AccountActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    @SkipValidation
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // ユーザIDを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            this.userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
        }

        if (StringUtils.isEmpty(this.userId)) {

            try {

                this.addActionMessage(getMessage("EC0064", new String[] {}));
                this.customerKbn = NaikaraTalkConstants.CUSTOMER_KBN_PERSON;

            } catch (Exception e) {

                throw new NaiException(e);
            }

            return SUCCESS;
        }

        // 画面のパラメータセット
        setupModel();

        try {

            initRadio();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // サービスの初期化
        AccountLoadService service = new AccountLoadService();

        try {

            this.model.setStudentId_lbl(this.userId);

            int cnt = service.isExists(this.model);

            // データが存在しない場合
            if (cnt == NaikaraTalkConstants.RETURN_CD_DATA_NO) {

                this.message = getMessage("EC0020", new String[] {});

                removeLatestActionList();

                return ERROR;

            } else {

                // データが存在する場合
                // 表示データの取得処理
                this.load();

                return SUCCESS;

            }
        } catch (Exception e) {

            throw new NaiException(e);

        }
    }

    /**
     * データ取得<br>
     * <br>
     * 初期処理、表示データの取得<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void load() throws Exception {

        // サービスの初期化
        AccountLoadService service = new AccountLoadService();

        // データを取得
        this.model = service.select(this.model);

        // 受講者ID
        this.studentId_lbl = this.model.getStudentId_lbl();
        // 受講者所属部署
        this.studentPosi_txt = this.model.getStudentPosi_txt();
        // パスワード
        this.password_txt = this.model.getPassword_txt();
        // パスワード確認
        this.passwordConf_txt = this.model.getPasswordConf_txt();
        // お名前(姓)
        this.familyJnm_txt = this.model.getFamilyJnm_txt();
        // お名前(名)
        this.firstJnm_txt = this.model.getFirstJnm_txt();
        // フリガナ(姓)
        this.familyKnm_txt = this.model.getFamilyKnm_txt();
        // フリガナ(名)
        this.firstKnm_txt = this.model.getFirstKnm_txt();
        // ローマ字(姓)
        this.familyRomaji_txt = this.model.getFamilyRomaji_txt();
        // ローマ字(名)
        this.firstRomaji_txt = this.model.getFirstRomaji_txt();
        // ニックネーム
        this.nickNm_txt = this.model.getNickNm_txt();
        // 電話番号
        this.tel1_txt = this.model.getTel1_txt();
        // 携帯電話
        this.tel2_txt = this.model.getTel2_txt();
        // 生年月日年
        this.birthYy_txt = this.model.getBirthYy_txt();
        // 生年月日月
        this.birthMm_txt = this.model.getBirthMm_txt();
        // 生年月日日
        this.birthDd_txt = this.model.getBirthDd_txt();
        // 郵便番号
        this.zipCd_txt = this.model.getZipCd_txt();
        // 住所(地域)
        this.area_sel = this.model.getArea_sel();
        // 住所(都道府県)
        this.prefecture_sel = this.model.getPrefecture_sel();
        // 住所(市区町村 等)
        this.city_txt = this.model.getCity_txt();
        // 住所(ビル、マンション名)
        this.other_txt = this.model.getOther_txt();
        // 性別
        this.gender_rdl = this.model.getGender_rdl();
        // メールアドレス1
        this.mailAdd1_txt = this.model.getMailAdd1_txt();
        // メールアドレス2
        this.mailAdd2_txt = this.model.getMailAdd2_txt();
        // メールアドレス3
        this.mailAdd3_txt = this.model.getMailAdd3_txt();
        // ご職業
        this.occupa_sel = this.model.getOccupa_sel();
        // ご利用規約に同意する
        this.useAgreeFlg_chk = this.model.getUseAgreeFlg_chk();
        // ご利用の動機1
        this.motiveFlg1_chk = this.model.getMotiveFlg1_chk();
        // ご利用の動機2
        this.motiveFlg2_chk = this.model.getMotiveFlg2_chk();
        // ご利用の動機3
        this.motiveFlg3_chk = this.model.getMotiveFlg3_chk();
        // ご利用の動機4
//        this.motiveFlg4_chk = this.model.getMotiveFlg4_chk();
        // ご利用の動機5
        this.motiveFlg5_chk = this.model.getMotiveFlg5_chk();
        // ご利用の動機備考
        this.motive_txt = this.model.getMotive_txt();
        // 達成したい目標があればご記入ください
        this.achieve_txt = this.model.getAchieve_txt();
        // 個人情報の同意
        this.indivAgreeFlg_chk = this.model.getIndivAgreeFlg_chk();
        // 保護者お名前(姓)
        this.guardFamilyJnm_txt = this.model.getGuardFamilyJnm_txt();
        // 保護者お名前(名)
        this.guardFirstJnm_txt = this.model.getGuardFirstJnm_txt();
        // 保護者フリガナ(姓)
        this.guardFamilyKnm_txt = this.model.getGuardFamilyKnm_txt();
        // 保護者フリガナ(名)
        this.guardFirstKnm_txt = this.model.getGuardFirstKnm_txt();
        // あなたとの続柄
        this.guardRelat_txt = this.model.getGuardRelat_txt();
        // 保護者電話番号
        this.guardTel1_txt = this.model.getGuardTel1_txt();
        // 保護者携帯電話
        this.guardTel2_txt = this.model.getGuardTel2_txt();
        // 保護者生年月日年
        this.guardBirthYy_txt = this.model.getGuardBirthYy_txt();
        // 保護者生年月日月
        this.guardBirthMm_txt = this.model.getGuardBirthMm_txt();
        // 保護者生年月日日
        this.guardBirthDd_txt = this.model.getGuardBirthDd_txt();
        // 保護者郵便番号
        this.guardZipCd_txt = this.model.getGuardZipCd_txt();
        // 保護者住所(地域)
        this.guardArea_sel = this.model.getGuardArea_sel();
        // 保護者住所(都道府県)
        this.guardprefecture_sel = this.model.getGuardprefecture_sel();
        // 保護者住所(市区町村 等)
        this.guardCity_txt = this.model.getGuardCity_txt();
        // 保護者住所(ビル、マンション名 等)
        this.guardOther_txt = this.model.getGuardOther_txt();
        // 保護者性別
        this.guardGender_rdl = this.model.getGuardGender_rdl();
        // 保護者メールアドレス1
        this.guardMailAdd1_txt = this.model.getGuardMailAdd1_txt();
        // 保護者メールアドレス2
        this.guardMailAdd2_txt = this.model.getGuardMailAdd2_txt();
        // 保護者メールアドレス3
        this.guardMailAdd3_txt = this.model.getGuardMailAdd3_txt();
        // 顧客区分
        this.customerKbn = this.model.getCustomerKbn();
        // 組織名
        this.organizationNm = this.model.getOrganizationNm();
        // 組織ID
        this.organizationId = this.model.getOrganizationId();
        // 所属組織内ID
        this.positionOrganizationId = this.model.getPositionOrganizationId();
        // 自己負担率
        this.burdenNum = this.model.getBurdenNum();
        // ログイン回数
        this.loginNum = this.model.getLoginNum();
        // 最終ログイン日
        this.endLoginDt = this.model.getEndLoginDt();
        // 備考(生徒非公開)
        this.remark = this.model.getRemark();
        // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        this.schoolCmt = this.model.getSchoolCmt();
        // 排他用レコードバージョン
        this.recordVerNo = String.valueOf(this.model.getRecordVerNo());


        // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
        // 変更されたか比較するために初期表示時の値を格納
        SessionAccount sessionData = new SessionAccount();
        // Model値をsessionDataにセット
        sessionData = this.ModelToSessionData(model, sessionData);
        SessionDataUtil.setSessionData(sessionData);
        // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


    }


    // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
    /**
     * model値をSessionAccountへセット<br>
     * <br>
     * model値をSessionAccountへセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @param prm 戻り値を格納するSessionAccount<br>
     * @return prm 戻り値を格納するSessionAccount<br>
     * @exception NaiException
     */
    private SessionAccount ModelToSessionData(AccountModel model, SessionAccount prm) throws NaiException {

        // 受講者ID
        prm.setStudentId(model.getStudentId_lbl());
        // パスワード
        prm.setPassword(model.getPassword_txt());
        // お名前(姓)
        prm.setFamilyJnm(model.getFamilyJnm_txt());
        // お名前(名)
        prm.setFirstJnm(model.getFirstJnm_txt());
        // フリガナ(姓)
        prm.setFamilyKnm(model.getFamilyKnm_txt());
        // フリガナ(名)
        prm.setFirstKnm(model.getFirstKnm_txt());
        // ローマ字(姓)
        prm.setFamilyRomaji(model.getFamilyRomaji_txt());
        // ローマ字(名)
        prm.setFirstRomaji(model.getFirstRomaji_txt());
        // ニックネーム
        prm.setNickNm(model.getNickNm_txt());
        // 電話番号
        prm.setTel1(model.getTel1_txt());
        // 携帯電話
        prm.setTel2(model.getTel2_txt());
        // 生年月日年
        prm.setBirthYyyy(model.getBirthYy_txt());
        // 生年月日月
        prm.setBirthMm(model.getBirthMm_txt());
        // 生年月日日
        prm.setBirthDd(model.getBirthDd_txt());
        // 郵便番号
        prm.setZipCd(model.getZipCd_txt());
        // 住所(地域)コード
        prm.setAddressAreaCd(model.getArea_sel());
        // 住所(都道府県)コード
        prm.setAddressPrefectureCd(model.getPrefecture_sel());
        // 住所(市区町村 等)
        prm.setAddressCity(model.getCity_txt());
        // 住所(ビル、マンション名)
        prm.setAddressOthers(model.getOther_txt());
        // 性別区分
        prm.setGenderKbn(model.getGender_rdl());
        // メールアドレス1
        prm.setMailAddress1(model.getMailAdd1_txt());
        // メールアドレス2
        prm.setMailAddress2(model.getMailAdd2_txt());
        // メールアドレス3
        prm.setMailAddress3(model.getMailAdd3_txt());

        // ###保護者###
        // 保護者:お名前(姓)
        prm.setGuardianFamilyJnm(model.getGuardFamilyJnm_txt());
        // 保護者:お名前(名)
        prm.setGuardianFirstJnm(model.getGuardFirstJnm_txt());
        // 保護者:フリガナ(姓)
        prm.setGuardianFamilyKnm(model.getGuardFamilyKnm_txt());
        // 保護者:フリガナ(名)
        prm.setGuardianFirstKnm(model.getGuardFirstKnm_txt());
        // あなたとの続柄
        prm.setGuardianFamilyRelationship(model.getGuardRelat_txt());
        // 保護者:電話番号
        prm.setGuardianTel1(model.getGuardTel1_txt());
        // 保護者:携帯電話
        prm.setGuardianTel2(model.getGuardTel2_txt());
        // 保護者:生年月日年
        prm.setGuardianBirthYyyy(model.getGuardBirthYy_txt());
        // 保護者:生年月日月
        prm.setGuardianBirthMm(model.getGuardBirthMm_txt());
        // 保護者生年月日日
        prm.setGuardianBirthDd(model.getGuardBirthDd_txt());
        // 保護者:郵便番号
        prm.setGuardianZipCd(model.getGuardZipCd_txt());
        // 保護者:住所(地域)コード
        prm.setGuardianAddressAreaCd(model.getGuardArea_sel());
        // 保護者:住所(都道府県)コード
        prm.setGuardianAddressPrefectureCd(model.getGuardprefecture_sel());
        // 保護者:住所(市区町村 等)
        prm.setGuardianAddressCity(model.getGuardCity_txt());
        // 保護者:住所(ビル、マンション名 等)
        prm.setGuardianAddressOthers(model.getGuardOther_txt());
        // 保護者:性別区分
        prm.setGuardianGenderKbn(model.getGuardGender_rdl());
        // 保護者:メールアドレス1
        prm.setGuardianMailAddress1(model.getGuardMailAdd1_txt());
        // 保護者:メールアドレス2
        prm.setGuardianMailAddress2(model.getGuardMailAdd2_txt());
        // 保護者:メールアドレス3
        prm.setGuardianMailAddress3(model.getGuardMailAdd3_txt());

        // 排他用レコードバージョン
        prm.setRecordVerNo(model.getRecordVerNo());

        return prm;
    }
    // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


}