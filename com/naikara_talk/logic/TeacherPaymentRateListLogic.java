package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.TeaCouMTeaRateMCouUsePoiMDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.TeaCouMTeaRateMCouUsePoiMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>支払単価表。<br>
 * <b>クラス名称       :</b>支払単価表初期処理Logicクラス。<br>
 * <b>クラス概要       :</b>支払比率で、コース単位の支払単価の一覧表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherPaymentRateListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherPaymentRateListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 講師マスタのデータ取得処理。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return dtoResult UserMstTeacherMstDto
     * @throws NaiException
     */
    public List<TeaCouMTeaRateMCouUsePoiMDto> select(TeaCouMTeaRateMCouUsePoiMDto dto) throws NaiException {

        TeaCouMTeaRateMCouUsePoiMDao dao = new TeaCouMTeaRateMCouUsePoiMDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeaCouMTeaRateMCouUsePoiMDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getUserId());
        conditions.addCondition(TeaCouMTeaRateMCouUsePoiMDao.COND_USE_POINT_STR_DT,
                ConditionMapper.OPERATOR_LESS_EQUAL, DateUtil.getSysDate());
        conditions.addCondition(TeaCouMTeaRateMCouUsePoiMDao.COND_USE_POINT_END_DT,
                ConditionMapper.OPERATOR_LARGE_EQUAL, DateUtil.getSysDate());
        conditions.addCondition(TeaCouMTeaRateMCouUsePoiMDao.COND_START_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                DateUtil.getSysDate());
        conditions.addCondition(TeaCouMTeaRateMCouUsePoiMDao.COND_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());
        // 並び順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(TeaCouMTeaRateMCouUsePoiMDao.COND_COURSE_CD, OrderCondition.ASC);

        // 利用者マスタ、講師マスタから対象データの取得
        List<TeaCouMTeaRateMCouUsePoiMDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList;

    }

}
