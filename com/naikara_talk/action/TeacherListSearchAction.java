package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherListSearchService;
import com.naikara_talk.sessiondata.SessionTeacher;
import com.naikara_talk.sessiondata.SessionTeacherListBtnControl;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）Actionクラス<br>
 * <b>クラス概要       :</b>登録済みの講師情報の検索処理を行い。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class TeacherListSearchAction extends TeacherListActionSupport {

    private static final long serialVersionUID = 1L;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /**
     * 講師情報を検索。<br>
     * <br>
     * 講師情報の検索処理を行い。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        setupModel();

        TeacherListSearchService service = new TeacherListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                    .getReturnOnFlg();

            // 検索判断フラグ
            this.hasSearchFlg = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                    .getHasSearchFlg();
        }

        // 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
        this.hasSearchTcontactFlg = NaikaraTalkConstants.FALSE;  // 初期化
        if ((SessionTeacherListBtnControl) SessionDataUtil.getSessionData(SessionTeacherListBtnControl.class.toString()) != null) {
            this.hasSearchTcontactFlg = ((SessionTeacherListBtnControl) SessionDataUtil.getSessionData(SessionTeacherListBtnControl.class.toString()))
                    .getHasSearchTcontactFlg();
        }
        // 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正


        try {
            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionTeacherToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {

                // 講師一覧データ取得
                List<UserMstTeacherMstDto> retDtoList = service.selectTeacherList(this.model);

                // 件数のチェック
                // データ件数 ≦ 0件の場合
                if (retDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {

                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0020", new String[] {}));
                    return SUCCESS;

                    // 101件以上の場合
                } else if (retDtoList.size() > LIST_MAX_CNT) {

                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0023", new String[] { "101" }));
                    return SUCCESS;

                } else {

                    // 表示データの付帯情報の取得と設定
                    this.model.setResultList(retDtoList);

                    this.hasSearchFlg = Boolean.toString(Boolean.TRUE);

                    // 選択したラジオボタンをクリアする
                    this.select_rdl = NaikaraTalkConstants.BRANK;

                    // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
                    this.SessionTeacherToModelAfter();

                    // 戻る用に必要な情報を格納
                    this.modelToSessionTeacher();

                }
            }

            // 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
            this.error_noSelect = getMessage("EC0015", new String[] { "一覧部の左の選択" });
            // 2014/02/18 Add End 商品購入ページからの呼出追加に伴う修正

        } catch (Exception e) {
            throw new NaiException(e);
        }

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // 画面を返す
        return SUCCESS;
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値・SessionTeacher値をSessionTeacherにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    @Override
    protected void modelToSessionTeacher() throws Exception {

        // 戻る用に必要な情報を取得
        SessionTeacher sessionData = new SessionTeacher();
        // 戻る判定Onフラグ=Offとする
        sessionData.setReturnOnFlg(false);

        // 検索Key：講師ID
        sessionData.setSearchTeacherId(this.model.getTeacherId());
        // 検索Key：講師名(ニックネーム)
        sessionData.setSearchTeacherNm(this.model.getTeacherNm());
        // 検索Key：性別
        sessionData.setSearchSexKbn(this.model.getSexKbn());

        SessionDataUtil.setSessionData(sessionData);
    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionTeacher値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionTeacherToModelBefore() throws Exception {

        if ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()) != null) {

            boolean returnOnFlg = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                    .getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 講師ID
                String searchTeacherId = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class
                        .toString())).getSearchTeacherId();
                // 講師名(ニックネーム)
                String searchTeacherNm = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class
                        .toString())).getSearchTeacherNm();
                // 性別
                String searchSexKbn = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                        .getSearchSexKbn();

                // 検索Key：講師ID
                this.model.setTeacherId(searchTeacherId);
                // 検索Key：講師名(ニックネーム)
                this.model.setTeacherNm(searchTeacherNm);
                // 検索Key：性別
                this.model.setSexKbn(searchSexKbn);
            }
        }
    }

    /**
     * 検索の後 Session値 To Model<br>
     * <br>
     * SessionTeacher値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionTeacherToModelAfter() throws Exception {

        if ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()) != null) {
            // 戻る判定Onフラグ
            boolean returnOnFlg = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                    .getReturnOnFlg();

            if (returnOnFlg == true) {
                // 講師ID
                String teacherId = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                        .getTeacherId();
                // 講師名(ニックネーム)
                String teacherNm = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                        .getTeacherNm();
                // 性別
                String sexKbn = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                        .getSexKbn();
                // 検索Key：選択された明細のKey-講師ID
                String select_rdl = ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()))
                        .getSearchTeacherIdKey();

                // 検索Key：講師ID
                this.teacherId_txt = teacherId;
                // 検索Key：講師名(ニックネーム)
                this.teacherNm_txt = teacherNm;
                // 検索Key：性別
                this.sexKbn_rdl = sexKbn;
                this.select_rdl = select_rdl;
            }

            // sessionSessionTeacherのクリア
            SessionDataUtil.clearSessionData(SessionTeacher.class.toString());
        }
    }
}
