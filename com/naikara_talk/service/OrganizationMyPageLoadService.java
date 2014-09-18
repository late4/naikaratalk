package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.GooPurTStuMDto;
import com.naikara_talk.dto.LesResPerTStuMDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.OrginationBalancePointDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationBalancePointLogic;
import com.naikara_talk.logic.OrganizationMyPageLogic;
import com.naikara_talk.model.OrganizationMyPageModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人_初期処理<br>
 * <b>クラス名称　　　:</b>組織マイページ初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/01 TECS 新規作成。
 */
public class OrganizationMyPageLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    public OrganizationMyPageModel select(OrganizationMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationMyPageLogic organizationMyPageLogic = new OrganizationMyPageLogic(conn);

            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            OrganizationMstDto resultDto = organizationMyPageLogic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    public OrganizationMyPageModel selectBalancePoint(OrganizationMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationBalancePointLogic organizationBalancePointLogic = new OrganizationBalancePointLogic(conn);

            // DTOの初期化
            OrginationBalancePointDto prmDto = new OrginationBalancePointDto();

            // Model値をDTOにセット
            prmDto = this.modelToBalanceDto(model);

            // 検索実行
            OrginationBalancePointDto resultDto = organizationBalancePointLogic.getBalancePoint(
                    prmDto.getOrganizationId(), prmDto.getContractEndDt());

            // DTO値をModelにセット
            model = this.balancePointDtoToModel(resultDto, model);

            return model;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    public OrganizationMyPageModel selectPoint(OrganizationMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationMyPageLogic organizationMyPageLogic = new OrganizationMyPageLogic(conn);

            // DTOの初期化
            GooPurTStuMDto prmDto = new GooPurTStuMDto();

            // Model値をDTOにセット
            prmDto = this.modelToGoodDto(model);

            // 検索実行
            GooPurTStuMDto resultDto = organizationMyPageLogic.getPoint(prmDto.getOrganizationId(),
                    prmDto.getPurchaseDt());

            // DTO値をModelにセット
            model = this.pointDtoToModel(resultDto, model);

            return model;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    public OrganizationMyPageModel selectLesPoint(OrganizationMyPageModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationMyPageLogic organizationMyPageLogic = new OrganizationMyPageLogic(conn);

            // DTOの初期化
            LesResPerTStuMDto prmDto = new LesResPerTStuMDto();

            // Model値をDTOにセット
            prmDto = this.modelToLesDto(model);

            // 検索実行
            LesResPerTStuMDto resultDto = organizationMyPageLogic.getLesPoint(prmDto.getOrganizationId(),
                    prmDto.getLessonDt());

            // DTO値をModelにセット
            model = this.LesPointDtoToModel(resultDto, model);

            return model;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            OrganizationMyPageLogic organizationMyPageLogic = new OrganizationMyPageLogic(conn);

            // コード管理マスタ検索
            return organizationMyPageLogic.selectCodeMst(category);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param OrganizationMyPageModel 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private OrganizationMstDto modelToDto(OrganizationMyPageModel model) throws NaiException {

        // DTOの初期化
        OrganizationMstDto prmDto = new OrganizationMstDto();
        prmDto.setOrganizationId(model.getOrganizationId());                  // 組織ID
        prmDto.setConsSeq(model.getConsSeq());                                // 連番
        return prmDto;

    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private OrginationBalancePointDto modelToBalanceDto(OrganizationMyPageModel model) throws NaiException {

        // DTOの初期化
        OrginationBalancePointDto prmDto = new OrginationBalancePointDto();
        prmDto.setOrganizationId(model.getOrganizationId());                                        // 組織ID
        prmDto.setContractEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()));      // 契約終了日
        return prmDto;

    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private GooPurTStuMDto modelToGoodDto(OrganizationMyPageModel model) throws NaiException {

        // DTOの初期化
        GooPurTStuMDto prmDto = new GooPurTStuMDto();
        prmDto.setOrganizationId(model.getOrganizationId());   // 組織ID
        prmDto.setPurchaseDt(DateUtil.getSysDateYMNoSplit());  // サーバーのシステム日付の年月
        return prmDto;

    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private LesResPerTStuMDto modelToLesDto(OrganizationMyPageModel model) throws NaiException {

        // DTOの初期化
        LesResPerTStuMDto prmDto = new LesResPerTStuMDto();
        prmDto.setOrganizationId(model.getOrganizationId());   // 組織ID
        prmDto.setLessonDt(DateUtil.getSysDateYMNoSplit());    // サーバーのシステム日付の年月
        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param OrganizationMstDto
     * @param OrganizationMyPageModel
     * @return OrganizationMyPageModel
     * @throws NaiException
     */
    private OrganizationMyPageModel dtoToModel(OrganizationMstDto prmDto, OrganizationMyPageModel model)
            throws NaiException {

        model.setManagMailAddress1(prmDto.getManagMailAddress1());                                  // メールアドレス
        model.setContractStrDt(NaikaraStringUtil.converToYYYY_MM_DD(prmDto.getContractStrDt()));    // 契約期間：開始日
        model.setContractEndDt(NaikaraStringUtil.converToYYYY_MM_DD(prmDto.getContractEndDt()));    // 契約期間：終了日
        model.setManagGenderKbn(prmDto.getManagGenderKbn());                                    // 責任者性別区分
        model.setTempPointNum(prmDto.getTempPointNum());                                            // 契約ポイント
        model.setBalancePointNum(prmDto.getBalancePointNum());                                      // 未割振ポイント(残高)

        return model;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param OrginationBalancePointDto
     * @param OrganizationMyPageModel
     * @return OrganizationMyPageModel
     * @throws NaiException
     */
    private OrganizationMyPageModel balancePointDtoToModel(OrginationBalancePointDto prmDto,
            OrganizationMyPageModel model) throws NaiException {

        model.setBalancePoint(prmDto.getSumBalancePoint());         // ポイント残高

        return model;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param GooPurTStuMDto
     * @param OrganizationMyPageModel
     * @return OrganizationMyPageModel
     * @throws NaiException
     */
    private OrganizationMyPageModel pointDtoToModel(GooPurTStuMDto prmDto, OrganizationMyPageModel model)
            throws NaiException {

        model.setPaymentUsePoint(prmDto.getPaymentUsePoint());         // 商品購入テーブルの有償利用ポイント

        return model;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param LesResPerTStuMDto
     * @param OrganizationMyPageModel
     * @return OrganizationMyPageModel
     * @throws NaiException
     */
    private OrganizationMyPageModel LesPointDtoToModel(LesResPerTStuMDto prmDto, OrganizationMyPageModel model)
            throws NaiException {

        model.setLesPaymentUsePoint(prmDto.getPaymentUsePoint());         // レッスン予実テーブルの有償利用ポイント

        return model;

    }

}
