package com.dragon.service.impl;

import com.dragon.common.DataList;
import com.dragon.common.PageInf;
import com.dragon.common.model.Notice;
import com.dragon.mapper.TnoticeMapper;
import com.dragon.pojo.Tnotice;
import com.dragon.pojo.TnoticeExample;
import com.dragon.pojo.TnoticeExample.Criteria;
import com.dragon.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private TnoticeMapper mapper;

    @Override
    public DataList getNoticeByArgs(Notice noticeInf) throws Exception {

        String title = noticeInf.getTitle();
        String publisher = noticeInf.getPublisher();
        String startDate = noticeInf.getStartDate();
        String endDate = noticeInf.getEndDate();

        PageHelper.startPage(noticeInf.getPageNum(), noticeInf.getPageSize());

        TnoticeExample example = new TnoticeExample();
        example.setOrderByClause("publishdate desc");
        Criteria criteria = example.createCriteria();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (title != null && !"".equals(title.trim())) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (publisher != null && !"".equals(publisher.trim())) {
            criteria.andPublisherEqualTo(publisher);
        }
        if (startDate != null && !"".equals(startDate.trim())) {
            criteria.andPublishdateGreaterThan(dateFormat.parse(startDate));
        }
        if (endDate != null && !"".equals(endDate.trim())) {
            criteria.andPublishdateLessThan(dateFormat.parse(endDate));
        }

        List<Tnotice> list = mapper.selectByExample(example);
        PageInfo<Tnotice> pageInfo = new PageInfo<Tnotice>(list);

        return new DataList(list, new PageInf(pageInfo));
    }

    @Override
    public Tnotice getNoticeContent(Integer noticeId) throws Exception {

        if (noticeId == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public Integer updateNotice(Tnotice notice) throws Exception {

        Integer noticeid = notice.getNoticeid();
        notice.setPublishdate(new Date());
        Integer num = null;
        if (noticeid != null) {
            num = mapper.updateByPrimaryKey(notice);
        } else {
            num = mapper.insertSelective(notice);
        }

        return num;
    }

    @Override
    public Integer delNotices(String noticeIds) throws Exception {

        if (null == noticeIds || "".equals(noticeIds.trim())) {
            return 0;
        }
        String[] noticeId = noticeIds.split(",");
        int num = 0;
        for (String id : noticeId) {
            num += mapper.deleteByPrimaryKey(Integer.parseInt(id));
        }
        return num;
    }
}
