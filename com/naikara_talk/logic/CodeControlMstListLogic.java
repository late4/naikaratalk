package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.naikara_talk.dao.CodeClassMstDao;
import com.naikara_talk.dao.CdManagMstCdClassMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.dto.CdManagMstCdClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンスLogicクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンスLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public class CodeControlMstListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public CodeControlMstListLogic(Connection con) {
		this.conn = con;
	}

    /** 並び順の初期値(0より大きい値) */
    public static final int ORDER_BY_INIT = 0;

    /**
     * コード種別名(ドロップダウンリスト)用のデータ取得。<br>
     * <br>
     * @param CodeClassMstDto
     * @return int:DataCount
     * @throws NaiException
     */
    public List<CodeClassMstDto> selectCodeClassMst(CodeClassMstDto dto) throws NaiException {

        //Listクラスのオブジェクトを生成
        List<CodeClassMstDto> list = new ArrayList<CodeClassMstDto>();

        //CodeClassMstDaoクラスのオブジェクトを生成
        CodeClassMstDao dao = new CodeClassMstDao(this.conn);

        // 並び順の生成
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(CodeClassMstDao.COND_ORDER_BY, OrderCondition.ASC);

        // コード種別名(ドロップダウンリスト)のデータを取得する
        list = dao.search(this.SetConditionsSel(dto), orderBy);

        // 戻り値
        return list;

    }


    /**
     * 権限チェック。<br>
     * <br>
     * @param CodeControlMstListModel
     * @return int:rturnCd
     * @throws NaiException
     */
    public int checkRole(CodeControlMstListModel model) throws NaiException {

        int rturnCd = CodeControlMstListModel.CHECK_OK;

        // ロール(権限) の取得処理
        String role = NaikaraTalkConstants.BRANK;
        SessionUser SessionUserData = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()));
        if (SessionUserData != null) {
            role = SessionUserData.getRole();    // ログイン中のユーザロール
        }

        if (StringUtils.equals(CodeControlMstListModel.PROS_KBN_DEL, model.getProcessKbn())) {
            // 処理区分[削除]の場合

            if (StringUtils.equals(SessionUser.ROLE_ADMIN, role)) {
                // 「管理者」の場合は[削除]権限なし
                rturnCd = CodeControlMstListModel.ERR_NO_DEL_ROLE;
            }

        }

        if (!StringUtils.equals(CodeControlMstListModel.PROS_KBN_REF, model.getProcessKbn()) &&
                StringUtils.equals(SessionUser.ROLE_STAFF, role)) {
            // 「スタッフ」の場合は[照会]のみ
            rturnCd = CodeControlMstListModel.ERR_NO_UPD_ROLE;
        }

        return rturnCd;
    }


    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param CdManagMstCdClassMstDto
     * @return int:DataCount
     * @throws NaiException
     */
    public int getRowCount(CdManagMstCdClassMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.CODE_MANAG_MST, SetConditions(dto));

        // 戻り値
        return DataCount;

    }


    /**
     * 検索処理。<br>
     * <br>
     * @param CdManagMstCdClassMstDto
     *            画面のパラメータ
     * @return List<CdManagMstCdClassMstDto>
     * @throws Exception
     */
    public List<CdManagMstCdClassMstDto> select(CdManagMstCdClassMstDto dto) throws NaiException {

        // CdManagMstCdClassMstDaoクラスの生成
        CdManagMstCdClassMstDao dao = new CdManagMstCdClassMstDao(this.conn);

        // 並び順:並び順 (予備：汎用コード) の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(CdManagMstCdClassMstDao.COND_CD_CATEGORY_M, OrderCondition.ASC);  // コード種別
        orderBy.addCondition(CdManagMstCdClassMstDao.COND_ORDER_BY_M, OrderCondition.ASC);     // 並び順
        orderBy.addCondition(CdManagMstCdClassMstDao.COND_MANAGER_CD, OrderCondition.ASC);     // 汎用コード

        // KEYでデータを取得する
        List<CdManagMstCdClassMstDto> list = dao.search(SetConditions(dto), orderBy);

        // 戻り値
        return list;

    }


    /**
     * (コード管理マスタメンテナンス<ドロップダウン>)検索条件の設定。<br>
     * <br>
     * @param CdManagMstCdClassMstDto
     * @return ConditionMapper:conditions
     * @throws NaiException
    */
    private ConditionMapper SetConditionsSel(CodeClassMstDto dto) throws NaiException {

        // ◆◆◆ 検索条件の作成 ◆◆◆

        // 検索条件の作成
        ConditionMapper  conditions = new ConditionMapper();


        // レッスン時刻 S01(※抽出から除く種別)
        conditions.addCondition(CodeClassMstDao.COND_CD_CATEGORY,
                    ConditionMapper.OPERATOR_NOT_EQUAL, NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);

        // 月次バッチ処理日S02 (※抽出から除く種別)
        conditions.addCondition(CodeClassMstDao.COND_CD_CATEGORY,
                ConditionMapper.OPERATOR_NOT_EQUAL, NaikaraTalkConstants.CODE_CATEGORY_MONTHLY_BATCH_S);

        // ペイパル情報 S03(※抽出から除く種別)
        conditions.addCondition(CodeClassMstDao.COND_CD_CATEGORY,
                ConditionMapper.OPERATOR_NOT_EQUAL, NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO);

        // 実行判定XMLファイルのパスS04(※抽出から除く種別)
        conditions.addCondition(CodeClassMstDao.COND_CD_CATEGORY,
                ConditionMapper.OPERATOR_NOT_EQUAL, NaikaraTalkConstants.EXEC_JUDGE_XML_PATH);

        // 添付付きメール送信の一時的な格納先S05(※抽出から除く種別)
        conditions.addCondition(CodeClassMstDao.COND_CD_CATEGORY,
                ConditionMapper.OPERATOR_NOT_EQUAL, NaikaraTalkConstants.CODE_CATEGORY_EMAIL_ATTACH_TEMPATH);

        // ダイレクトメール送信用のHTMLメールの画像格納先S06(※抽出から除く種別)
        conditions.addCondition(CodeClassMstDao.COND_CD_CATEGORY,
                ConditionMapper.OPERATOR_NOT_EQUAL, NaikaraTalkConstants.CODE_CATEGORY_HTML_EMAIL_IMG_PATH);


        // コード種別名が選択されている場合(完全一致検索)-更新時のチェック時のみ設定される
        if (!StringUtils.isEmpty(dto.getCdCategory())) {
            conditions.addCondition(CodeClassMstDao.COND_CD_CATEGORY,
                    ConditionMapper.OPERATOR_EQUAL, dto.getCdCategory());
        }

        // 戻り値
        return conditions;


    }


    /**
     * (コード管理マスタメンテナンス【一覧】)検索条件の設定。<br>
     * <br>
     * @param CdManagMstCdClassMstDto
     * @return ConditionMapper:conditions
     * @throws NaiException
    */
    private ConditionMapper SetConditions(CdManagMstCdClassMstDto dto) throws NaiException {

        // ◆◆◆ 検索条件の作成 ◆◆◆

        // ConditionMapperクラスの生成
        ConditionMapper  conditions = new ConditionMapper();

        // StringBufferの生成
        StringBuffer work = new StringBuffer();

        // コード種別名が選択されている場合(完全一致検索)
        if (!StringUtils.isEmpty(dto.getCdCategory())) {
            conditions.addCondition(CdManagMstCdClassMstDao.COND_CD_CATEGORY_M,
                    ConditionMapper.OPERATOR_EQUAL, dto.getCdCategory());
        }

        // 汎用コードが選択されている場合(曖昧検索)
        if (!StringUtils.isEmpty(dto.getManagerCd())) {
            work.setLength(0);
            work.append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);
            work.append(dto.getManagerCd()).append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);

            conditions.addCondition(CdManagMstCdClassMstDao.COND_MANAGER_CD,
                    ConditionMapper.OPERATOR_LIKE, work.toString());
        }

        // 汎用フィールドが選択されている場合(曖昧検索)
        if (!StringUtils.isEmpty(dto.getManagerNm())) {
            work.setLength(0);
            work.append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);
            work.append(dto.getManagerNm()).append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);

            conditions.addCondition(CdManagMstCdClassMstDao.COND_MANAGER_NM,
                    ConditionMapper.OPERATOR_LIKE, work.toString());
        }


        // 戻り値
        return conditions;


    }


}
