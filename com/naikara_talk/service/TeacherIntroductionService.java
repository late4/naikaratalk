package com.naikara_talk.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

import javax.transaction.SystemException;
//import javax.transaction.UserTransaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherIntroductionMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherIntroductionMstLogic;
import com.naikara_talk.model.TeacherCategoryModel;
import com.naikara_talk.model.TeacherIntroductionModel;
import com.naikara_talk.model.TeacherIntroductionMstModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.mstcache.TeacherMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * 講師紹介マスタメンテナンス画面の表示要素構築サービス
 * @author nos
 *
 */
public class TeacherIntroductionService implements ActionService {

	private Logger log = Logger.getLogger(this.getClass());

	/** 更新制御の定数 */
	private static final int UPDATE_PROVISIONAL = 1;
	private static final int UPDATE_EFFECTIVE = 2;


	/**
	 * 画面データの作成
	 * 初回表示の場合
	 * @param proc TODO
	 * @return
	 * @throws SQLException
	 * @throws SystemException
	 * @throws NaiException
	 */
	public TeacherIntroductionMstModel load(String proc) throws  NaiException {

		// コード管理マスタキャッシュ
		CodeManagMstCache codeCache = CodeManagMstCache.getInstance();

		Connection conn = null;
		try{
			conn = DbUtil.getConnection();

			// 講師紹介マスタのロジックを取得
			TeacherIntroductionMstLogic teacherIntroductionListLogic = new TeacherIntroductionMstLogic(conn);

			// 	画面表示用データモデルの初期化
			TeacherIntroductionMstModel teacherIntroductionMstModel = new TeacherIntroductionMstModel();

			// コード管理マスタから、講師カテゴリを取得
			Collection<CodeManagMstDto> categoryList = codeCache.getList(NaikaraTalkConstants.CODE_CATEGORY_INTRODUCTION_CATEGORY).values();

			// 講師カテゴリモデルの設定
			List<TeacherCategoryModel> teacherCategoryList = new ArrayList<TeacherCategoryModel>();
			// 講師カテゴリ表示用リストの作成
			for(CodeManagMstDto dto : categoryList){
				TeacherCategoryModel model = new TeacherCategoryModel();
				// カテゴリコード
				model.setCategoryCd(dto.getManagerCd());
				// カテゴリ名称
				model.setCategoryNm(dto.getManagerNm());
				teacherCategoryList.add(model);
			}
			teacherIntroductionMstModel.setCategoryList(teacherCategoryList);

			// 講師紹介マスタのデータ取得
			List<TeacherIntroductionMstDto> teacherIntroductionMstList = teacherIntroductionListLogic.getList(proc);

			// 講師紹介マスタモデルマップの初期化
			Map<String,List<TeacherIntroductionModel>> teacherIntroductionMap = new LinkedHashMap<String, List<TeacherIntroductionModel>>();

			// 講師マスタとあわせて表示用リストを作成
			for (TeacherIntroductionMstDto dto : teacherIntroductionMstList) {

				TeacherIntroductionModel model = this.getTeacherIntrodacutionModel(dto);

				if ( model != null) {

					if( !teacherIntroductionMap.containsKey(dto.getUserCategoryCd())){
						// マップに設定
						teacherIntroductionMap.put(model.getUserCategoryCd(),new ArrayList<TeacherIntroductionModel>());
					}
					teacherIntroductionMap.get(model.getUserCategoryCd()).add(model);
				}

			}

			teacherIntroductionMstModel.setintroductionMap(teacherIntroductionMap);

			return teacherIntroductionMstModel;

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
			 if ( !conn.isClosed() ) conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 画面の再表示。
	 * 2回目以降で表示データが存在する場合。
	 * @param prm
	 * @return
	 * @throws SQLException
	 * @throws SystemException
	 * @throws NaiException
	 */
	public TeacherIntroductionMstModel reload(String[] prm) throws NaiException {

//		// コード管理マスタリストロジックを初期化
//		CodeManagMstLstLogic codeManagMstLstLogic = new CodeManagMstLstLogic();

		// コード管理マスタキャッシュ
		CodeManagMstCache codeCache = CodeManagMstCache.getInstance();

		// 	画面表示用データモデルの初期化
		TeacherIntroductionMstModel teacherIntroductionMstModel = new TeacherIntroductionMstModel();

			// コード管理マスタから、講師カテゴリを取得
			Collection<CodeManagMstDto> categoryList = codeCache.getList(NaikaraTalkConstants.CODE_CATEGORY_INTRODUCTION_CATEGORY).values();
			// 講師カテゴリモデルの設定
			List<TeacherCategoryModel> teacherCategoryList = new ArrayList<TeacherCategoryModel>();
			// 講師カテゴリ表示用リストの作成
			for(CodeManagMstDto dto : categoryList){
				TeacherCategoryModel model = new TeacherCategoryModel();
				// カテゴリコード
				model.setCategoryCd(dto.getManagerCd());
				// カテゴリ名称
				model.setCategoryNm(dto.getManagerNm());
				teacherCategoryList.add(model);
			}
			teacherIntroductionMstModel.setCategoryList(teacherCategoryList);

			// 講師紹介マスタモデルマップの初期化
			Map<String,List<TeacherIntroductionModel>> teacherIntroductionMap = new LinkedHashMap<String, List<TeacherIntroductionModel>>();

			// 講師マスタとあわせて表示用リストを作成
			for (String elm : prm) {

				String[] elms = elm.split(",");

				if(elms.length == 3 && StringUtils.isNotEmpty(elms[2])){
					TeacherIntroductionModel model = this.getTeacherIntrodacutionModel(elms[0],elms[1],elms[2]);

					if( model != null) {
						if( !teacherIntroductionMap.containsKey(elms[0])){
							// マップに設定
							teacherIntroductionMap.put(model.getUserCategoryCd(),new ArrayList<TeacherIntroductionModel>());
						}
						teacherIntroductionMap.get(model.getUserCategoryCd()).add(model);
					}
				}

			}
			// TODO: 選択解除と講師選択対象をセットを作成する。
			teacherIntroductionMstModel.setintroductionMap(teacherIntroductionMap);

			return teacherIntroductionMstModel;

	}

	/**
	 * 画面の再表示。
	 * 2回目以降で表示データが存在する場合。
	 * @param prm
	 * @return
	 * @throws SQLException
	 * @throws SystemException
	 * @throws NaiException
	 */
	public TeacherIntroductionMstModel reload(TeacherIntroductionMstModel prm) throws SQLException, SystemException, NaiException {

//		// コード管理マスタリストロジックを初期化
//		CodeManagMstLstLogic codeManagMstLstLogic = new CodeManagMstLstLogic();

		// コード管理マスタキャッシュ
		CodeManagMstCache codeCache = CodeManagMstCache.getInstance();

		// 	画面表示用データモデルの初期化
		TeacherIntroductionMstModel teacherIntroductionMstModel = new TeacherIntroductionMstModel();

		try {

	    	String sessionId = ServletActionContext.getRequest().getSession().getId();
	    	log.debug(sessionId);

			// コード管理マスタから、講師カテゴリを取得
//			List<CodeManagMstDto> categoryList = codeManagMstLstLogic.getList(NaikaraTalkConstants.CODE_CATEGORY_INTRODUCTION_CATEGORY);
			Collection<CodeManagMstDto> categoryList = codeCache.getList(NaikaraTalkConstants.CODE_CATEGORY_INTRODUCTION_CATEGORY).values();
			// 講師カテゴリモデルの設定
			List<TeacherCategoryModel> teacherCategoryList = new ArrayList<TeacherCategoryModel>();
			// 講師カテゴリ表示用リストの作成
			for(CodeManagMstDto dto : categoryList){
				TeacherCategoryModel model = new TeacherCategoryModel();
				// カテゴリコード
				model.setCategoryCd(dto.getManagerCd());
				// カテゴリ名称
				model.setCategoryNm(dto.getManagerNm());
				teacherCategoryList.add(model);
			}

			// 講師紹介マスタモデルリストの初期化
			Map<String,List<TeacherIntroductionModel>> teacherIntroductionMap = prm.getintroductionMap();
			// 講師マスタとあわせて表示用リストを作成

			// TODO: 選択解除と講師選択対象をセットを作成する。

			teacherIntroductionMstModel.setintroductionMap(teacherIntroductionMap);

			return teacherIntroductionMstModel;

		} catch (Exception e) {
			throw new NaiException(e);
		}
	}

	/**
	 * 仮登録処理
	 *
	 * @param prm
	 * @return
	 * @throws NaiException
	 */
	public TeacherIntroductionMstModel updateProvisional(TeacherIntroductionMstModel prm) throws NaiException {
		return this.update(prm, UPDATE_PROVISIONAL);

	}

	/**
	 * 本登録処理
	 * @param prm
	 * @return
	 * @throws NaiException
	 */
	public TeacherIntroductionMstModel updateEffective(TeacherIntroductionMstModel prm) throws  NaiException {
		return this.update(prm, UPDATE_EFFECTIVE);
	}

	public TeacherIntroductionMstModel update(TeacherIntroductionMstModel prm, int mode) throws NaiException {

		Connection conn = null;
		try {
			conn = DbUtil.getConnection();

			// 講師紹介マスタのロジックを初期化
			TeacherIntroductionMstLogic teacherIntroductionListLogic = new TeacherIntroductionMstLogic(conn);
			// 画面表示用のモデルデータ
			TeacherIntroductionMstModel dispModel = null;

			List<TeacherIntroductionMstDto> dtoList = new ArrayList<TeacherIntroductionMstDto>();

			for ( List<TeacherIntroductionModel> maps : prm.getintroductionMap().values()){
				for (TeacherIntroductionModel model : maps ){
					TeacherIntroductionMstDto dto = new TeacherIntroductionMstDto();
					dto.setUserCategoryCd(model.getUserCategoryCd());
					dto.setUserCategorysubCd(model.getUserCategorysubCd());
					dto.setUserId(model.getUserId());
					dtoList.add(dto);
				}
			}

//		UserTransaction ut = null;
//		try {
//			ut = DbUtil.getUserTransaction();
//			ut.begin();

			if (UPDATE_PROVISIONAL == mode){
				teacherIntroductionListLogic.updateProvisional(dtoList);
			} else if (UPDATE_EFFECTIVE == mode) {
				teacherIntroductionListLogic.updateEffective(dtoList);
			}

//			ut.commit();
			conn.commit();

			dispModel = this.load(TeacherIntroductionMstLogic.PROCESS_CD_PROVISIONAL);

			return dispModel;

		} catch (Exception e) {
			try {
//				ut.rollback();
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new NaiException(e1);
			}
			throw new NaiException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new NaiException(e1);
			}
		}
	}

	/**
	 * 講師紹介データを返却する。
	 * @param dto
	 * @return 講師紹介データ
	 * @throws SQLException
	 * @throws NaiException
	 */
	private TeacherIntroductionModel getTeacherIntrodacutionModel(TeacherIntroductionMstDto dto)
			throws NaiException {

		String userCategoryCd = dto.getUserCategoryCd();
		String userCategorysubCd = dto.getUserCategorysubCd();
		String userId = dto.getUserId();

		return getTeacherIntrodacutionModel(userCategoryCd,userCategorysubCd,userId);
	}

	/**
	 * 講師紹介データを返却する。
	 * @param model
	 * @return 講師紹介データ
	 * @throws SQLException
	 * @throws NaiException
	 */
	private TeacherIntroductionModel getTeacherIntrodacutionModel(TeacherIntroductionModel model)
			throws NaiException {

		String userCategoryCd = model.getUserCategoryCd();
		String userCategorysubCd = model.getUserCategorysubCd();
		String userId = model.getUserCategoryCd();

		return getTeacherIntrodacutionModel(userCategoryCd,userCategorysubCd,userId);
	}

	/**
	 * 講師紹介データを返却する。
	 * @param userCategoryCd		講師紹介カテゴリ
	 * @param userCategorysubCd		講師紹介サブカテゴリ
	 * @param userId				講師利用者ID
	 * @return						講師紹介データ
	 * @throws NaiException
	 */
	private TeacherIntroductionModel getTeacherIntrodacutionModel(
			String userCategoryCd, String userCategorysubCd, String userId) throws NaiException {


		TeacherIntroductionModel model = new TeacherIntroductionModel();

		TeacherMstCache mst = TeacherMstCache.getInstance();
		CodeManagMstCache code = CodeManagMstCache.getInstance();

		model.setUserCategoryCd(userCategoryCd);
		model.setUserCategorysubCd(userCategorysubCd);
		model.setUserId(userId);

		// userIdが空でなく、ユーザが取得できた場合（起動時にユーザが無効である場合もある）
		if(!StringUtils.isEmpty(userId) && mst.get(userId) != null){
			model.setNickAnm(mst.get(userId).getNickAnm());
			model.setNationality(mst.get(userId).getNationality());
			model.setNativeLang(mst.get(userId).getNativeLang());
			model.setCountry(mst.get(userId).getCountry());
			model.setUserCategoryNm(code.decode(NaikaraTalkConstants.CODE_CATEGORY_INTRODUCTION_CATEGORY, userCategoryCd));

			model.setSellingPoint(mst.get(userId).getSellingPoint());
			model.setSelfRecommendation(mst.get(userId).getSelfRecommendation());

			// 本名の設定
			model.setFamilyNm(mst.get(userId).getFamilyNm());
			model.setFirstNm(mst.get(userId).getFirstNm());

			//モデルを返却
			return model;

		} else {
			return null;
		}
	}
}
