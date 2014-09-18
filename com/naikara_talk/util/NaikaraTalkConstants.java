package com.naikara_talk.util;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>システム全体<br>
 * <b>クラス名称　　　:</b>定数管理クラス。<br>
 * <b>クラス概要　　　:</b>特定の業務領域に依存しない定数項目を管理します。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author nos
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成
 *                     </b>2013/11/14 TECS 要望対応 No1022-6(PAYMENT_RATE_MAX,PAYMENT_RATE_MIN　int⇒BigDecimal)
 *                     </b>2014/01/03 TECS 変更 スクールのメール送信・受信履歴照会に伴う対応
 *                     </b>2014/01/15 TECS 変更 FlashからTokBoxへの変更対応
 *                     </b>2014/02/18 TECS 変更 商品マスタメンテナンスへの項目追加「講師への購入メール連絡付き」対応
 *                     </b>2014/04/22 TECS 追加 ファイルタイプの追加対応(pdf⇒pdf、txt)
 *                     </b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */

public class NaikaraTalkConstants {

    /** ブランク(空白) */
    public static final String BRANK = "";
    /** true */
    public static final String TRUE = "true";
    /** false */
    public static final String FALSE = "false";

    /** コンマ */
    public static final char COMMA = ',';

    //2013/12/07-Add
    /** コンマ */
    public static final String COMMA_BRANK = ", ";
    /** () */
    public static final String BRACKET_START = "(";
    public static final String BRACKET_END = ")";

    /** 新規処理 */
    public static final String PROCESSKBN_INS = "新規処理";
    /** 修正処理 */
    public static final String PROCESSKBN_UPD = "修正処理";
    /** 削除処理 */
    public static final String PROCESSKBN_DEL = "削除処理";
    /** 照会処理 */
    public static final String PROCESSKBN_REF = "照会処理";
    /** 流用作成処理 */
    public static final String PROCESSKBN_DIV = "流用作成処理";

    /** 照会処理コメント */
    public static final String PROCESSKBN_REF_COMMENT = "※照会モードの場合は、DBの更新はできません。";

    /** メッセージパラメータ用const */
    public static final String MESSAGE_PARAM_PREFIX = "%";

    // 2014/06/02-Add レッスン予約に関する「応相談」対応
    /** メッセージパラメータ用const */
    public static final String MESSAGE_PARAM_PREFIX1 = "%1";
    public static final String MESSAGE_PARAM_PREFIX2 = "%2";
    public static final String MESSAGE_PARAM_PREFIX3 = "%3";
    public static final String MESSAGE_PARAM_PREFIX4 = "%4";
    public static final String MESSAGE_PARAM_PREFIX5 = "%5";
    public static final String MESSAGE_PARAM_PREFIX6 = "%6";
    public static final String MESSAGE_PARAM_PREFIX7 = "%7";


    /** SQL文中like用 */
    public static final String OPERATOR_PERCENT = "%";
    /** 日付SEPARATOR */
    public static final String DATE_SEPARATOR = "/";
    /** 復帰改行コード */
    public static final String NEW_LINE_CODE_WIN = "\r\n";
    /** 改行コード */
    public static final String NEW_LINE_CODE_UNIX = "\n";
    /** 採番ID ST */
    public static final String ORDER_NUMBERS_ST = "ST";
    /** 採番ID TE */
//    public static final String ORDER_NUMBERS_TE = "TE";
    /** 採番ID CU */
    public static final String ORDER_NUMBERS_CU = "CU";
    /** 採番ID BU */
    public static final String ORDER_NUMBERS_BU = "BU";
    /** 採番ID PT */
    public static final String ORDER_NUMBERS_PT = "PT";
    /** 採番ID BY */
    public static final String ORDER_NUMBERS_BY = "BY";
    /** 採番ID RE */
    public static final String ORDER_NUMBERS_RE = "RE";
    /** 採番ID OW */
    public static final String ORDER_NUMBERS_OW = "OW";
    /** 採番ID DM */
    public static final String ORDER_NUMBERS_DM = "DM";

    /** 戻るボタン画面遷移＋イベント(struts.xmlに合わせて定義) */

    /** 会社側のログイン */
    public static final String SCHOOL_LOGIN_LOAD = "schoolLoginLoad";

    /** サービス提供ページ */
    public static final String TEACHER_LOGIN_LOAD = "teacherLoginLoad";

    /** 会社管理ページ */
    public static final String SYSTEM_MENU_LOAD = "systemMenuLoad";
    /** マスタ保守サブメニュー */
    public static final String MST_MANAGED_SUB_MENU_LOAD = "subMenuMstManagedLoad";
    /** 利用者マスタメンテ【一覧】 */
    public static final String USER_MST_LIST_SEARCH = "userMstListSearch";
    /** 講師マスタメンテ【一覧】 */
    public static final String TEACHER_MST_LIST_SEARCH = "teacherMstListSearch";
    /** 講師マスタメンテ【単票】 */
    public static final String TEACHER_MST_LOAD = "teacherMstLoad";
    /** コースマスタメンテ【一覧】 */
    public static final String COURSE_MST_LIST_SEARCH = "courseMstListSearch";
    /** コースマスタメンテ【単票】 */
    public static final String COURSE_MST_LOAD = "courseMstLoad";
    /** 販売商品マスタメンテ【一覧】 */
    public static final String SALE_GOODS_MST_LIST_SEARCH = "saleGoodsMstListSearch";
    /** 受講者マスタメンテ【一覧】 */
    public static final String STUDENT_MST_LIST_SEARCH = "studentMstListSearch";
    /** 受講者マスタメンテ【単票】 */
    public static final String STUDENT_MST_LOAD = "studentMstLoad";
    /** コード管理マスタメンテ【一覧】 */
    public static final String CODE_CONTROL_MST_LIST_SEARCH = "codeControlMstListSearch";
    /** 時差管理マスタメンテ【一覧】 */
    public static final String TIME_ZONE_CONTROL_MST_LIST_SEARCH = "timeZoneControlMstListSearch";
    /** お客様管理ページ */
    public static final String CUSTOMER_MANAGED_LIST_SEARCH = "customerManagedListSearch";
    /** 入金管理ページ */
    public static final String SALES_MANAGED_LIST_SEARCH = "salesManagedListSearch";
    /** 支払管理ページ */
    public static final String PAYMENT_MANAGED_LIST_SEARCH = "paymentManagedListSearch";
    /** 帳票出力サブメニュー */
    public static final String LIST_OUTPUT_SUB_MENU_LOAD = "subMenuListOutputLoad";
    /** ポイント管理マスタメンテ【一覧】 */
    public static final String POINT_CONTROL_LIST_SEARCH = "pointControlListSearch";
    /** 講師紹介マスタメンテ */
    public static final String TEACHER_INTRODUCTION_MST_LOAD = "teacherIntroductionMst";
    /** 講師紹介マスタメンテ 講師選択の戻り*/
    public static final String TEACHER_INTRODUCTION_MST_ = "teacherIntroductionMstReload";
    /** 組織契約情報登録一覧 */
    public static final String ORGANIZATION_CONTRACTMST_LIST_SEARCH = "organizationContractMstListSearch";
    /** 講師用個別マイページ */
    public static final String TEACHER_MY_PAGE_LOAD = "teacherMyPageLoad";
    /** レッスン管理サブメニュー */
    public static final String LESSON_MANAGED_SUB_MENU_LOAD = "lessonManagedSubMenuLoad";
    /** 講師側の視点から：１-１レッスン管理 <検索条件に必須有り> */
    public static final String TEACHER_LESSON_MANAGEMENT_SEARCH = "teacherLessonManagementReturn";
    /** 添付付きメール送信 */
    public static final String MAIL_WITH_ATTACHMENTS_SEND_TEACHER_LOAD = "mailWithAttachmentsSendTeacherLoad";
    /** マイページ */
    public static final String STUDENT_MY_PAGE_LOAD = "studentMyPageLoad";
    /** 添付付きメール送信 */
    public static final String MAIL_WITH_ATTACHMENTS_SEND_STUDENT_LOAD = "mailWithAttachmentsSendStudentLoad";
    /** ((POP)詳細説明から遷移してきた場合)コース別ポイント <検索条件に必須有り> */
    public static final String SCOM_COURSE_POINT_SEARCH = "sComCoursePointReturn";
    /** ((Pop)講師一覧から遷移してきた場合)'予約申込みページ */
    public static final String RESERVATION_CANCELLATION_COURSE_LIST_SEARCH = "reservationCancellationCourseListSearch";
    /** 予約スケジュール */
    public static final String RESERVATION_CANCELLATION_TEACHER_SCHEDULE_LOAD = "reservationCancellationTeacherScheduleLoad";
    /** コース名選択ページ */
    public static final String RESERVATION_CANCELLATION_COURSE_SELECTION_LIST_LOAD = "reservationCancellationCourseSelectionListLoad";
    /** 予約申込みページ <検索条件に必須有り> */
    public static final String RESERVATION_CANCELLATIONCOURSE_LIST_SEARCH = "reservationCancellationCourseListReturn";
    /** 受講者側の視点から_1-2_レッスン実績(受講者用) */
    public static final String STUDENT_LESSON_HISTORY_STUDENT_USES_LIST_LOAD = "studentLessonHistoryStudentUsesListLoad";
    /** 受講者側の視点から_1-2_レッスン実績(講師用) */
    public static final String STUDENT_LESSON_HISTORY_TEACHER_USES_LIST_LOAD = "studentLessonHistoryTeacherUsesListLoad";
    /** 受講者側の視点から_1-2_レッスン実績(受講者用) <検索条件に必須有り> */
    public static final String STUDENT_LESSON_HISTORY_STUDENT_USES_LIST_SEARCH = "studentLessonHistoryStudentUsesListReturn";
    /** 受講者側の視点から_1-2_レッスン実績(講師用) <検索条件に必須有り> */
    public static final String STUDENT_LESSON_HISTORY_TEACHER_USES_LIST_SEARCH = "studentLessonHistoryTeacherUsesListReturn";
    /** 購入ページ */
    public static final String PURCHASE_POINTS_LOAD = "purchasePointsLoad";
    /** 購入内容確認 */
    public static final String PURCHASE_POINTS_CONFIRMATION_LOAD = "purchasePointsConfirmationLoad";
    /** 商品購入ページ初期表示 */
    public static final String PURCHASE_GOODS_LIST_LOAD = "purchaseGoodsListLoad";
    /** 商品購入ページ検索状態 */
    public static final String PURCHASE_GOODS_LIST_SEARCH = "purchaseGoodsListSearch";
    /** 商品購入確認 */
    public static final String PURCHASE_GOODS_CONFIRMATION_LOAD = "purchaseGoodsConfirmationLoad";
    /** 組織マイページ */
    public static final String ORGANIZATION_MY_PAGE_LOAD = "organizationMyPageLoad";
    /** 組織マイページ(組織 選択)検索 */
    public static final String ORGANIZATION_SELECTION_LIST_SEARCH = "organizationSelectionListSearch";
    /** 受講者の管理 */
    public static final String ORGANIZATION_STUDENT_MST_LIST_SEARCH = "organizationStudentMstListSearch";
    /** 利用状況照会 */
    public static final String ORGANIZATION_USAGE_SITUATION_LIST_SEARCH = "organizationUsageSituationListSearch";
    /** レッスン管理サブメニュー */
    public static final String SUB_MENU_LESSON_MANAGED_LOAD = "subMenuLessonManagedLoad";
    /** 講師側の視点から_1-1_レッスン実績 */
    public static final String TEACHER_LESSON_MANAGEMENT_LOAD = "teacherLessonManagementLoad";
    /** システム受講者登録 */
    public static final String ORGANIZATION_STUDENT_MST_LIST_LOAD = "organizationStudentMstListLoad";
    /** ポイント割振り */
    public static final String ORGANIZATION_POINT_ASSIGNMENT_LIST_LOAD = "organizationPointAssignmentListLoad";
    /** 利用状況照会 */
    public static final String ORGANIZATION_USAGE_SITUATION_LIST_LOAD = "organizationUsageSituationListLoad";
    /** 問合せ */
    public static final String CONTACT_MAIL_SEND_LOAD = "contactMailSendLoad";
    /** 一覧_講師選択 */
    public static final String TEACHER_SELECTION_LIST_LOAD = "teacherSelectionListLoad";
    /** 一覧_講師選択検索 */
    public static final String TEACHER_SELECTION_LIST_SEARCH = "teacherSelectionListSearch";
    /** 講師初期登録ページ */
    public static final String TEACHER_REGISTRATION_LOAD = "teacherRegistrationLoad";
    /** 講師スケジュール */
    public static final String TEACHER_SCHEDULE_LOAD = "teacherScheduleLoad";

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /** 応相談予約回答 画面 初期表示 */
    public static final String TEACHER_APPROVE_REQUEST_LOAD = "teacherApproveRequestLoad";
    /** 応相談予約回答 画面 回答 */
    public static final String TEACHER_APPROVE_REQUEST_SEND = "TeacherApproveRequestSend";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

