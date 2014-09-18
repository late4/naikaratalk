package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherMstLogic;
import com.naikara_talk.logic.TeacherRateMstLogic;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.model.TeacherMstModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)初期処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public class TeacherMstLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return TeacherMstModel<br>
     * @exception なし
     */
    public TeacherMstModel initLoad(TeacherMstModel model) {
        TeacherMstModel workModel = new TeacherMstModel();

        // 前画面から処理区分を画面にセット
        workModel.setProcessKbn_Rdl(model.getProcessKbn_Rdl());
        if (StringUtils.equals(PointControlListModel.PROS_KBN_UPD, model.getProcessKbn_Rdl())) {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_UPD);
        } else {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_REF);
        }
        List<TeacherCourseDto> teacherCourseDtoList = new ArrayList<TeacherCourseDto>();
        for (int i = 0; i < 10; i++) {
            teacherCourseDtoList.add(new TeacherCourseDto());
        }
        workModel.setTeacherCourseDtoList(teacherCourseDtoList);

        return workModel;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param UserMstTeacherMstModel  画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(TeacherMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
            // DTOの初期化
            UserMstTeacherMstDto dto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // 検索実行
            return teacherMstLogic.getExist(dto);
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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category  汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);

            // コード管理マスタ検索
            return teacherMstLogic.selectCodeMst(category);
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
     * 画面初期表示。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param TeacherMstModel  画面のパラメータ<br>
     * @return TeacherMstModel<br>
     * @exception なし
     */
    public TeacherMstModel select(TeacherMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
            // DTOの初期化
            UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            UserMstTeacherMstDto resultDto = teacherMstLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);
            model.setTeacherCourseDtoList(this.selectTeacherCourse(model));
            if (!StringUtils.isEmpty(model.getUserIdT())) {
                List<TeacherRateMstDto> tempList = this.selectList(model);
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO != tempList.get(0).getReturnCode()) {
                    model.setTeacherRateMstDtoList(tempList);
                }
            }

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
     * 講師別コースマスタ(+コースマスタ)取得処理。<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)取得処理。<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return List<TeacherCourseDto><br>
     * @exception NaiException
     */
    private List<TeacherCourseDto> selectTeacherCourse(TeacherMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
            TeacherCourseDto dto = new TeacherCourseDto();
            dto.setUserId(model.getUserId());

            List<TeacherCourseDto> teacherCourseDtoList = teacherMstLogic.getTeacherCourseDtos(dto);
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == teacherCourseDtoList.get(0).getReturnCode()) {
                teacherCourseDtoList.clear();
            }
            if (teacherCourseDtoList.size() < 10) {
                for (int i = teacherCourseDtoList.size(); i < 10; i++) {
                    teacherCourseDtoList.add(new TeacherCourseDto());
                }
            }
            return teacherCourseDtoList;
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
     * 講師支払比率取得処理。<br>
     * <br>
     * 講師支払比率取得処理。<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return List<TeacherRateMstDto><br>
     * @exception NaiException
     */
    public List<TeacherRateMstDto> selectList(TeacherMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            TeacherRateMstLogic teacherRateMstLogic = new TeacherRateMstLogic(conn);

            // DTOの初期化
            TeacherRateMstDto dto = new TeacherRateMstDto();

            // 講師ID (利用者ID)
            dto.setUserId(model.getUserIdT());

            // データの取得＆リターン
            return teacherRateMstLogic.select(dto);
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
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return UserMstTeacherMstDto<br>
     * @exception なし
     */
    private UserMstTeacherMstDto modelToDto(TeacherMstModel model) {

        // DTOの初期化
        UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

        prmDto.setUserId(model.getUserId());           // 利用者ID

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param UserMstTeacherMstDto<br>
     * @return TeacherMstModel<br>
     * @exception なし
     */
    private TeacherMstModel dtoToModel(UserMstTeacherMstDto prmDto, TeacherMstModel model) {

        model.setUserId(prmDto.getUserId());                                                    // 利用者ID
        model.setFamilyJnm(prmDto.getFamilyJnm());                                              // 名前(姓)
        model.setFirstJnm(prmDto.getFirstJnm());                                                // 名前(名)
        model.setFamilyKnm(prmDto.getFamilyKnm());                                              // フリガナ(姓)
        model.setFirstKnm(prmDto.getFirstKnm());                                                // フリガナ(名）
        model.setFamilyRomaji(prmDto.getFamilyRomaji());                                        // ローマ字(姓)
        model.setFirstRomaji(prmDto.getFirstRomaji());                                          // ローマ字(名)
        model.setNickAnm(prmDto.getNickAnm());                                                  // ニックネーム
        model.setTel1(prmDto.getTel1());                                                        // 電話番号1
        model.setTel2(prmDto.getTel2());                                                        // 電話番号2
        model.setBirthYyyy(prmDto.getBirthYyyy());                                              // 生年月日：年
        model.setBirthMm(prmDto.getBirthMm());                                                  // 生年月日：月
        model.setBirthDd(prmDto.getBirthDd());                                                  // 生年月日：日
        model.setZipCd(prmDto.getZipCd());                                                      // 郵便番号
        model.setAddressCity(prmDto.getAddressCity());                                          // 住所(市区町村)
        model.setAddressOthers(prmDto.getAddressOthers());                                      // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        model.setGenderKbn(prmDto.getGenderKbn());                                              // 性別区分
        model.setNationalityCd(prmDto.getNationalityCd());                                      // 国籍コード
        model.setNativeLangCd(prmDto.getNativeLangCd());                                        // 母国語コード
        model.setMailAddress1(prmDto.getMailAddress1());                                        // メールアドレス1
        model.setMailAddress2(prmDto.getMailAddress2());                                        // メールアドレス2
        model.setMailAddress3(prmDto.getMailAddress3());                                        // メールアドレス3
        model.setCountryCd(prmDto.getCountryCd());                                              // 滞在国コード
        model.setAreaNoCd(prmDto.getAreaNoCd());                                                // 時差地域NOコード
        model.setCityTown(prmDto.getCityTown());                                                // 勤務拠点
        model.setContractDt(prmDto.getContractDt());                                            // 契約日
        model.setContractStartDt(prmDto.getContractStartDt());                                  // 契約開始日
        model.setContractEndDt(prmDto.getContractEndDt());                                      // 契約終了日
        model.setBankJpnBankNm(prmDto.getBankJpnBankNm());                                      // 国内銀行向け送金の場合：銀行名
        model.setBankJpnBranchNm(prmDto.getBankJpnBranchNm());                                  // 国内銀行向け送金の場合：支店名
        model.setBankJpnTypeOfAccount(prmDto.getBankJpnTypeOfAccount());                        // 国内銀行向け送金の場合：預金種別
        model.setBankJpnAccountHoldersKnm(prmDto.getBankJpnAccountHoldersKnm());                // 国内銀行向け送金の場合：口座名義人（フリガナ）
        model.setBankJpnAccountHoldersNm(prmDto.getBankJpnAccountHoldersNm());                  // 国内銀行向け送金の場合：口座名義人
        model.setBankJpnAccountNumber(prmDto.getBankJpnAccountNumber());                        // 国内銀行向け送金の場合：口座番号
        model.setBankJpnAdditionalInfo(prmDto.getBankJpnAdditionalInfo());                      // 国内銀行向け送金の場合：追加情報
        model.setJpnPbankBranchNm(prmDto.getJpnPbankBranchNm());                                // 国内ゆうちょ銀行向け送金の場合:店番
        model.setJpnPbankTypeOfAccount(prmDto.getJpnPbankTypeOfAccount());                      // 国内ゆうちょ銀行向け送金の場合:預金種別
        model.setJpnPbankAccountHoldersKnm(prmDto.getJpnPbankAccountHoldersKnm());              // 国内ゆうちょ銀行向け送金の場合:口座名義人（フリガナ）
        model.setJpnPbankAccountHoldersNm(prmDto.getJpnPbankAccountHoldersNm());                // 国内ゆうちょ銀行向け送金の場合:口座名義人
        model.setJpnPbankAccountNumber(prmDto.getJpnPbankAccountNumber());                      // 国内ゆうちょ銀行向け送金の場合:口座番号
        model.setJpnPbankAdditionalInfo(prmDto.getJpnPbankAdditionalInfo());                    // 国内ゆうちょ銀行向け送金の場合:追加情報
        model.setOverseaAccountHNm(prmDto.getOverseaAccountHNm());                              // 海外送金の場合:口座名義人
        model.setOverseaAccountHRAddress1(prmDto.getOverseaAccountHRAddress1());                // 海外送金の場合:口座名義人登録住所
        model.setOverseaAccountHRAddress2(prmDto.getOverseaAccountHRAddress2());                // 海外送金の場合:口座名義人登録住所（上記住所欄が一杯のとき）
        model.setOverseaAccountNumberIban(prmDto.getOverseaAccountNumberIban());                // 海外送金の場合:口座番号/IBAN（ヨーロッパ）
        model.setOverseaAbanoSwiftcdBiccd(prmDto.getOverseaAbanoSwiftcdBiccd());                // 海外送金の場合:ABAナンバー/SWIFTコード/BIC　Code　等
        model.setOverseaEtc(prmDto.getOverseaEtc());                                            // 海外送金の場合:等
        model.setOverseaBankNm(prmDto.getOverseaBankNm());                                      // 海外送金の場合:銀行名
        model.setOverseaBranchNm(prmDto.getOverseaBranchNm());                                  // 海外送金の場合:支店名
        model.setOverseaBranchAddress1(prmDto.getOverseaBranchAddress1());                      // 海外送金の場合:支店住所
        model.setOverseaBranchAddress2(prmDto.getOverseaBranchAddress2());                      // 海外送金の場合:支店住所（上記住所欄が一杯のとき）
        model.setOverseaCountryBranchExists(prmDto.getOverseaCountryBranchExists());            // 海外送金の場合:支店が所在する国名
        model.setOtherRemittanceFeeKbn(prmDto.getOtherRemittanceFeeKbn());                      // 外国送金関係銀行手数料区分
        model.setOverseaAdditionalInfo(prmDto.getOverseaAdditionalInfo());                      // 海外送金の場合:追加情報
        model.setOverseaPlPaypalAddress(prmDto.getOverseaPlPaypalAddress());                    // 海外ペイパル送金の場合:PayPalアドレス
        model.setOverseaPlAdditionalInfo(prmDto.getOverseaPlAdditionalInfo());                  // 海外ペイパル送金の場合:追加情報

        model.setSellingPoint(prmDto.getSellingPoint());                                        // セールスポイント(スクール記入)
        model.setSelfRecommendation(prmDto.getSelfRecommendation());                            // 受講生へのアピールポイント
        if (!StringUtils.equals(NaikaraTalkConstants.IMG_NODATA, prmDto.getImgPhotoNm())) {
            model.setTeacherFileName(prmDto.getImgPhotoNm());                                   // 講師画像名
        }

        model.setEvaluationFromSchoolCmt1(prmDto.getEvaluationFromSchoolCmt1());                // スクール側からのコメント(講師公開)
        model.setEvaluationFromSchoolCmt2(prmDto.getEvaluationFromSchoolCmt2());                // スクール側からのコメント(講師非公開)
        model.setRemarkT(prmDto.getRemarkT());                                                  // 備考(講師は見えません)
        model.setLatestEvaluationFromStudentCmt(prmDto.getLatestEvaluationFromStudentCmt());    // 最新の受講生から講師へのコメント
        model.setUseStartDt(prmDto.getUseStartDt());                                            // 利用開始日
        model.setUseEndDt(prmDto.getUseEndDt());                                                // 利用終了日
        model.setUserIdT(prmDto.getUserIdT());                                                  // 講師利用者ID
        model.setRecordVerNoU(prmDto.getRecordVerNoU());                                        // 利用者レコードバージョン番号
        model.setRecordVerNoT(prmDto.getRecordVerNoT());                                        // 講師レコードバージョン番号

        return model;

    }

}
