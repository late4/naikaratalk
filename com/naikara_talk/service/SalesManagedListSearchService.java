package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.SalesManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SalesManagedListLogic;
import com.naikara_talk.model.SalesManagedListModel;
import com.naikara_talk.sessiondata.SessionCsvFileDelete;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.CsvUtil;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページSearchService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedListSearchService implements ActionService {

	/* 取得したデータ件数がZero件 */
    public static final int LIST_ZERO_CNT = 0;

	/* 対象年月が必須チェック */
    public static final int OBJECT_YYYYMM_NULL = -1;

    /* 対象年月の日付チェック */
    public static final int OBJECT_YYYYMM_DATE = -2;

    /* チェックOK */
    public static final int CHECK_OK = 0;

    /** CSV Title */
    private final String title = "対象年月";

    /** ファイル名 集計 */
    private final String fileNamePartCount = "入金管理ページ(集計)";

    /** ファイル名 明細 */
    private final String fileNamePartDedail = "入金管理ページ(明細)";
    /** 下線 */
    private final String underline = "_";
    /** .csv */
    private final String csvEnding = ".csv";

    /** 集計のヘッダ */
    private final String header1 = "受講者ID／組織ID";
    private final String header2 = "受講者名／組織名";
    private final String header3 = "前受有償ポイント(前月残)";
    private final String header4 = "購入有償ポイント(合計)";
    private final String header5 = "利用ポイント(売上)※有償";
    private final String header6 = "ポイント外商品購入";
    private final String header7 = "前受無償ポイント(前月残)";
    private final String header8 = "購入無償ポイント(合計)";
    private final String header9 = "利用ポイント(無償)";
    private final String header10 = "請求書番号";

    /** 明細の見出し部 */
    private final String detail1 = "顧客区分";
    private final String detail2 = "受講者ID／組織ID";
    private final String detail3 = "受講者名／組織名";
    private final String detail4 = "前受有償ポイント(合計)";
    private final String detail5 = "購入金額(合計)";
    private final String detail6 = "利用無償ポイント(合計)";
    private final String detail7 = "利用有償ポイント(合計)";

    /** 明細のヘッダ */
    private final String detailHeader1 = "受講者ID";
    private final String detailHeader2 = "受講者名";
    private final String detailHeader3 = "レッスン日/購入日";
    private final String detailHeader4 = "予約NO/購入ID";
    private final String detailHeader5 = "コース名／購入商品";
    private final String detailHeader6 = "購入有償ポイント";
    private final String detailHeader7 = "利用無償ポイント";
    private final String detailHeader8 = "利用有償ポイント(売上)";
    private final String detailHeader9 = "ポイント外商品購入";

    /**
	 * 検索前チェック処理。
	 * @param model 入金管理ページModel
	 * @return DataCount
	 * @throws NaiException
	 */
	public int checkPreSelect(SalesManagedListModel model) throws NaiException {

		if (!checkNull(model)) {
			// 対象年月が必須チェック エラー
			return OBJECT_YYYYMM_NULL;
		}

		if (!checkDate(model)) {
			// 対象年月の日付チェック エラー
			return OBJECT_YYYYMM_DATE;
		}

		return CHECK_OK;

	}

	/**
	 * データ件数チェック処理。
	 * @param model 入金管理ページModel
	 * @return DataCount
	 * @throws NaiException
	 */
	public int checkDetaCount(SalesManagedListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;
		// データ件数を取得
		int dataCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

		try {

			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			SalesManagedListDto dto = modelToDto(model);
			SalesManagedListLogic logic = new SalesManagedListLogic(conn);

			// 入金管理ページのデータ件数を取得
			dataCount = logic.getRowCount(dto);

			// 件数のチェック
			if (dataCount == LIST_ZERO_CNT) {
				// 取得したデータ件数がZero件の場合
				return LIST_ZERO_CNT;
			}

			return dataCount;

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

	/**
	 * データ(集計)を取得する。
	 * @param model 入金管理ページModel
	 * @return list
	 * @throws NaiException
	 */
	public List<SalesManagedListDto> search(SalesManagedListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			SalesManagedListDto dto = modelToDto(model);
			SalesManagedListLogic logic = new SalesManagedListLogic(conn);

			// データ(集計)を取得する
			return logic.search(dto);

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

	/**
	 * CSV作成処理(集計)。
	 * @param resultList 画面一覧list
	 * @param model 入金管理ページModel
	 * @return 入金管理ページModel
	 * @throws Exception
	 */
	public SalesManagedListModel csvCreateCount(
			List<SalesManagedListDto> resultList, SalesManagedListModel model) throws NaiException {

		// 入金管理ページ(集計)CSVファイルの格納先（サーバー）
        String csvFilePath;
        csvFilePath = this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_MANAG_COUNT).get(
                NaikaraTalkConstants.PAYMENT_MANAG_COUNT_CSV_OUTPUT);

        // Title 作成
        List<String> termsList = new ArrayList<String>();
        termsList.add(this.title);
        termsList.add(NaikaraStringUtil.converToYYYY_Year_MM_Month(model.getObjectYyyyMm()));

        // CSV ヘッダ部 作成
        List<String> headerList = new ArrayList<String>();
        headerList.add(this.header1);   // 受講者ID／組織ID
        headerList.add(this.header2);   // 受講者名／組織名
        headerList.add(this.header3);   // 前受有償ポイント(前月残)
        headerList.add(this.header4);   // 購入有償ポイント(合計)
        headerList.add(this.header5);   // 利用ポイント(売上)※有償
        headerList.add(this.header6);   // ポイント外商品購入
        headerList.add(this.header7);   // 前受無償ポイント(前月残)
        headerList.add(this.header8);   // 購入無償ポイント(合計)
        headerList.add(this.header9);   // 利用ポイント(無償)
        headerList.add(this.header10);  // 請求書番号

        // 出力list
        List<List<String>> list = new ArrayList<List<String>>();
        // Titleを追加
        list.add(termsList);
        // ヘッダを追加
        list.add(headerList);

        // CSV明細部 作成
        for (SalesManagedListDto dto : resultList) {

        	List<String> contentsList = new ArrayList<String>();
        	contentsList.add(dto.getStudentOrganizationId());                             // 受講者ID／組織ID
        	contentsList.add(dto.getStudentOrganizationJnm());                            // 受講者名／組織名
        	contentsList.add(String.valueOf(dto.getCompensationBeforePoint()));           // 前受有償ポイント(前月残)
        	contentsList.add(String.valueOf(dto.getCompensationPurchasePoint()));         // 購入有償ポイント(合計)
        	contentsList.add(String.valueOf(dto.getCompensationUsePoint()));              // 利用ポイント(売上)※有償
        	contentsList.add(String.valueOf(dto.getGoodsPurchaseYen()));                  // ポイント外商品購入
        	contentsList.add(String.valueOf(dto.getFreeBeforePoint()));                   // 前受無償ポイント(前月残)
        	contentsList.add(String.valueOf(dto.getFreePurchasePoint()));                 // 購入無償ポイント(合計)
        	contentsList.add(String.valueOf(dto.getFreeUsePoint()));                      // 利用ポイント(無償)
        	contentsList.add(dto.getBillNo());                                            // 請求書番号

        	// 明細部を追加
        	list.add(contentsList);

        }

        // ファイル名
        StringBuffer csvName = new StringBuffer();
        csvName.append(fileNamePartCount).append(underline)
                .append(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId())
                .append(underline).append(DateUtil.getSysDateTimeNoSplit()).append(csvEnding);

        // ファイル格納先
        StringBuffer csvPath = new StringBuffer();
        csvPath.append(csvFilePath.replace("\\", NaikaraTalkConstants.DATE_SEPARATOR))
                .append(NaikaraTalkConstants.DATE_SEPARATOR).append(csvName.toString());

        // CSV作成
        CsvUtil.createCsvFile(list, csvPath.toString());

        // 作成したファイル名 は modelに設定して、返す。
        SalesManagedListModel retModel = new SalesManagedListModel();
        retModel.setFileName(csvName.toString());

        // SessionCsvFileDelete作成
        List<String> cdCategory = new ArrayList<String>();
        List<String> managerCd = new ArrayList<String>();
        cdCategory.add(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_MANAG_COUNT);
        managerCd.add(NaikaraTalkConstants.PAYMENT_MANAG_COUNT_CSV_OUTPUT);
        this.createSessionData(cdCategory, managerCd);

		return retModel;

	}

	/**
	 * CSV作成処理(明細)。
	 * @param resultList 画面一覧list
	 * @param model 入金管理ページModel
	 * @return 入金管理ページModel
	 * @throws Exception
	 */
	public SalesManagedListModel csvCreateDetail(
			List<SalesManagedListDto> resultList, SalesManagedListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;
		SalesManagedListLogic logic ;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();
			logic = new SalesManagedListLogic(conn);

			// 入金管理ページ(集計)CSVファイルの格納先（サーバー）
	        String csvFilePath;
	        csvFilePath = this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_MANAG_DETAIL).get(
	                NaikaraTalkConstants.PAYMENT_MANAG_DETAIL_CSV_OUTPUT);

	        // Title 作成
	        List<String> termsList = new ArrayList<String>();
	        termsList.add(this.title);
	        termsList.add(NaikaraStringUtil.converToYYYY_Year_MM_Month(model.getObjectYyyyMm()));

	        // 出力list
	        List<List<String>> list = new ArrayList<List<String>>();
	        // Titleを追加
	        list.add(termsList);

	        SalesManagedLoadService service = new SalesManagedLoadService();

	        // 見出し部、ヘッダ部、CSV明細部がセットにして、作成する
	        for (SalesManagedListDto dto : resultList) {

	        	// CSV 見出し部 作成
	        	List<String> headerlineList = new ArrayList<String>();
	        	headerlineList.add(this.detail1);                                              // 顧客区分
	        	headerlineList.add(service.getCostomerKbnName(dto.getCostomerKbn()));
	        	headerlineList.add(this.detail2);                                              // 受講者ID／組織ID
	        	headerlineList.add(dto.getStudentOrganizationId());
	        	headerlineList.add(this.detail3);                                              // 受講者名／組織名
	        	headerlineList.add(dto.getStudentOrganizationJnm());
	        	headerlineList.add(this.detail4);                                              // 前受有償ポイント(前月残)
	        	headerlineList.add(String.valueOf(dto.getCompensationBeforePoint()));
	        	headerlineList.add(this.detail5);                                              // 購入有償ポイント(合計)
	        	headerlineList.add(String.valueOf(dto.getCompensationPurchasePoint()));
	        	headerlineList.add(this.detail6);                                              // 利用ポイント(無償)
	        	headerlineList.add(String.valueOf(dto.getFreeUsePoint()));
	        	headerlineList.add(this.detail7);                                              // 利用ポイント(売上)※有償
	        	headerlineList.add(String.valueOf(dto.getCompensationUsePoint()));
	        	// 見出し部を追加
	        	list.add(headerlineList);

	        	// CSV ヘッダ部 作成
	            List<String> headerList = new ArrayList<String>();
	            headerList.add(this.detailHeader1);   // 受講者ID
	            headerList.add(this.detailHeader2);   // 受講者名
	            headerList.add(this.detailHeader3);   // ポイント購入日/利用日
	            headerList.add(this.detailHeader4);   // 購入ID
	            headerList.add(this.detailHeader5);   // 購入(レッスン／商品)
	            headerList.add(this.detailHeader6);   // 購入有償ポイント
	            headerList.add(this.detailHeader7);   // 利用無償ポイント
	            headerList.add(this.detailHeader8);   // 利用有償ポイント(売上)
	            headerList.add(this.detailHeader9);   // ポイント外商品購入
	            // ヘッダを追加
	            list.add(headerList);

	            // データ(明細)を取得する
				List<SalesManagedListDto> getlist = logic.searchDetail(
						NaikaraStringUtil.delSlash(model.getObjectYyyyMm()), dto.getStudentOrganizationId(), dto.getCostomerKbn());

				// CSV明細部 作成
	        	for (SalesManagedListDto getDto : getlist) {

	        		List<String> contentsList = new ArrayList<String>();
	            	contentsList.add(getDto.getStudentId());                                      // 受講者ID
	            	contentsList.add(getDto.getFamilyFirstJnm());                                 // 受講者名
	            	contentsList.add(getDto.getPurchaseUseDt());                                  // ポイント購入日/利用日
	            	contentsList.add(getDto.getPurchaseId());                                     // 購入ID
	            	contentsList.add(getDto.getPurchaseNm());                                     // 購入(レッスン／商品)
	            	contentsList.add(String.valueOf(getDto.getCompensationPurchasePoint()));      // 購入有償ポイント
	            	contentsList.add(String.valueOf(getDto.getFreeUsePoint()));                   // 利用無償ポイント
	            	contentsList.add(String.valueOf(getDto.getCompensationUsePoint()));           // 利用有償ポイント(売上)
	            	contentsList.add(String.valueOf(getDto.getGoodsPurchaseYen()));               // ポイント外商品購入

	            	// 明細部を追加
	            	list.add(contentsList);
	        	}
	        }

	        // ファイル名
	        StringBuffer csvName = new StringBuffer();
	        csvName.append(fileNamePartDedail).append(underline)
	                .append(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId())
	                .append(underline).append(DateUtil.getSysDateTimeNoSplit()).append(csvEnding);

	        // ファイル格納先
	        StringBuffer csvPath = new StringBuffer();
	        csvPath.append(csvFilePath.replace("\\", NaikaraTalkConstants.DATE_SEPARATOR))
	                .append(NaikaraTalkConstants.DATE_SEPARATOR).append(csvName.toString());

	        // CSV作成
	        CsvUtil.createCsvFile(list, csvPath.toString());

	        // 作成したファイル名 は modelに設定して、返す。
	        SalesManagedListModel retModel = new SalesManagedListModel();
	        retModel.setFileName(csvName.toString());

	        // SessionCsvFileDelete作成
	        List<String> cdCategory = new ArrayList<String>();
	        List<String> managerCd = new ArrayList<String>();
	        cdCategory.add(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_MANAG_DETAIL);
	        managerCd.add(NaikaraTalkConstants.PAYMENT_MANAG_DETAIL_CSV_OUTPUT);
	        this.createSessionData(cdCategory, managerCd);

			return retModel;

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

	/**
     * SessionCsvFileDelete作成(yyyy/MM)。<br>
     * <br>
     * SessionCsvFileDelete作成(yyyy/MM)。<br>
     * <br>
     * @param なし <br>
     * @return なし <br>
     * @exception なし
     */
    private void createSessionData(List<String> cdCategory, List<String> managerCd) throws NaiException {

        SessionCsvFileDelete sessionData = new SessionCsvFileDelete();
        sessionData.setUserId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());

        sessionData.setCdCategory(cdCategory);
        sessionData.setManagerCd(managerCd);
        SessionDataUtil.setSessionData(sessionData);

    }

	/**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category  汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            SalesManagedListLogic logic = new SalesManagedListLogic(conn);

            // コード管理マスタ検索
            return logic.selectCodeMst(category);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (NaiException e) {
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
	 * Model値をDTOにセット。
     * @param model 入金管理ページModel
     * @return SalesManagedListDto
	 */
	private SalesManagedListDto modelToDto(SalesManagedListModel model) throws NaiException {

		// DTOの初期化
		SalesManagedListDto dto = new SalesManagedListDto();

		// 顧客区分
		dto.setCostomerKbn(model.getCostomerKbn());
		// 対象年月
		dto.setObjectYyyyMm(NaikaraStringUtil.delSlash(model.getObjectYyyyMm()));
		// 受講者ID／組織ID
		dto.setStudentOrganizationId(model.getStudentOrganizationId());
		// 受講者名(フリガナ)
		dto.setFamilyFirstKnm(model.getFamilyFirstKnm());
		// 組織名
		dto.setOrganizationJnm(model.getOrganizationJnm());

		return dto;

	}

    /**
	 * 日付チェック
	 * @param model 入金管理ページModel
	 * @return boolean
	 */
	public boolean checkDate(SalesManagedListModel model) throws NaiException {

		String objectYyyyMm = model.getObjectYyyyMm();
		// 桁数チェック
		if (objectYyyyMm.length() < 6) {
			return false;
		}

		StringBuffer sb = new StringBuffer();
		// 桁数=6桁
		if (objectYyyyMm.length() == 6) {
			sb.append(NaikaraStringUtil.converToYYYY_MM(objectYyyyMm));
		} else {
			sb.append(objectYyyyMm);
		}

		// yyyy/mm -> yyyy/mm/01に変更
		sb.append("/01");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(sb.toString());
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * 対象年月が必須チェック
	 * @param model 入金管理ページModel
	 * @return boolean
	 */
	public boolean checkNull(SalesManagedListModel model) throws NaiException {

		if (StringUtils.isEmpty(model.getObjectYyyyMm())) {
			// nullの場合
			return false;
		}
		return true;

	}

}