    /** 支払単価 */
    public static final String TEACHER_PAYMENT_RATE_LIST_LOAD = "teacherPaymentRateListLoad";
    /** 組織契約情報登録 */
    public static final String ORGANIZATION_CONTRACTMST_LIST_LOAD = "organizationContractMstListLoad";
    /** 組織マイページ(組織 選択) */
    public static final String ORGANIZATION_SELECTION_LIST_LOAD = "organizationSelectionListLoad";
    /** お客様管理ページ */
    public static final String CUSTOMER_MANAGED_LIST_LOAD = "customerManagedListLoad";
    /** マイページ(受講者 選択) */
    public static final String STUDENT_SELECTION_LIST_LOAD = "studentSelectionListLoad";
    /** 入金管理ページ */
    public static final String SALES_MANAGED_LIST_LOAD = "salesManagedListLoad";
    /** 支払管理ページ */
    public static final String PAYMENT_MANAGED_LIST_LOAD = "paymentManagedListLoad";
    /** 利用者マスタメンテ【一覧】 */
    public static final String USER_MST_LIST_LOAD = "userMstListLoad";
    /** 講師マスタメンテ【一覧】 */
    public static final String TEACHER_MST_LIST_LOAD = "teacherMstListLoad";
    /** コースマスタメンテ【一覧】 */
    public static final String COURSE_MST_LIST_LOAD = "courseMstListLoad";
    /** 販売商品マスタメンテ【一覧】 */
    public static final String SALE_GOODS_MST_LIST_LOAD = "saleGoodsMstListLoad";
    /** 受講者マスタメンテ【一覧】 */
    public static final String STUDENT_MST_LIST_LOAD = "studentMstListLoad";
    /** コード管理マスタメンテ【一覧】 */
    public static final String CODE_CONTROL_MST_LIST_LOAD = "codeControlMstListLoad";
    /** 時差管理マスタメンテ【一覧】 */
    public static final String TIME_ZONE_CONTROL_MST_LIST_LOAD = "timeZoneControlMstListLoad";
    /** ポイント管理マスタメンテ【一覧】 */
    public static final String POINT_CONTROL_LIST_LOAD = "pointControlListLoad";
    /** 講師紹介マスタメンテ */
    //public static final String TEACHER_INTRODUCTION_MST_LOAD = "teacherIntroductionMstLoad";
    /** 講師毎の支払管理表(CSV) */
    public static final String PAYMENT_MANAGEMENT_LIST_DIRECTIONS_LOAD = "paymentManagementListDirectionsLoad";
    /** 請求書発行処理 */
    public static final String BILL_DOWNLOAD_LOAD = "billDownloadLoad";
    /** 講師一覧(Pop) */
    public static final String TEACHER_LIST_SEARCH = "teacherListSearch";
    /** 予約申込みページ */
    public static final String RESERVATION_CANCELLATION_COURSE_LIST_LOAD = "reservationCancellationCourseListLoad";
    /** アカウント取得 */
    public static final String ACCOUNT_LOAD = "accountLoad";
    /** 講師紹介 一覧 */
    public static final String TEACHER_INTORODUCTION = "teacherIntroduction";
    /** ブックマーク済の講師一覧（Pop） */
    public static final String TEACHER_BOOKMARK_LIST_LOAD = "teacherBookmarkListLoad";

    //2013/12/07-Add-スクールメール送信・受信履歴照会
    /** スクールメール送信・受信履歴照会 */
    public static final String SCHOOL_MAIL_LIST_LOAD = "schoolMailListLoad";


    /** コード種別 */
    /** 地域（地方） */
    public static final String CODE_CATEGORY_REGION = "J01";
    /** 都道府県 */
    public static final String CODE_CATEGORY_STATE = "J02";
    /** 性別 */
    public static final String CODE_CATEGORY_GENDER = "J03";
    /** 種別（権限） */
    public static final String CODE_CATEGORY_AUTHORITY = "J04";
    /** 国籍／国 */
    public static final String CODE_CATEGORY_COUNTRY = "J05";
    /** 時差地域No */
    public static final String CODE_CATEGORY_AREA_NO = "J06";
    /** 母国語 */
    public static final String CODE_CATEGORY_NATIVE_LANG = "J07";
    /** 支払方法(Payment Method) */
    public static final String CODE_CATEGORY_PAYMENT_METHOD = "J08";
    /** 利用状態 */
    public static final String CODE_CATEGORY_USE_STATE = "J08";
    /** 支払サイクル(Payment Cycle) */
    public static final String CODE_CATEGORY_PAYMENT_CYCLE = "J09";
    /** 大分類 */
    public static final String CODE_CATEGORY_BIG_CLASSIFICATION = "J10";
    /** 中分類 */
    public static final String CODE_CATEGORY_MIDDLE_CLASSIFICATION = "J11";
    /** 小分類 */
    public static final String CODE_CATEGORY_SMALL_CLASSIFICATION = "J12";
    /** 受取方法 */
    public static final String CODE_CATEGORY_SALE_KBN = "J13";
    /** 商品形態 */
    public static final String CODE_CATEGORY_PRODUCT_KBN = "J14";
    /** 該当商品の有無 */
    public static final String CODE_CATEGORY_PRODUCTS_GOODS_KBN = "J15";
    /** 顧客区分 */
    public static final String CODE_CATEGORY_CUSTOMER_KBN = "J16";
    /** 源泉有無区分 */
    public static final String CODE_CATEGORY_SOURCE_WHETHER = "J17";
    /** 外国送金関係銀行手数料区分 */
    public static final String CODE_CATEGORY_OTHER_REMITTANCE_FEE_KBN = "J18";
    /** 削除フラグ */
    public static final String CODE_CATEGORY_DEL_FLG = "J19";
    /** 職業 */
    public static final String CODE_CATEGORY_OCCUPATION = "J20";
    /** 符号 */
    public static final String CODE_CATEGORY_SIGN = "J21";
    /** 支払対象区分 */
    public static final String CODE_CATEGORY_PAYMENT_KBN = "J22";
    /** 支払サイト区分 */
    public static final String CODE_CATEGORY_PAYMENT_SITE_KBN = "J23";
    /** 個人：スクールからのキャンペーン */
    public static final String CODE_CATEGORY_CAMPAIGN_FROM_SCOOL_TO_PERS = "J24";
    /** 個人：スクールからのお知らせ */
    public static final String CODE_CATEGORY_NEWS_FROM_SCOOL_TO_PERS = "J25";
    /** 法人：スクールからのキャンペーン */
    public static final String CODE_CATEGORY_CAMPAIGN_FROM_SCOOL_TO_CORP = "J26";
    /** 法人：スクールからのお知らせ */
    public static final String CODE_CATEGORY_NEWS_FROM_SCOOL_TO_CORP = "J27";
    /** 講師：スクールからのお知らせ */
    public static final String CODE_CATEGORY_NEWS_FROM_SCOOL_TO_TEAC_J = "J28";
    /** お問合せ目的 */
    public static final String CODE_CATEGORY_CONTACT = "J29";
    /** 利用停止状態フラグ */
    public static final String CODE_CATEGORY_SUSPENDED_STATE = "J30";
    /** 予約可能の有無(予約状況、予約区分) */
    public static final String CODE_CATEGORY_RESERV_KBN = "J31";
    /** 予約状態 */
    public static final String CODE_CATEGORY_RESERV_STATE = "J32";
    /** 受講者からの講師評価 */
    public static final String CODE_CATEGORY_EVALUATION_FROM_PERS_TO_TEAC = "J33";
    /** 推奨レベル※講師からの受講者評価 */
    public static final String CODE_CATEGORY_EVALUATION_FROM_TEAC_TO_PERS = "J34";
    /** Self-Evaluation */
    public static final String CODE_CATEGORY_SELF_EVALUATION = "J35";
    /** 利用動機 */
    public static final String CODE_CATEGORY_USE_MOTIVATION = "J36";
    /** 受講者マスタ検索条件 */
    public static final String CODE_CATEGORY_STUDENT_MST_SEARCH = "J37";
    /** 通常月謝区分 */
    public static final String CODE_CATEGORY_FEE_KBN = "J38";
    /** 添付付き有無区分 */
    public static final String CODE_CATEGORY_ATTACHMENT_KBN = "J39";
    /** 添付メール送付済区分 */
    public static final String CODE_CATEGORY_MAIL_KBN = "J40";
    /** 入金管理ページ(集計) */
    public static final String CODE_CATEGORY_PAYMENT_MANAG_COUNT = "J41";
    /** 入金管理ページ(明細) */
    public static final String CODE_CATEGORY_PAYMENT_MANAG_DETAIL = "J42";
    /** 支払管理表CSV出力先 */
    public static final String CODE_CATEGORY_PAYMENT_CSV_OUTPUT = "J43";
    /** カテゴリ */
    public static final String CODE_CATEGORY_INTRODUCTION_CATEGORY = "J44";
    /** ポイント付加理由 */
    public static final String CODE_CATEGORY_POINT_ADDITIONAL_REASON = "J45";
    /** 保護者の同意の有無区分 */
    public static final String CODE_CATEGORY_PARENTAL_CONSENT = "J46";
    /** サマータイム区分 */
    public static final String CODE_CATEGORY_SUM_TIME_KBN = "J47";
    /** 有償無償区分 */
    public static final String CODE_CATEGORY_COMPENSATION_FREE_KBN = "J48";
    /** 源泉税率 */
    public static final String CODE_CATEGORY_WITHHOLDING_TAX_RATE = "J49";

