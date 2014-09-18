package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.AgeCalculateDto;
import com.naikara_talk.dto.DmHistoryDetailsTrnDto;
import com.naikara_talk.dto.DmHistoryTrnDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.dto.GoodsPurchaseListDto;
import com.naikara_talk.dto.LessonResPerCommentDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.AgeCalculateLogic;
import com.naikara_talk.logic.LessonResPerCommentLogic;
import com.naikara_talk.logic.StudentMyPageLogic;
import com.naikara_talk.model.StudentMyPageModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期処理<br>
 * <b>クラス名称　　　:</b>受講者マイページ初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/16 TECS 新規作成。
 * <b>                :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class StudentMyPageLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return List<LessonReservationPerformanceTrnDto>画面パラメータ<br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectLesson(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            LessonReservationPerformanceTrnDto prmDto = new LessonReservationPerformanceTrnDto();

            // Model値をDTOにセット
            prmDto = this.modelToLessonDto(model);

            // 検索実行
            List<LessonReservationPerformanceTrnDto> list = studentMyPageLogic.selectLesson(prmDto);

            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == list.get(0).getReturnCode()) {
                // データなし
                return null;
            }

            for (LessonReservationPerformanceTrnDto lrptDto : list) {

                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                // ◆◆◆講師予定予約テーブルから対象のレッスン予実テーブルデータに対応する「予約区分(予約状況)」の取得
                List<ScheduleReservationTrnDto> srDtoList = studentMyPageLogic.selectScheduleReservationTrn(lrptDto);
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == srDtoList.get(0).getReturnCode()) {
                    // データなし ※発生なし
                    lrptDto.setReservationKbn(NaikaraTalkConstants.BRANK);       // 予約区分(予約状況)
                    lrptDto.setReservationKbnJnm(NaikaraTalkConstants.BRANK);    // 予約区分(予約状況) 表示名
                } else {
                    // データ有り
                    String reservationKbn = srDtoList.get(0).getReservationKbn();
                    CodeManagMstCache cache = CodeManagMstCache.getInstance();
                    String reservationKbnJnm = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_RESERV_KBN_STUDENT_MYPAGE, reservationKbn);
                    lrptDto.setReservationKbn(reservationKbn);          // 予約区分(予約状況)
                    lrptDto.setReservationKbnJnm(reservationKbnJnm);    // 予約区分(予約状況) 表示名
                }
                // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


                // 大分類/中分類/小分類/コース名(日本語)
                lrptDto.setCourseJnmAll(NaikaraStringUtil.unionString4(lrptDto.getBigClassificationJnm(),
                        lrptDto.getMiddleClassificationJnm(), lrptDto.getSmallClassificationJnm(),
                        lrptDto.getCourseJnm()));
                // 大分類/中分類/小分類/コース名(英語)
                lrptDto.setCourseEnmAll(NaikaraStringUtil.unionString4(lrptDto.getBigClassificationEnm(),
                        lrptDto.getMiddleClassificationEnm(), lrptDto.getSmallClassificationEnm(),
                        lrptDto.getCourseEnm()));
            }
            if (list.size() > NaikaraTalkConstants.LIMIT) {
                list = list.subList(0, 20);

            }

            return list;
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return List<GoodsPurchaseListDto> 新画面パラメータ<br>
     * @exception NaiException
     */
    public List<GoodsPurchaseListDto> selectGood(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            GoodsPurchaseListDto prmDto = new GoodsPurchaseListDto();

            // Model値をDTOにセット
            prmDto = this.modelToGoodDto(model);

            // 検索実行
            List<GoodsPurchaseListDto> list = studentMyPageLogic.selectGood(prmDto.getStudentId(),
                    prmDto.getPurchaseDt(), prmDto.getPurchaseDtNext(), prmDto.getSaleKbn());
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == list.get(0).getReturnCode()) {
                list.clear();
            }
            return list;
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return List<LessonReservationPerformanceTrnDto> 新画面パラメータ<br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectLes(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            LessonReservationPerformanceTrnDto prmDto = new LessonReservationPerformanceTrnDto();

            // ロジックの初期化
            LessonResPerCommentLogic lessonResPerCommentLogic = new LessonResPerCommentLogic(conn);

            // Model値をDTOにセット
            prmDto = this.modelToLesResDto(model);

            // 検索実行
            List<LessonReservationPerformanceTrnDto> list = studentMyPageLogic.selectLes(prmDto);

            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == list.get(0).getReturnCode()) {
                return null;
            }
            for (LessonReservationPerformanceTrnDto lrptDto : list) {

                LessonResPerCommentDto lctDto = lessonResPerCommentLogic.getLessonResPerComment(lrptDto
                        .getReservationNo());

                lrptDto.setTeacherNickNm(lctDto.getTeacherNickNm());
                lrptDto.setTPositiveCmt(lctDto.gettPositiveCmtCut());
            }
            if (list.size() > NaikaraTalkConstants.LIMIT) {
                list = list.subList(0, 20);

            }
            return list;

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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return StudentMyPageModel 新画面パラメータ<br>
     * @exception NaiException
     */
    public StudentMyPageModel selectStu(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToStuDto(model);

            // 検索実行
            StudentMstDto resultDto = studentMyPageLogic.selectStu(prmDto);

            // 検索実行
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return List<DmHistoryTrnDto> 新画面パラメータ<br>
     * @exception NaiException
     */
    public List<DmHistoryDetailsTrnDto> selectDm(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            DmHistoryDetailsTrnDto prmDto = new DmHistoryDetailsTrnDto();
            DmHistoryTrnDto dhtDto = new DmHistoryTrnDto();

            // Model値をDTOにセット
            prmDto = this.modelToDmDto(model);

            // 検索実行
            List<DmHistoryDetailsTrnDto> list = studentMyPageLogic.selectDm(prmDto);

            for (int i = 0; i < list.size(); i++) {
                DmHistoryDetailsTrnDto dhdDto = list.get(i);
                dhtDto = studentMyPageLogic.getDmCd(dhdDto.getDmCd(), dhdDto.getStudentId());
                if (dhtDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    dhdDto.setInsertDt(dhtDto.getInsertDt());
                    dhdDto.setSubjectTitle(dhtDto.getSubjectTitle());
                } else {
                    list.remove(i);
                    i--;
                }
            }
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        boolean flg = false;
                        if (DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil
                                .converToYYMMDD(list.get(j).getInsertDt())), NaikaraStringUtil
                                .converToYYYYMMDD(NaikaraStringUtil.converToYYMMDD(list.get(i).getInsertDt())))) {
                            flg = true;
                        }
                        if (flg) {
                            DmHistoryDetailsTrnDto dhdDto = list.get(j);
                            list.set(j, list.get(i));
                            list.set(i, dhdDto);
                        }
                    }
                }
            }

            if (list.size() > NaikaraTalkConstants.LIMIT) {
                list = list.subList(0, 20);

            }

            return list;
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return List<PointOwnershipTrnDto> 新画面パラメータ<br>
     * @exception NaiException
     */
    public List<PointOwnershipTrnDto> selectPoint(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();

            // Model値をDTOにセット
            prmDto = this.modelToPointDto(model);

            // 検索実行
            List<PointOwnershipTrnDto> list = studentMyPageLogic.selectPoint(prmDto);
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == list.get(0).getReturnCode()) {
                list.clear();
            }
            return list;

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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return StudentMyPageModel 新画面パラメータ<br>
     * @exception NaiException
     */
    public StudentMyPageModel selectBalancePoint(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();

            // Model値をDTOにセット
            prmDto = this.modelToPointDto(model);

            BigDecimal balancePoint = new BigDecimal(0);

            // 検索実行
            balancePoint = studentMyPageLogic.selectBalancePoint(prmDto);

            // 検索実行
            model = this.balancePointDtoToModel(balancePoint, model);

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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return StudentMyPageModel 新画面パラメータ<br>
     * @exception NaiException
     */
    public StudentMyPageModel selectAge(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            AgeCalculateLogic ageCalculateLogic = new AgeCalculateLogic();

            // DTOの初期化
            AgeCalculateDto prmDto = new AgeCalculateDto();

            // Model値をDTOにセット
            prmDto = this.modelToAgeDto(model);

            // 検索実行
            AgeCalculateDto resultDto = ageCalculateLogic.ageCalculate(prmDto.getBirth(), DateUtil.getSysDate());

            // 検索実行
            model = this.ageDtoToModel(resultDto, model);

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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param StudentMyPageModel 画面パラメータ<br>
     * @return StudentMyPageModel 新画面パラメータ<br>
     * @exception NaiException
     */
    public StudentMyPageModel selectGoods(StudentMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMyPageLogic studentMyPageLogic = new StudentMyPageLogic(conn);

            // DTOの初期化
            GoodsMstDto prmDto = new GoodsMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToGoodsDto(model);

            // 検索実行
            GoodsMstDto resultDto = studentMyPageLogic.selectGoods(prmDto);

            // 検索実行
            model = this.GoodsDtoToModel(resultDto, model);

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
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return LessonReservationPerformanceTrnDto<br>
     * @exception Exception
     */
    private LessonReservationPerformanceTrnDto modelToLessonDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        LessonReservationPerformanceTrnDto prmDto = new LessonReservationPerformanceTrnDto();
        prmDto.setStudentId(model.getStudentId());                  // 受講者ID
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return GoodsPurchaseListDto<br>
     * @exception Exception
     */
    private GoodsPurchaseListDto modelToGoodDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        GoodsPurchaseListDto prmDto = new GoodsPurchaseListDto();
        prmDto.setStudentId(model.getStudentId());                  // 受講者ID
        prmDto.setPurchaseDt(DateUtil.getSysDate());                // 購入日
        prmDto.setPurchaseDtNext(DateUtil.getSysDateAddYear(-1));   // 購入日+1年
        prmDto.setSaleKbn(NaikaraTalkConstants.SALE_KBN_DWL);       // 受取方法区分
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return LessonReservationPerformanceTrnDto<br>
     * @exception Exception
     */
    private LessonReservationPerformanceTrnDto modelToLesResDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        LessonReservationPerformanceTrnDto prmDto = new LessonReservationPerformanceTrnDto();
        prmDto.setStudentId(model.getStudentId());                  // 受講者ID
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return StudentMstDto<br>
     * @exception Exception
     */
    private StudentMstDto modelToStuDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();
        prmDto.setStudentId(model.getStudentId());                  // 受講者ID
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return DmHistoryDetailsTrnDto<br>
     * @exception Exception
     */
    private DmHistoryDetailsTrnDto modelToDmDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        DmHistoryDetailsTrnDto prmDto = new DmHistoryDetailsTrnDto();
        prmDto.setStudentId(model.getStudentId());                  // 受講者ID
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return PointOwnershipTrnDto<br>
     * @exception Exception
     */
    private PointOwnershipTrnDto modelToPointDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        PointOwnershipTrnDto prmDto = new PointOwnershipTrnDto();
        prmDto.setStudentId(model.getStudentId());                  // 受講者ID
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return AgeCalculateDto<br>
     * @exception Exception
     */
    private AgeCalculateDto modelToAgeDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        AgeCalculateDto prmDto = new AgeCalculateDto();
        prmDto.setBirth(NaikaraStringUtil.converToYYYYMMDD(model.getBirthMd()));                  // 受講者ID
        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param StudentMyPageModel 画面のパラメータ<br>
     * @return GoodsMstDto<br>
     * @exception Exception
     */
    private GoodsMstDto modelToGoodsDto(StudentMyPageModel model) throws Exception {

        // DTOの初期化
        GoodsMstDto prmDto = new GoodsMstDto();
        prmDto.setGoodsCd(model.getGoodsCd());                  // 商品コード
        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param StudentMstDto
     * @param StudentMyPageModel
     * @return StudentMyPageModel
     * @throws Exception
     */
    private StudentMyPageModel dtoToModel(StudentMstDto prmDto, StudentMyPageModel model) throws Exception {

        model.setSchoolCmt(prmDto.getSchoolCmt());                                                   // ｽｸｰﾙからのｺﾒﾝﾄ(生徒公開)
        model.setMailAddress(prmDto.getMailAddress1());                                              // メールアドレス
        model.setGenderKbn(prmDto.getGenderKbn());                                                   // 性別区分
        model.setBirthMd(NaikaraStringUtil.unionString2(prmDto.getBirthYyyy(),
                NaikaraStringUtil.unionString2(prmDto.getBirthMm(), prmDto.getBirthDd())));          // 生年月日
        model.setPointPurchaseFlg(prmDto.getPointPurchaseFlg());                                     // ポイント購入済フラグ
        model.setConsentDocumentAcquisitionFlg(prmDto.getConsentDocumentAcquisitionFlg());           // 保護者の同意書の入手フラグ
        model.setCustomerKbn(prmDto.getCustomerKbn());                                               // 顧客区分

        return model;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param BigDecimal
     * @param StudentMyPageModel
     * @return StudentMyPageModel
     * @throws Exception
     */
    private StudentMyPageModel balancePointDtoToModel(BigDecimal bigDecimal, StudentMyPageModel model) throws Exception {

        model.setBalancePoint(bigDecimal);               // ポイント残高合計
        return model;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param AgeCalculateDto
     * @param StudentMyPageModel
     * @return StudentMyPageModel
     * @throws Exception
     */
    private StudentMyPageModel ageDtoToModel(AgeCalculateDto prmDto, StudentMyPageModel model) throws Exception {

        model.setAdult(prmDto.getAdult());               // 成人区分
        return model;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param GoodsMstDto
     * @param StudentMyPageModel
     * @return StudentMyPageModel
     * @throws Exception
     */
    private StudentMyPageModel GoodsDtoToModel(GoodsMstDto prmDto, StudentMyPageModel model) throws Exception {

        model.setGoodsFile(prmDto.getGoodsFile());                   // 商品ファイル
        model.setGoodsFileNm(prmDto.getGoodsFileNm());               // 商品ファイル名
        return model;

    }

}
