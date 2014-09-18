package com.naikara_talk.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.naikara_talk.dao.PointOwnershipTrnListDao;
import com.naikara_talk.dao.PointProvisionTrnListDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointOwnershipTrnListDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointProvisionTrnListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Logicクラス<br>
 * <b>クラス名称　　　:</b>ポイント引当・解除クラス<br>
 * <b>クラス概要　　　:</b>ポイント引当・解除Logic<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成
 */
public class PointProvisionReleaseLogic {

    protected Logger log = Logger.getLogger(this.getClass());

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public PointProvisionReleaseLogic(Connection con) {
        this.conn = con;
    }

    /**
     * ポイント引当・解除チェック<br>
     * <br>
     * ポイント引当・解除チェックを行う<br>
     * <br>
     * @param studentId
     * @param PointProvisionDataList
     * @param PointReleaseDataList
     * @return PointProvisionReleaseResultList
     * @throws NaiException
     */
    public List<LessonReserveCancelResultListDto> pointCheck(String studentId, List<PointProvisionDataListDto> ppdlDto,
            List<PointReleaseDataListDto> prdlDto) throws NaiException {

        // 初期化
        int pointReleaseCnt = 0;
        int RETURN_CODE_NORMAL = 0;
        int POINT_OWNERSHIP_TRN_NOTHING = 1;
        int POINT_PROVISION_TRN_NOTHING = 2;
        int POINT_RELEASE_ERROR = 3;
        int POINT_PROVISION_ERROR = 4;
        int USE_POINT_NULL = 5;

        String strLDt = "99999999";
        BigDecimal wkBP = new BigDecimal(0);
        BigDecimal wkPP = new BigDecimal(0);
        BigDecimal wkFP = new BigDecimal(0);

        List<LessonReserveCancelResultListDto> lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
        List<PointOwnershipTrnListDto> potlDto = new ArrayList<PointOwnershipTrnListDto>();
        List<PointProvisionTrnListDto> pptlDto = new ArrayList<PointProvisionTrnListDto>();

        LessonReserveCancelResultListDto wklrcrlDto = new LessonReserveCancelResultListDto();
        PointOwnershipTrnListDto wkpoDto = new PointOwnershipTrnListDto();

        // ポイント解除対象とポイント引当可能なポイント所有テーブルリストを作成する
        if (ppdlDto.size() > 0 || prdlDto.size() > 0) {
            // ポイント引当データリストからレッスン日の最小日を取得する
            for (int i = 0, n = ppdlDto.size(); i < n; i++) {
                if (ppdlDto.get(i).getLessonDt().compareTo(strLDt) < 0) {
                    strLDt = ppdlDto.get(i).getLessonDt();
                }
            }
            // ポイント解除データリストからレッスン日の最小日を取得する
            if (prdlDto.size() > 0) {
                for (int i = 0, n = prdlDto.size(); i < n; i++) {
                    if (prdlDto.get(i).getLessonDt().compareTo(strLDt) < 0) {
                        strLDt = prdlDto.get(i).getLessonDt();
                    }
                }
            }
            // ポイント所有テーブルリスト取得
            potlDto = this.pointOwnershipTrnListGet(studentId, strLDt);
            if (potlDto.size() < 1) {
                lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
                wklrcrlDto = new LessonReserveCancelResultListDto();
                wklrcrlDto.setReturnCode(POINT_OWNERSHIP_TRN_NOTHING);
                lrcrlDto.add(wklrcrlDto);
                return lrcrlDto;
            }
        }

        // ポイント引当テーブルリストを作成する
        if (prdlDto.size() > 0) {

            // ポイント引当テーブルリスト取得
            pptlDto = this.pointProvisionTrnListGet(prdlDto);

            if (pptlDto.size() < 1) {
                lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
                wklrcrlDto = new LessonReserveCancelResultListDto();
                wklrcrlDto.setReturnCode(POINT_PROVISION_TRN_NOTHING);
                lrcrlDto.add(wklrcrlDto);
                return lrcrlDto;
            }
        }

        // ポイント解除（ポイント所有に戻す）
        if (prdlDto.size() > 0) {
            for (int i = 0, n = pptlDto.size(); i < n; i++) {
                pointReleaseCnt = 0;
                wkpoDto = new PointOwnershipTrnListDto();
                for (int j = 0, m = potlDto.size(); j < m; j++) {
                    wkBP = BigDecimal.ZERO;
                    if (pptlDto.get(i).getOwnId().equals(potlDto.get(j).getOwnershipID())
                            && pptlDto.get(i).getCompensationFreeKbn().equals(potlDto.get(j).getCompensationFreeKbn())) {
                        // ポイント所有．ポイント残高（j）+ ポイント引当．利用ポイント（i）
                        wkpoDto.setOwnershipID(potlDto.get(j).getOwnershipID());
                        wkpoDto.setCompensationFreeKbn(potlDto.get(j).getCompensationFreeKbn());
                        wkpoDto.setEffectiveStrDt(potlDto.get(j).getEffectiveStrDt());
                        wkpoDto.setEffectiveEndDt(potlDto.get(j).getEffectiveEndDt());
                        wkBP = wkBP.add(potlDto.get(j).getBalancePoint());
                        wkBP = wkBP.add(pptlDto.get(i).getUsePoint());
                        wkpoDto.setBalancePoint(wkBP);
                        potlDto.set(j, wkpoDto);
                        j = potlDto.size();
                        pointReleaseCnt++;
                    }
                }
                wklrcrlDto = new LessonReserveCancelResultListDto();
                wklrcrlDto.setRsvNoPurchaseId(pptlDto.get(i).getRsvNoPurchaseId());
                wklrcrlDto.setTeacherId(pptlDto.get(i).getTeacherId());
                wklrcrlDto.setLessonDt(pptlDto.get(i).getLessonDt());
                wklrcrlDto.setLessonTmCd(pptlDto.get(i).getLessonTmCd());
                wklrcrlDto.setCourseCd(pptlDto.get(i).getCourseCd());
                if (NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES.equals(pptlDto.get(i).getCompensationFreeKbn())) {
                    wklrcrlDto.setPaymentUsePoint(pptlDto.get(i).getUsePoint());
                    wklrcrlDto.setFreeUsePoint(BigDecimal.ZERO);
                } else {
                    wklrcrlDto.setPaymentUsePoint(BigDecimal.ZERO);
                    wklrcrlDto.setFreeUsePoint(pptlDto.get(i).getUsePoint());
                }
                // ポイント解除完了チェック
                if (pointReleaseCnt == 0) {
                    wklrcrlDto.setReturnCode(POINT_RELEASE_ERROR);
                } else {
                    wklrcrlDto.setReturnCode(RETURN_CODE_NORMAL);
                }
                lrcrlDto.add(wklrcrlDto);
            }
        }

        // ポイント引当チェック

        for (int i = 0, n = ppdlDto.size(); i < n; i++) {
            wkBP = BigDecimal.ZERO;
            wkPP = BigDecimal.ZERO;
            wkFP = BigDecimal.ZERO;
            if (ppdlDto.get(i).getUsePoint() != null) {
                wkBP = wkBP.add(ppdlDto.get(i).getUsePoint());
                for (int j = 0, m = potlDto.size(); j < m; j++) {
                    if (ppdlDto.get(i).getLessonDt().compareTo(potlDto.get(j).getEffectiveEndDt()) <= 0
                            && potlDto.get(j).getBalancePoint().compareTo(BigDecimal.ZERO) > 0
                            && wkBP.compareTo(BigDecimal.ZERO) > 0) {
                        wkpoDto = new PointOwnershipTrnListDto();
                        wkpoDto.setOwnershipID(potlDto.get(j).getOwnershipID());
                        wkpoDto.setCompensationFreeKbn(potlDto.get(j).getCompensationFreeKbn());
                        wkpoDto.setEffectiveStrDt(potlDto.get(j).getEffectiveStrDt());
                        wkpoDto.setEffectiveEndDt(potlDto.get(j).getEffectiveEndDt());
                        // ポイント所有．ポイント残高（j）- ポイント引当．利用ポイント（i）
                        if (wkBP.compareTo(potlDto.get(j).getBalancePoint()) <= 0) {
                            wkpoDto.setBalancePoint(potlDto.get(j).getBalancePoint().subtract(wkBP));
                            if (NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES.equals(potlDto.get(j)
                                    .getCompensationFreeKbn())) {
                                wkPP = wkPP.add(wkBP);
                            } else {
                                wkFP = wkFP.add(wkBP);
                            }
                            wkBP = BigDecimal.ZERO;
                        } else {
                            wkBP = wkBP.subtract(potlDto.get(j).getBalancePoint());
                            if (NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES.equals(potlDto.get(j)
                                    .getCompensationFreeKbn())) {
                                wkPP = wkPP.add(potlDto.get(j).getBalancePoint());
                            } else {
                                wkFP = wkFP.add(potlDto.get(j).getBalancePoint());
                            }
                            wkpoDto.setBalancePoint(BigDecimal.ZERO);
                        }
                        potlDto.set(j, wkpoDto);
                    }
                }

                wklrcrlDto = new LessonReserveCancelResultListDto();
                wklrcrlDto.setRsvNoPurchaseId("");
                wklrcrlDto.setTeacherId(ppdlDto.get(i).getTeacherId());
                wklrcrlDto.setLessonDt(ppdlDto.get(i).getLessonDt());
                wklrcrlDto.setLessonTmCd(ppdlDto.get(i).getLessonTmCd());
                wklrcrlDto.setCourseCd(ppdlDto.get(i).getCourseCd());
                wklrcrlDto.setPaymentUsePoint(wkPP);
                wklrcrlDto.setFreeUsePoint(wkFP);
                // ポイント引当完了チェック
                if (wkBP.compareTo(BigDecimal.ZERO) > 0) {
                    wklrcrlDto.setReturnCode(POINT_PROVISION_ERROR);
                } else {
                    wklrcrlDto.setReturnCode(RETURN_CODE_NORMAL);
                }
            } else {
                wklrcrlDto.setReturnCode(USE_POINT_NULL);
            }
            lrcrlDto.add(wklrcrlDto);
        }

        return lrcrlDto;

    }