    /** データ区分※バッチ処理でのみ使用 */
    public static final String CODE_CATEGORY_DATA_KBN = "J50";
    /** 入会ポイント */
    public static final String CODE_CATEGORY_JOIN_POINT = "J51";
    /** 入会ポイント期限(ヶ月) */
    public static final String CODE_CATEGORY_JOIN_POINT_PERIOD = "J52";
    /** DM送信者メールアドレス */
    public static final String CODE_CATEGORY_DM_SENDER_MAIL_ADDRESS = "J53";
    /** DM控えメールアドレス */
    public static final String CODE_CATEGORY_MAIL_ADDRESS_REFRAIN_DM = "J54";


    // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
    /** 講師への購入メール連絡付き */
    public static final String CODE_CATEGORY_T_CONTACT_KBN = "J56";

    // 2014/06/02-Add レッスン予約に関する「応相談」対応
    /** 応相談回答区分 */
    public static final String CODE_CATEGORY_REPLY_KBN = "J57";


    /** 性別(講師用) */
    public static final String CODE_CATEGORY_GENDER_T = "E03";
    /** 国籍／国(講師用) */
    public static final String CODE_CATEGORY_COUNTRY_T = "E05";
    /** 時差地域No */
    public static final String CODE_CATEGORY_AREA_NO_T = "E06";
    /** 母国語 */
    public static final String CODE_CATEGORY_NATIVE_LANG_T = "E07";
    /** 支払方法(Payment Method) */
    public static final String CODE_CATEGORY_PAYMENT_METHOD_T = "E08";
    /** 支払サイクル(講師用)(Payment Cycle) */
    public static final String CODE_CATEGORY_PAYMENT_CYCLE_T = "E09";
    /** 大分類(講師用) */
    public static final String CODE_CATEGORY_BIG_CLASSIFICATION_T = "E10";
    /** 中分類(講師用) */
    public static final String CODE_CATEGORY_MIDDLE_CLASSIFICATION_T = "E11";
    /** 小分類(講師用) */
    public static final String CODE_CATEGORY_SMALL_CLASSIFICATION_T = "E12";
    /** 源泉有無区分(講師用) */
    public static final String CODE_CATEGORY_SOURCE_WHETHER_T = "E17";
    /** 外国送金関係銀行手数料区分 */
    public static final String CODE_CATEGORY_OTHER_REMITTANCE_FEE_KBN_T = "E18";
    /** 符号 */
    public static final String CODE_CATEGORY_SIGN_T = "E21";
    /** 予約可能の有無(予約状況、予約区分) */
    /** 講師：スクールからのお知らせ */
    public static final String CODE_CATEGORY_NEWS_FROM_SCOOL_TO_TEAC_E = "E28";
    public static final String CODE_CATEGORY_RESERV_KBN_T = "E31";
    /** 予約状態 */
    public static final String CODE_CATEGORY_RESERV_STATE_T = "E32";
    /** 受講者からの講師評価 */
    public static final String CODE_CATEGORY_EVALUATION_FROM_PERS_TO_TEAC_T = "E33";
    /** 推奨レベル(講師用)※講師からの受講者評価 */
    public static final String CODE_CATEGORY_EVALUATION_FROM_TEAC_TO_PERS_T = "E34";
    /** Self-Evaluation(講師用) */
    public static final String CODE_CATEGORY_SELF_EVALUATION_T = "E35";
    /** 通常月謝区分 */
    public static final String CODE_CATEGORY_FEE_KBN_T = "E38";
    /** 添付付き有無区分 */
    public static final String CODE_CATEGORY_ATTACHMENT_KBN_T = "E39";
    /** 添付メール送付済区分 */
    public static final String CODE_CATEGORY_MAIL_KBN_T = "E40";
    /** サマータイム区分 */
    public static final String CODE_CATEGORY_SUM_TIME_KBN_T = "E47";
    /** 国内外区分(振込・送金) */
    public static final String CODE_CATEGORY_NATIONAL_INTERNATIONAL_SEGMENT_T = "E48";
    /** 問合せメールアドレス(講師用個別マイページ用) */
    public static final String CODE_CATEGORY_CONTACT_MAIL_ADDRESS_T = "E55";

    // 2014/06/02-Add レッスン予約に関する「応相談」対応
    /** 応相談回答区分 */
    public static final String CODE_CATEGORY_REPLY_KBN_T = "E57";


    /** レッスン時刻 */
    public static final String CODE_CATEGORY_LESSON_TM_S = "S01";
    /** 月次バッチ処理日 */
    public static final String CODE_CATEGORY_MONTHLY_BATCH_S = "S02";
    public static final String CODE_CATEGORY_MONTHLY_BATCH_PROC_DAY = "0001";
    /** ペイパル情報 */
    public static final String CODE_CATEGORY_PAYPAL_INFO = "S03";
    /** 実行判定XMLファイルのパス	*/
    public static final String EXEC_JUDGE_XML_PATH = "S04";
    /** 添付付きメール送信の一時的な格納先 */
    public static final String CODE_CATEGORY_EMAIL_ATTACH_TEMPATH = "S05";
    /** HTMLメールの画像格納先 */
    public static final String CODE_CATEGORY_HTML_EMAIL_IMG_PATH = "S06";
    // 2013/12/07-Add スクールメール送信・受信履歴照会
    /** メールパターン名 */
    public static final String CODE_CATEGORY_TMAIL_PATTERN_NM = "S07";
    // 2014/01/15-Add FlashからToxBoxへの変更対応
    /** ToxBox情報 */
    public static final String CODE_CATEGORY_TOXBOX_INFO = "S08";
    // 2013/12/07-Add スクールメール送信・受信履歴照会
    /** 利用者区分（顧客区分 + 法人責任者 + 講師） */
    public static final String CODE_CATEGORY_CUSTOMER_OTHER_KBN = "S09";

    // 2014/06/02-Add レッスン予約に関する「応相談」対応
    /** 予約可能の有無(予約状況、予約区分) */
    public static final String CODE_CATEGORY_RESERV_KBN_STUDENT_MYPAGE = "S31";


    /** テーブル名 */
    public static final String CODE_CLASS_MST = "CODE_CLASS_MST";
    public static final String CODE_MANAG_MST = "CODE_MANAG_MST";
    public static final String USER_MST = "USER_MST";
    public static final String TEACHER_MST = "TEACHER_MST";
    public static final String TEACHER_RATE_MST = "TEACHER_RATE_MST";
    public static final String TEACHER_COURSE_MST = "TEACHER_COURSE_MST";
    public static final String COURSE_MST = "COURSE_MST";
    public static final String COURSE_USE_POINT_MST = "COURSE_USE_POINT_MST";
    public static final String COURSE_GOODS_MST = "COURSE_GOODS_MST";
    public static final String GOODS_MST = "GOODS_MST";
    public static final String TIME_MANAG_MST = "TIME_MANAG_MST";
    public static final String ORGANIZATION_MST = "ORGANIZATION_MST";
    public static final String STUDENT_MST = "STUDENT_MST";
    public static final String POINT_MANAG_MST = "POINT_MANAG_MST";
    public static final String MAIL_MANAG_MST = "MAIL_MANAG_MST";
    public static final String MESSAGE_MST = "MESSAGE_MST";
    public static final String ORDER_NUMBERS_MST = "ORDER_NUMBERS_MST";
    public static final String HOLIDAY_MST = "HOLIDAY_MST";
    public static final String TEACHER_BOOKMARK_MST = "TEACHER_BOOKMARK_MST";
    public static final String DM_HISTORY_TRN = "DM_HISTORY_TRN";
    public static final String DM_HISTORY_DETAILS_TRN = "DM_HISTORY_DETAILS_TRN";
    public static final String GOODS_PURCHASE_TRN = "GOODS_PURCHASE_TRN";
    public static final String SCHEDULE_RESERVATION_TRN = "SCHEDULE_RESERVATION_TRN";
    public static final String LESSON_RESERVATION_PERFORMANCE_TRN = "LESSON_RESERVATION_PERFORMANCE_TRN";
    public static final String LESSON_COMMENT_TRN = "LESSON_COMMENT_TRN";
    public static final String POINT_PROVISION_TRN = "POINT_PROVISION_TRN";
    public static final String POINT_OWNERSHIP_TRN = "POINT_OWNERSHIP_TRN";
    public static final String SALES_ORGANIZATION_TRN = "SALES_ORGANIZATION_TRN";
    public static final String SALES_TRN = "SALES_TRN";
    public static final String SALES_DETAILS_TRN = "SALES_DETAILS_TRN";
    public static final String PAYMENT_TRN = "PAYMENT_TRN";
    public static final String PAYMENT_DETAILS_TRN = "PAYMENT_DETAILS_TRN";

