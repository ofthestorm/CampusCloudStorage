package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.service.FileHeaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class FileHeaderServiceImplTest {
    @Resource
    private FileHeaderService fileHeaderService;

    @Test
    public void createFileHeader() throws Exception {
        FileHeader fileHeader=new FileHeader();
        fileHeader.setuId(1);
        fileHeader.setName("test.txt");
        fileHeader.setParent(1);
        fileHeader.setPath("/ds");
        fileHeader.setSize(256);
        CreateStateEnum createState = fileHeaderService.createFileHeader(fileHeader);
        System.out.println(createState.getStateInfo());
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {
    }

}