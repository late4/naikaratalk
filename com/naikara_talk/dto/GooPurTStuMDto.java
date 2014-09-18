package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>商品購入の有償利用ポイントクラス<br>
 * <b>クラス概要　　　:</b>商品購入の有償利用ポイントDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/01 TECS 新規作成
 */
public class GooPurTStuMDto extends AbstractDto {

    private String studentId;               // 受講者ID (購入者ID)
    private String purchaseDt;              // 購入日
    private String organizationId;          // 組織ID
    private BigDecimal paymentUsePoint;     // 有償利用ポイント

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
     * 受講者ID (購入者ID)取得<br>
     * <br>
     * 受講者ID (購入者ID)を戻り値で返却する<br>
     * <br>
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID (購入者ID)設定<br>
     * <br>
     * 受講者ID (購入者ID)を引数で設定する<br>
     * <br>
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 購入日取得<br>
     * <br>
     * 購入日を戻り値で返却する<br>
     * <br>
     * @return purchaseDt
     */
    public String getPurchaseDt() {
        return purchaseDt;
    }

    /**
     * 購入日設定<br>
     * <br>
     * 購入日を引数で設定する<br>
     * <br>
     * @param purchaseDt
     */
    public void setPurchaseDt(String purchaseDt) {
        this.purchaseDt = purchaseDt;
    }

    /**
     * 有償利用ポイント取得<br>
     * <br>
     * 有償利用ポイントを戻り値で返却する<br>
     * <br>
     * @return paymentUsePoint
     */
    public BigDecimal getPaymentUsePoint() {
        return paymentUsePoint;
    }

    /**
     * 有償利用ポイント設定<br>
     * <br>
     * 有償利用ポイントを引数で設定する<br>
     * <br>
     * @param paymentUsePoint
     */
    public void setPaymentUsePoint(BigDecimal paymentUsePoint) {
        this.paymentUsePoint = paymentUsePoint;
    }

}