    /** ドロップダウン・ラジオボタン :選択なし・すべて */
    public static final String CHOICE_ALL_ZERO = "0000";
    public static final String CHOICE_ALL = "全て";

    /** 日付 初期値 最大値 */
    public static final String MAX_END_DT = "2999/12/31";

    /** 性別(講師用) :男性 */
    public static final String GENDER_MALE = "0001";
    /** 性別(講師用) :女性 */
    public static final String GENDER_FEMALE = "0002";

    /** 種別： 利用者マスタ システム管理者 */
    public static final String AUTHORITY_A = "A";
    /** 種別： 利用者マスタ 管理者 */
    public static final String AUTHORITY_M = "M";
    /** 種別： 利用者マスタ スタッフ */
    public static final String AUTHORITY_S = "S";
    /** 種別： 利用者マスタ 講師 */
    public static final String AUTHORITY_T = "T";


    /** 利用状態：利用可(利用中) */
    public static final String USE_KBN_OK = "0";
    /** 利用状態：利用不可(停止) */
    public static final String USE_KBN_NG = "1";

    /** 受取方法 :ダウンロード*/
    public static final String SALE_KBN_DWL = "1000";
    /** 受取方法 :配送*/
    public static final String SALE_KBN_DELIVERY = "2000";
    /** 受取方法 :外部購入*/
    public static final String SALE_KBN_OUTSIDE = "3000";
    /** 受取方法 :その他*/
    public static final String SALE_KBN_ETC = "4000";

    /** 商品形態 :電子書籍 */
    public static final String PRODUCT_KBN_ELECTRON = "1001";
    /** 商品形態 :音声ファイル */
    public static final String PRODUCT_KBN_VOICE = "1002";
    /** 商品形態 :その他の電子データ */
    public static final String PRODUCT_KBN_ELECTRON_ETC = "1003";
    /** 商品形態 :書籍・その他 */
    public static final String PRODUCT_KBN_ETC = "2001";

    /** 顧客区分 :法人 */
    public static final String CUSTOMER_KBN_ORGANIZATION = "0001";
    /** 顧客区分 :個人 */
    public static final String CUSTOMER_KBN_PERSON = "0002";

    // 2014/01/10-Add-スクールのメール送信・受信履歴照会-Start
    /** 利用者区分（顧客区分 + 法人責任者 + 講師） :法人責任者 */
    public static final String CUSTOMER_KBN_ORGANIZATION_MANAG = "0003";
    /** 利用者区分（顧客区分 + 法人責任者 + 講師） :講師 */
    public static final String CUSTOMER_KBN_TEACHER = "0004";
    // 2014/01/10-Add-スクールのメール送信・受信履歴照会-End

    /** 該当商品の有無 :有 */
    public static final String PRODUCTS_GOODS_KBN_Y = "0001";
    /** 該当商品の有無 :無 */
    public static final String PRODUCTS_GOODS_KBN_N = "0002";

    /** 源泉有無区分 :有 */
    public static final String SOURCE_WHETHER_Y = "0001";
    /** 源泉有無区分 :無 */
    public static final String SOURCE_WHETHER_N = "0002";

    /** 外国送金関係銀行手数料区分 :有 */
    public static final String OTHER_REMITTANCE_FEE_KBN_Y = "0001";
    /** 外国送金関係銀行手数料区分 :無 */
    public static final String OTHER_REMITTANCE_FEE_KBN_N = "0002";

    /** 削除フラグ :OFF */
    public static final String DEL_FLG_OFF = "0001";
    /** 削除フラグ :ON */
    public static final String DEL_FLG_ON = "0002";

    /** 符号 + */
    public static final String SIGN_PLUS = "0001";
    /** 符号 - */
    public static final String SIGN_MINUS = "0002";

    /** 個人：スクールからのキャンペーン */
    public static final String CAMPAIGN_FROM_SCOOL_TO_PERS = "0001";

    /** 個人：スクールからのキャンペーン */
    public static final String NEWS_FROM_SCOOL_TO_PERS = "0001";

    /** 法人：スクールからのキャンペーン */
    public static final String CAMPAIGN_FROM_SCOOL_TO_CORP = "0001";

    /** 法人：スクールからのお知らせ */
    public static final String NEWS_FROM_SCOOL_TO_CORP = "0001";

    /** 講師：スクールからのお知らせ */
    public static final String NEWS_FROM_SCOOL_TO_TEAC = "0001";

    /** 利用停止状態フラグ:利用停止0001 */
    public static final String SUSPENDED_STATE = "0001";

    /** 予約可能の有無(予約状況、予約区分):予約可 */
    public static final String RESERV_KBN_YES = "1";
    /** 予約可能の有無(予約状況、予約区分):予約済 */
    public static final String RESERV_KBN_ALREADY = "2";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /** 予約可能の有無(予約状況、予約区分):予約受付不可 */
    public static final String RESERV_KBN_NO = "0";
    /** 予約可能の有無(予約状況、予約区分):応相談可 */
    public static final String RESERV_KBN_CON_YES = "4";
    /** 予約可能の有無(予約状況、予約区分):応相談-予約済(仮予約) */
    public static final String RESERV_KBN_CON_PROVISIONAL_RESERV = "5";
    /** 予約可能の有無(予約状況、予約区分):応相談-予約確定 */
    public static final String RESERV_KBN_CON_ALREADY = "6";

    /** 予約/取消処理区分：取消 */
    public static final String RESERV_PROCESS_KBN_NO = "0";
    /** 予約/取消処理区分：予約 */
    public static final String RESERV_PROCESS_KBN_YES = "1";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

    /** 予約状態:予約可 */
    public static final String RESERV_STATE_YES = "0001";
    /** 予約状態:予約済 */
    public static final String RESERV_STATE_ALREADY = "0002";
    /** 予約状態:予約受付不可 */
    public static final String RESERV_STATE_NO = "0003";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /** 予約状態:応相談可 */
    public static final String RESERV_STATE_CON_YES = "0004";
    /** 予約状態:応相談-予約有り(仮予約) */
    public static final String RESERV_STATE_CON_PROVISIONAL_RESERV = "0005";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


    /** 受講者からの講師評価：素晴らしい */
    public static final String EVALUATION_FROM_PERS_TO_T_EXCELLENT = "0001";
    /** 受講者からの講師評価：良い */
    public static final String EVALUATION_FROM_PERS_TO_T_GOOD = "0002";
    /** 受講者からの講師評価：普通 */
    public static final String EVALUATION_FROM_PERS_TO_T_FAIR = "0003";
    /** 受講者からの講師評価：不十分 */
    public static final String EVALUATION_FROM_PERS_TO_T_POOL = "0004";
    /** 受講者からの講師評価：良くない */
    public static final String EVALUATION_FROM_PERS_TO_T_VERYPOOL = "0005";

    /** 推奨レベル(講師用)：より上のレベルへ */
    public static final String EVALUATION_FROM_TEAC_TO_PERS_HIGHER = "0001";
    /** 推奨レベル(講師用)：レベル適合 */
    public static final String EVALUATION_FROM_TEAC_TO_PERS_SUITABLE = "0002";
    /** 推奨レベル(講師用)：より下のレベルへ */
    public static final String EVALUATION_FROM_TEAC_TO_PERS_LOWER = "0003";
    /** 推奨レベル(講師用)：適用外 */
    public static final String EVALUATION_FROM_TEAC_TO_PERS_NA = "0004";

    /** Self-Evaluation：素晴らしい */
    public static final String SELF_EVALUATION_EXCELLENT = "0001";
    /** Self-Evaluation：良い */
    public static final String SELF_EVALUATION_GOOD = "0002";
    /** Self-Evaluation：普通 */
    public static final String SELF_EVALUATION_FAIR = "0003";
    /** Self-Evaluation：不十分 */
    public static final String SELF_EVALUATION_POOL = "0004";
    /** Self-Evaluation：良くない */
    public static final String SELF_EVALUATION_VERYPOOL = "0005";

    /** 利用動機：１個目 */
    public static final String USE_MOTIVATION_1 = "0001";
    /** 利用動機：２個目 */
    public static final String USE_MOTIVATION_2 = "0002";
    /** 利用動機：３個目 */
    public static final String USE_MOTIVATION_3 = "0003";
    /** 利用動機：４個目 */
    public static final String USE_MOTIVATION_4 = "0004";
    /** 利用動機：５個目 */
    public static final String USE_MOTIVATION_5 = "0005";

    /** 通常月謝区分：通常 */
    public static final String FEE_KBN_NORMAL = "1";
    /** 通常月謝区分：月謝 */
    public static final String FEE_KBN_MONTHLY = "2";

    /** 添付付き有無区分：不可 */
    public static final String ATTACHMENT_KBN_YES = "0";
    /** 添付付き有無区分：可 */
    public static final String ATTACHMENT_KBN_NO = "1";

    /** 添付メール送付済区分：未 */
    public static final String MAIL_KBN_NOSEND = "0";
    /** 添付メール送付済区分：済 */
    public static final String MAIL_KBN_SENT = "1";
    /** 添付メール送付済区分：なし */
    public static final String MAIL_KBN_NOTHING = "9";

    /** 入金管理ページ(集計) */
    public static final String PAYMENT_MANAG_COUNT_CSV_OUTPUT = "0001";

    /** 入金管理ページ(明細) */
    public static final String PAYMENT_MANAG_DETAIL_CSV_OUTPUT = "0001";

    /** 支払管理表CSV出力先 */
    public static final String PAYMENT_CSV_OUTPUT = "0001";

    /** 保護者の同意の有無フラグ：無 */
    public static final String PARENTAL_CONSENT_FLG_NO = "0";
    /** 保護者の同意の有無フラグ：有 */
    public static final String PARENTAL_CONSENT_FLG_YES = "1";

    /** サマータイムフラグ：無 */
    public static final String SUM_TIME_FLG_NO = "0";
    /** サマータイムフラグ：有 */
    public static final String SUM_TIME_FLG_YES = "1";

