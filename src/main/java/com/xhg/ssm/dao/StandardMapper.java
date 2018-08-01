package com.xhg.ssm.dao;

import com.xhg.ssm.entity.Standard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Standard record);

    int insertSelective(Standard record);

    Standard selectByPrimaryKey(Integer id);

    List<Standard> selectAllStandard();

    /**
     * 查询所有
     *
     * @return
     */
    List<Standard> selectAll(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 按照名称模糊分页查询
     *
     * @param likeName
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Standard> selectByLikeName(@Param("likeName") String likeName, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    int updateByPrimaryKeySelective(Standard record);

    int updateByPrimaryKey(Standard record);

    int getCounts();

    /**
     * 获得模糊查询结果的总记录数
     *
     * @param likeName
     * @return
     */
    int getCounts(@Param("likeName") String likeName);
}