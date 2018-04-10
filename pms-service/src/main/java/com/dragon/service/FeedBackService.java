package com.dragon.service;

import com.dragon.common.DataList;
import com.dragon.common.model.FeedBack;

public interface FeedBackService {

    DataList getFeedBacks(FeedBack feedBack) throws Exception;

    Integer delFeedBacks(String fIds) throws Exception;
}