    /** 有償無償区分：無償入会 */
    public static final String COMPENSATION_FREE_KBN_NO_ENROLLMENT = "1";
    /** 有償無償区分：無償特別 */
    public static final String COMPENSATION_FREE_KBN_NO_SPECIAL = "2";
    /** 有償無償区分：無償 */
    public static final String COMPENSATION_FREE_KBN_NO = "3";
    /** 有償無償区分：有償 */
    public static final String COMPENSATION_FREE_KBN_YES = "9";

    /** 源泉税率 */
    public static final String WITHHOLDING_TAX_RATE = "0001";

    /** データ区分：レッスン売上 */
    public static final String DATA_KBN_YES_LESSON = "1000";
    /** データ区分：商品購入PAYPAL売上 */
    public static final String DATA_KBN_YES_GOODS_PAYPAL = "2000";
    /** データ区分：商品購入ポイント売上 */
    public static final String DATA_KBN_YES_GOODS_POINT = "3000";
    /** データ区分：キャンセル売上 */
    public static final String DATA_KBN_YES_CXL = "4000";
    /** データ区分：ポイント有効期限切れ売上 */
    public static final String DATA_KBN_YES_EXPIRED_POINT = "5000";
    /** データ区分：退会者のポイント売上 */
    public static final String DATA_KBN_YES_WITHDRAWAL = "6000";
    /** データ区分：ポイントの前受け(先月末時点の残ポイント) */
    public static final String DATA_KBN_YES_BEFORE_POINT = "9100";
    /** データ区分：購入ポイント */
    public static final String DATA_KBN_PURCHASE_POINT = "9200";
    /** データ区分：レッスン利用(無償) */
    public static final String DATA_KBN_NO_LESSON = "1010";
    /** データ区分：商品購入ポイント利用(無償) */
    public static final String DATA_KBN_NO_GOODS_POINT = "3010";
    /** データ区分：キャンセル利用(無償) */
    public static final String DATA_KBN_NO_CXL = "4010";
    /** データ区分：ポイント有効期限切れ(無償) */
    public static final String DATA_KBN_NO_EXPIRED_POINT = "5010";
    /** データ区分：退会者のポイント(無償) */
    public static final String DATA_KBN_NO_WITHDRAWAL = "6010";
    /** データ区分：ポイントの前受け(無償)(先月末時点の残ポイント) */
    public static final String DATA_KBN_NO_BEFORE_POINT = "9110";
    /** データ区分：特典ポイント(無償) */
    public static final String DATA_KBN_NO_SPECIAL = "9210";

    /** 売上区分：有償：前受有償ポイント */
    public static final String SALES_KBN_YES_BEFORE_POINT = "1";
    /** 売上区分：有償：購入有償ポイント */
    public static final String SALES_KBN_YES_PURCHASE_POINT = "2";
    /** 売上区分：有償：利用有償ポイント */
    public static final String SALES_KBN_YES_USE_POINT = "3";
    /** 売上区分：有償：ポイント外商品購入 */
    public static final String SALES_KBN_YES_PURCHASE_GOODS = "4";
    /** 売上区分：無償：前受無償ポイント */
    public static final String SALES_KBN_NO_BEFORE_POINT = "5";
    /** 売上区分：無償：購入無償ポイント */
    public static final String SALES_KBN_NO_PURCHASE_POINT = "6";
    /** 売上区分：無償：利用無償ポイント */
    public static final String SALES_KBN_NO_USE_POINT = "7";

    /** 入会ポイント */
    public static final String JOIN_POIN = "0001";

    /** 入会ポイント期限(ヶ月) */
    public static final String JOIN_POINT_PERIOD = "0001";

    /** DM送信者メールアドレス */
    public static final String DM_SENDER_MAIL_ADDRESS = "0001";

    /** DM控えメールアドレス */
    public static final String MAIL_ADDRESS_REFRAIN_DM = "0001";

    // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
    /** 講師への購入メール連絡付き区分 :送信あり*/
    public static final String T_CONTACT_KBN_YES = "0001";
    /** 講師への購入メール連絡付き区分 :送信なし*/
    public static final String T_CONTACT_KBN_NO = "0002";

    // 2014/06/02-Add レッスン予約に関する「応相談」対応
    /** 応相談回答区分 :回答ＯＫ */
    public static final String CODE_CATEGORY_REPLY_KBN_YES = "0001";
    /** 応相談回答区分 :回答ＮＧ */
    public static final String CODE_CATEGORY_REPLY_KBN_NO = "0002";


    /** 登録者コード（バッチ処理用） */
    public static final String INSERT_CD = "system";
    /** 更新者コード（バッチ処理用） */
    public static final String UPDATE_CD = "system";

    /** Daoの返却値：データ取得：正常 */
    public static final int RETURN_CD_DATA_YES = 0;
    /** Daoの返却値：データ取得：データなし */
    public static final int RETURN_CD_DATA_NO = 1;
    /** Daoの返却値：データ取得：異常 (初期値) */
    public static final int RETURN_CD_DATA_ERR = -1;

    /** Daoの返却値：追加：異常 (初期値) (※正常時は処理した件数を返却) */
    public static final int RETURN_CD_ERR_INS = -1;

    /** Daoの返却値：更新：異常 (初期値) (※正常時は処理した件数を返却) */
    public static final int RETURN_CD_ERR_UPD = -1;

    /** Daoの返却値：削除：異常 (初期値) (※正常時は処理した件数を返却) */
    public static final int RETURN_CD_ERR_DEL = -1;

    /** Daoの返却値：DB更新なし(排他エラー) 結果判定で使用 */
    public static final int RETURN_CD_ERR_NO_UPD = 0;

    /** 売上計上済フラグ：未計上 */
    public static final String SALES_ACCRUED_FLG_NO = "0";
    /** 売上計上済フラグ：計上済 */
    public static final String SALES_ACCRUED_FLG_YES = "1";
    /** 売上計上済フラグ：計上不要(ポイント返却) */
    public static final String SALES_ACCRUED_FLG_NOTHING = "9";

    /** レッスン区分（受講者／講師共通）：正常 */
    public static final String LESSON_KBN_NORMAL = "0";
    /** レッスン区分（受講者／講師共通）：遅刻 */
    public static final String LESSON_KBN_LATENESS = "1";
    /** レッスン区分（受講者／講師共通）：早退 */
    public static final String LESSON_KBN_LEAVE_EARLY = "2";
    /** レッスン区分（受講者／講師共通）：遅刻/早退 */
    public static final String LESSON_KBN_LATENESS_LEAVE_EARLY = "3";
    /** レッスン区分（受講者／講師共通）：欠席 */
    public static final String LESSON_KBN_NON_ATTENDANCE = "4";
    /** レッスン区分（受講者／講師共通）：途中退席 */
    public static final String LESSON_KBN_LEAVE_THE_WAY = "5";

    /** レッスン状態区分：1:予約 */
    public static final String LESSON_STATE_KBN_RESERVATION = "1";
    /** レッスン状態区分：2：レッスン開始 */
    public static final String LESSON_STATE_KBN_START = "2";
    /** レッスン状態区分：3：レッスン終了 */
    public static final String LESSON_STATE_KBN_END = "3";

    /** 予約購入区分：予約 */
    public static final String RSV_PURCHASE_KBN_RSV = "1";
    /** 予約購入区分：購入 */
    public static final String RSV_PURCHASE_KBN_PRCHS = "2";

    /** サマータイムなし */
    public static final String SUMMER_TIME_NO = "--:-- --:--";

    /** ポイントコード */
    public static final String POINT_INIT_CD = "PT0000000000";

    /** ログ出力： 処理ログ編集 */
    public static final String LOG_USER_ID_NM = "UseerId=";
    public static final String LOG_SESSION_ID_NM = "SessionId=";
    public static final String LOG_COMMA = ",";
    public static final String LOG_PAR_START = "[";
    public static final String LOG_PAR_END = "]";

    /** ログ出力： 認証ログ */
    public static final String LOG_CERTIFICATION = "log_certification";

    /** ログ出力： 処理ログ編集 */
    public static final String LOG_CLASS_NM = "class=";
    public static final String LOG_BEFORE_ID = "beforeId=";
    public static final String LOG_CATEGORY_CHANGE_T = "change to teacher  from school";
    public static final String LOG_CATEGORY_CHANGE_C = "change to student from school";
    public static final String LOG_CATEGORY_CHANGE_B = "change to students from school";
    public static final String LOG_CATEGORY_LOGIN = "login";

    /** ログ出力：認証ログ 画面ID */
    public static final String FRM_LOGIN_SCHOOL = "SchoolLogin";
    public static final String FRM_LOGIN_TEACHER = "TeacherLogin";
    public static final String FRM_LOGIN_STUDENT = "StudentLogin";
    public static final String FRM_LOGIN_ORGANIZATION = "OrganizationLogin";
    public static final String FRM_TEACHER_SELECTIONLIST = "TeacherSelectionList";
    public static final String FRM_STUDENT_SELECTIONLIST = "OrganizationSelectionList";
    public static final String FRM_ORGANIZATION_SELECTIONLIST = "StudentSelectionList";

    /** 画面表示用画像が設定されていない場合用 */
    public static final String IMG_NODATA_PATH = "/images";
    public static final String IMG_NODATA = "noimage.png";

    /** レッスン開始可能オフセット時間(msec) */
    public static final int LESSON_START_OFFSET_MSEC = 600000;
    /** レッスン終了オフセット時間(msec) */
    public static final int LESSON_END_OFFSET_MSEC = 0;

    /** リトライ回数上限値 */
    public static final int UPDATE_RETRY_COUNT = 5;

    /** パスワードコメント */
    public static final String PASSWORD_COMMENT = "※半角英字・数字で、８文字以上１６文字以内で入力してください。";

    // CSV出力 start
    /** コーディング：UTF-8 */
    public static final String UTF_8 = "UTF-8";
    /** コーディング：SJIS */
    public static final String SJIS = "SJIS";
    /** コーディング：ISO8859-1 */
    public static final String ISO8859_1 = "ISO8859-1";
    /** ユーザーエージェント */
    public static final String USER_AGENT = "USER-AGENT";
    /** IE */
    public static final String MSIE = "msie";
    // CSV出力 end

    /** メール送信処理(DMの控えのDM履歴明細テーブル.受講者IDへの設定値) */
    public static final String DM_HISTORY_DETAILES_SCHOOL_ID = "SCHOOLID";
    /** メール送信処理(DMの控えのDM履歴明細テーブル.受講者名への設定値) */
    public static final String DM_HISTORY_DETAILES_SCHOOL_NOTE = "スクール(控え)";

