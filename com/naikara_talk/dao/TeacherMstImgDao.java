package com.naikara_talk.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.TeacherMstCacheDto;
import com.naikara_talk.exception.NaiException;

public class TeacherMstImgDao extends AbstractDao {

	/** 適用開始日　条件項目　*/
	public static final String COND_CONTRACT_START_DT = "CONTRACT_START_DT";
	/** 適用終了日　条件項目　*/
	public static final String COND_CONTRACT_END_DT = "CONTRACT_END_DT";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public TeacherMstImgDao(Connection con) {
		this.conn = con;
	}

	public List<TeacherMstCacheDto> search(ConditionMapper conditions) throws NaiException {
    	/** 画像用　*/
        Blob imgPhoto;
    	byte byteData[] = null;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		// 項目名が古いため、エラー回避の為、一時的にTECS修正 Start
		sb.append("  USER_ID ");
		sb.append(", NICK_ANM ");
		sb.append(", NATIONALITY_CD ");
		sb.append(", NATIVE_LANG_CD ");
		sb.append(", COUNTRY_CD ");
		sb.append(", AREA_NO_CD ");
		sb.append(", CONTRACT_DT ");
		sb.append(", CONTRACT_START_DT ");
		sb.append(", CONTRACT_END_DT ");
		sb.append(", SELLING_POINT ");
		sb.append(", SELF_RECOMMENDATION ");
		sb.append(", IMG_PHOTO ");
		// 項目名が古いため、エラー回避の為、一時的にTECS修正 End


		sb.append("from ");
		sb.append(" TEACHER_MST ");
if(!StringUtils.isEmpty(conditions.getConditionString())) {
		sb.append("where ");
		sb.append(conditions.getConditionString());
}

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

			ArrayList<TeacherMstCacheDto> list = new ArrayList<TeacherMstCacheDto>();

			while (res.next()){

				TeacherMstCacheDto retDto = new TeacherMstCacheDto();
				retDto.setUserId(res.getString(1));
				retDto.setNickAnm(res.getString(2));
				retDto.setNationalityCd(res.getString(3));
				retDto.setNativeLangCd(res.getString(4));
				retDto.setCountryCd(res.getString(5));
				retDto.setAreaNoCd(res.getString(6));
				retDto.setContractDt(res.getString(7));
				retDto.setContractStartDt(res.getString(8));
				retDto.setContractEndDt(res.getString(9));
				retDto.setSellingPoint(res.getString(10));
				retDto.setSelfRecommendation(res.getString(11));

                // Blob型からByte型へ変換して格納
                imgPhoto = res.getBlob("IMG_PHOTO");
                if (imgPhoto == null) {
            		byteData = new byte[4096];
                } else {
                	byteData = imgPhoto.getBytes(1, (int)imgPhoto.length());
                }
                retDto.setImgPhoto(byteData);

				//retDto.setImgPhoto(res.getBytes(12));

				list.add(retDto);
			}

			return list;

		} catch ( SQLException se ) {
			throw new NaiException(se);
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NaiException(e);
			}
		}
	}
}
