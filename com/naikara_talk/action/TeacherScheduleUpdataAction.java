package com.naikara_talk.action;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.SchResTCodeManagMDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
//import com.naikara_talk.service.TeacherScheduleLoadService;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.service.TeacherScheduleUpdataService;
//import com.naikara_talk.sessiondata.SessionCodeControlMst;
//import com.naikara_talk.sessiondata.SessionTeacherSchedule;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュール。<br>
 * <b>クラス名称       :</b>講師スケジュール更新処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師予定予約テーブルの情報更新ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherScheduleUpdataAction extends TeacherScheduleActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String NEXTPAGE <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // TeacherScheduleUpdataServiceの生成
        TeacherScheduleUpdataService service = new TeacherScheduleUpdataService();

        int unt;

        try {

            // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
            this.SessionTeacherScheduleToModel();
            // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

            // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
            // ◆◆◆ 一覧選択のデータの取得(明細)と model.setScheduleReservationTrnDtoへの設定 ◆◆◆
            //this.load(this.Midnight_chkn, this.Morning_chkn, this.Daytime_chkn, this.Night_chkn);
            this.loadSet();
            // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

            // ◆◆◆画面のデータをmodelにセット◆◆◆
            setupModel();

            // ◆◆◆関連チェック ・(errorCheck内のerrorCheck2にて)追加処理 ◆◆◆
            if (this.errorCheck()) {
                // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
                // ※画面で選択した状態が保持されないため、削除
                //// 初期処理(講師予定予約テーブルのデータ取得処理)
                //TeacherScheduleLoadService loadService = new TeacherScheduleLoadService();
                //this.model.setDateFlg(this.dateFlg);
                //this.model = loadService.select(this.model);
                // 2014/06/02 レッスン予約に関する「応相談」対応 Del End

                // 返却
                return SUCCESS;
            }

            // ◆◆◆更新処理◆◆◆
            unt = service.update(this.model);

            // ◆◆◆更新結果の判定◆◆◆
            if (unt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
                // 排他エラーメッセージの設定
                String msg = getMessage("EE0014", new String[] { "", "" });
                this.addActionMessage(msg);

                // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
                // ※画面で選択した状態が保持されないため、削除
                // 初期処理(講師予定予約テーブルのデータ取得処理)
                //TeacherScheduleLoadService loadService = new TeacherScheduleLoadService();
                //this.model.setDateFlg(this.dateFlg);
                //this.model = loadService.select(this.model);
                // 2014/06/02 レッスン予約に関する「応相談」対応 Del End

                // 返却
                return SUCCESS;
            } else {
                // 更新完了メッセージの設定
                this.message = getMessage("IT0011", new String[] { "", "" });
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.TEACHER_MY_PAGE_LOAD);

        // 返却
        return NEXTPAGE;
    }

    /**
     * 一覧選択のデータの取得。<br>
     * <br>
     * 一覧選択のデータを取得する。<br>
     * <br>
     * @param details1 ｢00：00 - 06：00｣<br>
     * @param details2 ｢06：00 - 12：00｣明細チェックBOX<br>
     * @param details3 ｢12：00 - 18：00｣明細チェックBOX<br>
     * @param details4 ｢18：00 - 24：00｣明細チェックBOX<br>
     * @return なし<br>
     * @throws Exception
     */
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    // private void load(String[] details1, String[] details2, String[] details3, String[] details4) throws Exception {
    private void loadSet() throws Exception {
        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

        ArrayList<ScheduleReservationTrnDto> scheduleReservationTrnDto = new ArrayList<ScheduleReservationTrnDto>();


        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
        String nowSelList = null;            // 予約区分(予約状況)4桁(0001:○ /0002:● /0003:－   /0004:△   /0005:▲)：画面の選択値
        ArrayList<SchResTCodeManagMDto> schResTListDto = null;

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:    // ｢00：00 - 06：00｣部分
                    nowSelList = this.midnight_sel;                                    // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto0();
                    break;
                case 1:    // ｢06：00 - 12：00｣部分
                    nowSelList = this.morning_sel;                                    // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto1();
                    break;
                case 2:    // ｢12：00 - 18：00｣部分
                    nowSelList = this.daytime_sel;                                    // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto2();
                    break;
                case 3:    // ｢18：00 - 24：00｣部分
                    nowSelList = this.night_sel;                                // 予約区分(予約状況)4桁：画面の選択値
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

                // ◆◆◆ 表示時と現在の値の入れ替え処理(エラー時に現在の値を保持させて表示させるため) ↓↓↓↓↓
                // 表示時と現在の値の入れ替え処理(エラー時に現在の値を保持させて表示させるため)。
                // err後再度初期表示時と同じ値を設定する可能性ありのため、この位置にて設定
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
                // ◆◆◆ 表示時と現在の値の入れ替え処理(エラー時に現在の値を保持させて表示させるため) ↑↑↑↑↑↑

                if (StringUtils.equals(schrDto.getReservationState(), nowSel[nowSelCnt])) {
                    // 表示時と現在の選択値が同じ場合は処理しない(更新対象のみmodel.setScheduleReservationTrnDtoへ格納)。次の明細へ
                    continue;
                }

                // ◆◆◆ 予約区分(予約状況)の判定
                String reservationKbn = NaikaraTalkConstants.RESERV_KBN_NO;
                if (StringUtils.equals(nowSel[nowSelCnt], NaikaraTalkConstants.RESERV_STATE_YES)) {
                    // 予約可 の場合
                    reservationKbn = NaikaraTalkConstants.RESERV_KBN_YES;
                } else if (StringUtils.equals(nowSel[nowSelCnt], NaikaraTalkConstants.RESERV_STATE_ALREADY)) {
                    // 受講者から予約済 の場合, 受講者から応相談-予約済(予約確定) の場合
                    reservationKbn = NaikaraTalkConstants.RESERV_KBN_ALREADY;
                } else if (StringUtils.equals(nowSel[nowSelCnt], NaikaraTalkConstants.RESERV_STATE_CON_YES)) {
                    // 応相談可 の場合
                     reservationKbn = NaikaraTalkConstants.RESERV_KBN_CON_YES;
                } else if (StringUtils.equals(nowSel[nowSelCnt], NaikaraTalkConstants.RESERV_STATE_CON_PROVISIONAL_RESERV)) {
                    // 受講者から応相談-予約済(仮予約) の場合
                    reservationKbn = NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV;
                } else {
                    // 予約受付不可 の場合
                    reservationKbn = NaikaraTalkConstants.RESERV_KBN_NO;
                }

                // ◆◆◆ 操作者
                String userId = NaikaraTalkConstants.RESERV_STATE_NO;
                SessionUser user = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()));
                if ( user != null ) {
                    userId = user.getUserId();
                }

                // ◆◆◆ 更新値の設定
                // ScheduleReservationTrnDtoの生成
                ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();
                dto.setTeacherId(this.teacherId);                                              // 変更なし：(KEY)講師ID
                dto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(schrDto.getLessonDt()));    // 変更なし：(KEY)レッスン日
                dto.setLessonTmCd(schrDto.getLessonTmCd());                                    // 変更なし：(KEY)レッスン時刻コード
                dto.setReservationKbn(reservationKbn);                                         // <選択値で> 予約区分(予約状況)
                dto.setReservationNo(null);                                                    // 変更なし：予約No        ※追加処理用に設定。更新の場合、後の処理で検索結果の値が設定される
                dto.setStudentId(null);                                                        // 変更なし：受講者ID      ※追加処理用に設定。更新の場合、後の処理で検索結果の値が設定される
                dto.setCourseCd(null);                                                         // 変更なし：コースコード  ※追加処理用に設定。更新の場合、後の処理で検索結果の値が設定される
                dto.setInsertCd(this.teacherId);                                               // 変更なし：登録者コード  ※追加処理用に設定。更新の場合、後の処理で検索結果の値が設定される
                dto.setRecordVerNo(schrDto.getRecordVerNo());                                  // レコードバージョン番号
                dto.setUpdateCd(userId);                                                       // <操作者で> 更新者コード

                // ◆◆◆ ArrayList<ScheduleReservationTrnDto> への設定
                scheduleReservationTrnDto.add(dto);

            }    // for (int j = 0; j < nowSel.length-1; j++) {

        }    // for (int i = 0; i < 4; i++) {
        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