    // ポイント管理マスタメンテナンス start

    /** 無償ポイント 初期値 */
    public static final BigDecimal FREE_POINT_INIT = new BigDecimal(0);
    /** 無償ポイント期限 初期値 */
    public static final int FREE_POINT_TIM_INIT = 0;
    /** 金額(税込)／有償ポイントの下限 */
    public static final BigDecimal MONEY_YEN_MIN = new BigDecimal(1000);
    /** 有償ポイント期限の下限 */
    public static final int PAYMENT_POINT_TIM_MIN = 1;

    // ポイント管理マスタメンテナンス end

    // バッチ start
    /** 処理時間区分：0001 */
    public static final String PROCESS_TIME_KBN_1 = "0001";
    /** 処理時間区分：0035 */
    public static final String PROCESS_TIME_KBN_35 = "0035";
    /** 処理時間区分：0036 */
    public static final String PROCESS_TIME_KBN_36 = "0036";

    /** メール：改行文字 */
    public static final String MAIL_NEW_LINE_CODE = "<br/>";
    /** パディング文字：0 */
    public static final char PAD_CHAR_0 = '0';

    /** メール：プロパティーファイル */
    public static final String EMAIL_ROPERTIES = "email.properties";
    /** メール：サーバー */
    public static final String EMAIL_HOST = "email.host";
    /** メール：ユーザ名 */
    public static final String EMAIL_USERNAME = "email.username";
    /** メール：パスワード */
    public static final String EMAIL_PASSWORD = "email.password";


    /** メール本文：text/html;charset=UTF-8 */
    public static final String CHARSET_UTF_8 = "text/html;charset=UTF-8";

    /** メール本文：shift-jis */
    public static final String EMAIL_CHARSET_SHIFT_JIS = "shift-jis";

    /** メール：イメージパス*/
    public static final String EMAIL_IMG_PATH = "images";

    /** メール：イメージCID*/
    public static final String EMAIL_IMG_CID = "cid:";

    /** メール：イメージRELATED*/
    public static final String EMAIL_IMG_RELATED = "related";

    /** メール：\"*/
    public static final String EMAIL_IMG_QUOTES = "\"";

    /** メール：CONTENT-ID*/
    public static final String EMAIL_CONTENT_ID = "Content-ID";

    /** メール：SMTP*/
    public static final String EMAIL_SMTP = "smtp";


    /** バッチ：開始ログ */
    public static final String BATCH_LOG_START = "＊＊＊　リマインダー送信処理　ＳＴＡＲＴ　%　＊＊＊";
    /** バッチ：エラーログ（処理時間区分） */
    public static final String BATCH_LOG_ERROR_PROCESS_TIME_KBN = "＊＊＊　エラー　処理時間区分：[ % ] が間違っています。";
    /** バッチ：エラーログ（処理時間区分） */
    public static final String BATCH_LOG_ERROR_SEND_ID = "＊＊＊　エラー　受講者ID：[ % ] が存在しません。";
    /** バッチ：終了ログ */
    public static final String BATCH_LOG_ENDED = "＊＊＊　リマインダー送信処理　ＥＮＤＥＤ　%　＊＊＊";
    /** 添付付きメール送信の一時的な格納先コード */
    public static final String EMAIL_ATTACH_TEMPATH_CD = "0001";

    /** 支払対象区分：未支払 */
    public static final String PAYMENT_KBN_NO = "0";
    /** 支払対象区分：支払 */
    public static final String PAYMENT_KBN_YES = "1";
    // バッチ end

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /** バッチ：プログラム開始ログ */
    public static final String BATCH_LOG_REQUEST_CXL_START = "＊＊＊　応相談予約の自動キャンセル(テーブル更新処理)とリマインダーメール送信処理　ＳＴＡＲＴ　%　＊＊＊";
    /** バッチ：プログラム終了ログ */
    public static final String BATCH_LOG_REQUEST_CXL_ENDED = "＊＊＊　応相談予約の自動キャンセル(テーブル更新処理)とリマインダーメール送信処理　ＥＮＤＥＤ　%　＊＊＊";
    /** バッチ：service処理開始ログ */
    public static final String BATCH_LOG_REQUEST_CXL_START_SV = "＊＊＊　応相談予約の自動キャンセル(テーブル更新処理)とリマインダーメール送信処理service　ＳＴＡＲＴ　%　＊＊＊";
    /** バッチ：service処理終了ログ */
    public static final String BATCH_LOG_REQUEST_CXL_ENDED_SV = "＊＊＊　応相談予約の自動キャンセル(テーブル更新処理)とリマインダーメール送信処理service　ＥＮＤＥＤ　%　＊＊＊";

    /** バッチ：infoログ 講師予定予約テーブル取得時の応相談予約登録日時 */
    public static final String BATCH_LOG_REQUEST_CXL_INFO_SCHEDULE_RESERVATION_TRN_GET_DT = "＊＊＊　応相談予約登録日時　≦　%（システム日付―２時間）＊＊＊";
    /** バッチ：warningログ 講師予定予約テーブルに対象データなし */
    public static final String BATCH_LOG_REQUEST_CXL_WRN_SCHEDULE_RESERVATION_TRN_DATA_NO = "＊＊＊　「リクエスト中」のデータが存在しません。　＊＊＊";
    /** バッチ：errログ 講師予定予約テーブルの取得失敗 */
    public static final String BATCH_LOG_REQUEST_CXL_ERR_SCHEDULE_RESERVATION_TRN = "＊＊＊　「リクエスト中」のデータ取得時にシステムエラーが発生しました。システム管理者へ連絡してください。 　＊＊＊";
    /** バッチ：errログ 宛先の取得失敗＜受講者マスタ＞ */
    public static final String BATCH_LOG_REQUEST_CXL_ERR_STUDENT_DATA_NO = "＊＊＊　受講者マスタに対象者が存在しない 又は メールアドレス1が設定されていません。　[受講者ID：%1]　[受講者名：%2]　[予約No：%3]　[レッスン日：%4]　[レッスン時刻：%5]　[講師ID：%6]　[応相談予約登録日時：%7]　＊＊＊";
    /** バッチ：warningログ メール文面に利用する講師マスタの取得失敗 */
    public static final String BATCH_LOG_REQUEST_CXL_WRN_TEACHER_DATA_NO = "＊＊＊　講師マスタに対象者が存在しません。　[講師ID：%]　＊＊＊";
    /** バッチ：errログ 宛先の取得失敗＜利用者マスタ＞ */
    public static final String BATCH_LOG_REQUEST_CXL_ERR_USER_DATA_NO = "＊＊＊　利用者マスタに対象者が存在しません。　[講師ID：%]　＊＊＊";

    /** バッチ：errログ 共通：レッスン予約・取消の処理失敗 */
    public static final String BATCH_LOG_REQUEST_CXL_ERR_TBL_UPDATE = "＊＊＊　ポイント返却、応相談予約の取消処理に失敗しました。　[受講者ID：%1]　[受講者名：%2]　[予約No：%3]　[レッスン日：%4]　[レッスン時刻：%5]　[講師ID：%6]　[応相談予約登録日時：%7]　＊＊＊";

    public static final String OUTPUT_TARGET_KBN_TEACHER = "講師";
    public static final String OUTPUT_TARGET_KBN_STUDENT = "受講者";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


    // 講師評価 Start
    /** 受講者側の視点から_1-2_レッスン実績(受講者用)(講師評価用) */
    public static final String STUDENT_LESSON_HISTORY_STUDENT_USES_LIST_MOVE = "studentLessonHistoryStudentUsesListMove";
    /** レッスンコメント */
    public static final String LESSON_COMMENT_MESSAGE = "レッスンお疲れ様でした。";
    /** コメント入力者区分 */
    public static final String CMT_INPUTS_KBN_C = "C";
    // 講師評価 Start

    // 講師一覧 start
    /** ブックマーク：済 */
    public static final String BOOKMARK_OK = "済";
    // 講師一覧 end

    // システム受講者登録 start

    /** 利用動機フラグ:有 */
    public static final String USE_MOTIVE_FLG_YES = "1";
    /** 利用動機フラグ:無 */
    public static final String USE_MOTIVE_FLG_NO = "0";
    /** 利用規約に同意する：チェックフラグ 有 */
    public static final String USE_AGREEMENT_FLG_YES = "1";
    /** 利用規約に同意する：チェックフラグ 無 */
    public static final String USE_AGREEMENT_FLG_NO = "0";
    /** 個人情報の同意：有 */
    public static final String INDIVIDUAL_AGREEMENT_FLG_YES = "1";
    /** 個人情報の同意：無 */
    public static final String INDIVIDUAL_AGREEMENT_FLG_NO = "0";
    /** ポイント購入済フラグ：有 */
    public static final String POINT_PURCHASE_FLG_YES = "1";
    /** ポイント購入済フラグ：無 */
    public static final String POINT_PURCHASE_FLG_NO = "0";
    /** DM不要フラグ：不要 */
    public static final int DM_NO_NEED_FLG_NO = 1;
    /** DM不要フラグ：必要 */
    public static final int DM_NO_NEED_FLG_YES = 0;
    /** その他フラグ：ON */
    public static final int OTHER_FLG_ON = 1;
    /** その他フラグ：OFF */
    public static final int OTHER_FLG_OFF = 0;
    /** 状態区分 予約*/
    public static final String STATE_KBN = "1";
    /** 自己負担率*/
    public static final String BURDENNUM = "0";

    // システム受講者登録 end

    // 予約申込ページ start
    /** 予約区分：予約受付ＮＧ */
    public static final String RESERV_KBN_NG = "0";
    // 予約申込ページ end

    // ポイント所有テーブル
    /** 画面システム作成区分：画面 */
    public static final String SCREEN_SYSTEM_KBN_SCREEN = "1";
    /** 画面システム作成区分：システム */
    public static final String SCREEN_SYSTEM_KBN_SYSTEM = "2";
    /** 月謝停止フラグ：未処理 */
    public static final String END_FLG_NO = "0";
    /** 月謝停止フラグ：処理済（停止済） */
    public static final String END_FLG_YES = "1";
    /** 有効期限終了計算日数 */
    // 0：nヶ月後にその日が存在しない場合、月末日を返す
    // 1：nヶ月後にその日が存在しない場合、翌月1日を返す
    public static final int EFFECTIVE_END_CALC_DT = 1;

