package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.TeacherCourseListDto;
import com.naikara_talk.exception.NaiException;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>講師単位のコースリスト取得クラス<br>
 * <b>クラス概要　　　:</b>講師単位のコースリスト取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author MIRA/TECS
 * <b>変更履歴　　　　:</b>2013/06/05 TECS 新規作成
 */
public class TeacherCourseListDao extends AbstractDao {

	public static final String COND_USER_ID = "USER_ID";
	public static final String COND_USE_STR_DT = "CSM.USE_STR_DT";
	public static final String COND_USE_END_DT = "CSM.USE_END_D";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public TeacherCourseListDao(Connection con) {
		this.conn = con;
	}


	/**
	 * 講師単位のコースリスト取得<br>
	 * <br>
	 * 講師単位のコースリスト取得を行う<br>
	 * <br>
	 * @param userId
	 * @param useStrDt
	 * @param useEndDt
	 * @return ArrayList<TeacherCourceListDto>
	 * @throws NaiException
	 */
	public List<TeacherCourseListDto> search(ConditionMapper conditions) throws NaiException {

		List<TeacherCourseListDto> list=new ArrayList<TeacherCourseListDto>();

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//講師単位のコースリスト取得
		sb.append(" SELECT ");
		sb.append("    TCM.USER_ID ");
		sb.append("   ,CSM.COURSE_CD ");
		sb.append("   ,CSM.BIG_CLASSIFICATION_CD ");
		sb.append("   ,CSM.MIDDLE_CLASSIFICATION_CD ");
		sb.append("   ,CSM.SMALL_CLASSIFICATION_CD ");
		sb.append("   ,CSM.COURSE_JNM ");
		sb.append("   ,CSM.COURSE_ENM ");
		sb.append(" FROM ");
		sb.append("    TEACHER_COURSE_MST AS TCM ");
		sb.append("   ,COURSE_MST         AS CSM ");
		sb.append(" WHERE TCM.COURSE_CD = CSM.COURSE_CD and ");
		sb.append( conditions.getConditionString());
		sb.append(" ORDER BY CSM.COURSE_CD ");


		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
			int i = 0;
			for( QueryCondition cond : conditions.getConditions()){
				for(String val : cond.getValues()){
					ps.setString(i + 1, val);
					i++;
				}
			}

			res = ps.executeQuery();


			while(res.next()){
				TeacherCourseListDto dto = new TeacherCourseListDto();
				dto.setUserId(res.getString("USER_ID"));
				dto.setCourseId(res.getString("COURSE_CD"));
				dto.setBigClassificationCd(res.getString("BIG_CLASSIFICATION_CD"));
				dto.setMiddleClassificationCd(res.getString("MIDDLE_CLASSIFICATION_CD"));
				dto.setSmallClassificationCd(res.getString("SMALL_CLASSIFICATION_CD"));
				dto.setCourseJnm(res.getString("COURSE_JNM"));
				dto.setCourseEnm(res.getString("COURSE_ENM"));
				list.add(dto);
			}

			return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
        		if(res != null){
        			res.close();
        		}
        	}catch(SQLException se ){
        		throw new NaiException(se);
        	}
        }
	}

//以下ではなく、TeacherCourseDao.searchを使用してください。
	/**
	 * 講師単位のコースリスト取得(TECS)<br>
	 * <br>
	 * 講師単位のコースリスト取得を行う<br>
	 * <br>
	 * @param userId
	 * @param useStrDt
	 * @param useEndDt
	 * @return ArrayList<TeacherCourceListDto>
	 * @throws NaiException
	 */
