package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.OrganizationPointAssignmentListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationPointAssignmentListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振りActionクラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListSearchAction extends OrganizationPointAssignmentListActionSupport {

	private static final long serialVersionUID = 1L;

	// 0件
	private static final int ZERO = 0;

	// 100件
	private static final int HUNDERD = 100;

	/**
	 * 検索処理。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// Modelクラス設定
		setupModel();

		// 表示データの取得処理
		try {
			load();
		} catch (Exception e) {
			throw new NaiException(e);
		}

		OrganizationPointAssignmentListSearchService service = new OrganizationPointAssignmentListSearchService();

		try {
			// 検索前チェック
			if (!service.checkContract(model)) {
				this.addActionMessage(getMessage("EB0001", new String[] { "契約期間" }));
				// 画面を返す
				return SUCCESS;
			}

			// 画面一覧のデータ取得処理
			List<OrganizationPointAssignmentListDto> list = service.searchStudent(model);

			// 0件の場合メッセージ情報を設定する
			if (list == null || list.size() == ZERO) {
				this.addActionMessage(getMessage("EB0020", new String[] {}));
				// 画面を返す
				return SUCCESS;
			}
			// 100件以上の場合メッセージ情報を設定する
			if (list.size() > HUNDERD) {
				this.addActionMessage(getMessage("EB0023", new String[] { "101" }));
				// 画面を返す
				return SUCCESS;
			}
			// 画面一覧のlistを設定
			this.model.setResultList(list);

			// 未割振りポイントの取得
			OrganizationMstDto returnDto = service.searchOrganization(model);
			this.setBalancePointNum(returnDto.getBalancePointNum());
			// 一覧下の項目の合計欄(未割振りポイント)
			this.setBalancePointNumDown(returnDto.getBalancePointNum());

			// 隠しﾌｨｰﾙﾄﾞの設定
			this.setOrganizationId(returnDto.getOrganizationId());     // 組織ID
			this.setConsSeq(returnDto.getConsSeq());                   // 連番
			this.setRecordVerNo(returnDto.getRecordVerNo());           // レコードバージョン番号

			// 現残高ポイント(合計)の取得
			this.setBalancePoint(service.getBalancePoint(model));

			// 一覧下の各合計欄(ご利用済ポイント、ご利用可能ポイント、今回割振ポイント、新残高ポイント)を計算して表示する
			this.sum(list);

			if (!StringUtils.isEmpty(this.message)) {
	            this.addActionMessage(this.message);
	        }
		} catch (Exception e) {
			throw new NaiException(e);
		}
		// 画面を返す
		return SUCCESS;
	}

}