    /** メールパターンコード */
    /** LBC:レッスン予約 ＜受講者向け＞ */
    public static final String MAIL_PATTERN_CODE_LBC = "LBC";
    /** LCC:レッスン取消 ＜受講者向け＞ */
    public static final String MAIL_PATTERN_CODE_LCC = "LCC";
    /** LBT:レッスン予約 ＜講師向け＞ */
    public static final String MAIL_PATTERN_CODE_LBT = "LBT";
    /** LCT:レッスン取消 ＜講師向け＞ */
    public static final String MAIL_PATTERN_CODE_LCT = "LCT";
    /** R1C:リマインダーメール(1日前) */
    public static final String MAIL_PATTERN_CODE_R1C = "R1C";
    /** R2C:リマインダーメール(２レッスン前) */
    public static final String MAIL_PATTERN_CODE_R2C = "R2C";
    /** PPU:ポイント購入（通常） */
    public static final String MAIL_PATTERN_CODE_PPU = "PPU";
    /** PPM:ポイント購入（月謝） */
    public static final String MAIL_PATTERN_CODE_PPM = "PPM";
    /** GBC:商品購入 */
    public static final String MAIL_PATTERN_CODE_GBC = "GBC";
    /** GBS:商品購入(配送) */
    public static final String MAIL_PATTERN_CODE_GBS = "GBS";
    /** AGC:アカウント取得処理 */
    public static final String MAIL_PATTERN_CODE_AGC = "AGC";
    /** AGS:アカウント取得処理 */
    public static final String MAIL_PATTERN_CODE_AGS = "AGS";
    /** SIS:問合せ画面 */
    public static final String MAIL_PATTERN_CODE_SIS = "SIS";
    /** DMC:DM */
    public static final String MAIL_PATTERN_CODE_DMC = "DMC";
    /** PTC:パスワード送信 */
    public static final String MAIL_PATTERN_CODE_PTC = "PTC";
    /** SMT:添付ファイル付きレッスンでのメール送信画面 ＜講師向け＞ */
    public static final String MAIL_PATTERN_CODE_SMT = "SMT";
    /** SMC:レッスンに対するメール送信画面 ＜受講者向け＞ */
    public static final String MAIL_PATTERN_CODE_SMC = "SMC";
    /** UCS:日次更新処理の定期購入不正キャンセル通知 */
    public static final String MAIL_PATTERN_CODE_UCS = "UCS";
    /** MCF:日次更新処理の定期購入キャンセル失敗通知 */
    public static final String MAIL_PATTERN_CODE_MCF = "MCF";

    //2014.02.18. 商品購入ページからの呼出追加に伴う修正
    /** GBT: 商品購入 講師への連絡通知メール */
    public static final String MAIL_PATTERN_CODE_GBT = "GBT";

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /** LRC: レッスン予約申込み画面 応相談予約 ＜受講者向け＞ */
    public static final String MAIL_PATTERN_CODE_LRC = "LRC";
    /** LRT: レッスン予約申込み画面 応相談予約 ＜講師向け＞ */
    public static final String MAIL_PATTERN_CODE_LRT = "LRT";
    /** ACC: レッスン予約申込み画面 応相談予約取消 ＜受講者向け＞ */
    public static final String MAIL_PATTERN_CODE_ACC = "ACC";
    /** ACT: レッスン予約申込み画面 応相談予約取消 ＜講師向け＞ */
    public static final String MAIL_PATTERN_CODE_ACT = "ACT";
    /** AOC: 応相談回答画面 ＯＫ ＜受講者向け＞ */
    public static final String MAIL_PATTERN_CODE_AOC = "AOC";
    /** AOT: 応相談回答画面 ＯＫ ＜講師向け＞ */
    public static final String MAIL_PATTERN_CODE_AOT = "AOT";
    /** ANC: 応相談回答画面 ＮＧ ＜受講者向け＞ */
    public static final String MAIL_PATTERN_CODE_ANC = "ANC";
    /** ANT: 応相談回答画面 ＮＧ ＜講師向け＞ */
    public static final String MAIL_PATTERN_CODE_ANT = "ANT";
    /** ANS: 応相談回答自動キャンセル＜受講者向け＞  */
    public static final String MAIL_PATTERN_CODE_ANS = "ANS";
    /** ANE: 応相談回答自動キャンセル＜講師向け＞  */
    public static final String MAIL_PATTERN_CODE_ANE = "ANE";
    /** ANA: 応相談回答自動キャンセル宛先不明-スクール連絡  */
    public static final String MAIL_PATTERN_CODE_ANA = "ANA";


    // コード管理マスタメンテナンス Start
    /** コード種別マスタ：汎用コード登録不可フラグ 登録可 */
    public static final String MANAGER_INSERT_NG_FLG_NO = "0";
    /** コード種別マスタ：汎用コード登録不可フラグ 登録不可 */
    public static final String MANAGER_INSERT_NG_FLG_YES = "1";
    /** コード管理マスタ：システム削除不可フラグ 削除可 */
    public static final String SYSTEM_DEL_NG_FLG_NO = "0";
    /** コード管理マスタ：システム削除不可フラグ 削除不可 */
    public static final String SYSTEM_DEL_NG_FLG_YES = "1";
    // コード管理マスタメンテナンス End

    // 予約スケジュール start
    public static final String HALF_SPACE = " ";
    /** ドロップダウン :コード・9999 */
    public static final String CHOICE_ALL_NINE = "9999";
    /** ドロップダウン :名称・予約中止 */
    public static final String RESERV_STATE_BREAK = "予約中止";
    // 予約スケジュール end

    // 講師マスタメンテナンスの講師支払比率の設定 start
    /** 支払比率の上限 */
    public static final BigDecimal PAYMENT_RATE_MAX = new BigDecimal(100);
    /** 支払比率の下限 */
    public static final BigDecimal PAYMENT_RATE_MIN = new BigDecimal(0);
    // 講師マスタメンテナンスの講師支払比率の設定 end

    // 組織マイページ start
    /** 遷移元画面ID 組織マイページ */
    public static final String ORGANIZATION_MY_PAGE = "OrganizationMyPage";
    /** 遷移元画面ID：マイページ（お客様） */
    public static final String STUDENT_MY_PAGE = "StudentMyPage";
    /** 遷移元画面ID：講師評価 */
    public static final String TEACHER_EVALUATION = "TeacherEvaluation";
    /** 遷移元画面ID：講師用個別マイページ */
    public static final String TEACHER_MY_PAGE = "TeacherMyPage";
    /** 遷移元画面ID：講師側の視点から_1-1_レッスン実績 */
    public static final String TEACHER_LESSON_MANAGEMENT = "TeacherLessonManagement";
    // 組織マイページ end

    // 予約／取消確認ページ start
    /** 遷移元画面ID コース名選択ページ */
    public static final String RESERVATION_CANCELLATION_COURSE_SELECTION_LIST = "ReservationCancellationCourseSelectionList";
    /** 遷移元画面ID：予約申込ページ */
    public static final String RESERVATION_CANCELLATION_COURSE_LIST = "ReservationCancellationCourseList";
    // 予約／取消確認ページ end

    // 特別無償ポイント設定 start
    /** 遷移元画面ID */
    public static final String STUDENT_SPECIAL_FREE_POINTMST_LOAD = "studentSpecialFreePointMstLoad";
    // 特別無償ポイント設定 end

    // 講師側の視点から_1-1_レッスン実績 start
    /** ｼｽﾃﾑ日付-1ヶ月  */
    public static final int SYS_MONTH_LAST = -1;
    // 講師側の視点から_1-1_レッスン実績 end

    // 組織契約情報登録 start
    /** 請求先住所区分：ON */
    public static final String REQUEST_ADDRESS_KBN_ON = "1";
    /** 請求先住所区分：OFF（停止済） */
    public static final String REQUEST_ADDRESS_KBN_OFF = "0";
    /** 日付 初期値 最小値 */
    public static final String MIN_END_DT = "0000/00/00";
    // 組織契約情報登録end

    // ペイパル関連
    /** 通貨コードタイプ：円（日本） */
    public static final String CURRENCY_CODE_TYPE_JPY = "JPY";
    /** 請求タイプ：定期支払 */
    public static final String BILLING_TYPE_RP = "RecurringPayments";
    /** 支払タイプ：販売 */
    public static final String PAYMENT_ACTION_SALE = "Sale";
    /** 請求期間：月 */
    public static final String BILLING_PERIOD_MONTH = "Month";
    /** 請求頻度：1回 */
    public static final String BILLING_FREQUENCY_ONE = "1";
    /** 請求サイクル：無制限 */
    public static final String TOTAL_BILLING_CYCLES_LIMITLESS = "0";
    /** 請求状態：キャンセル */
    public static final String ACTION_CANCEL = "Cancel";
    /** ペイパル 接続先 */
    public static final String PAYPAL_ENV = "0000";
    /** ペイパル 接続先 本番 */
    public static final String PAYPAL_ENV_L = "0";
    /** ペイパル 接続先 テスト */
    public static final String PAYPAL_ENV_T = "9";
    /** ペイパル API User */
    public static final String PAYPAL_API_USER = "0001";
    /** ペイパル API Password */
    public static final String PAYPAL_API_PASSWORD = "0002";
    /** ペイパル API Signature */
    public static final String PAYPAL_API_SIGNATURE = "0003";
    /** ペイパル ポイント購入 RETURN URL */
    public static final String PAYPAL_POINTS_RETURN_URL = "0004";
    /** ペイパル ポイント購入 CANCEL URL */
    public static final String PAYPAL_POINTS_CANCEL_URL = "0005";
    /** ペイパル 商品購入 RETURN URL */
    public static final String PAYPAL_GOODS_RETURN_URL = "0006";
    /** ペイパル 商品購入 CANCEL URL */
    public static final String PAYPAL_GOODS_CANCEL_URL = "0007";
    /** ペイパル ログイン URL */
    public static final String PAYPAL_LOGIN_URL = "0008";
    /** ペイパル TEST API User */
    public static final String PAYPAL_API_USER_T = "9001";
    /** ペイパル TEST API Password */
    public static final String PAYPAL_API_PASSWORD_T = "9002";
    /** ペイパル TEST API Signature */
    public static final String PAYPAL_API_SIGNATURE_T = "9003";
    /** ペイパル TEST ポイント購入 RETURN URL */
    public static final String PAYPAL_POINTS_RETURN_URL_T = "9004";
    /** ペイパル TEST ポイント購入 CANCEL URL */
    public static final String PAYPAL_POINTS_CANCEL_URL_T = "9005";
    /** ペイパル TEST 商品購入 RETURN URL */
    public static final String PAYPAL_GOODS_RETURN_URL_T = "9006";
    /** ペイパル TEST 商品購入 CANCEL URL */
    public static final String PAYPAL_GOODS_CANCEL_URL_T = "9007";
    /** ペイパル TEST ログイン URL */
    public static final String PAYPAL_LOGIN_URL_T = "9008";

