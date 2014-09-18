package com.naikara_talk.logic;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.naikara_talk.dao.SmailAccountHistoryDetailsTrnDao;
import com.naikara_talk.dao.SmailAccountHistoryTrnDao;
import com.naikara_talk.dto.SmailAccountHistoryDetailsTrnDto;
import com.naikara_talk.dto.SmailAccountHistoryTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>共通。<br>
 * <b>クラス名称　　　:</b>共通：スクールのメール送信・受信履歴データ作成Logicクラス。<br>
 * <b>クラス概要　　　:</b>共通：スクールのメール送信・受信履歴データ作成Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2014/01/03 TECS 新規作成。
 */
public class SmailAccountHistoryLogic {

    /** コネクション変数 */
    public Connection conn = null;

    /** コンストラクタ */
    public SmailAccountHistoryLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 更新処理<br>
     * <br>
     * 更新処理を行う(2TBL)<br>
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
     * 更新処理を行う(1TBL)<br>
     * <br>
     * @param mailPatternCd メールパターンコード<br>
     * @param userId ログイン者コード<br>
     * @param studentId 受講者ID<br>
     * @return int 更新結果<br>
     * @exception NaiException
     */
    public int smailActHistoryInsertMain(String mailPatternCd, String userId, String studentId) throws NaiException {

        // SmailAccountHistoryTrnDtoクラスの生成
        SmailAccountHistoryTrnDto prmSAHDto = new SmailAccountHistoryTrnDto();

        // Dtoへの値の設定
        prmSAHDto = this.prmToDtoAHistory(mailPatternCd, userId, studentId, prmSAHDto);

        // スクールメール・アカウント変更履歴テーブル、スクールメール・アカウント変更履歴明細テーブルの更新処理
        int cnt = this.smailAccountHistoryInsert(prmSAHDto);

        // スクールメール・アカウント変更履歴テーブルへの結果の件数の返却
        return cnt;

    }


    /**
     * 更新処理<br>
     * <br>
     * 更新処理を行う(1TBL)<br>
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
        seq = seq + 1;
        dto.setSendDttmSeq(seq);                                        // 連番

        // 更新処理
        int cnt = dao.insert(dto);

        // スクールメール・アカウント変更履歴テーブルへの結果の件数の返却
        return cnt;

    }


    /**
     * 変更箇所のModel値をセット(スクールメール・アカウント変更履歴テーブル)<br>
     * <br>
     * 変更箇所のModel値をDTOにセット(スクールメール・アカウント変更履歴テーブル)<br>
     * <br>
     * @param mailPatternCd メールパターンコード<br>
     * @param userId ログイン者コード<br>
     * @param studentId 受講者ID<br>
     * @param prmDto 更新内容の格納先<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    public SmailAccountHistoryTrnDto prmToDtoAHistory(String mailPatternCd, String userId,
    		String studentId, SmailAccountHistoryTrnDto prmDto) throws NaiException {

        // メールパターンコード
        prmDto.setMailPatternCd(mailPatternCd);

        // 受講者ID
        prmDto.setStudentId(studentId);

        // 登録者コード
        prmDto.setInsertCd(userId);

        // 更新者コード
        prmDto.setUpdateCd(userId);

        // 排他用レコードバージョン
        prmDto.setRecordVerNo(NaikaraTalkConstants.INS_REC_VER_NO);

        // 返却
        return prmDto;
    }

}