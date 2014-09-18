package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.StuMPoiOwnTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentMstListLogic;
import com.naikara_talk.model.StudentMstListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】検索処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMstListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 1000;

    /** 検索前チェック：処理区分と押下ボタンの不整合 */
    public static final int ERR_PROS_BTN_MISMATCH = -1;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = -2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = -3;

    /** 検索前チェック： 権限無*/
    public static final int ERR_NO_AUTH = -4;

    /** 検索前チェック： 条件１エラー */
    public static final int ERR_ITEM_1 = 1;

    /** 検索前チェック： 条件２エラー */
    public static final int ERR_ITEM_2 = 2;

    /** 検索前チェック： 条件３エラー */
    public static final int ERR_ITEM_3 = 3;

    /** 検索前チェック： 条件４エラー */
    public static final int ERR_ITEM_4 = 4;

    /** 検索前チェック： 条件５エラー */
    public static final int ERR_ITEM_5 = 5;

    /**
     * 検索前チェック処理<br>
     * <br>
     * @param int
     * @return StudentMstListModel
     * @exception NaiException
     */
    public int checkPreSelect(StudentMstListModel model) throws NaiException {

        // 処理区分のチェック、処理区分が[新規]の場合は、メッセージ情報を設定する
        // '0':'新規','1':'修正','2':'照会'
        if (StringUtils.equals(StudentMstListModel.PROS_KBN_ADD, model.getProcessKbn_rdl())) {
            return ERR_PROS_BTN_MISMATCH;
        }

        // 処理区分が　[照会]　以外の場合で　且つ　操作者（ログイン者）の権限が　「スタッフ」　の場合は、メッセージ情報を
        String userRole = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // 処理区分
        String processKbn = model.getProcessKbn_rdl();

        // (1) 処理区分が [照会] 以外の場合で 且つ 操作者（ログイン者）の権限が 「スタッフ」
        // の場合は、メッセージ情報を設定する
        if (!StringUtils.equals(StudentMstListModel.PROS_KBN_REF, processKbn)
                && StringUtils.equals(SessionUser.ROLE_STAFF, userRole)) {
            return ERR_NO_AUTH;
        }

        /** 条件キー条件１のチェック */
        // 条件キー項目１ ＝ 空欄 且つ 条件キー条件の１ が ”なし”　以外の項目が選択されている場合
        if (StringUtils.isEmpty(model.getItemNm1_sel())
                && !StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_1;
        }

        // 条件キー項目１ ≠ 空欄 且つ 条件キー条件の１ が ”なし”の場合
        if ((!StringUtils.isEmpty(model.getItemNm1_sel()))
                && StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_1;
        }

        // 条件キー条件の１ が ”なし” 且つ 条件キー期間：開始日、条件キー期間：終了日に値が設定されている場
        if (StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_NONE)
                && (!StringUtils.isEmpty(model.getItemFrom1_txt()) || !StringUtils.isEmpty(model.getItemTo1_txt()))) {
            return ERR_ITEM_1;
        }

        // 条件キー条件の１ が ”範囲” 且つ 条件キー期間：開始日、条件キー期間：終了日の両方に、値が設定されていない場合
        if (StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)
                && (StringUtils.isEmpty(model.getItemFrom1_txt()) || StringUtils.isEmpty(model.getItemTo1_txt()))) {
            return ERR_ITEM_1;
        }
        // 条件キー条件の１ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：開始日に値が設定されていない場合
        if ((StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && StringUtils.equals(model.getItemFrom1_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_1;
        }

        // 条件キー条件の１ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：終了日に値が設定されている場合
        if ((StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn1_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && !StringUtils.equals(model.getItemTo1_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_1;
        }

        /** 条件キー条件２のチェック */
        // 条件キー項目２ ＝ 空欄 且つ 条件キー条件の２ が ”なし”　以外の項目が選択されている場合
        if (StringUtils.isEmpty(model.getItemNm2_sel())
                && !StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_2;
        }

        // 条件キー項目２ ≠ 空欄 且つ 条件キー条件の２ が ”なし”の場合
        if ((!StringUtils.isEmpty(model.getItemNm2_sel()))
                && StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_2;
        }

        // 条件キー条件の２ が ”なし” 且つ 条件キー期間：開始日、条件キー期間：終了日に値が設定されている場
        if (StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_NONE)
                && (!StringUtils.isEmpty(model.getItemFrom2_txt()) || !StringUtils.isEmpty(model.getItemTo2_txt()))) {
            return ERR_ITEM_2;
        }

        // 条件キー条件の２ が ”範囲” 且つ 条件キー期間：開始日、条件キー期間：終了日の両方に、値が設定されていない場合
        if (StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)
                && (StringUtils.isEmpty(model.getItemFrom2_txt()) || StringUtils.isEmpty(model.getItemTo2_txt()))) {
            return ERR_ITEM_2;
        }
        // 条件キー条件の２ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：開始日に値が設定されていない場合
        if ((StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && StringUtils.equals(model.getItemFrom2_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_2;
        }

        // 条件キー条件の２ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：終了日に値が設定されている場合
        if ((StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn2_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && !StringUtils.equals(model.getItemTo2_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_2;
        }

        /** 条件キー条件３のチェック */
        // 条件キー項目３ ＝ 空欄 且つ 条件キー条件の３ が ”なし”　以外の項目が選択されている場合
        if (StringUtils.isEmpty(model.getItemNm3_sel())
                && !StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_3;
        }

        // 条件キー項目３ ≠ 空欄 且つ 条件キー条件の３ が ”なし”の場合
        if ((!StringUtils.isEmpty(model.getItemNm3_sel()))
                && StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_3;
        }

        // 条件キー条件の３ が ”なし” 且つ 条件キー期間：開始日、条件キー期間：終了日に値が設定されている場
        if (StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_NONE)
                && (!StringUtils.isEmpty(model.getItemFrom3_txt()) || !StringUtils.isEmpty(model.getItemTo3_txt()))) {
            return ERR_ITEM_3;
        }

        // 条件キー条件の３ が ”範囲” 且つ 条件キー期間：開始日、条件キー期間：終了日の両方に、値が設定されていない場合
        if (StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)
                && (StringUtils.isEmpty(model.getItemFrom3_txt()) || StringUtils.isEmpty(model.getItemTo3_txt()))) {
            return ERR_ITEM_3;
        }
        // 条件キー条件の３ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：開始日に値が設定されていない場合
        if ((StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && StringUtils.equals(model.getItemFrom3_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_3;
        }

        // 条件キー条件の３ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：終了日に値が設定されている場合
        if ((StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn3_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && !StringUtils.equals(model.getItemTo3_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_3;
        }

        /** 条件キー条件４のチェック */
        // 条件キー項目４ ＝ 空欄 且つ 条件キー条件の４ が ”なし”　以外の項目が選択されている場合
        if (StringUtils.isEmpty(model.getItemNm4_sel())
                && !StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_4;
        }

        // 条件キー項目４ ≠ 空欄 且つ 条件キー条件の４ が ”なし”の場合
        if ((!StringUtils.isEmpty(model.getItemNm4_sel()))
                && StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_4;
        }

        // 条件キー条件の４ が ”なし” 且つ 条件キー期間：開始日、条件キー期間：終了日に値が設定されている場
        if (StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_NONE)
                && (!StringUtils.isEmpty(model.getItemFrom4_txt()) || !StringUtils.isEmpty(model.getItemTo4_txt()))) {
            return ERR_ITEM_4;
        }

        // 条件キー条件の４ が ”範囲” 且つ 条件キー期間：開始日、条件キー期間：終了日の両方に、値が設定されていない場合
        if (StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)
                && (StringUtils.isEmpty(model.getItemFrom4_txt()) || StringUtils.isEmpty(model.getItemTo4_txt()))) {
            return ERR_ITEM_4;
        }
        // 条件キー条件の４ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：開始日に値が設定されていない場合
        if ((StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && StringUtils.equals(model.getItemFrom4_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_4;
        }

        // 条件キー条件の４ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：終了日に値が設定されている場合
        if ((StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn4_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && !StringUtils.equals(model.getItemTo4_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_4;
        }

        /** 条件キー条件５のチェック */
        // 条件キー項目５ ＝ 空欄 且つ 条件キー条件の５ が ”なし”　以外の項目が選択されている場合
        if (StringUtils.isEmpty(model.getItemNm5_sel())
                && !StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_5;
        }

        // 条件キー項目５ ≠ 空欄 且つ 条件キー条件の５ が ”なし”の場合
        if ((!StringUtils.isEmpty(model.getItemNm5_sel()))
                && StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_NONE)) {
            return ERR_ITEM_5;
        }

        // 条件キー条件の５ が ”なし” 且つ 条件キー期間：開始日、条件キー期間：終了日に値が設定されている場
        if (StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_NONE)
                && (!StringUtils.isEmpty(model.getItemFrom5_txt()) || !StringUtils.isEmpty(model.getItemTo5_txt()))) {
            return ERR_ITEM_5;
        }

        // 条件キー条件の５ が ”範囲” 且つ 条件キー期間：開始日、条件キー期間：終了日の両方に、値が設定されていない場合
        if (StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_RANGE)
                && (StringUtils.isEmpty(model.getItemFrom5_txt()) || StringUtils.isEmpty(model.getItemTo5_txt()))) {
            return ERR_ITEM_5;
        }
        // 条件キー条件の５ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：開始日に値が設定されていない場合
        if ((StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && StringUtils.equals(model.getItemFrom5_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_5;
        }

        // 条件キー条件の５ が ”前方一致” 又は ”曖昧” 又は ”後方一致” 又は ”完全一致” のいずれかの場合 且つ
        // 条件キー期間：終了日に値が設定されている場合
        if ((StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_FOREEQU)
                || StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_LIKE)
                || StringUtils.equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_BACKEQU) || StringUtils
                    .equals(model.getItemCondn5_rdl(), StudentMstListModel.CONDITION_KBN_ALLEQU))
                && !StringUtils.equals(model.getItemTo5_txt(), NaikaraTalkConstants.BRANK)) {
            return ERR_ITEM_5;
        }

        // 入力チェック - DBアクセスありチェック
        // 共通部品：受講者マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (count == LIST_ZERO_CNT) {
            return ERR_ZERO_DATA;
        } else if (count > LIST_MAX_CNT) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return StudentMstListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param StudentMstListModel
     *            画面のパラメータ
     * @return int
     * @throws NaiException
     */
    public int getRowCount(StudentMstListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentMstListLogic studentMstListLogic = new StudentMstListLogic(conn);

            // DTOの初期化
            StuMPoiOwnTDto dto = new StuMPoiOwnTDto();

            // Model値をDTOにセット
            dto = modelToDto(model);

            // データの取得件数＆リターン

            List<StuMPoiOwnTDto> list = studentMstListLogic.selectList(dto);
            if (list.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                return LIST_ZERO_CNT;
            } else {
                return list.size();
            }

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * @param StuMPoiOwnTDto
     *            画面のパラメータ
     * @return List<StuMPoiOwnTDto>
     * @throws NaiException
     */
    public List<StuMPoiOwnTDto> selectList(StudentMstListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            StudentMstListLogic studentMstListLogic = new StudentMstListLogic(conn);

            // DTOの初期化
            StuMPoiOwnTDto dto = new StuMPoiOwnTDto();

            // Model値をDTOにセット
            dto = modelToDto(model);

            // データの取得＆リターン
            return studentMstListLogic.selectList(dto);

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
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
     * @param model
     *           StudentMstListModel
     * @return StuMPoiOwnTDto
     * @throws NaiException
     */
    private StuMPoiOwnTDto modelToDto(StudentMstListModel model) throws NaiException {

        // DTOの初期化
        StuMPoiOwnTDto prmDto = new StuMPoiOwnTDto();
        prmDto.setCustomerKbn(model.getCustomerKbn_rdl());                           // 検索Key：お客様区分
        prmDto.setItemNm1_sel(model.getItemNm1_sel());                               // 検索Key：項目名1
        prmDto.setItemNm2_sel(model.getItemNm2_sel());                               // 検索Key：項目名2
        prmDto.setItemNm3_sel(model.getItemNm3_sel());                               // 検索Key：項目名3
        prmDto.setItemNm4_sel(model.getItemNm4_sel());                               // 検索Key：項目名4
        prmDto.setItemNm5_sel(model.getItemNm5_sel());                               // 検索Key：項目名5
        prmDto.setItemCondn1_rdl(model.getItemCondn1_rdl());                         // 検索Key：項目条件1
        prmDto.setItemCondn2_rdl(model.getItemCondn2_rdl());                         // 検索Key：項目条件2
        prmDto.setItemCondn3_rdl(model.getItemCondn3_rdl());                         // 検索Key：項目条件3
        prmDto.setItemCondn4_rdl(model.getItemCondn4_rdl());                         // 検索Key：項目条件4
        prmDto.setItemCondn5_rdl(model.getItemCondn5_rdl());                         // 検索Key：項目条件5
        prmDto.setItemFrom1_txt(model.getItemFrom1_txt());                           // 検索Key：項目1from
        prmDto.setItemFrom2_txt(model.getItemFrom2_txt());                           // 検索Key：項目2from
        prmDto.setItemFrom3_txt(model.getItemFrom3_txt());                           // 検索Key：項目3from
        prmDto.setItemFrom4_txt(model.getItemFrom4_txt());                           // 検索Key：項目4from
        prmDto.setItemFrom5_txt(model.getItemFrom5_txt());                           // 検索Key：項目5from
        prmDto.setItemTo1_txt(model.getItemTo1_txt());                               // 検索Key：項目1to
        prmDto.setItemTo2_txt(model.getItemTo2_txt());                               // 検索Key：項目2to
        prmDto.setItemTo3_txt(model.getItemTo3_txt());                               // 検索Key：項目3to
        prmDto.setItemTo4_txt(model.getItemTo4_txt());                               // 検索Key：項目4to
        prmDto.setItemTo5_txt(model.getItemTo5_txt());                               // 検索Key：項目5to

        return prmDto;

    }
}