    // コースマスタメンテナンス start
    /** レッスン時間 初期値 */
    public static final int FREE_LESSON_TIME_INIT = 30;
    // コースマスタメンテナンス end

    // 商品購入テーブル start
    /** 支払方法区分 1:PAYPAL購入、2:ポイント購入 */
    public static final String PAYMENT_METHOD_KBN_PAYPAL = "1";
    /** 支払方法区分 1:PAYPAL購入、2:ポイント購入 */
    public static final String PAYMENT_METHOD_KBN_POINT = "2";
    // コースマスタメンテナンス end


    // マイページ(お客様_個人) start
    /** レッスン時間 初期値 */
    public static final int LIMIT = 20;
    /** 残高ポイント */
    public static final String BALANCE_POINT_ZERO = "0";
    // マイページ(お客様_個人) end

    /** 予約NO判断用 */
    public static final String RESERVATION_NO_NULL = "null";

    /** ブックマーク済の講師一覧 データ件数 */
    public static final int TEACHER_LIST_DATA_COUNT = 50;

    /** HTMLメールの画像格納先 */
    public static final String CODE_CATEGORY_HTML_EMAIL_IMG_TEMPATH = "0001";

    /** 売上明細テーブル 日次作成フラグ＝”1”(日次作成) */
    public static final String DAILYBATCH_FLG_ON = "1";
    /** 売上明細テーブル 日次作成フラグ＝”0”(月次作成) */
    public static final String DAILYBATCH_FLG_OFF = "0";
    /** 売上明細テーブル 日次作成フラグ＝”9”(不要(ポイント返却)) */
    public static final String DAILYBATCH_FLG_NOTHING = "9";

    /** レッスン予約・取消 ReserveCancel 予約 */
    public static final String LESSON_RESERVE = "R";
    /** レッスン予約・取消 ReserveCancel 取消 */
    public static final String LESSON_CANCEL = "C";

    //2013/12/07-Add スクールメール送信・受信履歴照会
    /** コード管理マスタS07:メールパターン名 受講者未成年のアカウント作成 AGS */
    public static final String MAIL_PATTERN_ACCOUNT_MINOR = "0001";
    /** コード管理マスタS07:メールパターン名 問合せ(受講者、組織責任者) SIS */
    public static final String MAIL_PATTERN_CONTACT_MAIL_SEND = "0002";
    /** コード管理マスタS07:メールパターン名 日次更新処理の定期購入不正キャンセル通知 UCS */
    public static final String MAIL_PATTERN_CXL_ILLEGALITY = "0003";
    /** コード管理マスタS07:メールパターン名 日次更新処理の定期購入キャンセル失敗通知 MCF */
    public static final String MAIL_PATTERN_CXL_ERROR = "0004";
    /** コード管理マスタS07:メールパターン名 受講者⇒講師へ添付ファイル付きレッスンメール SMT */
    public static final String MAIL_PATTERN_ATTACHMENTS_T_FROM_S = "0005";
    /** コード管理マスタS07:メールパターン名 講師⇒受講者へレッスンに対するメール SMC */
    public static final String MAIL_PATTERN_ATTACHMENTS_S_FROM_T = "0006";
    /** コード管理マスタS07:メールパターン名 ポイント購入（通常）PPU */
    public static final String MAIL_PATTERN_POINT_PURCHASE_NORMAL = "0007";
    /** コード管理マスタS07:メールパターン名 ポイント購入（月謝）PPM */
    public static final String MAIL_PATTERN_POINT_PURCHASE_MONTHLY = "0008";
    /** コード管理マスタS07:メールパターン名 商品購入 GBC */
    public static final String MAIL_PATTERN_GOODS_PURCHASE = "0009";
    /** コード管理マスタS07:メールパターン名 商品購入(配送) GBS */
    public static final String MAIL_PATTERN_GOODS_DELIVERY = "0010";
    /** コード管理マスタS07:メールパターン名 DMメール送信 DMC */
    public static final String MAIL_PATTERN_DM = "0011";
    /** コード管理マスタS07:メールパターン名 パスワード送信 PTC */
    public static final String MAIL_PATTERN_PWD = "0012";
    /** コード管理マスタS07:メールパターン名 アカウント更新処理 */
    public static final String MAIL_PATTERN_ACCOUNT_UPD = "1001";

    // 2014/06/02-Add レッスン予約に関する「応相談」対応
    /** コード管理マスタS07:メールパターン名 応相談-レッスン予約 LRC */
    public static final String MAIL_PATTERN_LESSON_REQUEST = "0013";
    /** コード管理マスタS07:メールパターン名 応相談講師回答-ＯＫ AOT */
    public static final String MAIL_PATTERN_APPROVE_REQUEST_YES = "0014";
    /** コード管理マスタS07:メールパターン名 応相談講師回答-ＮＧ ANT */
    public static final String MAIL_PATTERN_APPROVE_REQUEST_NO = "0015";
    /** コード管理マスタS07:メールパターン名 応相談自動キャンセル ANS */
    public static final String MAIL_PATTERN_APPROVE_REQUEST_AUTO = "0016";


    //2013/12/07-Add スクールメール送信・受信履歴照会
    /** スクールメール送信・受信履歴照会 アカウント更新処理の変更項目名 */
    public static final String JNM_FAMILY_JNM  = "名前(姓)";
    public static final String JNM_FIRST_JNM  = "名前(名)";
    public static final String JNM_FAMILY_KNM  = "フリガナ(姓)";
    public static final String JNM_FIRST_KNM  = "フリガナ(名)";
    public static final String JNM_FAMILY_ROMAJI  = "ローマ字(姓)";
    public static final String JNM_FIRST_ROMAJI  = "ローマ字(名)";
    public static final String JNM_NICK_NM  = "ニックネーム";
    public static final String JNM_PASSWORD  = "パスワード";
    public static final String JNM_TEL1  = "電話番号";                                     // 電話番号1
    public static final String JNM_TEL2  = "携帯電話";                                     // 電話番号2
    public static final String JNM_BIRTH_DT  = "生年月日";
    public static final String JNM_ZIP_CD  = "郵便番号";
    public static final String JNM_ADDRESS_AREA  = "住所(地域)";
    public static final String JNM_ADDRESS_PREFECTURE  = "住所(都道府県)";
    public static final String JNM_ADDRESS_CITY  = "住所(市区町村 等)";
    public static final String JNM_ADDRESS_OTHERS  = "住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名)";
    public static final String JNM_GENDER  = "性別";
    public static final String JNM_MAIL_ADDRESS1  = "メールアドレス1";
    public static final String JNM_MAIL_ADDRESS2  = "メールアドレス2";
    public static final String JNM_MAIL_ADDRESS3  = "メールアドレス3";
    public static final String JNM_GUARDIAN_FAMILY_JNM  = "保護者 名前(姓)";
    public static final String JNM_GUARDIAN_FIRST_JNM  = "保護者 名前(名）";
    public static final String JNM_GUARDIAN_FAMILY_KNM  = "保護者 フリガナ(姓)";
    public static final String JNM_GUARDIAN_FIRST_KNM  = "保護者 フリガナ(名）";
    public static final String JNM_GUARDIAN_FAMILY_RELATIONSHIP  = "あなたとの続柄";
    public static final String JNM_GUARDIAN_TEL1  = "保護者 電話番号";                     // 電話番号1
    public static final String JNM_GUARDIAN_TEL2  = "保護者 携帯電話";                     // 電話番号2
    public static final String JNM_GUARDIAN_BIRTH_DT  = "保護者 生年月日";
    public static final String JNM_GUARDIAN_ZIP_CD  = "保護者 郵便番号";
    public static final String JNM_GUARDIAN_ADDRESS_AREA  = "保護者 住所(地域)";
    public static final String JNM_GUARDIAN_ADDRESS_PREFECTURE  = "保護者 住所(都道府県)";
    public static final String JNM_GUARDIAN_ADDRESS_CITY  = "保護者 住所(市区町村 等)";
    public static final String JNM_GUARDIAN_ADDRESS_OTHERS  = "保護者 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名)";
    public static final String JNM_GUARDIAN_GENDER_KBN  = "保護者 性別";
    public static final String JNM_GUARDIAN_MAIL_ADDRESS1  = "保護者 メールアドレス1";
    public static final String JNM_GUARDIAN_MAIL_ADDRESS2  = "保護者 メールアドレス2";
    public static final String JNM_GUARDIAN_MAIL_ADDRESS3  = "保護者 メールアドレス3";

    public static final String RIGHT = "→";

    /** 追加処理用：RecordVerNo */
    public static final int INS_REC_VER_NO = 0;

    // 2014/01/15-Add FlashからTokBoxへの変更対応
    /** TokBox情報：apiKey */
    public static final String TOXBOX_APIKEY = "0001";
    /** TokBox情報：apiSecret */
    public static final String TOXBOX_APISECRET = "0002";
    /** TokBox情報：apiUrl */
    public static final String TOXBOX_APIURL = "0003";

    // 2014/02/18-講師への購入メール連絡対応
    /** 商品購入ページ */
    public static final String NM_PURCHASE_GOODS_LIST = "purchaseGoodsList";
    /** (POP)講師一覧 */
    public static final String NM_TEACHER_LIST = "teacherList";

    // 2014/04/22-Add ファイルタイプの追加対応(pdf⇒pdf、txt)
    public final static String CONTENT_TYPE_PDF ="application/pdf";
    public final static String CONTENT_TYPE_TXT ="text/plain";
    public final static String CHARSET ="charset=";
    public final static String WIN_31J ="Windows-31J";

}
