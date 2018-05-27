package com.dragon.service;

import com.dragon.common.DataList;
import com.dragon.common.model.Payment;

public interface PaymentService {

    public DataList getPaymentList(Payment payment) throws Exception;

}
