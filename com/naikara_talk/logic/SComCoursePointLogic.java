package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dao.CourseGoodsListDao;
import com.naikara_talk.dao.CourseUsePointListDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.CourseUsePointListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称       :</b>コース別ポイント初期処理Logicクラス。<br>
 * <b>クラス概要       :</b>コース別ポイントの一覧表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/06 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：キーワード検索の削除・コース名の追加対応
 */
public class SComCoursePointLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SComCoursePointLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param CourseUsePointListDto<br>
     * @return int:DataCount<br>
     * @exception NaiException
     */
    public int getRowCount(CourseMstDto dto) throws NaiException {

        // 初期化処理
        CourceMstDao dao = new CourceMstDao(this.conn);

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(setConditions(dto), dto);

        // 戻り値
        return DataCount;

    }

    /**
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param CourseUsePointListDto 画面のパラメータ<br>
     * @return ConditionMapper:conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(CourseMstDto dto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用開始日
        conditions.addCondition(CourseUsePointListDao.USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                dto.getUseStrDt());

        // 利用終了日
        conditions.addCondition(CourseUsePointListDao.USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                dto.getUseEndDt());

        // 戻り値
        return conditions;

    }

    /**
     * コースマスタのデータ取得処理。<br>
     * <br>
     * コースマスタのデータ取得処理。<br>
     * <br>
     * @param dto CourseUsePointListDto
     * @return dtoResult CourseUsePointListDto
     * @throws NaiException
     */
    public List<CourseUsePointListDto> select(CourseUsePointListDto dto) throws NaiException {

        // 初期化処理
        List<CourseUsePointListDto> list = new ArrayList<CourseUsePointListDto>();

        CourseUsePointListDao dao = new CourseUsePointListDao(this.conn);

        // 2014/04/22 Add Start 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応／検索条件：コース名の追加対応
        // 一覧データを取得する
        //list = dao.search(dto.getSysDate(), dto.getKeyword1(), dto.getKeyword2(), dto.getKeyword3(), dto.getKeyword4(),
        //        dto.getKeyword5());

        list = dao.search(dto.getHopeDt(), dto.getSearchKeyword(), "", "", "",
                "", dto.getSearchBigClassificationCd(), dto.getSearchMiddleClassificationCd(),
                dto.getSearchSmallClassificationCd(), "");

        // 2014/04/22 Add End   検索条件：希望日のコントロールをテキストからプルダウンへ変更対応／検索条件：コース名の追加対応


        // 戻り値
        return list;
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
        list = dao.search(setConditions(cglDto), orderBy);

        // 戻り値
        return list;
    }

    /**
     * 検索条件設定<br>
     * <br>
     * 検索条件を設定<br>
     * <br>
     * @param CourseGoodsListDto
     * @return conditions 検索条件<br>
     * @exception NaiException
     */
    private ConditionMapper setConditions(CourseGoodsListDto cglDto) throws NaiException {

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // ログインIDを入力されている場合
        conditions
                .addCondition(CourseGoodsListDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, cglDto.getCourseCd());

        // 戻り値
        return conditions;

    }

    /**
     * 検索処理<br>
     * <br>
     * 検索処理<br>
     * <br>
     * @param CourseUsePointListDto 画面のパラメータ<br>
     * @return List<CourseUsePointListDto> 一覧データ <br>
     * @exception Exception
     */
    public List<CourseUsePointListDto> selectList(CourseUsePointListDto dto) throws NaiException {

        // 初期化処理
        List<CourseUsePointListDto> list = new ArrayList<CourseUsePointListDto>();
        CourseUsePointListDao dao = new CourseUsePointListDao(this.conn);

        // 並び順:コースコード の昇順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(CourseUsePointListDao.COURSE_CD, OrderCondition.ASC);

        // 2014/04/22 Add Start 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応／検索条件：コース名の追加対応
        // 一覧データを取得する
        //list = dao.search(dto.getSysDate(), dto.getKeyword1(), dto.getKeyword2(), dto.getKeyword3(), dto.getKeyword4(),
        //        dto.getKeyword5());

        list = dao.search(dto.getHopeDt(), dto.getSearchKeyword(), "", "", "",
                "", dto.getSearchBigClassificationCd(), dto.getSearchMiddleClassificationCd(),
                dto.getSearchSmallClassificationCd(), "");
        // 2014/04/22 Add End   検索条件：希望日のコントロールをテキストからプルダウンへ変更対応／検索条件：コース名の追加対応

        // 戻り値
        return list;

    }

    // 2014/04/22 Add Start 検索条件：コース名の追加対応
    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理を行う。<br>
     * <br>
     * @param String 汎用コード <br>
     * @return LinkedHashMap<String, String> ハッシュマップ<br>
     * @throws NaiException
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
    // 2014/04/22 Add Start 検索条件：コース名の追加対応

}
