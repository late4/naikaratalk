package com.naikara_talk.logic;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.SmailAccountHistoryDetailsTrnDao;
import com.naikara_talk.dao.SmailAccountHistoryTrnDao;
import com.naikara_talk.dao.StudentMailExistChkDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.SmailAccountHistoryDetailsTrnDto;
import com.naikara_talk.dto.SmailAccountHistoryTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様_初期処理。<br>
 * <b>クラス名称　　　:</b>アカウント取得処理Logicクラス。<br>
 * <b>クラス概要　　　:</b>アカウント取得処理Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/08 TECS 新規作成。
 *                     </b>2014/01/03 TECS 変更 スクールのメール送信・受信履歴照会に伴う対応
 */
public class AccountLogic {

    /** コネクション変数 */
    public Connection conn = null;

    /** コンストラクタ */
    public AccountLogic(Connection con) {
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
    public StudentMstDto select(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 検索を行う
        List<StudentMstDto> resultDto = dao.searchWithKnm(setConditions(dto), orderBy, new StudentMstDto());

        // DTOの初期化
        StudentMstDto dtoResult = new StudentMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }

    /**
     * 受講者登録処理<br>
     * <br>
     * 受講者登録処理を行う<br>
     * <br>
     * @param dto 登録データ<br>
     * @return int 登録結果<br>
     * @exception NaiException
     */
    public int insertStudent(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 登録を行う
        return dao.insert(dto);
    }

    /**
     * ポイント登録処理<br>
     * <br>
     * ポイント登録処理を行う<br>
     * <br>
     * @param dto 登録データ<br>
     * @return int 登録結果<br>
     * @exception NaiException
     */
    public int insertPoint(PointOwnershipTrnDto dto) throws NaiException {

        // DAOの初期化
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);

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
    public int update(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

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
    public int isExists(StudentMstDto dto) throws NaiException {

        // DAOの初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 並び順:受講者ID の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(StudentMstDao.COND_STUDENT_ID, OrderCondition.ASC);

        // 検索を行う
        List<StudentMstDto> resultDto = dao.search(setConditions(dto), orderBy);

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
    private ConditionMapper setConditions(StudentMstDto dto) throws NaiException {

        // 検索条件の初期化
        ConditionMapper conditions = new ConditionMapper();

        // 検索条件を設定
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        // 戻り値
        return conditions;
    }

    /**
     * メール重複チェック処理<br>
     * <br>
     * メール重複チェック処理を行う<br>
     * <br>
     * @param mailAddress メールアドレス<br>
     * @param yyyyMMdd サーバシステム日付<br>
     * @return int 重複有無<br>
     * @exception NaiException
     */
    public int count(String mailAddress, String yyyyMMdd, String studentId) throws NaiException {

        // DAOの初期化
        StudentMailExistChkDao dao = new StudentMailExistChkDao(this.conn);

        // 更新を行う
        return dao.count(mailAddress, yyyyMMdd, studentId);
    }


    // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
    /**
     * 更新処理<br>
     * <br>
     * 更新処理を行う<br>
     * <br>
     * @param ddto 更新データ:スクールメール・アカウント変更履歴明細テーブル<br>
     * @param dto  更新データ:スクールメール・アカウント変更履歴テーブル<br>
     * @return int 更新結果<br>
     * @exception NaiException
     */
    public int smailAccountHistoryAndDInsert(SmailAccountHistoryDetailsTrnDto ddto, SmailAccountHistoryTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        // ###スクールメール・アカウント変更履歴明細テーブル###
        // DAOクラスの生成
        SmailAccountHistoryDetailsTrnDao ddao = new SmailAccountHistoryDetailsTrnDao(this.conn);

        // 更新値の設定
        ddto.setSendDttm(Timestamp.valueOf(sdf.format(cal.getTime())));    // 送信日時

        // 更新処理
        int seq = ddao.insert(ddto);

        // ###スクールメール・アカウント変更履歴テーブル###
        // DAOクラスの生成
        SmailAccountHistoryTrnDao dao = new SmailAccountHistoryTrnDao(this.conn);

        // 更新値の設定
        dto.setSendDttm(ddto.getSendDttm());  // 送信日時
        dto.setSendDttmSeq(seq);              // 連番

        // 更新処理
        int cnt = dao.insert(dto);

        // スクールメール・アカウント変更履歴テーブルへの結果の件数の返却
        return cnt;

    }


    /**
     * 更新処理<br>
     * <br>
     * 更新処理を行う<br>
     * <br>
     * @param dto  更新データ:スクールメール・アカウント変更履歴テーブル<br>
     * @return int 更新結果<br>
     * @exception NaiException
     */
    public int smailAccountHistoryInsert(SmailAccountHistoryTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // ###スクールメール・アカウント変更履歴テーブル###
        // DAOクラスの生成
        SmailAccountHistoryTrnDao dao = new SmailAccountHistoryTrnDao(this.conn);

        // 更新値の設定
        dto.setSendDttm(Timestamp.valueOf(sdf.format(cal.getTime())));  // 送信日時
        int seq = dao.searchMaxSeq(dto.getSendDttm());                  // 現時点の連番の最大値の取得
        dto.setSendDttmSeq(seq);                                        // 連番

        // 更新処理
        int cnt = dao.insert(dto);

        // スクールメール・アカウント変更履歴テーブルへの結果の件数の返却
        return cnt;

    }
    // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


}