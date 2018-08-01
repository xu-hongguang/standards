package com.xhg.ssm.service;

import com.xhg.ssm.dao.StandardMapper;
import com.xhg.ssm.entity.Page;
import com.xhg.ssm.entity.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 16033
 */
@Service
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Standard record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Standard record) {
        return mapper.insertSelective(record);
    }

    @Override
    public Standard selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Standard> selectAllStandard() {
        return mapper.selectAllStandard();
    }

    @Override
    public List<Standard> selectAll(int pageNo, int pageSize) {
        pageNo = (pageNo - 1) * pageSize;
        return mapper.selectAll(pageNo, pageSize);
    }

    @Override
    public List<Standard> selectByLikeName(String likeName, int pageNo, int pageSize) {
        pageNo = (pageNo - 1) * pageSize;
        return mapper.selectByLikeName(likeName, pageNo, pageSize);
    }

    @Override
    public Page<Standard> selectByLike(String likeName, int pageNo, int pageSize) {
        Page<Standard> page = new Page<>();
        if(pageNo <= 0){
            pageNo = 1;
        }
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalCounts(mapper.getCounts(likeName));
        page.setPageCounts();
        page.setRows(mapper.selectByLikeName(likeName, (pageNo - 1) * pageSize, pageSize));

        return page;
    }

    @Override
    public int updateByPrimaryKeySelective(Standard record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Standard record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int getCounts() {
        return mapper.getCounts();
    }

    @Override
    public int getCounts(String likeName) {
        return mapper.getCounts(likeName);
    }
}
