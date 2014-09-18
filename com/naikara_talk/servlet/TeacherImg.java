package com.naikara_talk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naikara_talk.mstcache.TeacherMstCache;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Servletクラス<br>
 * <b>クラス名称　　　:</b>販売商品マスタ(キャッシュ)のサンプル画像取得クラス<br>
 * <b>クラス概要　　　:</b>販売商品マスタ(キャッシュ)のサンプル画像Servlet<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>
 * 2013/06/03 TECS 新規作成
 */
public class TeacherImg extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        byte[] inDataByte = null;
        ServletOutputStream outStream = null;

        // 表示する利用者IDの取得
        String[] userCd = request.getParameterValues("userCd");

        TeacherMstCache mst;

        try {

            mst = TeacherMstCache.getInstance();

            if (mst != null) {
                if (mst.get(userCd[0]) != null) {
                    if (mst.get(userCd[0]).getImgPhoto() != null) {
                        /* 画像ファイルを 読取 */
                        inDataByte = mst.get(userCd[0]).getImgPhoto();
                    }
                }
            }

            /* OutputStream を取得 */
            outStream = response.getOutputStream();

            /* 画像を出力 */
            if (inDataByte != null) {
                if (inDataByte.length > 0) {
                    outStream.write(inDataByte);
                } else {
                    outStream.write(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 閉じて、このストリームに関連するすべてのシステムリソースを解放する
                if (outStream != null) {
                    outStream.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
}
