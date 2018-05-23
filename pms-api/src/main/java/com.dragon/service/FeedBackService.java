package com.dragon.service;

import com.dragon.common.DataList;
import com.dragon.common.model.FeedBack;
import com.dragon.pojo.Tfeedback;
import org.springframework.stereotype.Component;

@Component
public interface FeedBackService {

    DataList getFeedBacks(FeedBack feedBack) throws Exception;

    Integer delFeedBacks(String fIds) throws Exception;

    Tfeedback getFBContent(Integer fId) throws Exception;

    Integer updateStatus(Integer fId) throws Exception;

    int addFeedBack(Tfeedback feedback);
}
