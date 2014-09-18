package com.naikara_talk.logic;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.naikara_talk.dao.OrderNumbersMstDao;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.OrderNumbersMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>採番マスタ自動採番クラス<br>
 * <b>クラス概要　　　:</b>採番マスタ自動採番DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/23 TECS 新規作成
 */
public class OrderNumbersMstLogic {

    /** 返却値　引数のパラメータチェックErr */
    public static final int RETURN_ERR_PARAM = 1;

    /** 返却値　オーダー番号なしErr */
    public static final int RETURN_ERR_NO_ORDER_NO = 2;

    /** 返却値　更新なしErr */
    public static final int RETURN_ERR_NO_UPD = 3;

    /** 返却値　正常 */
    public static final int RETURN_OK = 0;

	/**
	 * 採番取得(自動更新付)<br>
	 * <br>
	 * 採番マスタのデータ取得、自動更新を行う<br>
	 * <br>
	 * @param orderNumId
	 * @param yyMM
	 * @return OrderNumberDto
	 * @throws NaiException
	 */
	public OrderNumberDto getOrderNumber(String orderNumId,String yyyyMMdd) throws NaiException {

		//戻り値とリターンコードの初期化
		OrderNumberDto dto=null;
		String seqNum = null;     // シーケンスコード
		Connection conn = null;   // DB接続は採番固有とするためココで定義する

		//引数のパラメータチェック
		//採番IDチェック
		String[] idList ={"ST", "TE", "CU", "BU", "PT"
							, "BY", "RE", "OW", "DM"}; //採番IDのリスト
		Boolean check = false;
		for(int i = 0; idList.length > i; i++) {
			if(orderNumId.equals(idList[i])){
				check = true;
				break;
			}
		}
		if(!check){
			dto=new OrderNumberDto();
			dto.setReturnCode(RETURN_ERR_PARAM);
			return dto;
		}
		//システム日付のチェック
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try {
			sdf.parse(yyyyMMdd);
		} catch (Exception e) {
			dto=new OrderNumberDto();
			dto.setReturnCode(RETURN_ERR_PARAM);
			return dto;
		}


		OrderNumbersMstDto workDto = new OrderNumbersMstDto();
		workDto.setOrderNumbersId(orderNumId);

        try {
            conn = DbUtil.getConnection();    // DB接続

            OrderNumbersMstDao dao= new OrderNumbersMstDao(conn);

            //SEQ更新
            if(dao.updateData(workDto) > NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD){
            	//データ更新に成功した場合
            	//採番マスタからデータ取得
            	workDto = dao.search(orderNumId);

            	if(workDto.getOrderNumbersId()==null || "".equals(workDto.getOrderNumbersId())){
            		dto=new OrderNumberDto();
            		dto.setReturnCode(RETURN_ERR_NO_ORDER_NO);
            		return dto;
            	}

            	seqNum=workDto.getOrderNumbersId();
            	StringBuffer sb = new StringBuffer();
            	sb = sb.append(seqNum).append(String.valueOf(NaikaraStringUtil.nvlString(workDto.getYymm())));
            	seqNum=sb.toString();

            	String str="";
            	//有効桁数でゼロ埋め
            	for(int i=0;workDto.getEffectiveDigitNum()>i;i++){
            		StringBuffer sbE = new StringBuffer();
            		sbE = sbE.append(str).append("0");
            		str = sbE.toString();
            	}
            	DecimalFormat seqFormat = new DecimalFormat(str);
            	StringBuffer sbN = new StringBuffer();
            	sbN = sbN.append(seqNum).append(seqFormat.format(workDto.getSeq()));
            	seqNum=sbN.toString();

            }else{
            	//データ更新に失敗した場合
            	dto=new OrderNumberDto();
            	dto.setReturnCode(RETURN_ERR_NO_UPD);
            	return dto;
            }

            // コミット処理
            conn.commit();

            dto=new OrderNumberDto();
            dto.setOrderNumber(seqNum);
            dto.setReturnCode(RETURN_OK);
            return dto;

        } catch (Exception e) {
        	try {
        		// ロールバック処理
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
        	}
        }
	}
}
