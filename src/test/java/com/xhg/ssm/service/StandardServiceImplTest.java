package com.xhg.ssm.service;

import com.xhg.ssm.entity.Standard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mybatis.xml")
public class StandardServiceImplTest {

    @Autowired
    private StandardService standardService;

    @Test
    public void selectByPrimaryKey() {
        Standard standard1 = standardService.selectByPrimaryKey(1);
        System.out.println("第一次查询：" + standard1);
        Standard standard2 = standardService.selectByPrimaryKey(1);
        System.out.println("第二次查询：" + standard2);
    }
}