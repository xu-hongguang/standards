package com.xhg.ssm.service;

import com.xhg.ssm.entity.Page;
import com.xhg.ssm.entity.Standard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardService {

    int deleteByPrimaryKey(Integer id);

    int insert(Standard record);

    int insertSelective(Standard record);

    Standard selectByPrimaryKey(Integer id);

    List<Standard> selectAllStandard();

    /**
     * 查询所有
     * @return
     */
    List<Standard> selectAll(int pageNo, int pageSize);

    /**
     * 按照名称模糊分页查询
     * @param likeName
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Standard> selectByLikeName(String likeName, int pageNo, int pageSize);


    Page<Standard> selectByLike(String likeName, int pageNo, int pageSize);


    int updateByPrimaryKeySelective(Standard record);

    int updateByPrimaryKey(Standard record);

    int getCounts();

    int getCounts(String likeName);

}
