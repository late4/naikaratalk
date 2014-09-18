package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherRegistrationLogic;
import com.naikara_talk.model.TeacherRegistrationModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Serviceクラス。<br>
 * <b>クラス概要       :</b>講師初期登録ページ初期処理Service。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class TeacherRegistrationLoadService implements ActionService {

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード
     * @return LinkedHashMap<String, String>
     * @throws Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws Exception {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
            // コード管理マスタ検索
            return teacherRegistrationLogic.selectCodeMst(category);
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
     * 初期表示の利用者マスタ、講師マスタから対象データの取得処理。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return model TeacherRegistrationModel
     * @throws Exception
     */
    public TeacherRegistrationModel select(TeacherRegistrationModel model) throws Exception {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
            // DTOの初期化
            UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            UserMstTeacherMstDto resultDto = teacherRegistrationLogic.select(prmDto);
            // データが存在しない場合
            if (StringUtils.isEmpty(resultDto.getUserId())) {
                model.setUserId(null);
                return model;
            }
            // ｢契約日｣＝Nullの場合
            if (StringUtils.isEmpty(resultDto.getContractDt())) {
                model.setContractDt(null);
                return model;
            }
            if (!StringUtils.isEmpty(resultDto.getNickAnm())) {
                // DTO値をModelにセット
                model = this.dtoToModel(resultDto, model);
            }

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
     * 講師単位のコースリスト取得。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return returnList TeacherCourseListDtoのリスト
     * @throws Exception
     */
    public List<TeacherCourseDto> selectTeacherCourse(TeacherRegistrationModel model) throws Exception {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
            // DTOの初期化
            UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            List<TeacherCourseDto> teacherCourseListDtoList = teacherRegistrationLogic.selectTeacherCourse(prmDto);

            // 汎用フィールド名の取得
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            // 大分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workBigList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);

            // 中分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workMidList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);

            // 小分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workSmoList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);

            // 大分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workBigListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T);

            // 中分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workMidListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T);

            // 小分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workSmoListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T);
            List<TeacherCourseDto> returnList = new ArrayList<TeacherCourseDto>();
            TeacherCourseDto retDto = new TeacherCourseDto();
            if (NaikaraTalkConstants.RETURN_CD_DATA_YES == teacherCourseListDtoList.get(0).getReturnCode()) {
                for (int i = 0; teacherCourseListDtoList.size() > i; i++) {
                    retDto = teacherCourseListDtoList.get(i);

System.out.println("i=" + i);
                    // 日本語コース名
                    retDto.setCourseJnm(NaikaraStringUtil.unionString4(
                            getName(workBigList, retDto.getBigClassificationCd()),
                            getName(workMidList, retDto.getMiddleClassificationCd()),
                            getName(workSmoList, retDto.getSmallClassificationCd()), retDto.getCourseJnm()));
                    // 英語コース名
                    retDto.setCourseEnm(NaikaraStringUtil.unionString4(
                            getName(workBigListE, retDto.getBigClassificationCd()),
                            getName(workMidListE, retDto.getMiddleClassificationCd()),
                            getName(workSmoListE, retDto.getSmallClassificationCd()), retDto.getCourseEnm()));

                    returnList.add(retDto);
                }
            }
            return returnList;
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
     * 汎用コードから汎用フィールドを抽出する<br>
     * <br>
     * @param list LinkedHashMap
     * @param managerCd String
     * @return ManagerNm String
     */
    private String getName(LinkedHashMap<String, CodeManagMstDto> list, String managerCd) {

        CodeManagMstDto dto;
        dto = list.get(managerCd);
        return dto.getManagerNm();
    }

    /**
     * 講師別支払比率マスタリスト取得。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return teacherRateMstDtoList TeacherRateMstDtoのリスト
     * @throws Exception
     */
    public List<TeacherRateMstDto> selectTeacherRate(TeacherRegistrationModel model) throws Exception {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
            // DTOの初期化
            UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            List<TeacherRateMstDto> teacherRateMstDtoList = teacherRegistrationLogic.selectTeacherRate(prmDto);

            return teacherRateMstDtoList;
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
     * Model値をDTOにセット。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return TeacherMstDto
     * @throws Exception
     */
    private UserMstTeacherMstDto modelToDto(TeacherRegistrationModel model) throws Exception {

        // DTOの初期化
        UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

        prmDto.setUserId(model.getUserId()); // 利用者ID

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * @param prmDto TeacherRegistrationModel
     * @param model UserMstTeacherMstDto
     * @return SaleGoodsMstModel
     * @throws Exception
     */
    private TeacherRegistrationModel dtoToModel(UserMstTeacherMstDto prmDto, TeacherRegistrationModel model)
            throws Exception {

        // コード管理マスタのキャッシュ読み込み
        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        // 性別
        LinkedHashMap<String, CodeManagMstDto> genderKbnListJ = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
        // 国籍
        LinkedHashMap<String, CodeManagMstDto> nationalityListJ = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);
        // 母国語
        LinkedHashMap<String, CodeManagMstDto> nativeLangListJ = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG);
        // 外国送金関係銀行手数料
        LinkedHashMap<String, CodeManagMstDto> otherRemittanceFeeListJ = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_OTHER_REMITTANCE_FEE_KBN);
        // 性別
        LinkedHashMap<String, CodeManagMstDto> genderKbnListE = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_GENDER_T);
        // 国籍
        LinkedHashMap<String, CodeManagMstDto> nationalityListE = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY_T);
        // 母国語
        LinkedHashMap<String, CodeManagMstDto> nativeLangListE = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG_T);
        // 外国送金関係銀行手数料
        LinkedHashMap<String, CodeManagMstDto> otherRemittanceFeeListE = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_OTHER_REMITTANCE_FEE_KBN_T);

        model.setUserId(prmDto.getUserId());
        model.setFamilyJnm(prmDto.getFamilyJnm());
        model.setFirstJnm(prmDto.getFirstJnm());
        model.setFamilyKnm(prmDto.getFamilyKnm());
        model.setFirstKnm(prmDto.getFirstKnm());
        model.setFamilyRomaji(prmDto.getFamilyRomaji());
        model.setFirstRomaji(prmDto.getFirstRomaji());
        model.setTel1(prmDto.getTel1());
        model.setTel2(prmDto.getTel2());
        String yyymmdd = prmDto.getBirthYyyy() + prmDto.getBirthMm() + prmDto.getBirthDd();
        model.setBirthYyyyMmDd(yyymmdd);
        model.setZipCd(prmDto.getZipCd());
        model.setAddressCity(prmDto.getAddressCity());
        model.setAddressOthers(prmDto.getAddressOthers());
        model.setGender(NaikaraStringUtil.unionString2(genderKbnListE.get(prmDto.getGenderKbn()).getManagerNm(),
                genderKbnListJ.get(prmDto.getGenderKbn()).getManagerNm()));
        model.setMailAddress1(prmDto.getMailAddress1());
        model.setMailAddress2(prmDto.getMailAddress2());
        model.setMailAddress3(prmDto.getMailAddress3());
        model.setCityTown(prmDto.getCityTown());
        model.setUserMstRecordVerNo(prmDto.getRecordVerNoU());
        model.setNickAnm(prmDto.getNickAnm());
        model.setNationality(NaikaraStringUtil.unionString2(nationalityListE.get(prmDto.getNationalityCd())
                .getManagerNm(), nationalityListJ.get(prmDto.getNationalityCd()).getManagerNm()));
        model.setNativeLang(NaikaraStringUtil.unionString2(
                nativeLangListE.get(prmDto.getNativeLangCd()).getManagerNm(),
                nativeLangListJ.get(prmDto.getNativeLangCd()).getManagerNm()));
        model.setCountry(prmDto.getCountryCd());
        model.setAreaNo(prmDto.getAreaNoCd());
        model.setContractDt(prmDto.getContractDt());
        model.setContractStartDt(prmDto.getContractStartDt());
        model.setContractEndDt(prmDto.getContractEndDt());
        model.setBankJpnBankNm(prmDto.getBankJpnBankNm());
        model.setBankJpnBranchNm(prmDto.getBankJpnBranchNm());
        model.setBankJpnTypeOfAccount(prmDto.getBankJpnTypeOfAccount());
        model.setBankJpnAccountHoldersKnm(prmDto.getBankJpnAccountHoldersKnm());
        model.setBankJpnAccountHoldersNm(prmDto.getBankJpnAccountHoldersNm());
        model.setBankJpnAccountNumber(prmDto.getBankJpnAccountNumber());
        model.setBankJpnAdditionalInfo(prmDto.getBankJpnAdditionalInfo());
        model.setJpnPbankBranchNm(prmDto.getJpnPbankBranchNm());
        model.setJpnPbankTypeOfAccount(prmDto.getJpnPbankTypeOfAccount());
        model.setJpnPbankAccountHoldersKnm(prmDto.getJpnPbankAccountHoldersKnm());
        model.setJpnPbankAccountHoldersNm(prmDto.getJpnPbankAccountHoldersNm());
        model.setJpnPbankAccountNumber(prmDto.getJpnPbankAccountNumber());
        model.setJpnPbankAdditionalInfo(prmDto.getJpnPbankAdditionalInfo());
        model.setOverseaAccountHNm(prmDto.getOverseaAccountHNm());
        model.setOverseaAccountHRAddress1(prmDto.getOverseaAccountHRAddress1());
        model.setOverseaAccountHRAddress2(prmDto.getOverseaAccountHRAddress2());
        model.setOverseaAccountNumberIban(prmDto.getOverseaAccountNumberIban());
        model.setOverseaAbanoSwiftcdBiccd(prmDto.getOverseaAbanoSwiftcdBiccd());
        model.setOverseaEtc(prmDto.getOverseaEtc());
        model.setOverseaBankNm(prmDto.getOverseaBankNm());
        model.setOverseaBranchNm(prmDto.getOverseaBranchNm());
        model.setOverseaBranchAddress1(prmDto.getOverseaBranchAddress1());
        model.setOverseaBranchAddress2(prmDto.getOverseaBranchAddress2());
        model.setOverseaCountryBranchExists(prmDto.getOverseaCountryBranchExists());
        if (!StringUtils.isEmpty(prmDto.getOtherRemittanceFeeKbn())) {
            model.setOtherRemittanceFee((NaikaraStringUtil.unionString2(
                    otherRemittanceFeeListE.get(prmDto.getOtherRemittanceFeeKbn()).getManagerNm(),
                    otherRemittanceFeeListJ.get(prmDto.getOtherRemittanceFeeKbn()).getManagerNm())));
        }
        model.setOverseaAdditionalInfo(prmDto.getOverseaAdditionalInfo());
        model.setOverseaPlPaypalAddress(prmDto.getOverseaPlPaypalAddress());
        model.setOverseaPlAdditionalInfo(prmDto.getOverseaPlAdditionalInfo());
        model.setSellingPoint(prmDto.getSellingPoint());
        model.setSelfRecommendation(prmDto.getSelfRecommendation());
        model.setImgPhotoNm(prmDto.getImgPhotoNm());
        model.setEvaluationFromSchoolCmt1(prmDto.getEvaluationFromSchoolCmt1());
        model.setLatestEvaluationFromStudentCmt(prmDto.getLatestEvaluationFromStudentCmt());
        model.setTeacherMstRecordVerNo(prmDto.getRecordVerNoT());

        return model;

    }
}
