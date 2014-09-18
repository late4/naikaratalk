package com.naikara_talk.service;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CodeControlMstListLogic;
import com.naikara_talk.model.CodeControlMstListModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public class CodeControlMstListLoadService implements ActionService {


	/**
	 * 初期処理<br>
	 * <br>
	 * 画面項目の初期処理を行う<br>
	 * <br>
	 * @return CodeControlMstListModel
	 */
    public CodeControlMstListModel load(){

        CodeControlMstListModel model=new CodeControlMstListModel();

        // 処理区分は「照会」を選択する
        model.setProcessKbn(CodeControlMstListModel.PROS_KBN_REF);

        return model;

    }

	/**
	 * コード種別マスタからデータ取得処理(コード種別名：ドロップダウン用)。<br>
	 * <br>
     * @param CodeClassMstDto
     * @return List<CodeClassMstDto>
     * @throws Exception
	 */
    public List<CodeClassMstDto> selectCodeClassMst(CodeClassMstDto dto) throws  NaiException{

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            CodeControlMstListLogic logic = new CodeControlMstListLogic(conn);

            // コード管理マスタ検索
            return logic.selectCodeClassMst(dto);

        } catch ( SQLException se ) {
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

}
