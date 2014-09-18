package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dao.CourseGoodsListDao;
import com.naikara_talk.dao.CourseGoodsMstDao;
import com.naikara_talk.dao.CourseUsePointMstDao;
import com.naikara_talk.dao.SaleGoodsMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseGoodsMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)Logic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/07 TECS 新規作成
 */
public class CourseMstLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CourseMstLogic(Connection con) {
        this.conn = con;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * データの存在チェック。<br>
     * <br>
     * @param CourseMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(CourseMstDto dto) throws NaiException {

        CourceMstDao dao = new CourceMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        List<CourseMstDto> resultDto = dao.search(setConditions(dto), orderBy);

        return resultDto.get(0).getReturnCode();

    }

    /**
     * 初期表示の検索処理。<br>
     * <br>
     * 初期表示の検索処理。<br>
     * <br>
     * @param CourseMstDto 画面のパラメータ<br>
     * @return CourseMstDto<br>
     * @exception NaiException
     */
    public CourseMstDto select(CourseMstDto dto) throws NaiException {

        CourceMstDao dao = new CourceMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        List<CourseMstDto> resultDto = dao.search(setConditions(dto), orderBy);

        CourseMstDto dtoResult = new CourseMstDto();

        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * コースマスタ登録処理<br>
     * <br>
     * コースマスタ登録処理<br>
     * <br>
     * @param CourseMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertCourseMst(CourseMstDto dto) throws NaiException {
        CourceMstDao dao = new CourceMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * コースマスタ更新処理<br>
     * <br>
     * コースマスタ更新処理<br>
     * <br>
     * @param CourseMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int updateCourseMst(CourseMstDto dto) throws NaiException {
        CourceMstDao dao = new CourceMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * コース別商品マスタ取得処理。<br>
     * <br>
     * コース別商品マスタ取得処理。<br>
     * <br>
     * @param CourseGoodsListDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getCourseGoodsExist(CourseGoodsListDto dto) throws NaiException {

        List<CourseGoodsListDto> courseGoodsListList = new ArrayList<CourseGoodsListDto>();

        CourseGoodsListDao dao = new CourseGoodsListDao(this.conn);

        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(CourseGoodsListDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        courseGoodsListList = dao.search(conditions, new OrderCondition());

        return courseGoodsListList.get(0).getReturnCode();
    }

    /**
     * コース別商品マスタ取得処理。<br>
     * <br>
     * コース別商品マスタ取得処理。<br>
     * <br>
     * @param CourseGoodsListDto 画面のパラメータ<br>
     * @return List<CourseGoodsListDto><br>
     * @exception NaiException
     */
    public List<CourseGoodsListDto> getCourseGoodsListDtos(CourseGoodsListDto dto) throws NaiException {

        List<CourseGoodsListDto> courseGoodsListList = new ArrayList<CourseGoodsListDto>();

        CourseGoodsListDao dao = new CourseGoodsListDao(this.conn);

        OrderCondition orderBy = new OrderCondition();
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(CourseGoodsListDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        courseGoodsListList = dao.search(conditions, orderBy);

        return courseGoodsListList;
    }

    /**
     * コース別商品マスタデータの存在チェック。<br>
     * <br>
     * コース別商品マスタデータの存在チェック。<br>
     * <br>
     * @param CourseGoodsMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExistCourseGoodsMstDto(CourseGoodsMstDto dto) throws NaiException {

        CourseGoodsMstDao dao = new CourseGoodsMstDao(this.conn);

        ConditionMapper conditions = new ConditionMapper();
        // コースコード
        conditions.addCondition(CourseGoodsMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());
        // 該当商品コード
        conditions.addCondition(CourseGoodsMstDao.COND_GOODS_CD, ConditionMapper.OPERATOR_EQUAL, dto.getGoodsCd());

        List<CourseGoodsMstDto> resultList = dao.search(conditions, new OrderCondition());

        return resultList.get(0).getReturnCode();

    }

    /**
     * コース別商品マスタ登録処理<br>
     * <br>
     * コース別商品マスタ登録処理<br>
     * <br>
     * @param CourseUsePointMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertCourseGoodsMst(CourseGoodsMstDto dto) throws NaiException {
        CourseGoodsMstDao dao = new CourseGoodsMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * コース別商品マスタ更新処理<br>
     * <br>
     * コース別商品マスタ更新処理<br>
     * <br>
     * @param CourseUsePointMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int updateCourseGoodsMst(CourseGoodsMstDto dto) throws NaiException {
        CourseGoodsMstDao dao = new CourseGoodsMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * コース別商品マスタの削除処理<br>
     * <br>
     * コース別商品マスタの削除処理<br>
     * <br>
     * @param CourseGoodsMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int deleteCourseGoodsMst(CourseGoodsMstDto dto) throws NaiException {
        CourseGoodsMstDao dao = new CourseGoodsMstDao(this.conn);

        ConditionMapper conditions = new ConditionMapper();
        // コースコード
        conditions.addCondition(CourseGoodsMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());
        // 該当商品コード
        conditions.addCondition(CourseGoodsMstDao.COND_GOODS_CD, ConditionMapper.OPERATOR_EQUAL, dto.getGoodsCd());

        return dao.delete(conditions);
    }

    /**
     * コース別利用ポイントマスタ登録処理<br>
     * <br>
     * コース別利用ポイントマスタ登録処理<br>
     * <br>
     * @param CourseUsePointMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int insertCourseUsePointMst(CourseUsePointMstDto dto) throws NaiException {
        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);
        return dao.insert(dto);
    }

    /**
     * コース別利用ポイントマスタ更新処理<br>
     * <br>
     * コース別利用ポイントマスタ更新処理<br>
     * <br>
     * @param CourseUsePointMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int updateCourseUsePointMst(CourseUsePointMstDto dto) throws NaiException {
        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);
        return dao.update(dto);
    }

    /**
     * コース別利用ポイントマスタの削除処理<br>
     * <br>
     * コース別利用ポイントマスタの削除処理<br>
     * <br>
     * @param CourseUsePointMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    public int delCourseUsePointMst(CourseUsePointMstDto dto) throws NaiException {
        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);

        ConditionMapper conditions = new ConditionMapper();
        // コースコード
        conditions.addCondition(CourseUsePointMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());
        // 利用ポイント開始日
        conditions.addCondition(CourseUsePointMstDao.COND_USE_POINT_STR_DT, ConditionMapper.OPERATOR_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointStrDt()));

        return dao.delete(conditions);
    }

    /**
     * コース別利用ポイントデータの存在チェック。<br>
     * <br>
     * コース別利用ポイントデータの存在チェック。<br>
     * <br>
     * @param CourseUsePointMstDto 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExistCourseUsePointMst(CourseUsePointMstDto dto) throws NaiException {

        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);

        OrderCondition orderBy = new OrderCondition();

        ConditionMapper conditions = new ConditionMapper();
        // コースコード
        conditions.addCondition(CourseUsePointMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());
        // 利用ポイント開始日
        conditions.addCondition(CourseUsePointMstDao.COND_USE_POINT_STR_DT, ConditionMapper.OPERATOR_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointStrDt()));

        List<CourseUsePointMstDto> resultDto = dao.search(conditions, orderBy);

        return resultDto.get(0).getReturnCode();

    }

    /**
     * 該当商品取得処理。<br>
     * <br>
     * 該当商品取得処理。<br>
     * <br>
     * @param GoodsMstDto 画面のパラメータ<br>
     * @return GoodsMstDto<br>
     * @exception NaiException
     */
    public GoodsMstDto getGoods(GoodsMstDto dto) throws NaiException {

        SaleGoodsMstDao dao = new SaleGoodsMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(SaleGoodsMstDao.COND_GOODS_CD, ConditionMapper.OPERATOR_EQUAL, dto.getGoodsCd());

        ArrayList<GoodsMstDto> resultDto = dao.search(conditions, new OrderCondition());

        GoodsMstDto dtoResult = new GoodsMstDto();

        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDto.get(0).getReturnCode()) {
            dtoResult = resultDto.get(0);
        }

        return dtoResult;

    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> list = cache.getList(category);

        Iterator<String> iter = list.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            CodeManagMstDto dto = list.get(key);
            hashMap.put(dto.getManagerCd(), dto.getManagerNm());
        }

        return hashMap;
    }

    /**
     * 検索条件の設定。<br>
     * <br>
      * @param CourceMstDao
      * @return ConditionMapper:conditions
      * @throws NaiException
     */
    private ConditionMapper setConditions(CourseMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コースコード
        conditions.addCondition(CourceMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        // 戻り値
        return conditions;

    }

}
