package com.xhg.ssm.dao;

import com.xhg.ssm.entity.Standard;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.*;

public class StandardMapperTest {
    private SqlSessionFactory sqlSessionFactory;
    private String resource = "mybatis-config.xml";

    @Before
    public void setUp() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void selectByPrimaryKey() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            StandardMapper standardMapper = sqlSession.getMapper(StandardMapper.class);

            Standard standard = standardMapper.selectByPrimaryKey(1);

            System.out.println(standard);

            System.out.println("**************************************************");

            Standard standard1 = sqlSession.selectOne("com.xhg.ssm.dao.StandardMapper.selectByPrimaryKey", 1);
            System.out.println(standard1);
        } finally {
            sqlSession.close();
        }

    }
}