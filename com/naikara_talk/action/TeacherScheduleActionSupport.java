package com.naikara_talk.action;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.SchResTCodeManagMDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherScheduleModel;
import com.naikara_talk.service.TeacherScheduleLoadService;
import com.naikara_talk.sessiondata.SessionCodeControlMst;
import com.naikara_talk.sessiondata.SessionTeacherSchedule;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュール。<br>
 * <b>クラス名称       :</b>講師スケジュールActionクラス。<br>
 * <b>クラス概要       :</b>講師スケジュールの情報ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public abstract class TeacherScheduleActionSupport extends NaikaraActionSupport {
    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "Teacher Schedule";

    // Help画面名
    protected String helpPageId = "HelpTeacherSchedule.html";

    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    /**
     * validate <br>
     * <br>
     * 初期化する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、初期データ再取得。
        try {

            // 予約受付・予約状態のドロップダウンの取得
            this.initSelect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws NaiException
     */
    protected String load() throws NaiException {

        // サービスの生成
        TeacherScheduleLoadService service = new TeacherScheduleLoadService();

        // 前画面の情報
        this.model.setUserId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        this.model.setDateFlg(this.dateFlg);


        // ◆◆◆データの取得◆◆◆
        try {
            // 講師予定予約テーブルデータの取得処理
            this.model = service.select(this.model);

            if (StringUtils.isEmpty(this.model.getTeacherId())) {
                // 対象データなし
                this.message = getMessage("ET0020", new String[] { "", "" });
                return "previous";
            }

            // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
            this.modelAsToSession();
            // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ◆◆◆取得データを画面へ設定◆◆◆
        /** 時間(符号) */
        this.timeMark = model.getTimeMark();
        /** 時間(分) */
        this.timeMinutes = model.getTimeMinutes();
        /** サマータイム(時間)(符号) */
        this.sumTimeMark = model.getSumTimeMark();
        /** サマータイム(分) */
        this.sumTimeMinutes = model.getSumTimeMinutes();
        /** 備考 */
        this.remark = model.getRemark();
        /** 講師ID */
        this.teacherId = model.getTeacherId();
        /** 基点日フラグ */
        this.dateFlg = model.getDateFlg();

        return SUCCESS;
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * セレクトボックス取得。<br>
     * <br>
     * セレクトボックスを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    public void initSelect() throws Exception {

        // サービスの生成
        TeacherScheduleLoadService service = new TeacherScheduleLoadService();

        // データ取得
        LinkedHashMap<String, String> resStateList = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_RESERV_STATE_T);

        this.midnight_sell = resStateList;    // ｢00：00 - 06：00｣部分
        this.morning_sell = resStateList;     // ｢06：00 - 12：00｣部分
        this.daytime_sell = resStateList;     // ｢12：00 - 18：00｣部分
        this.night_sell = resStateList;       // ｢18：00 - 24：00｣部分
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {

        /** 利用者ID */
        this.model.setUserId(this.teacherId);
        /** 講師ID */
        //this.model.setTeacherId(this.teacherId);
        this.model.setTeacherId(this.teacherId);

        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
        /** ｢00：00 - 06：00｣部分のチェックBOX */
        //this.model.setMidnight_chkn(this.Midnight_chkn);
        this.model.setMidnight(this.midnight_sel);

        /** ｢06：00 - 12：00｣部分のチェックBOX */
        //this.model.setMorning_chkn(this.Morning_chkn);
        this.model.setMorning(this.morning_sel);

        /** ｢12：00 - 18：00｣部分のチェックBOX */
        //this.model.setDaytime_chkn(this.Daytime_chkn);
        this.model.setDaytime(this.daytime_sel);

        /** ｢18：00 - 24：00｣部分のチェックBOX */
        //this.model.setNight_chkn(this.Night_chkn);
        this.model.setNight(this.night_sel);
        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

    }


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * Model(ActionSuporrt)値・Session値をSessionTeacherScheduleにセット<br>
     * <br>
     * @throws Exception
     */
    private void modelAsToSession() throws Exception {

        SessionTeacherSchedule sessionData = new SessionTeacherSchedule();

        sessionData.setResult(this.model);

        SessionDataUtil.setSessionData(sessionData);

    }

    /**
     * SessionTeacherSchedule値を画面項目にセット。<br>
     * <br>
     * @throws Exception
     */
    public void SessionTeacherScheduleToModel() throws Exception {

        SessionTeacherSchedule sData = (SessionTeacherSchedule) SessionDataUtil.getSessionData(SessionTeacherSchedule.class.toString());

        if (sData != null) {
            this.model = sData.getResult();
        }

        //SessionCodeControlMstのクリア
        SessionDataUtil.clearSessionData(SessionCodeControlMst.class.toString());

    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * 画面の選択値の保持。<br>
     * <br>
     * 一覧選択のデータを保持する。<br>
     * <br>
     * @param details1 ｢00：00 - 06：00｣<br>
     * @return なし<br>
     * @throws Exception
     */
    public void listSelSet() throws Exception {

        String nowSelList = null;            // 予約区分(予約状況)4桁(0001:○ /0002:● /0003:－   /0004:△   /0005:▲)：画面の選択値
        ArrayList<SchResTCodeManagMDto> schResTListDto = null;

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:    // ｢00：00 - 06：00｣部分
                    nowSelList = this.midnight_sel;                       // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto0();
                    break;
                case 1:    // ｢06：00 - 12：00｣部分
                    nowSelList = this.morning_sel;                        // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto1();
                    break;
                case 2:    // ｢12：00 - 18：00｣部分
                    nowSelList = this.daytime_sel;                        // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto2();
                    break;
                case 3:    // ｢18：00 - 24：00｣部分
                    nowSelList = this.night_sel;                          // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto3();
                    break;
            }

            String[] nowSel = nowSelList.substring(0, nowSelList.length())
                .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                .split(String.valueOf(NaikaraTalkConstants.COMMA));        // 予約区分(予約状況)：画面の選択値

            // 9コマ×14日(2週間)単位に処理
            int skipCnt = 0;
            int nowSelCnt = 0;
            int col = 0;        // 縦：日付(2週間)
            int row = 0;        // 横：コマ数
            int amari = -1;

            // 9コマ*14日＝0～125回
            for (int j = 0; j < 126; j++) {

                // ◆◆◆ 表示時と現在の値の入れ替え処理(エラー時に現在の値を保持させて表示させるため) ↓↓↓↓↓
                if (j != 0) {
                    col = col + 1;     // 日付(2週間)
                    amari = j%14;      // 14＝2週間
                }

                if (amari == 0) {
                    row = row + 1;     // コマ数
                    col = 0;           // 日付(2週間)
                }
                // ◆◆◆ 表示時と現在の値の入れ替え処理(エラー時に現在の値を保持させて表示させるため) ↑↑↑↑↑↑

                SchResTCodeManagMDto schResTDto = schResTListDto.get(row);
                ArrayList<ScheduleReservationTrnDto> schTDto = schResTDto.getScheduleReservationTrnDto();
                ScheduleReservationTrnDto schrDto = schTDto.get(col);
                if (schrDto == null) {
                    continue;
                }

                if (StringUtils.equals(schrDto.getDisabledFlg(), NaikaraTalkConstants.TRUE)) {
                    // 画面で使用不可の場合
                    // 初期表示時で予約済、応相談予約済(仮予約)のデータは処理対象外 ※応相談予約済(予約確定)の場合は0002：予約済へ変換している
                    skipCnt = skipCnt + 1;
                    continue;
                }

                nowSelCnt = j-skipCnt;  // Lockしている項目の選択値は画面から引き渡されないので減算

                // 表示時と現在の値の入れ替え処理
                schrDto.setDefaultSelValue(nowSel[nowSelCnt]);
                // 格納先の判定
                switch (i) {
                case 0:    // ｢00：00 - 06：00｣部分
                    model.setSchResTCodeManagMDto0(schResTListDto);
                    break;
                case 1:    // ｢06：00 - 12：00｣部分
                    model.setSchResTCodeManagMDto1(schResTListDto);
                    break;
                case 2:    // ｢12：00 - 18：00｣部分
                    model.setSchResTCodeManagMDto2(schResTListDto);
                    break;
                case 3:    // ｢18：00 - 24：00｣部分
                    model.setSchResTCodeManagMDto3(schResTListDto);
                    break;
                }

            }    // for (int j = 0; j < nowSel.length-1; j++) {

        }    // for (int i = 0; i < 4; i++) {


    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


    /** 検索結果 */
    protected TeacherScheduleModel model = new TeacherScheduleModel();

    /** 時間（符号） */
    protected String timeMark;
    /** 時間(分) */
    protected BigDecimal timeMinutes;
    /** サマータイム(時間)(符号) */
    protected String sumTimeMark;
    /** サマータイム(分) */
    protected BigDecimal sumTimeMinutes;
    /** 備考 */
    protected String remark;
    /** 講師ID */
    protected String teacherId;
    /** 基点日フラグ */
    protected int dateFlg;

    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    /** ｢00：00 - 06：00｣部分のチェックBOX⇒ドロップダウン */
    //protected String[] Midnight_chkn;
    protected String midnight_sel;
    protected Map<String, String> midnight_sell = new LinkedHashMap<String, String>();

    /** ｢06：00 - 12：00｣部分のチェックBOX⇒ドロップダウン */
    //protected String[] Morning_chkn;
    protected String morning_sel;
    protected Map<String, String> morning_sell = new LinkedHashMap<String, String>();

    /** ｢12：00 - 18：00｣部分のチェックBOX⇒ドロップダウン */
    //protected String[] Daytime_chkn;
    protected String daytime_sel;
    protected Map<String, String> daytime_sell = new LinkedHashMap<String, String>();

    /** ｢18：00 - 24：00｣部分のチェックBOX⇒ドロップダウン */
    //protected String[] Night_chkn;
    protected String night_sel;
    protected Map<String, String> night_sell = new LinkedHashMap<String, String>();
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


    /** メッセージ */
    protected String message;
    /** 明細0表示フラグ */
    protected boolean displayFlg0;
    /** 明細1表示フラグ */
    protected boolean displayFlg1;
    /** 明細2表示フラグ */
    protected boolean displayFlg2;
    /** 明細3表示フラグ */
    protected boolean displayFlg3;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return model
     */
    public TeacherScheduleModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherScheduleModel model) {
        this.model = model;
    }

    /**
     * @return timeMark
     */
    public String getTimeMark() {
        return timeMark;
    }

    /**
     * @param timeMark セットする timeMark
     */
    public void setTimeMark(String timeMark) {
        this.timeMark = timeMark;
    }

    /**
     * @return timeMinutes
     */
    public BigDecimal getTimeMinutes() {
        return timeMinutes;
    }

    /**
     * @param timeMinutes セットする timeMinutes
     */
    public void setTimeMinutes(BigDecimal timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    /**
     * @return sumTimeMark
     */
    public String getSumTimeMark() {
        return sumTimeMark;
    }

    /**
     * @param sumTimeMark セットする sumTimeMark
     */
    public void setSumTimeMark(String sumTimeMark) {
        this.sumTimeMark = sumTimeMark;
    }

    /**
     * @return sumTimeMinutes
     */
    public BigDecimal getSumTimeMinutes() {
        return sumTimeMinutes;
    }

    /**
     * @param sumTimeMinutes セットする sumTimeMinutes
     */
    public void setSumTimeMinutes(BigDecimal sumTimeMinutes) {
        this.sumTimeMinutes = sumTimeMinutes;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark セットする remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId セットする teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    /**
     * @return midnight_chkn
     */
    //public String[] getMidnight_chkn() {
    //    return Midnight_chkn;
    //}

    ///**
    // * @param midnight_chkn セットする midnight_chkn
    // */
    //public void setMidnight_chkn(String[] midnight_chkn) {
    //    Midnight_chkn = midnight_chkn;
    //}

    ///**
    // * @return morning_chkn
    // */
    //public String[] getMorning_chkn() {
    //    return Morning_chkn;
    //}

    ///**
    // * @param morning_chkn セットする morning_chkn
    // */
    //public void setMorning_chkn(String[] morning_chkn) {
    //    Morning_chkn = morning_chkn;
    //}

    ///**
    // * @return daytime_chkn
    // */
    //public String[] getDaytime_chkn() {
    //    return Daytime_chkn;
    //}

    ///**
    // * @param daytime_chkn セットする daytime_chkn
    // */
    //public void setDaytime_chkn(String[] daytime_chkn) {
    //    Daytime_chkn = daytime_chkn;
    //}

    ///**
    // * @return night_chkn
    // */
    //public String[] getNight_chkn() {
    //    return Night_chkn;
    //}

    ///**
    // * @param night_chkn セットする night_chkn
    // */
    //public void setNight_chkn(String[] night_chkn) {
    //    Night_chkn = night_chkn;
    //}

    //◆◆◆ ｢00：00 - 06：00｣部分
    /**
     * @return midnight_sel
     */
    public String getMidnight_sel() {
        return midnight_sel;
    }

    /**
     * @param midnight_sel セットする midnight_sel
     */
    public void setMidnight_sel(String midnight_sel) {
        this.midnight_sel = midnight_sel;
    }

    /**
     * @return midnight_sell
     */
    public Map<String, String> getMidnight_sell() {
        return midnight_sell;
    }

    /**
     * @param midnight_sell セットする midnight_sell
     */
    public void setMidnight_sell(Map<String, String> midnight_sell) {
        this.midnight_sell = midnight_sell;
    }

    //◆◆◆ ｢06：00 - 12：00｣部分
    /**
    * @return morning_sel
    */
    public String getMorning_sel() {
        return morning_sel;
    }

    /**
    * @param morning_sel セットする morning_sel
    */
    public void setMorning_sel(String morning_sel) {
        this.morning_sel = morning_sel;
    }

    /**
    * @return morning_sell
    */
    public Map<String, String> getMorning_sell() {
        return morning_sell;
    }

    /**
    * @param morning_sell セットする morning_sell
    */
    public void setMorning_sell(Map<String, String> morning_sell) {
        this.morning_sell = morning_sell;
    }

    //◆◆◆ ｢12：00 - 18：00｣部分
    /**
    * @return daytime_sel
    */
    public String getDaytime_sel() {
        return daytime_sel;
    }

    /**
    * @param daytime_sel セットする daytime_sel
    */
    public void setDaytime_sel(String daytime_sel) {
        this.daytime_sel = daytime_sel;
    }

    /**
    * @return daytime_sell
    */
    public Map<String, String> getDaytime_sell() {
        return daytime_sell;
    }

    /**
    * @param daytime_sell セットする daytime_sell
    */
    public void setDaytime_sell(Map<String, String> daytime_sell) {
        this.daytime_sell = daytime_sell;
    }

    //◆◆◆ ｢18：00 - 24：00｣部分
    /**
    * @return night_sel
    */
    public String getNight_sel() {
        return night_sel;
    }

    /**
    * @param night_sel セットする night_sel
    */
    public void setNight_sel(String night_sel) {
        this.night_sel = night_sel;
    }

    /**
    * @return night_sell
    */
    public Map<String, String> getNight_sell() {
        return night_sell;
    }

    /**
    * @param night_sell セットする night_sell
    */
    public void setNight_sell(Map<String, String> night_sell) {
        this.night_sell = night_sell;
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return dateFlg
     */
    public int getDateFlg() {
        return dateFlg;
    }

    /**
     * @param dateFlg セットする dateFlg
     */
    public void setDateFlg(int dateFlg) {
        this.dateFlg = dateFlg;
    }

    // ｢00：00 - 06：00｣用
    /**
     * @return displayFlg0
     */
    public boolean isDisplayFlg0() {
        return displayFlg0;
    }

    /**
     * @param displayFlg0 セットする displayFlg0
     */
    public void setDisplayFlg0(boolean displayFlg0) {
        this.displayFlg0 = displayFlg0;
    }

    // ｢06：00 - 12：00｣用
    /**
     * @return displayFlg1
     */
    public boolean isDisplayFlg1() {
        return displayFlg1;
    }

    /**
     * @param displayFlg1 セットする displayFlg1
     */
    public void setDisplayFlg1(boolean displayFlg1) {
        this.displayFlg1 = displayFlg1;
    }

    // ｢12：00 - 18：00｣用
    /**
     * @return displayFlg2
     */
    public boolean isDisplayFlg2() {
        return displayFlg2;
    }

    /**
     * @param displayFlg2 セットする displayFlg2
     */
    public void setDisplayFlg2(boolean displayFlg2) {
        this.displayFlg2 = displayFlg2;
    }

    // ｢18：00 - 24：00｣用
    /**
     * @return displayFlg3
     */
    public boolean isDisplayFlg3() {
        return displayFlg3;
    }

    /**
     * @param displayFlg3 セットする displayFlg3
     */
    public void setDisplayFlg3(boolean displayFlg3) {
        this.displayFlg3 = displayFlg3;
    }

}
