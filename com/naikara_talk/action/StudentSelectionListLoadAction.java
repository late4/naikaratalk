package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentSelectionListModel;
import com.naikara_talk.service.StudentSelectionListLoadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListLoadAction extends StudentSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        try {

            initRadio();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // サービスの初期化
        StudentSelectionListLoadService service = new StudentSelectionListLoadService();

        // モデルを取得
        StudentSelectionListModel model = service.load();

        // 顧客区分は「全て」を選択
        this.setCustomer_rdl(model.getCustomerKbn());

        // エラーメッセージありの場合
        if (!StringUtils.isEmpty(this.message)) {

            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }
}