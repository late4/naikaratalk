package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dao.CourseGoodsListDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録<br>
 * <b>クラス名称　　　:</b>詳細説明Logicクラス。<br>
 * <b>クラス概要　　　:</b>詳細説明の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */
public class DetailDescriptionLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public DetailDescriptionLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * @param GoodsMstDto:dto
     * @return int:DataCount
     * @throws NaiException
     */
    public int getRowCount(CourseMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.COURSE_MST, setConditions(dto));

        // 戻り値
        return DataCount;

    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ<br>
     * @return OrganizationMstDto<br>
     * @exception NaiException
     */
    public CourseMstDto select(CourseMstDto dto) throws NaiException {

        CourceMstDao dao = new CourceMstDao(this.conn);

        // 並び順:組織ID の昇順
        OrderCondition orderBy = new OrderCondition();
        ArrayList<CourseMstDto> resultDto = (ArrayList<CourseMstDto>) dao.searchWithNm(setConditions(dto), orderBy);
        CourseMstDto dtoResult = new CourseMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * コース単位の該当商品の取得処理。<br>
     * <br>
     * コース単位の該当商品の取得処理。<br>
     * <br>
     * @param dto CourseGoodsListDto
     * @return dtoResult CourseGoodsListDto
     * @throws NaiException
     */
    public List<CourseGoodsListDto> selectGoods(CourseGoodsListDto cglDto) throws NaiException {

        // 初期化処理
        List<CourseGoodsListDto> list = new ArrayList<CourseGoodsListDto>();

        // DAOの初期化
        CourseGoodsListDao dao = new CourseGoodsListDao(this.conn);

        // 並び順の初期化
        OrderCondition orderBy = new OrderCondition();

        // 一覧データを取得する
        list = dao.search(setConditionsGood(cglDto), orderBy);

        // 戻り値
        return list;
    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param CourseMstDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(CourseMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 組織ID
        conditions.addCondition(CourceMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        // 戻り値
        return conditions;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param CourseGoodsListDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditionsGood(CourseGoodsListDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 組織ID
        conditions.addCondition(CourseGoodsListDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        // 戻り値
        return conditions;

    }

}