    /**
     * ポイント所有テーブルリスト取得<br>
     * <br>
     * ポイント所有テーブルリストの取得を行う<br>
     * <br>
     * @param studentId
     * @param lessonDt
     * @return List:PointOwnershipTrnListDto
     * @throws NaiException
     */
    public List<PointOwnershipTrnListDto> pointOwnershipTrnListGet(String studentId, String lessonDt)
            throws NaiException {

        List<PointOwnershipTrnListDto> potlDto = new ArrayList<PointOwnershipTrnListDto>();
        PointOwnershipTrnListDao potlDao = new PointOwnershipTrnListDao(this.conn);

        ConditionMapper cm = new ConditionMapper();
        OrderCondition oc = new OrderCondition();

        // ポイント所有テーブルデータ取得

        cm.addCondition(PointOwnershipTrnListDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);
        cm.addCondition(PointOwnershipTrnListDao.COND_EFFECTIVE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, lessonDt);

        // 有効開始前のデータが取得ている為修正-ADD-Start
        cm.addCondition(PointOwnershipTrnListDao.COND_EFFECTIVE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, lessonDt);
        // 有効開始前のデータが取得ている為修正-ADD-End

        oc.addCondition(PointOwnershipTrnListDao.COND_EFFECTIVE_END_DT, OrderCondition.ASC);
        oc.addCondition(PointOwnershipTrnListDao.COND_COMPENSATION_FREE_KBN, OrderCondition.ASC);
        oc.addCondition(PointOwnershipTrnListDao.COND_FEE_KBN, OrderCondition.DESC);

        potlDto = potlDao.search(cm, oc);

        if (potlDto.size() == 1 && potlDto.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            potlDto = new ArrayList<PointOwnershipTrnListDto>();
        }

        return potlDto;

    }

