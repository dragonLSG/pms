package com.dragon.service.impl;

import com.dragon.common.DataList;
import com.dragon.common.PageInf;
import com.dragon.common.model.Payment;
import com.dragon.mapper.TpaymentMapper;
import com.dragon.pojo.Tpayment;
import com.dragon.pojo.TpaymentExample;
import com.dragon.pojo.TpaymentExample.Criteria;
import com.dragon.service.PaymentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TpaymentMapper mapper;

    @Override
    public DataList getPaymentList(Payment payment) throws Exception {

        PageHelper.startPage(payment.getPageNum(), payment.getPageSize());
        String startDate = payment.getStartDate();
        String endDate = payment.getEndDate();
        Short status = payment.getStatus();

        TpaymentExample example = new TpaymentExample();
        Criteria criteria = example.createCriteria();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (startDate != null && !"".equals(startDate.trim())) {
            criteria.andPaydateGreaterThanOrEqualTo(dateFormat.parse(startDate));
        }
        if (payment.getEndDate() != null) {
            criteria.andPaydateLessThanOrEqualTo(dateFormat.parse(endDate));
        }
        if (status != null) {
            criteria.andPaystatusEqualTo(status);
        }
        List<Tpayment> list = mapper.selectByExample(example);
        PageInfo<Tpayment> pageInfo = new PageInfo<Tpayment>(list);

        return new DataList(list, new PageInf(pageInfo));
    }
}
