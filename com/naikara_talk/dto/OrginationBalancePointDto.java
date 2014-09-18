package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>組織の残高ポイントクラス<br>
 * <b>クラス概要　　　:</b>組織の残高ポイントDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/07 TECS 新規作成
 */
public class OrginationBalancePointDto extends AbstractDto {

    private BigDecimal sumBalancePoint; // 組織の残高ポイント
    private int returnCode;             // リターンコード

    // 画面処理必要項目
    private String organizationId;             // 組織ID
    private String contractEndDt;              // 契約終了日

    /**
     * 組織の残高ポイント取得<br>
     * <br>
     * 組織の残高ポイントを戻り値で返却する<br>
     * <br>
     * @return sumBalancePoint
     */
    public BigDecimal getSumBalancePoint() {
        return sumBalancePoint;
    }

    /**
     * 組織の残高ポイント設定<br>
     * <br>
     * 組織の残高ポイントを引数で設定する<br>
     * <br>
     * @param sumBalancePoint
     */
    public void setSumBalancePoint(BigDecimal sumBalancePoint) {
        this.sumBalancePoint = sumBalancePoint;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;

    }

    /**
     * 組織ID取得<br>
     * <br>
     * 組織IDを戻り値で返却する<br>
     * <br>
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 組織ID設定<br>
     * <br>
     * 組織IDを引数で設定する<br>
     * <br>
     * @param organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 契約終了日取得<br>
     * <br>
     * 契約終了日を戻り値で返却する<br>
     * <br>
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * 契約終了日設定<br>
     * <br>
     * 契約終了日を引数で設定する<br>
     * <br>
     * @param contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

}
