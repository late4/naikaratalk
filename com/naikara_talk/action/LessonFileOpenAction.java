package com.naikara_talk.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;

import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.service.LessonPdfDownloadService;
import com.naikara_talk.sessiondata.SessionLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)、講師　共通<br>
 * <b>クラス名称       :</b>レッスン画面 View the PDF File in a separate window:別画面でPDFファイルを開くActionクラス<br>
 * <b>クラス概要       :</b>レッスン画面別画面でPDFファイルを開くAction<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/03/28 TECS 新規作成
 */
public class LessonFileOpenAction extends LessonActionSupport {

    private static final long serialVersionUID = 1L;

    ByteArrayOutputStream baos = null;  // 出力用のバイトデータを書き込む用

    /**
     * ファイル出力処理。<br>
     * <br>
     * ファイル出力処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // メッセージ クリア
        String msg = null;
        this.addActionMessage(msg);

        // サービス生成(レッスンファイル)
        LessonPdfDownloadService service = new LessonPdfDownloadService();

        // サービス生成(レッスン予実テーブルの取得データ格納用)
        LessonLauncherModel viewModel = new LessonLauncherModel();

        // パラメタの設定
        this.setParameter(model);

        // HIDDEN用パラメータを作成する
        this.reservationNo = model.getReservationNo();

        //////////////////////////////////////////////////////
        // Fileデータの取得
        //////////////////////////////////////////////////////
        viewModel = service.downloadLessonPdf(model);

        // 出力用のバイトデータを書き込むjava.io.ByteArrayOutputStreamオブジェクトの生成
        baos = new ByteArrayOutputStream();

        try {
            // レッスン予実テーブル.レッスン中ファイルをByteで格納
            byte[] byteData = null;

            // レッスン予実テーブル.レッスン中ファイルの取得
            Blob lessonFile = viewModel.getBlobDuringLessons();

            // Blob型からByte型へ変換
            if (lessonFile == null) {
                byteData = new byte[4096];
            } else {
                byteData = lessonFile.getBytes(1, (int) lessonFile.length());
            }

            /* PDFファイルを出力 */
            if (byteData != null) {
                if (byteData.length > 0) {
                    baos.write(byteData);
                } else {
                    baos.write(0);
                }
            }


        } catch (Exception e) {
            log.info("LessonFileOpenAction Exception e.getMessage=" + e.getMessage());
            log.info("LessonFileOpenAction Exception e.getStackTrace=" + e.getStackTrace());
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    // 閉じて、このストリームに関連するすべてのシステムリソースを解放する
                    baos.flush();
                    baos.close();
                } catch (Exception e1) {
                    log.info("LessonFileOpenAction e1.getMessage=" + e1.getMessage());
                    log.info("LessonFileOpenAction e1.getMessage=" + e1.getStackTrace());
                    e1.printStackTrace();
                }
            }
        }
        return SUCCESS;
    }

    /**
     * ファイルの出力。<br>
     * <br>
     * ファイルの出力。<br>
     * <br>
     * @param なし<br>
     * @return InputStream<br>
     * @exception Exception
     */
    public InputStream getInputStream() {
        // ファイルの出力
        return new ByteArrayInputStream(baos.toByteArray());
    }


    /**
     * 各種のパラメータを設定する<br>
     * <br>
     *
     * @param なし
     * <br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    private void setParameter(LessonLauncherModel model) throws NaiException {
        // セッションIDを取得して設定
        setSessionId(SessionDataUtil.getSessionId());

        // セッションから情報を取得する
        SessionLesson data = (SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString());
        SessionUser SessionUserData = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()));
        String role = NaikaraTalkConstants.BRANK;
        String userId = NaikaraTalkConstants.BRANK;
        if ( SessionUserData != null ) {
            role = SessionUserData.getRole();        // ログイン中のユーザロール
            userId = SessionUserData.getUserId();    // ログイン中のユーザId
        }
        model.setRole(role);                         // ロール
        model.setUserId(userId);                     // ユーザID

        if (data == null) {
            // 処理終了
            return;
        }

        // 予約番号
        String reservationNo = data.getReservationNo();
        // TOKBOXのAPIキー
        int apiKey = data.getTokboxApiKey();
        // TOKBOXセッションID
        String tokboxSessionId = data.getTokboxSessionId();
        // TOKBOXトークンID
        String tokboxTokenId = data.getTokboxTokenId();
        // 担当講師ニックネーム
        String teacherNickNm = data.getTeacherNickNm();
        // 受講者ニックネーム
        String studentNickNm = data.getStudentNickNm();
        // コースネーム（日本語）
        String courseJnm = data.getCourseJnm();
        // コースネーム（英語）
        String courseEnm = data.getCourseEnm();
        // レッスン日程
        String lessonSchedule = data.getLessonSchedule();
        // レッスン時刻名
        String lessonTmNm = data.getLessonTmNm();
        // レッスン現地時刻名
        String localLessonTmNm = data.getLocalLessonTmNm();
        // コメント
        String commentTo = data.getCommentTo();
        // 簡易説明
        String courseSimpleExplanation = data.getCourseSimpleExplanation();
        // 添付付き有無フラグ
        String attachmentFlg = data.getAttachmentFlg();
        // 講師ID
        String teacherId = data.getTeacherId();
        // 受講者ID
        String studentId = data.getStudentId();
        // 現在時刻
        String nowTime = DateUtil.getSysDateTimeNoSplit();

        // パラメータを画面に渡す
        // 予約番号
        model.setReservationNo(reservationNo);
        // TOKBOXのAPIキー
        model.setTokboxApiKey(apiKey);
        // TOKBOXセッションID
        model.setTokboxSessionId(tokboxSessionId);
        // TOKBOXトークンID
        model.setTokboxTokenId(tokboxTokenId);
        // 担当講師ニックネーム
        model.setTeacherNickNm(teacherNickNm);
        // 受講者ニックネーム
        model.setStudentNickNm(studentNickNm);
        // コースネーム（日本語）
        model.setCourseJnm(courseJnm);
        // コースネーム（英語）
        model.setCourseEnm(courseEnm);
        // レッスン日程
        model.setLessonSchedule(lessonSchedule);
        // コメント
        model.setCommentTo(commentTo);
        // 簡易説明
        model.setCourseMemo(courseSimpleExplanation);
        // 添付付き有無フラグ
        model.setAttachmentFlg(attachmentFlg);
        // 講師ID
        model.setTeacherId(teacherId);
        // 受講者ID
        model.setStudentId(studentId);
        // 現在時刻
        model.setNowTime(nowTime);
        // レッスンファイル有無：有
        model.setHasDuringLessons(true);

        // 講師、受講者の判定
        if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
            // 講師の場合
            // 講師START押下時刻
            model.setTeacherStartDttm(nowTime);
            // レッスン時刻名
            model.setLessonTmNm(new StringBuffer(lessonTmNm).append(CONST_JST).toString());
            // レッスン現地時刻名
            model.setLocalLessonTmNm(new StringBuffer(localLessonTmNm).append(CONST_LOCAL).toString());
        } else {
            // 受講者の場合
            // 受講者START押下時刻
            model.setStudentStartDttm(nowTime);
        }

        // チャット履歴
        String str4 = NaikaraTalkConstants.BRANK;
        if (this.chatHistoryDownloadOther != null) {
            Pattern p1 = Pattern.compile("\t|\r|\n");
            Matcher m1 = p1.matcher(this.chatHistoryDownloadOther);
            String str1 = m1.replaceAll("");

            Pattern p2 = Pattern.compile("\"sdt02\">");
            Matcher m2 = p2.matcher(str1);
            String str2 = m2.replaceAll("'sdt02'>");

            Pattern p3 = Pattern.compile("\"sdt01\">");
            Matcher m3 = p3.matcher(str2);
            String str3 = m3.replaceAll("'sdt01'>");

            Pattern p4 = Pattern.compile("\"tcr\">");
            Matcher m4 = p4.matcher(str3);
            str4 = m4.replaceAll("'tcr'>");
        }
        model.setChatHistory(str4);

        // モデルを設定する
        this.setModel(model);
    }

    /** セッションID */
    private String sessionId;

    /**
     * セッションID 取得<br>
     * <br>
     * セッションID 取得を行う<br>
     * <br>
     * @param sessionId セッションID<br>
     * @return なし<br>
     * @exception なし
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * セッションID 設定<br>
     * <br>
     * セッションID 設定を行う<br>
     * <br>
     * @param sessionId セッションID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


}