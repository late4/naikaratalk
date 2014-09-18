package com.naikara_talk.mstcache;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

import freemarker.template.utility.StringUtil;


/**
 * コード管理マスタキャッシュクラス。
 *
 * @author nos
 *
 */
public class CodeManagMstCache {
	private static CodeManagMstCache instance;
	private static Date today;
	private static HashMap<String, LinkedHashMap<String, CodeManagMstDto>> categoryMap;

	/**
	 * sプライベートなコンストラクタ
	 */
	private CodeManagMstCache() throws NaiException {
		init();
	}

	/**
	 * このクラスの唯一のインスタンスを取得します。
	 * @return
	 * @throws NaiException
	 */
	public static CodeManagMstCache getInstance() throws NaiException {
	    if (instance == null) {

	    	instance = new CodeManagMstCache();

	    }
	    return instance;
	}

	/**
	 * コード管理マスタを読み込んでこのインスタンスを初期化します。
	 * @throws SQLException
	 */
	private synchronized void init() throws NaiException{

		Connection conn = null;
		try {
			conn = DbUtil.getConnection();


			// 業務日付を初期化
			today = DateUtil.getOperationDate();

			// コード管理マスタDAO
			CodeManagMstDao dao = new CodeManagMstDao(conn);

			// カテゴリマップの初期化
			categoryMap = new HashMap<String, LinkedHashMap<String, CodeManagMstDto>>();

			// mapに変換する　検索はパラメタなしで実行
			/*List<CodeManagMstDto> list=dao.search(new ConditionMapper());
			CodeManagMstDto dto;
			for(int i=0;list.size()>i;i++){
				dto=list.get(i);

				// Mapの有無をチェック
				if (!categoryMap.containsKey(dto.getCdCategory())){
				// なければ作る
				categoryMap.put(dto.getCdCategory(), new LinkedHashMap<String, CodeManagMstDto>());
			}

			// マップに設定
			categoryMap.get(dto.getCdCategory()).put(dto.getManagerCd(), dto);

			}*/
			for (CodeManagMstDto dto : dao.search(new ConditionMapper())) {

				// Mapの有無をチェック
				//if (categoryMap.get(dto.getCdCategory()).isEmpty()){
				if (!categoryMap.containsKey(dto.getCdCategory())){
					// なければ作る
					categoryMap.put(dto.getCdCategory(), new LinkedHashMap<String, CodeManagMstDto>());
				}
				// マップに設定
				categoryMap.get(dto.getCdCategory()).put(dto.getManagerCd(), dto);
			}
		} catch (SQLException e) {

			throw new NaiException(e);

		} finally {
            try {
              if ( !conn.isClosed() ) conn.close();
            } catch (SQLException e) {
                throw new NaiException(e);
            }
        }
	}


	/**
	 * データベースの変更などで内容が変わった際に講師マスタを読み直します。
	 */
	public void reload() throws NaiException {
		init();
	}

	/**
	 * コード管理マスタの情報を返却する
	 * @param category コード管理マスタ　種別
	 * @param code コード管理マスタ　コード
	 * @return デコード文字列　コードが存在しない場合は長さ0のStringが返却されます。
	 * @throws Exception
	 */
	public String decode( String category, String code ) throws NaiException {

		if(!DateUtil.dateEquals( DateUtil.getOperationDate(), today )) {
			init();
		}

		return categoryMap.get(category).get(code) != null
				? categoryMap.get(category).get(code).getManagerNm() : "";
	}

	/**
	 * コード管理マスタの情報を返却する
	 * @param category コード管理マスタ　種別
	 * @param code コード管理マスタ　コード
	 * @return コード管理マスタの１レコード
	 * @throws Exception
	 */
	public CodeManagMstDto getEntity( String category, String code ) throws NaiException {

		if(!DateUtil.dateEquals( DateUtil.getOperationDate(), today )) {
			init();
		}

		// TODO: エラーメッセージ設定
		if (categoryMap.get(category).get(code) == null) throw new NaiException("コード管理マスタに値が設定されていません。key：" + category + "code:" + code );
		return categoryMap.get(category).get(code);
	}

	/**
	 * 読み込み済みのコード管理マスタリストを返却する
	 * @param category コード種別
	 * @return コード管理マスタの種別のLinkedHashMap
	 * @throws Exception
	 */
	public LinkedHashMap<String, CodeManagMstDto> getList(String category) throws NaiException {

		if(!DateUtil.dateEquals( DateUtil.getOperationDate(), today )) {
			init();
		}

		// TODO: エラーメッセージ設定
		if (categoryMap.get(category) == null) throw new NaiException("コード管理マスタに値が設定されていません。key：" + category  );

		return categoryMap.get(category);

	}
}
