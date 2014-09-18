package com.naikara_talk.mstcache;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.CodeClassMstDao;
import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dao.TeacherMstForCacheDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherMstCacheDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TeacherMstForCacheDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * 講師マスタキャッシュクラス。
 *
 * このクラスは、講師マスタの情報と、付随するコードを文字列変換した値を保持します。
 *
 * @author nos
 *
 */
public class TeacherMstCache {

	private static TeacherMstCache instance;
	private static Date today;
	private static LinkedHashMap<String, TeacherMstCacheDto> map;

	/**
	 * sプライベートなコンストラクタ
	 */
	private TeacherMstCache() throws NaiException {
		init();
	}

	/**
	 * このクラスの唯一のインスタンスを取得します。
	 * @return
	 * @throws SQLException
	 */
	public static TeacherMstCache getInstance() throws NaiException {
	    if (instance == null) {

	    	instance = new TeacherMstCache();

	    }
	    return instance;
	}

	/**
	 * 講師マスタを読み込んでこのインスタンスを初期化します。
	 * @throws SQLException
	 */
	private synchronized void init() throws NaiException{


		Connection conn = null;

		// 利用者マスタのマップを作成する
		// ハッシュのvalueの1番目にファミリーネーム　2番目にファーストネームを格納
		HashMap<String,String[]> userMap = new HashMap<String, String[]>();

		try {
			conn = DbUtil.getConnection();
			// 利用者マスタのマップを作成する
			// 利用者マスタを検索
			UserMstDao uDao = new UserMstDao(conn);
			ConditionMapper conditionsCountryCode = new ConditionMapper();
			conditionsCountryCode.addCondition(UserMstDao.COND_CLASSIFICATION_KBN, ConditionMapper.OPERATOR_EQUAL, SessionUser.ROLE_TEACHER);

			for(UserMstDto dto : uDao .search(conditionsCountryCode,new OrderCondition())){
				String[] name = {dto.getFamilyJnm(),dto.getFirstJnm()};
				userMap.put(dto.getUserId(), name);
			}



			// コード管理マスタのキャッシュ
			CodeManagMstCache codeCache = CodeManagMstCache.getInstance();


			// 業務日付を取得
			today = DateUtil.getOperationDate();

			// 業務日付で有効な講師を検索
			//		TeacherMstCacheDto prmtmDto = new TeacherMstCacheDto();
			ConditionMapper  conditions = new ConditionMapper();


			TeacherMstForCacheDao tmDao = new TeacherMstForCacheDao(conn);

			conditions.addCondition(TeacherMstDao.COND_CONTRACT_START_DT,
					ConditionMapper.OPERATOR_LESS_EQUAL, DateUtil.toString(today, DateUtil.DATE_FORMAT_yyyyMMdd));

			conditions.addCondition(TeacherMstDao.COND_CONTRACT_END_DT,
					ConditionMapper.OPERATOR_LARGE_EQUAL, DateUtil.toString(today, DateUtil.DATE_FORMAT_yyyyMMdd));

			map = new LinkedHashMap<String, TeacherMstCacheDto>();

			// mapに変換するetImgPhoto(
			for(TeacherMstForCacheDto mDto : tmDao.search(conditions,new OrderCondition())){

				TeacherMstCacheDto dto = new TeacherMstCacheDto();

				dto.setUserId(mDto.getUserId());
				dto.setNickAnm(mDto.getNickAnm());
				dto.setNationalityCd(mDto.getNationalityCd());
				dto.setNativeLangCd(mDto.getNativeLangCd());
				dto.setCountryCd(mDto.getCountryCd());
				dto.setAreaNoCd(mDto.getAreaNoCd());
				dto.setContractDt(mDto.getContractDt());
				dto.setContractStartDt(mDto.getContractStartDt());
				dto.setContractEndDt(mDto.getContractEndDt());
				dto.setSellingPoint(mDto.getSellingPoint());
				dto.setSelfRecommendation(mDto.getSelfRecommendation());
				dto.setImgPhoto(mDto.getImgPhoto());

				// 国籍の設定
				dto.setNationality(codeCache.decode(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY, dto.getNationalityCd()));

				// 母国語の設定
				dto.setNativeLang(codeCache.decode(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG, dto.getNativeLangCd()));

				// 滞在国名の設定
				dto.setCountry(codeCache.decode(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY, dto.getCountryCd()));


				// 利用者マスタ　ファミリーネーム
				dto.setFamilyNm(userMap.get(dto.getUserId())[0]);// append by nos
				// 利用者マスタ　ファーストーネーム
				dto.setFirstNm(userMap.get(dto.getUserId())[1]);// append by nos

				map.put(dto.getUserId(), dto);
			}

		} catch (SQLException e) {
			throw new NaiException(e);
		} finally {
			try {
				conn.close();
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
	 * 講師の情報を返却する
	 * @param userCd 講師のユーザーコード
	 * @return 講師マスタの１レコード
	 * @throws Exception
	 */
	public TeacherMstCacheDto get( String userCd ) throws NaiException {

		if(!DateUtil.dateEquals( DateUtil.getOperationDate(), today )) {
			init();
		}

		return map.get(userCd);

	}


	/**
	 * 読み込み済みのすべての講師リストを返却する
	 * @param userCd 講師のユーザーコード
	 * @return 講師マスタの１レコード
	 * @throws Exception
	 */
	public List<TeacherMstCacheDto> getList() throws NaiException {

		if(!DateUtil.dateEquals( DateUtil.getOperationDate(), today )) {
			init();
		}

		return new ArrayList<TeacherMstCacheDto>(map.values());
	}

}
