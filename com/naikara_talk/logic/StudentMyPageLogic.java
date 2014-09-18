package com.naikara_talk.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.DmHistoryDetailsTrnDao;
import com.naikara_talk.dao.DmHistoryTrnDao;
import com.naikara_talk.dao.GoodsPurchaseListDao;
import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.SaleGoodsMstDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.DmHistoryDetailsTrnDto;
import com.naikara_talk.dto.DmHistoryTrnDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.dto.GoodsPurchaseListDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期処理<br>
 * <b>クラス名称　　　:</b>受講者マイページLogicクラス。<br>
 * <b>クラス概要　　　:</b>受講者マイページLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/16 TECS 新規作成。
 *  　　　　　　　　　:</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class StudentMyPageLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentMyPageLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 初期表示の検索処理。「現在予約中のレッスン」※メイン※(レッスン予実テーブル)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param LessonReservationPerformanceTrnDto 画面のパラメータ<br>
     * @return List<LessonReservationPerformanceTrnDto><br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectLesson(LessonReservationPerformanceTrnDto dto)
            throws NaiException {

        // 検索結果の初期化
        List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();

        // DAOの初期化
        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // 並び順:レッスン日 の昇順、レッスン時刻 の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT, OrderCondition.ASC);
        orderBy.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_TM_CD, OrderCondition.ASC);
        list = dao.search(setConditions(dto), orderBy);

        return list;

    }

    /**
     * 初期表示の検索処理。「購入済みの商品一覧」※メイン※(商品購入テーブル、販売商品マスタ)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param String studentId, String purchaseDt, String purchaseDtNext,String saleKbn<br>
     * @return List<GoodsPurchaseListDto><br>
     * @exception NaiException
     */
    public List<GoodsPurchaseListDto> selectGood(String studentId, String purchaseDt, String purchaseDtNext,
            String saleKbn) throws NaiException {

        // 戻り値とリターンコードの初期化
        List<GoodsPurchaseListDto> list = null;
        GoodsPurchaseListDto dto = null;

        // 引数のパラメータチェック
        // 受講者IDの必須チェック
        if (StringUtils.isEmpty(studentId)) {
            dto = new GoodsPurchaseListDto();
            dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
            list = new ArrayList<GoodsPurchaseListDto>();
            list.add(dto);
            return list;
        }
        // 購入日の書式チェック
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(purchaseDt);
        } catch (Exception e) {
            dto = new GoodsPurchaseListDto();
            dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
            list = new ArrayList<GoodsPurchaseListDto>();
            list.add(dto);
            return list;
        }

        // DAOの初期化
        GoodsPurchaseListDao dao = new GoodsPurchaseListDao(this.conn);

        list = dao.searchNew(studentId, purchaseDt, purchaseDtNext, saleKbn);

        return list;

    }

    /**
     * 初期表示の検索処理。「講師からのコメント」(レッスン予実テーブルデータ)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param LessonReservationPerformanceTrnDto 画面のパラメータ<br>
     * @return List<LessonReservationPerformanceTrnDto><br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectLes(LessonReservationPerformanceTrnDto dto)
            throws NaiException {

        // 検索結果の初期化
        List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();

        // DAOの初期化
        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // 並び順:レッスン予実テーブル．レッスン日 の降順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT, OrderCondition.DESC);
        orderBy.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_TM_CD, OrderCondition.DESC);

        list = dao.search(setConditionsLes(dto), orderBy);

        return list;

    }

    /**
     * 初期表示の検索処理。「スクールからのコメント」「画面の右部の情報」(受講者マスタデータ)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return StudentMstDto<br>
     * @exception NaiException
     */
    public StudentMstDto selectStu(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順：なし
        OrderCondition orderBy = new OrderCondition();
        ArrayList<StudentMstDto> resultDto = (ArrayList<StudentMstDto>) dao.search(setConditionsStu(dto), orderBy);
        StudentMstDto dtoResult = new StudentMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * 初期表示の検索処理。「キャンペーンメール履歴」※メイン※(DM履歴明細テーブルデータ)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param DmHistoryTrnDto 画面のパラメータ<br>
     * @return List<DmHistoryTrnDto><br>
     * @exception NaiException
     */
    public List<DmHistoryDetailsTrnDto> selectDm(DmHistoryDetailsTrnDto dto) throws NaiException {

        // 検索結果の初期化
        List<DmHistoryDetailsTrnDto> list = new ArrayList<DmHistoryDetailsTrnDto>();

        // DAOの初期化
        DmHistoryDetailsTrnDao dao = new DmHistoryDetailsTrnDao(this.conn);

        // 並び順:なし
        OrderCondition orderBy = new OrderCondition();
        list = dao.search(setConditionsDm(dto), orderBy);
        return list;

    }

    /**
     * DM履歴テーブルデータ取得 「キャンペーンメール履歴」<br>
     * <br>
     * DM履歴テーブルを戻り値で返却する<br>
     * <br>
     * @param String DMCd, String studentId
     * @return DmHistoryDetailsTrnDto
     * @throws NaiException
     */
    public DmHistoryTrnDto getDmCd(String dmCd, String studentId) throws NaiException {

        // 戻り値とリターンコードの初期化
        DmHistoryTrnDto dto = null;                     // DM履歴テーブル
        ArrayList<DmHistoryTrnDto> workdto = null;      // DM履歴DTO(ワーク用)

        // 引数のパラメータチェック
        if (StringUtils.isEmpty(dmCd)) {
            dto = new DmHistoryTrnDto();
            dto.setReturnCode(1);
            return dto;
        }

        DmHistoryTrnDao dao = new DmHistoryTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // DMコードを入力されている場合
        if (!StringUtils.isEmpty(dmCd)) {
            conditions.addCondition(DmHistoryTrnDao.COND_DM_CD, ConditionMapper.OPERATOR_EQUAL, dmCd);
        }

        // 並び順:なし
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(DmHistoryTrnDao.COND_INSERT_DTTM, OrderCondition.DESC);

        // DM履歴の取得
        workdto = dao.search(conditions, orderBy);

        return workdto.get(0);
    }

    /**
     * 初期表示の検索処理。「ポイント購入履歴」(ポイント所有テーブル)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param PointOwnershipTrnDto 画面のパラメータ<br>
     * @return List<PointOwnershipTrnDto><br>
     * @exception NaiException
     */
    public List<PointOwnershipTrnDto> selectPoint(PointOwnershipTrnDto dto) throws NaiException {

        // 検索結果の初期化
        List<PointOwnershipTrnDto> list = new ArrayList<PointOwnershipTrnDto>();

        // DAOの初期化
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);

        // 並び順:有効期限の昇順、有償無償区分の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_END_DT, OrderCondition.ASC);
        orderBy.addCondition(PointOwnershipTrnDao.COND_COMPENSATION_FREE_KBN, OrderCondition.ASC);
        list = dao.search(setConditionsPoint(dto), orderBy);

        return list;

    }

    /**
     * 初期表示の検索処理。「画面の右部」(ポイント所有テーブルのポイント残高合計取得)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param PointOwnershipTrnDto 画面のパラメータ<br>
     * @return BigDecimal<br>
     * @exception NaiException
     */
    public BigDecimal selectBalancePoint(PointOwnershipTrnDto dto) throws NaiException {

        // DAOの初期化
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);
        return dao.getPointBalance(setConditionsPoint(dto));

    }

    /**
     * 初期表示の検索処理。「購入済みの商品一覧」(商品マスタ)<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param GoodsMstDto 画面のパラメータ<br>
     * @return GoodsMstDto<br>
     * @exception NaiException
     */
    public GoodsMstDto selectGoods(GoodsMstDto dto) throws NaiException {

        // DAOの初期化
        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);

        // 並び順:組織ID の昇順
        OrderCondition orderBy = new OrderCondition();
        ArrayList<GoodsMstDto> resultDto = (ArrayList<GoodsMstDto>) dao.search(setConditionsGoods(dto), orderBy);
        GoodsMstDto dtoResult = new GoodsMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> list = cache.getList(category);

        Iterator<String> iter = list.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            CodeManagMstDto dto = list.get(key);
            hashMap.put(dto.getManagerCd(), dto.getManagerNm());
        }

        return hashMap;
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param LessonReservationPerformanceTrnDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(LessonReservationPerformanceTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 状態区分
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.LESSON_STATE_KBN_RESERVATION);

        // レッスン日
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param LessonReservationPerformanceTrnDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditionsLes(LessonReservationPerformanceTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 状態区分
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN, ConditionMapper.OPERATOR_NOT_EQUAL,
                NaikaraTalkConstants.LESSON_STATE_KBN_RESERVATION);

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditionsStu(StudentMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param PointOwnershipTrnDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditionsPoint(PointOwnershipTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(PointOwnershipTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());
        // 有効開始日
        conditions.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                DateUtil.getSysDate());
        // 有効終了日
        conditions.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());
        // 残高ポイント
        conditions.addCondition(PointOwnershipTrnDao.COND_BALANCE_POINT, ConditionMapper.OPERATOR_LARGER_THAN,
                NaikaraTalkConstants.BALANCE_POINT_ZERO);
        // 月謝停止フラグ
        conditions.addCondition(PointOwnershipTrnDao.COND_END_FLG, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.END_FLG_NO);
        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param GoodsMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditionsGoods(GoodsMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(SaleGoodsMstDao.COND_GOODS_CD, ConditionMapper.OPERATOR_EQUAL, dto.getGoodsCd());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditionsDm(DmHistoryDetailsTrnDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(DmHistoryDetailsTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 戻り値
        return conditions;

    }


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * 講師予定予約テーブルのデータ取得処理。「現在予約中のレッスン」の「ステータス」<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param dto レッスン予実テーブルDTO<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrn(LessonReservationPerformanceTrnDto dto)
            throws NaiException {

        // ScheduleReservationTrnDaoの生成
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // ◆◆◆検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // KEY:講師予定予約テーブル．講師ID
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getTeacherId());
        // KEY:講師予定予約テーブル．レッスン日
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonDt());
        // KEY:講師予定予約テーブル．レッスン時刻コード
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonTmCd());

        // ◆◆◆並び順
        OrderCondition orderBy = new OrderCondition();

        // ◆◆◆データの取得
        return dao.search(conditions, orderBy);
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


}
