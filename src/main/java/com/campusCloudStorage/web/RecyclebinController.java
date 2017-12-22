package com.campusCloudStorage.web;

import com.campusCloudStorage.entity.Dir;
import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.entity.UserGroup;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.enums.UpdateStateEnum;
import com.campusCloudStorage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/recyclebin")
public class RecyclebinController {

    @Autowired
    private FileHeaderService fileHeaderService;

    @Autowired
    private DirService dirService;


    @RequestMapping(value="/{dId}",method= RequestMethod.POST)
    public String list(@PathVariable("dId")int dId, HttpServletRequest request, Model model) {
        HttpSession session=request.getSession();
        int uId=(int)session.getAttribute("uId");
        int rootDir=(int)session.getAttribute("rootDir");
        int recyclebin=(int)session.getAttribute("recyclebin");

        Dir dir=dirService.selectByPrimaryKey(dId);
        List<Dir> dirList=dirService.getFirstChildrenDirList(dId);
        List<FileHeader> fileHeaderList=dirService.getFirstChildrenFileHeaderList(dId);
        List<Dir> pathList=dirService.getPathList(dir);

        //User user=userService.selectByPrimaryKey(uId);

        model.addAttribute("uId",uId);
        model.addAttribute("currentDir",dId);
        model.addAttribute("rootDir",rootDir);
        model.addAttribute("recyclebin",recyclebin);

        model.addAttribute("dirList",dirList);
        model.addAttribute("fileHeaderList",fileHeaderList);
        model.addAttribute("pathList",pathList);

        session.setAttribute("currentDir",dId);
        return "recyclebin";
    }


    @RequestMapping(value="/{dId}/dir/delete",method= RequestMethod.POST)
    public String deleteDir(@PathVariable("dId")int dId, HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        DeleteStateEnum deleteState=dirService.deleteByPrimaryKey(dId);
        modelMap.addFlashAttribute("msg",deleteState.getStateInfo());

        HttpSession session=request.getSession();
        int currentDir = (int)session.getAttribute("currentDir");
        return "forward:/recyclebin/"+currentDir;
    }


    @RequestMapping(value="/{dId}/dir/{newParentId}/recover",method= RequestMethod.POST)
    public String recoverDir(@PathVariable("dId")int dId, @PathVariable("newParentId")int newParentId,
                            HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        Dir dir=dirService.selectByPrimaryKey(dId);
        dir.setParent(newParentId);
        UpdateStateEnum updateState = dirService.update(dir);

        modelMap.addFlashAttribute("msg",updateState.getStateInfo());

        HttpSession session=request.getSession();
        int currentDir = (int)session.getAttribute("currentDir");
        return "forward:/recyclebin/"+currentDir;
    }

    @RequestMapping(value="/{fId}/file/{newParentId}/recover",method= RequestMethod.POST)
    public String recoverFile(@PathVariable("fId")int fId, @PathVariable("newParentId")int newParentId,
                          HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        FileHeader fileHeader= fileHeaderService.selectByPrimaryKey(fId);
        fileHeader.setParent(newParentId);
        UpdateStateEnum updateState = fileHeaderService.update(fileHeader);

        modelMap.addFlashAttribute("msg",updateState.getStateInfo());

        HttpSession session=request.getSession();
        int currentDir = (int)session.getAttribute("currentDir");
        return "forward:/recyclebin/"+currentDir;
    }



}
