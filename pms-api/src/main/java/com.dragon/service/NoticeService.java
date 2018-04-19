package com.dragon.service;

import com.dragon.common.DataList;
import com.dragon.common.model.Notice;
import com.dragon.pojo.Tnotice;

public interface NoticeService {

    DataList getNoticeByArgs(Notice noticeInf) throws Exception;

    Tnotice getNoticeContent(Integer noticeid) throws Exception;

    Integer updateNotice(Tnotice notice) throws Exception;

    Integer delNotices(String noticeIds) throws Exception;
}
