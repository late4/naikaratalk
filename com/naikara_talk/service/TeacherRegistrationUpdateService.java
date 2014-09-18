package com.naikara_talk.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherRegistrationLogic;
import com.naikara_talk.logic.TimeZoneControlMstLogic;
import com.naikara_talk.model.TeacherRegistrationModel;
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
public class TeacherRegistrationUpdateService implements ActionService {

    /* 更新前チェック：滞在国必須入力のチェックエラー */
    public static final int ERR_COUNTRY = 1;

    /* 更新前チェック： 時差地域No必須入力のチェックエラー */
    public static final int ERR_AREA_NO = 2;

    /* 更新前チェック： ｢滞在国｣、「時差地域No」　のチェック */
    public static final int ERR_COUNTRY_AREA = 3;

    /* 更新前チェック： 画像ファイルサイズ　のチェック */
    public static final int IMGPHOTO_FIL = 4;

    /**
     * 利用者マスタ更新処理。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return cnt int
     * @throws Exception
     */
    public int update(TeacherRegistrationModel model) throws Exception {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD;
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();

            // 利用者マスタ更新処理
            cnt = this.updateUserMst(model, conn);

            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                return cnt;
            }

            // 講師マスタ更新処理
            cnt = this.updateTeacherMst(model, conn);

            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                // ロールバック
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw new NaiException(e1);
                }
                return cnt;
            }

            conn.commit();

        } catch (Exception e) {
            try {
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
        return cnt;
    }

    /**
     * 利用者マスタ更新処理。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return cnt int
     * @throws Exception
     */
    public int updateUserMst(TeacherRegistrationModel model, Connection conn) throws Exception {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD;

        TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
        // 利用者マスタDTOの初期化
        UserMstDto prmDto = new UserMstDto();
        // Model値をDTOにセット
        prmDto = this.modelToUserMstDto(model);

        // 利用者マスタ更新実行
        cnt = teacherRegistrationLogic.updateUserMstDto(prmDto);

        return cnt;

    }

    /**
     * 講師マスタ更新処理。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return cnt int
     * @throws Exception
     */
    public int updateTeacherMst(TeacherRegistrationModel model, Connection conn) throws Exception {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_UPD;

        TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
        // 講師マスタ取得DTOの初期化
        TeacherMstDto prmDto = new TeacherMstDto();
        // Model値をDTOにセット
        prmDto = this.modelToTeacherMstDto(model);

        // 講師マスタ更新実行
        cnt = teacherRegistrationLogic.updateTeacherMstDto(prmDto);

        return cnt;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return prmDto TeacherMstDto
     * @throws Exception
     */
    private UserMstDto modelToUserMstDto(TeacherRegistrationModel model) throws Exception {

        // DTOの初期化
        UserMstDto prmDto = new UserMstDto();
        prmDto.setUserId(model.getUserId());

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
            prmDto = teacherRegistrationLogic.selectUserInfo(prmDto);

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
        prmDto.setUserId(model.getUserId());
        prmDto.setTel1(model.getTel1());
        prmDto.setTel2(model.getTel2());
        prmDto.setZipCd(model.getZipCd());
        prmDto.setAddressCity(model.getAddressCity());
        prmDto.setAddressOthers(model.getAddressOthers());
        prmDto.setMailAddress1(model.getMailAddress1());
        prmDto.setMailAddress2(model.getMailAddress2());
        prmDto.setMailAddress3(model.getMailAddress3());
        prmDto.setCityTown(model.getCityTown());
        prmDto.setRecordVerNo(model.getUserMstRecordVerNo());
        prmDto.setUpdateCd(model.getUserId());

        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return prmDto TeacherMstDto
     * @throws Exception
     */
    private TeacherMstDto modelToTeacherMstDto(TeacherRegistrationModel model) throws Exception {

        // DTOの初期化
        TeacherMstDto prmDto = new TeacherMstDto();
        prmDto.setUserId(model.getUserId());
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TeacherRegistrationLogic teacherRegistrationLogic = new TeacherRegistrationLogic(conn);
            prmDto = teacherRegistrationLogic.selectTeacherInfo(prmDto);

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
        prmDto.setUserId(model.getUserId());
        prmDto.setCountryCd(model.getCountry());
        prmDto.setAreaNoCd(model.getAreaNo());
        prmDto.setSelfRecommendation(model.getSelfRecommendation().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        if (null != model.getImgPhoto()) {
            prmDto.setImgPhotoNm(model.getImgPhotoNm());
            prmDto.setImgPhotoPage(model.getImgPhoto());
        }
        prmDto.setRecordVerNo(model.getTeacherMstRecordVerNo());
        prmDto.setUpdateCd(model.getUserId());

        return prmDto;

    }

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * @param model TeacherRegistrationModel
     * @return int
     * @throws Exception
     */
    public int errorCheck(TeacherRegistrationModel model) throws Exception {

        if (model.getCountry().equals(NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            return ERR_COUNTRY;
        }
        if (model.getAreaNo().equals(NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            return ERR_AREA_NO;
        }
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            TimeManagMstDto timeManagMstDto = new TimeManagMstDto();
            timeManagMstDto.setCountryCd(model.getCountry());
            timeManagMstDto.setAreaNoCd(model.getAreaNo());

            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);

            if (timeZoneControlMstLogic.getExist(timeManagMstDto) == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                return ERR_COUNTRY_AREA;
            }

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

        if (model.getImgPhoto() != null) {
            FileInputStream fis = new FileInputStream(model.getImgPhoto());
            if (fis.available() > 500 * 1024) {
                return IMGPHOTO_FIL;
            }
        }

        return 0;
    }
}