    /**
     * ポイント引当テーブルリスト取得<br>
     * <br>
     * ポイント引当テーブルリストの取得を行う<br>
     * <br>
     * @param studentId
     * @param lessonDt
     * @return PointProvisionTrnListDto
     * @throws NaiException
     */
    public List<PointProvisionTrnListDto> pointProvisionTrnListGet(List<PointReleaseDataListDto> prdlDto)
            throws NaiException {

        List<PointProvisionTrnListDto> pptlDto = new ArrayList<PointProvisionTrnListDto>();
        List<PointProvisionTrnListDto> wkpptlDto = new ArrayList<PointProvisionTrnListDto>();
        PointProvisionTrnListDto wkDto = new PointProvisionTrnListDto();
        PointProvisionTrnListDao pptlDao = new PointProvisionTrnListDao(this.conn);

        ConditionMapper cm = new ConditionMapper();
        OrderCondition oc = new OrderCondition();

        // ポイント引当テーブルデータ取得
        for (int i = 0, n = prdlDto.size(); i < n; i++) {
            cm = new ConditionMapper();
            oc = new OrderCondition();

            wkpptlDto = new ArrayList<PointProvisionTrnListDto>();

            cm.addCondition(PointProvisionTrnListDao.COND_RSV_NO_PURCHASE_ID, ConditionMapper.OPERATOR_EQUAL, prdlDto
                    .get(i).getRsvNoPurchaseId());

            oc.addCondition(PointProvisionTrnListDao.COND_RSV_NO_PURCHASE_ID, OrderCondition.ASC);
            oc.addCondition(PointProvisionTrnListDao.COND_CONS_SEQ, OrderCondition.ASC);

            wkpptlDto = pptlDao.search(cm, oc);

            if (wkpptlDto.size() == 1 && wkpptlDto.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                pptlDto = new ArrayList<PointProvisionTrnListDto>();
                break;
            }
            for (int j = 0, m = wkpptlDto.size(); j < m; j++) {
                wkDto = new PointProvisionTrnListDto();
                wkDto.setRsvNoPurchaseId(wkpptlDto.get(j).getRsvNoPurchaseId());
                wkDto.setConsSeq(wkpptlDto.get(j).getConsSeq());
                wkDto.setTeacherId(prdlDto.get(i).getTeacherId());
                wkDto.setLessonDt(prdlDto.get(i).getLessonDt());
                wkDto.setLessonTmCd(prdlDto.get(i).getLessonTmCd());
                wkDto.setCourseCd(prdlDto.get(i).getCourseCd());
                wkDto.setOwnId(wkpptlDto.get(j).getOwnId());
                wkDto.setCompensationFreeKbn(wkpptlDto.get(j).getCompensationFreeKbn());
                wkDto.setUsePoint(wkpptlDto.get(j).getUsePoint());
                wkDto.setReturnCode(wkpptlDto.get(j).getReturnCode());
                pptlDto.add(wkDto);
            }
        }

        return pptlDto;

    }

}
