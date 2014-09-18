package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.logic.OrganizationContractMstLogic;
import com.naikara_talk.logic.PasswordChkLogic;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.model.OrganizationContractMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織契約情報登録。<br>
 * <b>クラス名称　　　:</b>組織契約情報登録の登録、更新、削除Serviceクラス。<br>
 * <b>クラス概要　　　:</b>組織契約情報登録の登録、更新、削除Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class OrganizationContractMstUpdateService implements ActionService {

    /** 更新前チェック：住所（都道府県）必須入力のチェックエラー */
    public static final int ERR_ADDRESS_PREFECTURE = 1;

    /** 更新前チェック： パスワードの複雑性のチェックエラー */
    public static final int ERR_PASSWORD_NG = 3;

    /** 更新前チェック： パスワードとパスワード確認の同一チェック */
    public static final int ERR_COUNTRY_COMP = 4;

    /** 更新前チェック： 日付の整合性チェック */
    public static final int ERR_DATE = 5;

    /** 更新前チェック： データの存在チェック */
    public static final int ERR_DATA_NONE = 6;

    /** 更新前チェック： 受講者マスタの存在チェックを行う　※処理区分[削除]の場合 */
    public static final int ERR_STUDENT_DATA = 7;

    /** 検索条件区分: 現在のデータの1つ前の情報の取得用 */
    public static final String SEARCH_KYE_ONE = "1";

    /** 検索条件区分: 現在のデータの1つ後の情報の取得 */
    public static final String SEARCH_KYE_TWO = "2";

    /** 検索条件区分: 組織ID単位での情報の取得 */
    public static final String SEARCH_KYE_THREE = "3";

    /** 組織ＩＤと連番のデータ有フラグ＝ＯＮとする */
    public static final String DATA_FLG_ON = "ON";

    /** 組織ＩＤと連番のデータ有フラグ＝ＯＦＦとする */
    public static final String DATA_FLG_OFF = "OFF";

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * @param OrganizationContractMstModel model
     * @return int
     * @throws Exception
     */
    public int errorCheck(OrganizationContractMstModel model) throws Exception {

        // ドロップダウンリストの必須チェック
        if (model.getAddressPrefectureCd().equals(NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            return ERR_ADDRESS_PREFECTURE;
        }

        // パスワードの複雑性のチェック
        PasswordChkLogic passwordChkLogic = new PasswordChkLogic();
        if (passwordChkLogic.passwordCheck(model.getPassword()) == -1) {
            return ERR_PASSWORD_NG;
        }

        // パスワードとパスワード確認の同一チェック
        if (!model.getPassword().equals(model.getPasswordCheck())) {
            return ERR_COUNTRY_COMP;
        }

        // 日付の整合性チェック
        if (DateUtil.dateCompare2(model.getContractStrDt(), model.getContractEndDt())) {
            return ERR_DATE;
        }

        Connection conn = null;

        // 入力チェック - DBアクセスありチェック
        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstLogic logic = new OrganizationContractMstLogic(conn);

            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // 組織ID
            prmDto.setOrganizationId(model.getOrganizationId());
            // 連番
            prmDto.setConsSeq(model.getConsSeq());
            if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_UPD)) {
                // 契約終了日
                prmDto.setContractEndDt(model.getContractEndDt());
            }

            // データの存在チェック
            if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_UPD)
                    || model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_DEL)) {

                // 組織マスタ検索実行
                OrganizationMstDto resultDto = logic.selectData(prmDto, model.getProcessKbn_rdl(),
                        OrganizationContractMstLoadService.SEARCH_KYE_ZERO).get(0);
                if (resultDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                    return ERR_DATA_NONE;

                }
            }

            // 受講者マスタの存在チェック
            String dataFlg;
            if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_DEL)) {
                // 組織マスタ検索実行
                List<OrganizationMstDto> resultDtoList = logic.selectData(prmDto, model.getProcessKbn_rdl(),
                        SEARCH_KYE_ONE);
                if (resultDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    dataFlg = DATA_FLG_ON;
                } else {
                    dataFlg = DATA_FLG_OFF;
                }
                // 受講者マスタデータが存在するかどうか
                List<StudentMstDto> studentMstDtoList = searchStudentMst(model);
                if (studentMstDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    if (dataFlg.equals(DATA_FLG_OFF)) {
                        return ERR_STUDENT_DATA;
                    }
                }
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

        return 0;
    }

    /**
     * 期間のチェック。<br>
     * <br>
     * @param OrganizationContractMstModel model
     * @return String[]
     * @throws Exception
     */
    public String[] errorDateCheck(OrganizationContractMstModel model) throws Exception {
        String[] msg = { "", "" };
        Connection conn = null;

        // 入力チェック - DBアクセスありチェック
        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstLogic logic = new OrganizationContractMstLogic(conn);

            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // 組織ID
            prmDto.setOrganizationId(model.getOrganizationId());
            // 連番
            prmDto.setConsSeq(model.getConsSeq());
            if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_UPD)) {
                // 契約終了日
                prmDto.setContractEndDt(model.getContractEndDt());
            }

            if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_MAKE)) {

                // 組織マスタ検索実行
                List<OrganizationMstDto> resultDtoList = logic.selectData(prmDto, model.getProcessKbn_rdl(),
                        SEARCH_KYE_THREE);

                if (resultDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    // 契約終了日比較
                    if (resultDtoList != null && resultDtoList.size() > 0) {
                        prmDto.setConsSeq(resultDtoList.get(0).getConsSeq());
                        for (OrganizationMstDto dto : resultDtoList) {
                            if (dto.getConsSeq() > prmDto.getConsSeq()) {
                                prmDto.setConsSeq(dto.getConsSeq());
                            }
                        }
                    }
                }
                prmDto.setConsSeq(prmDto.getConsSeq() + 1);
                // 組織マスタ検索実行
                List<OrganizationMstDto> resultDtoListForEnd = logic.selectData(prmDto, model.getProcessKbn_rdl(),
                        SEARCH_KYE_ONE);

                // ｢契約終了日｣の最大値
                String maxContractEndDt = NaikaraTalkConstants.BRANK;
                if (resultDtoListForEnd.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    // 契約終了日比較
                    if (resultDtoListForEnd != null && resultDtoListForEnd.size() > 0) {
                        maxContractEndDt = resultDtoListForEnd.get(0).getContractEndDt();
                        for (OrganizationMstDto dto : resultDtoListForEnd) {
                            if (DateUtil.dateCompare2(dto.getContractEndDt(), maxContractEndDt)) {
                                maxContractEndDt = dto.getContractEndDt();
                            }
                        }
                    }
                } else {
                    maxContractEndDt = NaikaraStringUtil.converToYYYYMMDD(NaikaraTalkConstants.MIN_END_DT);
                }

                // 組織マスタ検索実行
                List<OrganizationMstDto> resultDtoListForStart = logic.selectData(prmDto, model.getProcessKbn_rdl(),
                        SEARCH_KYE_TWO);
                // ｢契約開始日｣の最小値
                String minContractStrDt = NaikaraTalkConstants.BRANK;
                if (resultDtoListForStart.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    // 契約開始日比較
                    if (resultDtoListForStart != null && resultDtoListForStart.size() > 0) {
                        minContractStrDt = resultDtoListForStart.get(0).getContractStrDt();
                        for (OrganizationMstDto dto : resultDtoListForStart) {
                            if (DateUtil.dateCompare2(minContractStrDt, dto.getContractStrDt())) {
                                minContractStrDt = dto.getContractStrDt();
                            }
                        }
                    }
                } else {
                    minContractStrDt = NaikaraStringUtil.converToYYYYMMDD(NaikaraTalkConstants.MAX_END_DT);
                }

                if (DateUtil.dateCompare2(minContractStrDt, model.getContractEndDt())
                        && DateUtil.dateCompare3(model.getContractEndDt(), model.getContractStrDt())
                        && DateUtil.dateCompare2(model.getContractStrDt(), maxContractEndDt)) {

                } else {
                    msg[0] = maxContractEndDt;
                    msg[1] = minContractStrDt;
                    return msg;
                }
            }

            if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_UPD)) {
                // 組織マスタ検索実行
                List<OrganizationMstDto> resultDtoListForEnd = logic.selectData(prmDto, model.getProcessKbn_rdl(),
                        SEARCH_KYE_ONE);

                // ｢契約終了日｣の最大値
                String maxContractEndDt = NaikaraTalkConstants.BRANK;
                if (resultDtoListForEnd.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    // 契約終了日比較
                    if (resultDtoListForEnd != null && resultDtoListForEnd.size() > 0) {
                        maxContractEndDt = resultDtoListForEnd.get(0).getContractEndDt();
                        for (OrganizationMstDto dto : resultDtoListForEnd) {
                            if (DateUtil.dateCompare2(dto.getContractEndDt(), maxContractEndDt)) {
                                maxContractEndDt = dto.getContractEndDt();
                            }
                        }
                    }
                } else {
                    maxContractEndDt = NaikaraStringUtil.converToYYYYMMDD(NaikaraTalkConstants.MIN_END_DT);
                }

                // 組織マスタ検索実行
                List<OrganizationMstDto> resultDtoListForStart = logic.selectData(prmDto, model.getProcessKbn_rdl(),
                        SEARCH_KYE_TWO);
                // ｢契約開始日｣の最小値
                String minContractStrDt = NaikaraTalkConstants.BRANK;
                if (resultDtoListForStart.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    // 契約開始日比較
                    if (resultDtoListForStart != null && resultDtoListForStart.size() > 0) {
                        minContractStrDt = resultDtoListForStart.get(0).getContractStrDt();
                        for (OrganizationMstDto dto : resultDtoListForStart) {
                            if (DateUtil.dateCompare2(minContractStrDt, dto.getContractStrDt())) {
                                minContractStrDt = dto.getContractStrDt();
                            }
                        }
                    }
                } else {
                    minContractStrDt = NaikaraStringUtil.converToYYYYMMDD(NaikaraTalkConstants.MAX_END_DT);
                }

                if (DateUtil.dateCompare2(minContractStrDt, model.getContractEndDt())
                        && DateUtil.dateCompare3(model.getContractEndDt(), model.getContractStrDt())
                        && DateUtil.dateCompare2(model.getContractStrDt(), maxContractEndDt)) {

                } else {
                    msg[0] = maxContractEndDt;
                    msg[1] = minContractStrDt;
                    return msg;
                }
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
        return msg;
    }

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * @param OrganizationContractMstModel model
     * @return String
     * @throws Exception
     */
    public String requiredstringCheck(OrganizationContractMstModel model) throws Exception {
        if (StringUtils.isEmpty(model.getRequestOrganizationJnm())) {
            return "請求先情報：組織名";
        }
        if (StringUtils.isEmpty(model.getRequestOrganizationKnm())) {
            return "請求先情報：組織名(カナ)";
        }
        if (StringUtils.isEmpty(model.getRequestOrganizationRnm())) {
            return "請求先情報：組織名(ローマ字)";
        }
        if (StringUtils.isEmpty(model.getRequestTel())) {
            return "請求先情報：電話番号";
        }
        if (StringUtils.isEmpty(model.getRequestZipCd())) {
            return "請求先情報：郵便番号";
        }
        if (model.getRequestAddressPrefectureCd().equals(NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
            return "請求先　住所（都道府県）";
        }
        if (StringUtils.isEmpty(model.getRequestAddressCity())) {
            return "請求先情報：住所(市区町村)";
        }
        if (StringUtils.isEmpty(model.getRequestManagPosition())) {
            return "請求先情報：責任者所属";
        }
        if (StringUtils.isEmpty(model.getRequestManagFamilyJnm())) {
            return "請求先情報：責任者名(姓)";
        }
        if (StringUtils.isEmpty(model.getRequestManagFirstJnm())) {
            return "請求先情報：責任者名(名)";
        }
        if (StringUtils.isEmpty(model.getRequestManagFamilyKnm())) {
            return "請求先情報：責任者名フリガナ(姓)";
        }
        if (StringUtils.isEmpty(model.getRequestManagFirstKnm())) {
            return "請求先情報：責任者名フリガナ(名)";
        }
        if (StringUtils.isEmpty(model.getRequestManagFamilyRomaji())) {
            return "請求先情報：責任者名ローマ字(姓)";
        }
        if (StringUtils.isEmpty(model.getRequestManagFirstRomaji())) {
            return "請求先情報：責任者名ローマ字(名)";
        }
        return "";

    }

    /**
     * 登録処理。<br>
     * <br>
     * @param OrganizationContractMstModel 画面のパラメータ
     * @return なし
     * @throws Exception
     */
    public int insert(OrganizationContractMstModel model) throws Exception {
        int cnt = 0;
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstLogic organizationContractMstLogic = new OrganizationContractMstLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);
            // 受講者IDの自動採番処理
            OrderNumbersMstLogic orderNumbersMstLogic = new OrderNumbersMstLogic();
            OrderNumberDto orderNumberDto = orderNumbersMstLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_BU,
                    DateUtil.getSysDate());
            model.setOrganizationId(orderNumberDto.getOrderNumber());
            prmDto.setOrganizationId(orderNumberDto.getOrderNumber());
            model.setConsSeq(1);
            prmDto.setConsSeq(1);
            prmDto.setInsertCd(model.getUpdateCd());
            // 登録実行
            cnt = organizationContractMstLogic.insert(prmDto);

            conn.commit();
            return cnt;

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

    }

    /**
     * 流用作成処理。<br>
     * <br>
     * @param OrganizationContractMstModel 画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int updateWithConsSeq(OrganizationContractMstModel model) throws Exception {

        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD;

        Connection conn = null;
        try {

            conn = DbUtil.getConnection();

            OrganizationContractMstLogic organizationContractMstLogic = new OrganizationContractMstLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);
            prmDto.setInsertCd(model.getUpdateCd());

            // 組織マスタ検索実行
            List<OrganizationMstDto> resultDtoList = organizationContractMstLogic.selectData(prmDto,
                    model.getProcessKbn_rdl(), SEARCH_KYE_THREE);

            if (resultDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // 契約終了日比較
                if (resultDtoList != null && resultDtoList.size() > 0) {
                    prmDto.setConsSeq(resultDtoList.get(resultDtoList.size() - 1).getConsSeq() + 1);
                }
            } else {
                return NaikaraTalkConstants.RETURN_CD_DATA_ERR;
            }

            // 更新実行
            cnt = organizationContractMstLogic.insert(prmDto);

            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                return cnt;
            }
            model.setConsSeq(prmDto.getConsSeq());
            // 受講者マスタのデータ取得
            List<StudentMstDto> studentMstDtoList = searchStudentMst(model);

            if (studentMstDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // 受講者マスタのデータ更新
                cnt = this.updateStudentMst(studentMstDtoList, model, resultDtoList);
            }

            conn.commit();
            return cnt;

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

    }

    /**
     * 更新処理。<br>
     * <br>
     * @param OrganizationContractMstModel 画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int update(OrganizationContractMstModel model) throws Exception {

        int cnt = 0;
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstLogic organizationContractMstLogic = new OrganizationContractMstLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = organizationContractMstLogic.update(prmDto);

            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                return cnt;
            }

            // 組織マスタ検索実行
            List<OrganizationMstDto> resultDtoList = organizationContractMstLogic.selectData(prmDto,
                    model.getProcessKbn_rdl(), SEARCH_KYE_THREE);

            // 受講者マスタのデータ取得
            List<StudentMstDto> studentMstDtoList = searchStudentMst(model);

            if (studentMstDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // 受講者マスタのデータ更新
                this.updateStudentMst(studentMstDtoList, model, resultDtoList);
            }

            conn.commit();
            return cnt;

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

    }

    /**
     * 削除処理。<br>
     * <br>
     * @param OrganizationContractMstModel 画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int delete(OrganizationContractMstModel model) throws Exception {
        int cnt = 0;
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            OrganizationContractMstLogic organizationContractMstLogic = new OrganizationContractMstLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = organizationContractMstLogic.delete(prmDto);

            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                return cnt;
            }

            // 組織マスタ検索実行
            List<OrganizationMstDto> resultDtoList = organizationContractMstLogic.selectData(prmDto,
                    model.getProcessKbn_rdl(), SEARCH_KYE_ONE);

            // 受講者マスタのデータ取得
            List<StudentMstDto> studentMstDtoList = searchStudentMst(model);

            if (studentMstDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // 受講者マスタのデータ更新
                this.updateStudentMst(studentMstDtoList, model, resultDtoList);
            }

            conn.commit();
            return cnt;

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

    }

    /**
     * 受講者マスタのデータ取得処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ
     * @return StudentMstDto resultDtoList
     * @throws NaiException
     */
    public List<StudentMstDto> searchStudentMst(OrganizationContractMstModel model) throws NaiException {

        Connection conn = null;

        try {

            conn = DbUtil.getConnection();

            OrganizationContractMstLogic organizationContractMstLogic = new OrganizationContractMstLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // 画面の「組織ＩＤ」
            prmDto.setOrganizationId(model.getOrganizationId());

            return organizationContractMstLogic.searchStudentMst(prmDto);

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
     * 受講者マスタのデータ更新処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ
     * @return StudentMstDto resultDtoList
     * @throws NaiException
     */
    public int updateStudentMst(List<StudentMstDto> studentMstDtoList, OrganizationContractMstModel model,
            List<OrganizationMstDto> resultDtoList) throws NaiException {

        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD;

        // ｢契約開始日｣の最小値
        String minUseDt = NaikaraTalkConstants.BRANK;
        // ｢契約終了日｣の最大値
        String maxUseDt = NaikaraTalkConstants.BRANK;
        // 契約開始日比較
        if (resultDtoList != null && resultDtoList.size() > 0) {
            minUseDt = resultDtoList.get(0).getContractStrDt();
            maxUseDt = resultDtoList.get(0).getContractEndDt();
            for (OrganizationMstDto dto : resultDtoList) {
                if (DateUtil.dateCompare2(minUseDt, dto.getContractStrDt())) {
                    minUseDt = dto.getContractStrDt();
                }
                if (DateUtil.dateCompare2(dto.getContractEndDt(), maxUseDt)) {
                    maxUseDt = dto.getContractEndDt();
                }
            }
            if (DateUtil.dateCompare2(minUseDt, NaikaraStringUtil.converToYYYYMMDD(model.getContractStrDt()))) {
                minUseDt = NaikaraStringUtil.converToYYYYMMDD(model.getContractStrDt());
            }
            if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()), maxUseDt)) {
                maxUseDt = NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt());
            }
        }

        Connection conn = null;

        try {

            conn = DbUtil.getConnection();

            OrganizationContractMstLogic organizationContractMstLogic = new OrganizationContractMstLogic(conn);

            for (int i = 0; i < studentMstDtoList.size(); i++) {
                // 組織名
                studentMstDtoList.get(i).setOrganizationNm(model.getOrganizationJnm());
                // 利用開始日
                studentMstDtoList.get(i).setUseStrDt(minUseDt);
                // 利用終了日
                studentMstDtoList.get(i).setUseEndDt(maxUseDt);

                cnt = organizationContractMstLogic.updateStudentMst(studentMstDtoList.get(i));

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
            }

            conn.commit();
            return cnt;

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

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           SaleGoodsMstModel
     * @return GoodsMstDto
     * @throws Exception
     */
    private OrganizationMstDto modelToDto(OrganizationContractMstModel model) throws Exception {

        // DTOの初期化
        OrganizationMstDto prmDto = new OrganizationMstDto();

        prmDto.setOrganizationId(model.getOrganizationId());
        prmDto.setConsSeq(model.getConsSeq());
        if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_UPD)) {

            Connection conn = null;
            try {
                conn = DbUtil.getConnection();
                OrganizationContractMstLogic organizationContractMstLogic = new OrganizationContractMstLogic(conn);
                List<OrganizationMstDto> organizationMstDtoList = organizationContractMstLogic.selectData(prmDto,
                        OrganizationContractMstListModel.PROS_KBN_UPD,
                        OrganizationContractMstLoadService.SEARCH_KYE_ZERO);
                prmDto = organizationMstDtoList.get(0);
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

        prmDto.setPassword(model.getPassword());
        prmDto.setOrganizationJnm(model.getOrganizationJnm());
        prmDto.setOrganizationKnm(model.getOrganizationKnm());
        prmDto.setOrganizationRomaji(model.getOrganizationRomaji());
        prmDto.setTel(model.getTel());
        prmDto.setZipCd(model.getZipCd());
        prmDto.setAddressAreaCd(model.getAddressAreaCd());
        prmDto.setAddressPrefectureCd(model.getAddressPrefectureCd());
        prmDto.setAddressCity(model.getAddressCity());
        prmDto.setAddressOthers(model.getAddressOthers());
        prmDto.setManagFamilyJnm(model.getManagFamilyJnm());
        prmDto.setManagFirstJnm(model.getManagFirstJnm());
        prmDto.setManagFamilyKnm(model.getManagFamilyKnm());
        prmDto.setManagFirstKnm(model.getManagFirstKnm());
        prmDto.setManagFamilyRomaji(model.getManagFamilyRomaji());
        prmDto.setManagFirstRomaji(model.getManagFirstRomaji());
        prmDto.setManagPosition(model.getManagPosition());
        prmDto.setManagGenderKbn(model.getManagGenderKbn());
        prmDto.setManagMailAddress1(model.getManagMailAddress1());
        prmDto.setManagMailAddress2(model.getManagMailAddress2());
        prmDto.setManagMailAddress3(model.getManagMailAddress3());
        prmDto.setContractStrDt(model.getContractStrDt());
        prmDto.setContractEndDt(model.getContractEndDt());
        if (model.getProcessKbn_rdl().equals(OrganizationContractMstListModel.PROS_KBN_UPD)) {
            // 2014.05.09. Start
            //prmDto.setBalancePointNum(model.getTempPointNum().subtract(prmDto.getBalancePointNum()).abs());
            BigDecimal balanceNum = model.getTempPointNum().subtract(prmDto.getTempPointNum()).abs();    // 増減分 = 画面：仮ポイント - DB：仮ポイント
            BigDecimal tempPointNum = prmDto.getBalancePointNum().add(balanceNum);                       // 未割振ポイント + 増減分
            prmDto.setBalancePointNum(tempPointNum);
            // 2014.05.09. End
        } else {
            prmDto.setBalancePointNum(model.getTempPointNum());
        }

        prmDto.setTempPointNum(model.getTempPointNum());    // 仮ポイント数 (画面の値そのまま)

        prmDto.setRequestAddressKbn(model.getRequestAddressKbn());
        prmDto.setRemarks(model.getRemarks());
        if (model.getRequestAddressKbn().equals(NaikaraTalkConstants.REQUEST_ADDRESS_KBN_OFF)) {
            prmDto.setRequestOrganizationJnm(model.getRequestOrganizationJnm());
            prmDto.setRequestOrganizationKnm(model.getRequestOrganizationKnm());
            prmDto.setRequestOrganizationRnm(model.getRequestOrganizationRnm());
            prmDto.setRequestTel(model.getRequestTel());
            prmDto.setRequestZipCd(model.getRequestZipCd());
            prmDto.setRequestAddressAreaCd(model.getRequestAddressAreaCd());
            prmDto.setRequestAddressPrefectureCd(model.getRequestAddressPrefectureCd());
            prmDto.setRequestAddressCity(model.getRequestAddressCity());
            prmDto.setRequestAddressOthers(model.getRequestAddressOthers());
            prmDto.setRequestManagFamilyJnm(model.getRequestManagFamilyJnm());
            prmDto.setRequestManagFirstJnm(model.getRequestManagFirstJnm());
            prmDto.setRequestManagFamilyKnm(model.getRequestManagFamilyKnm());
            prmDto.setRequestManagFirstKnm(model.getRequestManagFirstKnm());
            prmDto.setRequestManagFamilyRomaji(model.getRequestManagFamilyRomaji());
            prmDto.setRequestManagFirstRomaji(model.getRequestManagFirstRomaji());
            prmDto.setRequestManagPosition(model.getRequestManagPosition());
        }
        prmDto.setRequestPaymentSiteKbn(model.getRequestPaymentSiteKbn());
        prmDto.setRecordVerNo(model.getRecordVerNo());
        prmDto.setUpdateCd(model.getUpdateCd());

        return prmDto;

    }

}