/*
	public ArrayList<TeacherCourseListDto> getTeacherCourseList(String userId,String useStrDt,String useEndDt) throws NaiException {


		//戻り値とリターンコードの初期化
		ArrayList<TeacherCourseListDto> list=null;
		TeacherCourseListDto dto=null;

		//引数のパラメータチェック
		//講師ID
		//必須チェック
		if(userId==null || "".equals(userId)){
			list=new ArrayList<TeacherCourseListDto>();
			dto=new TeacherCourseListDto();
			dto.setReturnCode(1);
			list.add(dto);
			return list;
		}
		//提供開始日のyyyyMMdd書式チェック
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try {
			sdf.parse(useStrDt);
		} catch (Exception e) {
			list=new ArrayList<TeacherCourseListDto>();
			dto=new TeacherCourseListDto();
			dto.setReturnCode(1);
			list.add(dto);
			return list;
		}
		//提供終了日のyyyyMMdd書式チェック
		try {
			sdf.parse(useEndDt);
		} catch (Exception e) {
			list=new ArrayList<TeacherCourseListDto>();
			dto=new TeacherCourseListDto();
			dto.setReturnCode(1);
			list.add(dto);
			return list;
		}

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//講師単位のコースリスト取得
		sb.append(" SELECT ");
		sb.append("    CSM.COURSE_CD ");
		sb.append("   ,CSM.BIG_CLASSIFICATION_CD ");
		sb.append("   ,CSM.MIDDLE_CLASSIFICATION_CD ");
		sb.append("   ,CSM.SMALL_CLASSIFICATION_CD ");
		sb.append("   ,CSM.COURSE_JNM ");
		sb.append("   ,CSM.COURSE_ENM ");
		sb.append(" FROM ");
		sb.append("    TEACHER_COURSE_MST AS TCM ");
		sb.append("   ,COURSE_MST         AS CSM ");
		sb.append(" WHERE TCM.USER_ID = ? ");
		sb.append("   AND TCM.COURSE_CD = CSM.COURSE_CD ");
		sb.append("   AND CSM.USE_STR_DT = ? ");
		sb.append("   AND CSM.USE_END_DT = ? ");
		sb.append(" ORDER BY CSM.COURSE_CD ");


		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, userId);
			ps.setString(2, useStrDt);
			ps.setString(3, useEndDt);
			res = ps.executeQuery();

			ArrayList<CourseMstDto> workList=new ArrayList<CourseMstDto>();
			CourseMstDto workDto;

			while(res.next()){
				workDto=new CourseMstDto();
				workDto.setCourseCd(res.getString("COURSE_CD"));
				workDto.setBigClassificationCd(res.getString("BIG_CLASSIFICATION_CD"));
				workDto.setMiddleClassificationCd(res.getString("MIDDLE_CLASSIFICATION_CD"));
				workDto.setSmallClassificationCd(res.getString("SMALL_CLASSIFICATION_CD"));
				workDto.setCourseJnm(res.getString("COURSE_JNM"));
				workDto.setCourseEnm(res.getString("COURSE_ENM"));
				workList.add(workDto);
			}

			//データが存在しない場合、エラー
			if(workList.size()<1){
				list=new ArrayList<TeacherCourseListDto>();
				dto=new TeacherCourseListDto();
				dto.setReturnCode(2);
				list.add(dto);
				return list;
			}


			//汎用フィールド名の取得
			CodeManagMstCache cache=CodeManagMstCache.getInstance();
			//大分類の名称一覧取得
			LinkedHashMap<String, CodeManagMstDto> workBigList=cache.getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
			//中分類の名称一覧取得
			LinkedHashMap<String, CodeManagMstDto> workMidList=cache.getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
			//小分類の名称一覧取得
			LinkedHashMap<String, CodeManagMstDto> workSmoList=cache.getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);
			//大分類(英語)の名称一覧取得
			LinkedHashMap<String, CodeManagMstDto> workBigListE=cache.getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T);
			//中分類(英語)の名称一覧取得
			LinkedHashMap<String, CodeManagMstDto> workMidListE=cache.getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T);
			//小分類(英語)の名称一覧取得
			LinkedHashMap<String, CodeManagMstDto> workSmoListE=cache.getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T);

			//データ編集
			StringBuffer courseNMJ = new StringBuffer();
			StringBuffer courseNME = new StringBuffer();

			list=new ArrayList<TeacherCourseListDto>();

			for(int i=0;workList.size()>i;i++){
				workDto=workList.get(i);
				dto=new TeacherCourseListDto();

				//コースの編集
				//コースコード
				dto.setCourseId(workDto.getCourseCd());

				//各分類コード
				dto.setBigClassificationCd(workDto.getBigClassificationCd());
				dto.setMiddleClassificationCd(workDto.getMiddleClassificationCd());
				dto.setSmallClassificationCd(workDto.getSmallClassificationCd());

				//日本語コース名
				courseNMJ.setLength(0);
				courseNMJ.append(getName(workBigList,workDto.getBigClassificationCd())).append("/");
				courseNMJ.append(getName(workMidList,workDto.getMiddleClassificationCd())).append("/");
				courseNMJ.append(getName(workSmoList,workDto.getSmallClassificationCd())).append("/");
				courseNMJ.append(workDto.getCourseJnm());
				dto.setCourseJnm(courseNMJ.toString());

				//英語コース名
				courseNME.setLength(0);
				courseNME.append(getName(workBigListE,workDto.getBigClassificationCd())).append("/");
				courseNME.append(getName(workMidListE,workDto.getMiddleClassificationCd())).append("/");
				courseNME.append(getName(workSmoListE,workDto.getSmallClassificationCd())).append("/");
				courseNME.append(workDto.getCourseEnm());
				dto.setCourseEnm(courseNME.toString());

				dto.setReturnCode(0);
				list.add(dto);
			}

			return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
	        	res.close();
        	}catch(SQLException se ){
        		throw new NaiException(se);
        	}
        }
	}
*/

	/**
	 * 汎用コードから汎用フィールドを抽出する(TECS)<br>
	 * <br>
	 * @param ArrayList<CodeManagMstDto>
	 * @param managerCd
	 * @return String
	 */
/*
    private String getName(LinkedHashMap<String, CodeManagMstDto> list,String managerCd) {
    	CodeManagMstDto dto;
    	dto=list.get(managerCd);
    	return dto.getManagerNm();
    }
*/

}
