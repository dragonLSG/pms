package com.dragon.service;

import com.dragon.common.PMSResult;
import org.springframework.stereotype.Component;

@Component
public interface SendMsgService {

    public PMSResult sendCheckNum(String account, String phone, String email);

}
