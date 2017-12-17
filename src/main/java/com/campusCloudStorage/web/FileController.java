package com.campusCloudStorage.web;

import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.service.FileHeaderService;
import com.hadoop.HdfsFileSystem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;

@RequestMapping("file")
@Controller
public class FileController {

    @Resource
    private FileHeaderService fileHeaderService;

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public String fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        HttpSession session=request.getSession();
        int uId=(int)session.getAttribute("uId");
        int currentDir = (int)session.getAttribute("currentDir");

        String path = request.getServletContext().getRealPath(String.valueOf(uId));
        String fileName = String.valueOf(new Date().hashCode())+file.getOriginalFilename();
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }

        file.transferTo(dir);

        FileHeader fileHeader=new FileHeader();
        fileHeader.setName(file.getOriginalFilename());
        fileHeader.setSize((int)file.getSize());
        fileHeader.setParent(currentDir);
        fileHeader.setuId(uId);
        fileHeader.setPath(dir.toString());
        fileHeaderService.createFileHeader(fileHeader);

        return "forward:/home/"+currentDir;
    }


    @RequestMapping(value="/{fId}/download",method = RequestMethod.POST)
    public void fileDownload(@PathVariable("fId") int fId, HttpServletResponse response) throws Exception{
        FileHeader fileHeader=fileHeaderService.selectByPrimaryKey(fId);
        String path = fileHeader.getPath();
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));

        String fileName = fileHeader.getName();
        fileName = URLEncoder.encode(fileName,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }


    @RequestMapping(value="/{fId}/delete",method = RequestMethod.POST)
    public String fileDelete(@PathVariable("fId") int fId, HttpServletRequest request){
        HttpSession session=request.getSession();
        int currentDir = (int)session.getAttribute("currentDir");
        fileHeaderService.deleteByPrimaryKey(fId);
        return "forward:/home/"+currentDir;
    }
}
