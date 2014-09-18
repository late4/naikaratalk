package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.ContactMailSendLogic;
import com.naikara_talk.model.ContactMailSendModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録<br>
 * <b>クラス名称　　　:</b>問合せ画面Serviceクラス。<br>
 * <b>クラス概要　　　:</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class ContactMailSendLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    public ContactMailSendModel selectStudent(ContactMailSendModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            ContactMailSendLogic contactMailSendLogic = new ContactMailSendLogic(conn);
            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToStudentDto(model);

            // 検索実行
            StudentMstDto resultDto = contactMailSendLogic.selectStudent(prmDto);

            // DTO値をModelにセット
            model = this.studentDtoToModel(resultDto, model);

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
    public ContactMailSendModel selectOrganization(ContactMailSendModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            ContactMailSendLogic contactMailSendLogic = new ContactMailSendLogic(conn);
            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToOrganizationDto(model);

            // 検索実行
            OrganizationMstDto resultDto = contactMailSendLogic.selectOrganization(prmDto);

            // DTO値をModelにセット
            model = this.organizationDtoToModel(resultDto, model);

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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード <br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            ContactMailSendLogic contactMailSendLogic = new ContactMailSendLogic(conn);
            // コード管理マスタ検索
            return contactMailSendLogic.selectCodeMst(category);
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
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int studentExists(ContactMailSendModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            ContactMailSendLogic ｃontactMailSendLogic = new ContactMailSendLogic(conn);

            // DTOの初期化
            StudentMstDto prmDto = new StudentMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToStudentDto(model);

            // 検索実行
            return ｃontactMailSendLogic.studentExists(prmDto);
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
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int organizationExists(ContactMailSendModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            ContactMailSendLogic ｃontactMailSendLogic = new ContactMailSendLogic(conn);

            // DTOの初期化
            OrganizationMstDto prmDto = new OrganizationMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToOrganizationDto(model);

            // 検索実行
            return ｃontactMailSendLogic.organizationExists(prmDto);
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
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */

    private StudentMstDto modelToStudentDto(ContactMailSendModel model) throws NaiException {
        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();
        prmDto.setStudentId(model.getOrganizationId());                  // 受講者ID
        prmDto.setCodeCategoryContact(model.getCodeCategoryContact());   // お問合せの目的
        prmDto.setSubject(model.getSubject());                           // 件名
        prmDto.setFamilyJnm(model.getManagFamilyJnm());                  // お名前(姓)
        prmDto.setFirstJnm(model.getManagFirstJnm());                    // お名前(名)
        prmDto.setTel1(model.getTel());                                  // 電話番号1
        prmDto.setMailAddress1(model.getManagMailAddress1());            // メールアドレス1
        prmDto.setContactText(model.getContactText());                   // ご意見・ご要望・お問合せ内容
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
    private OrganizationMstDto modelToOrganizationDto(ContactMailSendModel model) throws NaiException {

        // DTOの初期化
        OrganizationMstDto prmDto = new OrganizationMstDto();
        prmDto.setOrganizationId(model.getOrganizationId());                  // 組織ID
        prmDto.setCodeCategoryContact(model.getCodeCategoryContact());        // お問合せの目的
        prmDto.setSubject(model.getSubject());                                // 件名
        prmDto.setManagFamilyJnm(model.getManagFamilyJnm());                  // 責任者名(姓)
        prmDto.setManagFirstJnm(model.getManagFirstJnm());                    // 責任者名(名)
        prmDto.setTel(model.getTel());                                        // 電話番号
        prmDto.setManagMailAddress1(model.getManagMailAddress1());            // 責任者メールアドレス1
        prmDto.setContactText(model.getContactText());                        // ご意見・ご要望・お問合せ内容
        prmDto.setConsSeq(model.getConsSeq());                                // 連番
        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param StudentMstDto 画面パラメータ<br>
     * @param ContactMailSendModel 画面パラメータ<br>
     * @return ContactMailSendModel 変換後結果<br>
     * @exception NaiException
     */
    private ContactMailSendModel studentDtoToModel(StudentMstDto prmDto, ContactMailSendModel model)
            throws NaiException {

        model.setOrganizationId(prmDto.getStudentId());                        // 受講者ID
        model.setCodeCategoryContact(prmDto.getCodeCategoryContact());         // お問合せの目的
        model.setSubject(prmDto.getSubject());                                 // 件名
        model.setManagFamilyJnm(prmDto.getFamilyJnm());                        // お名前(姓)
        model.setManagFirstJnm(prmDto.getFirstJnm());                          // お名前(名)
        model.setTel(prmDto.getTel1());                                        // 電話番号1
        model.setManagMailAddress1(prmDto.getMailAddress1());                  // メールアドレス1
        model.setContactText(prmDto.getContactText());                         // ご意見・ご要望・お問合せ内容
        return model;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param OrganizationMstDto 画面パラメータ<br>
     * @param ContactMailSendModel 画面パラメータ<br>
     * @return ContactMailSendModel 変換後結果<br>
     * @exception NaiException
     */
    private ContactMailSendModel organizationDtoToModel(OrganizationMstDto prmDto, ContactMailSendModel model)
            throws NaiException {

        model.setOrganizationId(prmDto.getOrganizationId());                   // 受講者ID
        model.setCodeCategoryContact(prmDto.getCodeCategoryContact());         // お問合せの目的
        model.setSubject(prmDto.getSubject());                                 // 件名
        model.setManagFamilyJnm(prmDto.getManagFamilyJnm());                   // お名前(姓)
        model.setManagFirstJnm(prmDto.getManagFirstJnm());                     // お名前(名)
        model.setTel(prmDto.getTel());                                         // 電話番号1
        model.setManagMailAddress1(prmDto.getManagMailAddress1());             // メールアドレス1
        model.setContactText(prmDto.getContactText());                         // ご意見・ご要望・お問合せ内容
        return model;

    }

}
