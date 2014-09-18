package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.LessonCommentTrnDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-2_レッスン実績Logicクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-2_レッスン実績Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonReportLogic {

    public Connection conn = null;

    public TeacherLessonReportLogic(Connection con) {

        this.conn = con;
    }

    /**
     * 初期表示<br>
     * <br>
     * 初期表示の検索処理<br>
     * <br>
     * @param dto 検索条件<br>
     * @return dtoResult 検索結果<br>
     * @exception NaiException
     */
    public LessonCommentTrnDto select(LessonCommentTrnDto dto) throws NaiException {

        // DAOの初期化
        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);

        // 並び順:予約No の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(LessonCommentTrnDao.COND_RESERVATION_NO, OrderCondition.ASC);

        // 検索を行う
        List<LessonCommentTrnDto> resultDto = dao.search(setConditions(dto), orderBy);

        // DTOの初期化
        LessonCommentTrnDto dtoResult = new LessonCommentTrnDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 登録処理<br>
     * <br>
     * 登録処理を行う<br>
     * <br>
     * @param dto 登録データ<br>
     * @return int 登録結果<br>
     * @exception NaiException
     */
    public int insert(LessonCommentTrnDto dto) throws NaiException {

        // DAOの初期化
        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);

        // 登録を行う
        return dao.insert(dto);
    }

    /**
     * 更新処理<br>
     * <br>
     * 更新処理を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return int 更新結果<br>
     * @exception NaiException
     */
    public int update(LessonCommentTrnDto dto) throws NaiException {

        // DAOの初期化
        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);

        // 更新を行う
        return dao.update(dto);
    }

    /**
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param dto チェックデータ<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int getExists(LessonCommentTrnDto dto) throws NaiException {

        // DAOの初期化
        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);

        // 並び順:予約No の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(LessonCommentTrnDao.COND_RESERVATION_NO, OrderCondition.ASC);

        // 検索を行う
        List<LessonCommentTrnDto> resultDto = dao.search(setConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return hashMap 取得結果<br>
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
     * 検索条件設定<br>
     * <br>
     * 検索条件を設定<br>
     * <br>
     * @param dto 検索条件<br>
     * @return conditions 設定後の検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(LessonCommentTrnDto dto) throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 予約No
        conditions.addCondition(LessonCommentTrnDao.COND_RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL,
                dto.getReservationNo());

        // コメント入力者区分
        conditions.addCondition(LessonCommentTrnDao.COND_CMT_INPUTS_KBN, ConditionMapper.OPERATOR_EQUAL,
                dto.getCmtInputsKbn());

        // 戻り値
        return conditions;
    }
}