/*
        if (details1 != null) {
            for (String selected : details1) {
                // 明細チェックBOXのデータを取得
                String[] values = selected.substring(1, selected.length() - 1)
                        .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                        .split(String.valueOf(NaikaraTalkConstants.COMMA));

                // 一覧 選択されたチェックBOXのデータの設定
                ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();

                // レッスン日
                dto.setLessonDt(values[0]);
                // レッスン時刻コード
                dto.setLessonTmCd(values[1]);
                if (values.length > 2) {
                    // レコードバージョン番号
                    dto.setRecordVerNo(Integer.parseInt(values[2]));
                    // 予約区分(予約状況)
                    dto.setReservationKbn(values[3]);
                } else {
                    // レコードバージョン番号
                    dto.setRecordVerNo(0);
                    // 予約区分(予約状況)
                    dto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_NO);
                }
                // 講師ID
                dto.setTeacherId(this.teacherId);
                // 更新者コード
                dto.setUpdateCd(this.teacherId);
                // 登録者コード
                dto.setInsertCd(this.teacherId);

                scheduleReservationTrnDto.add(dto);
            }

        }

        if (details2 != null) {
            for (String selected : details2) {
                // 明細チェックBOXのデータを取得
                String[] values = selected.substring(1, selected.length() - 1)
                        .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                        .split(String.valueOf(NaikaraTalkConstants.COMMA));

                // 一覧から選択されたチェックBOXのデータの設定
                ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();
                // レッスン日
                dto.setLessonDt(values[0]);
                // レッスン時刻コード
                dto.setLessonTmCd(values[1]);
                if (values.length > 2) {
                    // レコードバージョン番号
                    dto.setRecordVerNo(Integer.parseInt(values[2]));
                    // 予約区分(予約状況)
                    dto.setReservationKbn(values[3]);
                } else {
                    // レコードバージョン番号
                    dto.setRecordVerNo(0);
                    // 予約区分(予約状況)
                    dto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_NO);
                }
                // 講師ID
                dto.setTeacherId(this.teacherId);
                // 更新者コード
                dto.setUpdateCd(this.teacherId);
                // 登録者コード
                dto.setInsertCd(this.teacherId);

                scheduleReservationTrnDto.add(dto);
            }
        }

        if (details3 != null) {
            for (String selected : details3) {
                // 明細チェックBOXのデータを取得
                String[] values = selected.substring(1, selected.length() - 1)
                        .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                        .split(String.valueOf(NaikaraTalkConstants.COMMA));

                // 一覧から選択されたチェックBOXのデータの設定
                ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();
                // レッスン日
                dto.setLessonDt(values[0]);
                // レッスン時刻コード
                dto.setLessonTmCd(values[1]);
                if (values.length > 2) {
                    // レコードバージョン番号
                    dto.setRecordVerNo(Integer.parseInt(values[2]));
                    // 予約区分(予約状況)
                    dto.setReservationKbn(values[3]);
                } else {
                    // レコードバージョン番号
                    dto.setRecordVerNo(0);
                    // 予約区分(予約状況)
                    dto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_NO);
                }
                // 講師ID
                dto.setTeacherId(this.teacherId);
                // 更新者コード
                dto.setUpdateCd(this.teacherId);
                // 登録者コード
                dto.setInsertCd(this.teacherId);

                scheduleReservationTrnDto.add(dto);
            }
        }

        if (details4 != null) {
            for (String selected : details4) {
                // 明細チェックBOXのデータを取得
                String[] values = selected.substring(1, selected.length() - 1)
                        .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                        .split(String.valueOf(NaikaraTalkConstants.COMMA));

                // 一覧から選択されたチェックBOXのデータの設定
                ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();
                // レッスン日
                dto.setLessonDt(values[0]);
                // レッスン時刻コード
                dto.setLessonTmCd(values[1]);
                if (values.length > 2) {
                    // レコードバージョン番号
                    dto.setRecordVerNo(Integer.parseInt(values[2]));
                    // 予約区分(予約状況)
                    dto.setReservationKbn(values[3]);
                } else {
                    // レコードバージョン番号
                    dto.setRecordVerNo(0);
                    // 予約区分(予約状況)
                    dto.setReservationKbn(NaikaraTalkConstants.RESERV_KBN_NO);
                }
                // 講師ID
                dto.setTeacherId(this.teacherId);
                // 更新者コード
                dto.setUpdateCd(this.teacherId);
                // 登録者コード
                dto.setInsertCd(this.teacherId);

                scheduleReservationTrnDto.add(dto);
            }
        }

*/
        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


        // 更新対象の内容をmodelへ設定
        this.model.setScheduleReservationTrnDto(scheduleReservationTrnDto);

    }

    /**
     * 更新前チェック。<br>
     * <br>
     * @param なし;
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        // 更新処理のサービスの生成
        TeacherScheduleUpdataService service = new TeacherScheduleUpdataService();

        // 関連チェック
        int chkResult = service.errorCheck(this.model);

        // チェック結果の判定
        switch (chkResult) {
        // 選択区分（日付、時刻単位のチェックBOX)がONのものが１つも存在しない場合
        case TeacherScheduleUpdataService.ERR_CHECK_ON:
            this.addActionMessage(getMessage("ET0016", new String[] { "selection classification", " 選択区分" }));
            return true;
        }

        // 2013/12/21
        // 講師マスタ．契約開始日～契約終了日の期間外の場合
        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        for (int i = 0; i < model.getScheduleReservationTrnDto().size(); i++) {

            ScheduleReservationTrnDto scheduleRTrnDto = model.getScheduleReservationTrnDto().get(i);

            String lessonDt =  NaikaraStringUtil.converToYYYY_MM_DD(scheduleRTrnDto.getLessonDt());

            // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
            if (StringUtils.equals(scheduleRTrnDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)
            		|| StringUtils.equals(scheduleRTrnDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)
                    || StringUtils.equals(scheduleRTrnDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
                // 選択した値が「予約済」、「応相談-予約済(仮予約)」、「応相談-予約済(予約確定)」の場合はエラーとする
                StringBuffer sbMsgE = new StringBuffer();
                StringBuffer sbMsgJ = new StringBuffer();
                String lessonTmNm = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S, scheduleRTrnDto.getLessonTmCd());
                sbMsgE.append("－").append(" or ").append("△").append(" or ").append("○");
                sbMsgE.append("(").append(lessonDt).append(" ").append(lessonTmNm).append(")");
                sbMsgJ.append("○").append(" 又は ").append("△").append(" 又は ").append("－");
                sbMsgJ.append("(").append(lessonDt).append(" ").append(lessonTmNm).append(")");
                this.addActionMessage(getMessage("ET0015", new String[] { sbMsgE.toString(), sbMsgJ.toString() }));
                return true;
            }
            // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

            TeacherMstDto prmDto = new TeacherMstDto();
            prmDto.setUserId(scheduleRTrnDto.getTeacherId());
            String lDt =  NaikaraStringUtil.converToYYYYMMDD(scheduleRTrnDto.getLessonDt());
            prmDto.setContractStartDt(lDt);
            prmDto.setContractEndDt(lDt);
            // 講師マスタ検索処理
            int cnt = service.teacherSearch(prmDto);

            // 講師マスタ.契約開始日≦画面の日付≦契約終了日外の場合はエラーとする
            if (cnt < 1) {
                // TeacherScheduleUpdataService.ERR_CONTRACT_DT_END;
                this.addActionMessage(getMessage("ET0076", new String[] { NaikaraStringUtil.converToYYYY_MM_DD(lDt),
                		NaikaraStringUtil.converToYYYY_MM_DD(lDt) }));
                return true;

            }
        }

        // 関連チェック
        int chkResult2 = service.errorCheck2(this.model);

        // チェック結果の判定
        switch (chkResult2) {
            // 講師予定予約テーブル．予約区分 ＝ ”2” (予約済み) の場合のエラー
        case TeacherScheduleUpdataService.ERR_KBN_END:
            this.addActionMessage(getMessage("ET0041", new String[] { "", "" }));
            return true;
        }

        return false;
    }

}
