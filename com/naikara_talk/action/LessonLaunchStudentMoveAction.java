package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

public class LessonLaunchStudentMoveAction extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    private String reservationNo;

    /**
     * @return reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * @param reservationNo セットする reservationNo
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
        this.reservationNo = "201307170000";

        return NEXTPAGE;

    }

}
