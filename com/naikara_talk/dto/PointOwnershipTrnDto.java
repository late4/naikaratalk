package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ポイン所有テーブルクラス<br>
 * <b>クラス概要　　　:</b>ポイン所有テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class PointOwnershipTrnDto extends AbstractDto {

    private String ownershipId;            // 所有ID
    private String compensationFreeKbn;    // 有償無償区分
    private String studentId;              // 受講者ID
    private String effectiveStrDt;         // 有効開始日
    private String effectiveEndDt;         // 有効終了日
    private String feeKbn;                 // 通常月謝区分
    private BigDecimal purchaseYen;        // 購入金額(税込)
    private BigDecimal purchaseFreePoint;  // 購入／無償ポイント
    private BigDecimal balancePoint;       // 残高ポイント
    private String pointAdditionReasonCd;  // ポイント付加理由コード
    private String purchaseDt;             // 購入日
    private String purchaseTm;             // 購入時刻
    private String pointCd;                // ポイントコード
    private String endDt;                  // 月謝停止日
    private String beginningPurchaseDt;    // 月謝用の実購入日
    private String beginningPurchaseTm;    // 月謝用の実購入時刻
    private String screenSystemKbn;        // 画面システム作成区分
    private String profileId;              // プロファイルID
    private String endFlg;                 // 月謝停止フラグ
    private int recordVerNo;               // レコードバージョン番号
    private Timestamp insertDttm;          // 登録日時
    private String insertCd;               // 登録者コード
    private Timestamp updateDttm;          // 更新日時
    private String updateCd;               // 更新者コード
    private int returnCode;                // リターンコード
    private String pointAdditionReason;    // ポイント付加理由
    private String updateKbn;              // 処理区分（新規："1"、削除："2"、不変：null/""）

    private String compensationFreeKbnNm;    // 有償無償区分名
    private String feeKbnNm;                 // 通常月謝区分名
    private BigDecimal usePoint;                 // 利用ポイント

    /**
     * 所有ID取得<br>
     * <br>
     * 所有IDを戻り値で返却する<br>
     * <br>
     * @return ownershipId
     */
    public String getOwnershipId() {
        return ownershipId;
    }

    /**
     * 所有ID設定<br>
     * <br>
     * 所有IDを引数で設定する<br>
     * <br>
     * @param ownershipId
     */
    public void setOwnershipId(String ownershipId) {
        this.ownershipId = ownershipId;
    }

    /**
     * 有償無償区分取得<br>
     * <br>
     * 有償無償区分を戻り値で返却する<br>
     * <br>
     * @return compensationFreeKbn
     */
    public String getCompensationFreeKbn() {
        return compensationFreeKbn;
    }

    /**
     * 有償無償区分設定<br>
     * <br>
     * 有償無償区分を引数で設定する<br>
     * <br>
     * @param compensationFreeKbn
     */
    public void setCompensationFreeKbn(String compensationFreeKbn) {
        this.compensationFreeKbn = compensationFreeKbn;
    }

    /**
     * 受講者ID取得<br>
     * <br>
     * 受講者IDを戻り値で返却する<br>
     * <br>
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID設定<br>
     * <br>
     * 受講者IDを引数で設定する<br>
     * <br>
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 有効開始日取得<br>
     * <br>
     * 有効開始日を戻り値で返却する<br>
     * <br>
     * @return effectiveStrDt
     */
    public String getEffectiveStrDt() {
        return effectiveStrDt;
    }

    /**
     * 有効開始日設定<br>
     * <br>
     * 有効開始日を引数で設定する<br>
     * <br>
     * @param effectiveStrDt
     */
    public void setEffectiveStrDt(String effectiveStrDt) {
        this.effectiveStrDt = effectiveStrDt;
    }

    /**
     * 有効終了日取得<br>
     * <br>
     * 有効終了日を戻り値で返却する<br>
     * <br>
     * @return effectiveEndDt
     */
    public String getEffectiveEndDt() {
        return effectiveEndDt;
    }

    /**
     * 有効終了日設定<br>
     * <br>
     * 有効終了日を引数で設定する<br>
     * <br>
     * @param effectiveEndDt
     */
    public void setEffectiveEndDt(String effectiveEndDt) {
        this.effectiveEndDt = effectiveEndDt;
    }

    /**
     * 通常月謝区分取得<br>
     * <br>
     * 通常月謝区分を戻り値で返却する<br>
     * <br>
     * @return feeKbn
     */
    public String getFeeKbn() {
        return feeKbn;
    }

    /**
     * 通常月謝区分設定<br>
     * <br>
     * 通常月謝区分を引数で設定する<br>
     * <br>
     * @param feeKbn
     */
    public void setFeeKbn(String feeKbn) {
        this.feeKbn = feeKbn;
    }

    /**
     * 購入金額(税込)取得<br>
     * <br>
     * 購入金額(税込)を戻り値で返却する<br>
     * <br>
     * @return purchaseYen
     */
    public BigDecimal getPurchaseYen() {
        return purchaseYen;
    }

    /**
     * 購入金額(税込)設定<br>
     * <br>
     * 購入金額(税込)を引数で設定する<br>
     * <br>
     * @param purchaseYen
     */
    public void setPurchaseYen(BigDecimal purchaseYen) {
        this.purchaseYen = purchaseYen;
    }

    /**
     * 購入／無償ポイント取得<br>
     * <br>
     * 購入／無償ポイントを戻り値で返却する<br>
     * <br>
     * @return purchaseFreePoint
     */
    public BigDecimal getPurchaseFreePoint() {
        return purchaseFreePoint;
    }

    /**
     * 購入／無償ポイント設定<br>
     * <br>
     * 購入／無償ポイントを引数で設定する<br>
     * <br>
     * @param purchaseFreePoint
     */
    public void setPurchaseFreePoint(BigDecimal purchaseFreePoint) {
        this.purchaseFreePoint = purchaseFreePoint;
    }

    /**
     * 残高ポイント取得<br>
     * <br>
     * 残高ポイントを戻り値で返却する<br>
     * <br>
     * @return balancePoint
     */
    public BigDecimal getBalancePoint() {
        return balancePoint;
    }

    /**
     * 残高ポイント設定<br>
     * <br>
     * 残高ポイントを引数で設定する<br>
     * <br>
     * @param balancePoint
     */
    public void setBalancePoint(BigDecimal balancePoint) {
        this.balancePoint = balancePoint;
    }

    /**
     * ポイント付加理由コード取得<br>
     * <br>
     * ポイント付加理由コードを戻り値で返却する<br>
     * <br>
     * @return pointAdditionReasonCd
     */
    public String getPointAdditionReasonCd() {
        return pointAdditionReasonCd;
    }

    /**
     * ポイント付加理由コード設定<br>
     * <br>
     * ポイント付加理由コードを引数で設定する<br>
     * <br>
     * @param pointAdditionReasonCd
     */
    public void setPointAdditionReasonCd(String pointAdditionReasonCd) {
        this.pointAdditionReasonCd = pointAdditionReasonCd;
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
     * 購入時刻取得<br>
     * <br>
     * 購入時刻を戻り値で返却する<br>
     * <br>
     * @return purchaseTm
     */
    public String getPurchaseTm() {
        return purchaseTm;
    }

    /**
     * 購入時刻設定<br>
     * <br>
     * 購入時刻を引数で設定する<br>
     * <br>
     * @param purchaseTm
     */
    public void setPurchaseTm(String purchaseTm) {
        this.purchaseTm = purchaseTm;
    }

    /**
     * ポイントコード取得<br>
     * <br>
     * ポイントコードを戻り値で返却する<br>
     * <br>
     * @return pointCd
     */
    public String getPointCd() {
        return pointCd;
    }

    /**
     * ポイントコード設定<br>
     * <br>
     * ポイントコードを引数で設定する<br>
     * <br>
     * @param pointCd
     */
    public void setPointCd(String pointCd) {
        this.pointCd = pointCd;
    }

    /**
     * 月謝停止日取得<br>
     * <br>
     * 月謝停止日を戻り値で返却する<br>
     * <br>
     * @return endDt
     */
    public String getEndDt() {
        return endDt;
    }

    /**
     * 月謝停止日設定<br>
     * <br>
     * 月謝停止日を引数で設定する<br>
     * <br>
     * @param endDt
     */
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    /**
     * 月謝用の実購入日取得<br>
     * <br>
     * 月謝用の実購入日を戻り値で返却する<br>
     * <br>
     * @return beginningPurchaseDt
     */
    public String getBeginningPurchaseDt() {
        return beginningPurchaseDt;
    }

    /**
     * 月謝用の実購入日設定<br>
     * <br>
     * 月謝用の実購入日を引数で設定する<br>
     * <br>
     * @param beginningPurchaseDt
     */
    public void setBeginningPurchaseDt(String beginningPurchaseDt) {
        this.beginningPurchaseDt = beginningPurchaseDt;
    }

    /**
     * 月謝用の実購入時刻取得<br>
     * <br>
     * 月謝用の実購入時刻を戻り値で返却する<br>
     * <br>
     * @return beginningPurchaseTm
     */
    public String getBeginningPurchaseTm() {
        return beginningPurchaseTm;
    }

    /**
     * 月謝用の実購入時刻設定<br>
     * <br>
     * 月謝用の実購入時刻を引数で設定する<br>
     * <br>
     * @param beginningPurchaseTm
     */
    public void setBeginningPurchaseTm(String beginningPurchaseTm) {
        this.beginningPurchaseTm = beginningPurchaseTm;
    }

    /**
     * 画面システム作成区分取得<br>
     * <br>
     * 画面システム作成区分を戻り値で返却する<br>
     * <br>
     * @return screenSystemKbn
     */
    public String getScreenSystemKbn() {
        return screenSystemKbn;
    }

    /**
     * 画面システム作成区分設定<br>
     * <br>
     * 画面システム作成区分を引数で設定する<br>
     * <br>
     * @param screenSystemKbn
     */
    public void setScreenSystemKbn(String screenSystemKbn) {
        this.screenSystemKbn = screenSystemKbn;
    }

    /**
     * プロファイルID取得<br>
     * <br>
     * プロファイルIDを戻り値で返却する<br>
     * <br>
     * @return profileId
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * プロファイルID設定<br>
     * <br>
     * プロファイルIDを引数で設定する<br>
     * <br>
     * @param profileId
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    /**
     * 月謝停止フラグ取得<br>
     * <br>
     * 月謝停止フラグを戻り値で返却する<br>
     * <br>
     * @return endFlg
     */
    public String getEndFlg() {
        return endFlg;
    }

    /**
     * 月謝停止フラグ設定<br>
     * <br>
     * 月謝停止フラグを引数で設定する<br>
     * <br>
     * @param endFlg
     */
    public void setEndFlg(String endFlg) {
        this.endFlg = endFlg;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttm
     */
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttm
     */
    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCd
     */
    public String getInsertCd() {
        return insertCd;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCd
     */
    public void setInsertCd(String insertCd) {
        this.insertCd = insertCd;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttm
     */
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttm
     */
    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCd
     */
    public String getUpdateCd() {
        return updateCd;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCd
     */
    public void setUpdateCd(String updateCd) {
        this.updateCd = updateCd;
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
     * ポイント付加理由取得<br>
     * <br>
     * ポイント付加理由を戻り値で返却する<br>
     * <br>
     * @return pointAdditionReason
     */
    public String getPointAdditionReason() {
        return pointAdditionReason;
    }

    /**
     * ポイント付加理由設定<br>
     * <br>
     * ポイント付加理由を引数で設定する<br>
     * <br>
     * @param pointAdditionReason
     */
    public void setPointAdditionReason(String pointAdditionReason) {
        this.pointAdditionReason = pointAdditionReason;
    }

    /**
     * 処理区分（新規："1"、削除："2"、不変：""）取得<br>
     * <br>
     * 処理区分（新規："1"、削除："2"、不変：""）を戻り値で返却する<br>
     * <br>
     * @return updateKbn
     */
    public String getUpdateKbn() {
        return updateKbn;
    }

    /**
     * 処理区分（新規："1"、削除："2"、不変：""）設定<br>
     * <br>
     * 処理区分（新規："1"、削除："2"、不変：""）を引数で設定する<br>
     * <br>
     * @param updateKbn
     */
    public void setUpdateKbn(String updateKbn) {
        this.updateKbn = updateKbn;
    }

    /**
     * 有償無償区分名取得<br>
     * <br>
     * 有償無償区分名を戻り値で返却する<br>
     * <br>
     * @return pointAdditionReason
     */
    public String getCompensationFreeKbnNm() {
        return compensationFreeKbnNm;
    }

    /**
     * 有償無償区分名設定<br>
     * <br>
     * 有償無償区分名を引数で設定する<br>
     * <br>
     * @param compensationFreeKbnNm
     */
    public void setCompensationFreeKbnNm(String compensationFreeKbnNm) {
        this.compensationFreeKbnNm = compensationFreeKbnNm;
    }

    /**
     * 通常月謝区分名取得<br>
     * <br>
     * 通常月謝区分名を戻り値で返却する<br>
     * <br>
     * @return feeKbnNm
     */
    public String getFeeKbnNm() {
        return feeKbnNm;
    }

    /**
     * 通常月謝区分名設定<br>
     * <br>
     * 通常月謝区分名を引数で設定する<br>
     * <br>
     * @param feeKbnNm
     */
    public void setFeeKbnNm(String feeKbnNm) {
        this.feeKbnNm = feeKbnNm;
    }

    /**
     * 利用ポイント取得<br>
     * <br>
     * 利用ポイントを戻り値で返却する<br>
     * <br>
     * @return feeKbnNm
     */
    public BigDecimal getUsePoint() {
        return usePoint;
    }

    /**
     * 利用ポイント設定<br>
     * <br>
     * 利用ポイントを引数で設定する<br>
     * <br>
     * @param feeKbnNm
     */
    public void setUsePoint(BigDecimal usePoint) {
        this.usePoint = usePoint;
    }

}
