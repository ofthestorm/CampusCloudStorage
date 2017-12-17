package com.campusCloudStorage.service.impl;

import com.campusCloudStorage.dao.DirDao;
import com.campusCloudStorage.dao.FileHeaderDao;
import com.campusCloudStorage.entity.Dir;
import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.enums.UpdateStateEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("dirService")
public class DirServiceImpl implements com.campusCloudStorage.service.DirService{

    @Resource
    private DirDao dirDao;

    @Resource
    private FileHeaderDao fileHeaderDao;

    @Override
    public CreateStateEnum createDir(Dir dir) {
        if(dir==null || dir.getParent()==null||
                dir.getName()==null||"".equals(dir.getName().trim())){
            return CreateStateEnum.IMCOMPLETE;
        }

        if(dir.getName().contains("/")||dir.getName().contains("\\")){
            return CreateStateEnum.ILLEGAL_INFO;
        }

        List<Dir>childrenDirList=dirDao.selectByParentId(dir.getdId());
        for(Dir childrenDir:childrenDirList){
            if(childrenDir.getName().equals(dir.getName())){
                return CreateStateEnum.DIR_NAME_REPEAT;
            }
        }

        dir.setCreateTime(new Date());
        int insertCount=dirDao.insert(dir);
        if(insertCount==1){
            return CreateStateEnum.SUCCESS;
        }
        return CreateStateEnum.FAILED;
    }

    @Override
    public UpdateStateEnum update(Dir dir) {
        if(dir==null||dir.getName()==null||"".equals(dir.getName().trim())
                ||dir.getParent()==null || dir.getdId()==null) {
            return UpdateStateEnum.FAILED;
        }

        if(dir.getName().contains("/")||dir.getName().contains("\\")){
            return UpdateStateEnum.FAILED;
        }

        List<Dir>siblings=getSiblingsDirList(dir);
        for (Dir sibling:siblings){
            if(sibling.getName().equals(dir.getName())){
                return UpdateStateEnum.FAILED;
            }
        }

        int updateCount=dirDao.updateByPrimaryKeySelective(dir);
        if(updateCount==1){
            return UpdateStateEnum.SUCCESS;
        }
        return UpdateStateEnum.FAILED;
    }

    @Override
    public DeleteStateEnum deleteByPrimaryKey(int dId) {
        Dir dir = dirDao.selectByPrimaryKey(dId);
        if(dir==null){
            return DeleteStateEnum.FAILED;
        }

        List<FileHeader> firstChildrenFileHeaderList=getFirstChildrenFileHeaderList(dId);
        if(firstChildrenFileHeaderList!=null){
            for (FileHeader fileHeader: firstChildrenFileHeaderList){
                fileHeaderDao.deleteByPrimaryKey(fileHeader.getfId());
            }
        }

        List<Dir> firstChildrenDirList=getFirstChildrenDirList(dId);
        if(firstChildrenDirList!=null){
            for(Dir child:firstChildrenDirList){
                deleteByPrimaryKey(child.getdId());
                dirDao.deleteByPrimaryKey(child.getdId());
            }
        }
        dirDao.deleteByPrimaryKey(dId);
        return DeleteStateEnum.SUCCESS;
    }

    @Override
    public Dir selectByPrimaryKey(int dId) {
        Dir dir = dirDao.selectByPrimaryKey(dId);
        return dir;
    }

    @Override
    public Dir selectWithFirstChildrenById(int dId) {
        Dir dir = dirDao.selectByPrimaryKey(dId);
        if(dir!=null){
            List<Dir>childrenDirList=dirDao.selectByParentId(dId);
            List<FileHeader>childrenFileList=fileHeaderDao.selectByParentId(dId);

            dir.setChildrenDirList(childrenDirList);
            dir.setChildrenFileHeaderList(childrenFileList);
        }
        return dir;
    }

    @Override
    public List<Dir> getFirstChildrenDirList(int dId) {
        List<Dir>childrenDirList=dirDao.selectByParentId(dId);
        return childrenDirList;
    }

    @Override
    public List<FileHeader> getFirstChildrenFileHeaderList(int dId) {
        List<FileHeader>childrenFileList=fileHeaderDao.selectByParentId(dId);
        return childrenFileList;
    }

    @Override
    public Dir selectWithAllChildrenById(int dId) {
        Dir rootDir= dirDao.selectByPrimaryKey(dId);
        if(rootDir!=null){
            List<Dir> firstChildrenDirList=dirDao.selectByParentId(dId);
            if(firstChildrenDirList!=null){
                int len=firstChildrenDirList.size();
                for(int i=0;i<len;++i){
                    Dir dir=firstChildrenDirList.get(i);
                    dir.setChildrenDirList(selectWithAllChildrenById(dir.getdId()).getChildrenDirList());
                }
                rootDir.setChildrenDirList(firstChildrenDirList);
            }
            List<FileHeader> firstChildrenFileHeaderList=fileHeaderDao.selectByParentId(dId);
            rootDir.setChildrenFileHeaderList(firstChildrenFileHeaderList);
        }
        return rootDir;
    }

    @Override
    public List<Dir> getSiblingsDirList(Dir dir) {
        Dir parentDir= selectWithFirstChildrenById(dir.getParent());
        List<Dir>siblingsDirList=null;
        if(parentDir!=null){
            siblingsDirList=parentDir.getChildrenDirList();
            for (Iterator<Dir> iterator=siblingsDirList.iterator();iterator.hasNext();){
                if(iterator.next().getdId()==dir.getdId()){
                    iterator.remove();
                }
                break;
            }
        }
        return siblingsDirList;
    }

    @Override
    public List<Dir> getPathList(Dir dir) {
        List<Dir>path=new ArrayList<>();
        Dir currentDir=dir;
        path.add(currentDir);
        while (currentDir.getParent()!=null){
            currentDir=selectByPrimaryKey(currentDir.getParent());
            path.add(currentDir);
        }
        Collections.reverse(path);
        return path;
    }
}
