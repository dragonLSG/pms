package com.dragon.service.impl;

import com.dragon.common.DataList;
import com.dragon.common.PageInf;
import com.dragon.common.model.FeedBack;
import com.dragon.mapper.TfeedbackMapper;
import com.dragon.pojo.Tfeedback;
import com.dragon.pojo.TfeedbackExample;
import com.dragon.pojo.TfeedbackExample.Criteria;
import com.dragon.service.FeedBackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private TfeedbackMapper mapper;

    @Override
    public DataList getFeedBacks(FeedBack feedBack) throws Exception {
        PageHelper.startPage(feedBack.getPageNum(), feedBack.getPageSize());

        String title = feedBack.getTitle();
        String startDate = feedBack.getStartDate();
        String endDate = feedBack.getEndDate();
        Short status = feedBack.getStatus();

        TfeedbackExample example = new TfeedbackExample();
        example.setOrderByClause("fdate desc");
        Criteria criteria = example.createCriteria();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (title != null && title != "") {
            criteria.andTitleLike("%" + title + "%");
        }
        if (startDate != null && startDate != "") {
            criteria.andFdateGreaterThan(dateFormat.parse(startDate));
        }
        if (endDate != null && endDate != "") {
            criteria.andFdateLessThan(dateFormat.parse(endDate));
        }
        if (status != null && status != 2) {
            criteria.andFstatusEqualTo(status);
        }
        List<Tfeedback> list = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);

        return new DataList(list, new PageInf(pageInfo));
    }

    @Override
    public Integer delFeedBacks(String fIds) throws Exception {

        if (null == fIds) {
            return 0;
        }
        String[] fId = fIds.split(",");
        int num = 0;
        for (String id : fId) {
            num += mapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
        return num;
    }

    @Override
    public Tfeedback getFBContent(Integer fId) throws Exception {

        if (fId == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(fId);
    }
}
