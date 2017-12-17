package com.campusCloudStorage.dao;

import com.campusCloudStorage.entity.Dir;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class DirDaoTest {
    @Resource
    private DirDao dirDao;

    @Test
    public void insert() throws Exception {
        Dir dir=new Dir();
        dir.setName("1");
        dir.setCreateTime(new Date());
        int insertCount = dirDao.insert(dir);
        System.out.println("Insert Count:"+insertCount);
        int dId=dir.getdId();
        System.out.println("DID:"+dId);
    }

    @Test
    public void getChildren() throws Exception {
        List<Dir> childrenDirList=dirDao.selectByParentId(1);

        for (Dir child:childrenDirList){
            System.out.println(child.getName());
        }
    }
}