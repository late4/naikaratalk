package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.MailWithAttachmentsSendLogic;
import com.naikara_talk.model.MailWithAttachmentsSendModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>マイページ初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */
public class MailWithAttachmentsSendLoadService implements ActionService {

    /** コロン */
    private static final String COLON = "：";

    /**
     * 初期処理<br>
     * <br>
     * 初期処理を行う<br>
     * <br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return MailWithAttachmentsSendModel 新画面パラメータ<br>
     * @exception Exception
     */
    public MailWithAttachmentsSendModel select(MailWithAttachmentsSendModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            MailWithAttachmentsSendLogic mailWithAttachmentsSendLogic = new MailWithAttachmentsSendLogic(conn);

            // Model値をDTOにセット
            TeacherMstDto prmDto = this.modelToDto(model);

            // 検索を実行
            TeacherMstDto resultDto = mailWithAttachmentsSendLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            MailWithAttachmentsSendLogic mailWithAttachmentsSendLogic = new MailWithAttachmentsSendLogic(conn);

            // コード管理マスタ検索
            LinkedHashMap<String, String> codeMap = mailWithAttachmentsSendLogic.selectCodeMst(category);

            return codeMap;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return TeacherMstDto 変換後結果<br>
     * @exception Exception
     */
    private TeacherMstDto modelToDto(MailWithAttachmentsSendModel model) throws NaiException {

        // DTOの初期化
        TeacherMstDto prmDto = new TeacherMstDto();

        // 利用者ID
        prmDto.setUserId(model.getTeacherId());

        return prmDto;
    }

    /**
     * DTO値をセット<br>
     * <br>
     * DTO値をModelにセット<br>
     * <br>
     * @param TeacherMstDto 画面パラメータ<br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return MailWithAttachmentsSendModel 変換後結果<br>
     * @exception Exception
     */
    private MailWithAttachmentsSendModel dtoToModel(TeacherMstDto prmDto, MailWithAttachmentsSendModel model)
            throws NaiException {

        // コード管理マスタのキャッシュ読み込み
        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        // 母国語リストを取得
        LinkedHashMap<String, CodeManagMstDto> nativeLangListJ = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG);

        if (prmDto.getNativeLangCd() != null) {

            // 母国語
            model.setNativeLanguage(nativeLangListJ.get(prmDto.getNativeLangCd()).getManagerNm());
        }

        // 送信者
        model.setTeacherIdNm(new StringBuffer().append(model.getTeacherId()).append(COLON)
                .append(model.getTeacherNickNm()).toString());

        // 受信者
        model.setStudentIdNm(new StringBuffer().append(model.getStudentId()).append(COLON)
                .append(model.getStudentNickNm()).toString());

        return model;
    }
}