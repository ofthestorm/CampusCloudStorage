package com.campusCloudStorage.web;

import com.campusCloudStorage.entity.Dir;
import com.campusCloudStorage.entity.FileHeader;
import com.campusCloudStorage.entity.User;
import com.campusCloudStorage.enums.CreateStateEnum;
import com.campusCloudStorage.enums.DeleteStateEnum;
import com.campusCloudStorage.enums.LoginStateEnum;
import com.campusCloudStorage.enums.UpdateStateEnum;
import com.campusCloudStorage.service.DirService;
import com.campusCloudStorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private DirService dirService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/{dId}",method= RequestMethod.POST)
    public String list(@PathVariable("dId")int dId, HttpServletRequest request, Model model) {
        HttpSession session=request.getSession();
        int uId=(int)session.getAttribute("uId");

        Dir dir=dirService.selectByPrimaryKey(dId);
        List<Dir> dirList=dirService.getFirstChildrenDirList(dId);
        List<FileHeader> fileHeaderList=dirService.getFirstChildrenFileHeaderList(dId);
        List<Dir> pathList=dirService.getPathList(dir);

        User user=userService.selectByPrimaryKey(uId);
        List<Dir> moveList=dirService.getFirstChildrenDirList(user.getRootDir());


        model.addAttribute("currentDir",dId);
        model.addAttribute("rootDir",user.getRootDir());
        model.addAttribute("moveBackDir",user.getRootDir());
        model.addAttribute("uId",uId);
        model.addAttribute("dirList",dirList);
        model.addAttribute("fileHeaderList",fileHeaderList);
        model.addAttribute("pathList",pathList);
        model.addAttribute("moveList",moveList);

        session.setAttribute("currentDir",dId);
        return "home";
    }

    @RequestMapping(value="/{dId}/dir/add",method= RequestMethod.POST)
    public String addDir(@PathVariable("dId")int dId, Dir dir, HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        dir.setParent(dId);
        CreateStateEnum createState=dirService.createDir(dir);
        modelMap.addFlashAttribute("msg",createState.getStateInfo());
        return "forward:/home/"+dId;
    }

    @RequestMapping(value="/{dId}/dir/delete",method= RequestMethod.POST)
    public String deleteDir(@PathVariable("dId")int dId, HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        DeleteStateEnum deleteState=dirService.deleteByPrimaryKey(dId);
        modelMap.addFlashAttribute("msg",deleteState.getStateInfo());

        HttpSession session=request.getSession();
        int currentDir = (int)session.getAttribute("currentDir");
        return "forward:/home/"+currentDir;
    }

    @RequestMapping(value="/{dId}/dir/rename",method= RequestMethod.POST)
    public String renameDir(@PathVariable("dId")int dId, String name, HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        Dir dir=dirService.selectByPrimaryKey(dId);
        dir.setName(name);
        UpdateStateEnum updateState = dirService.update(dir);

        modelMap.addFlashAttribute("msg",updateState.getStateInfo());

        HttpSession session=request.getSession();
        int currentDir = (int)session.getAttribute("currentDir");
        return "forward:/home/"+currentDir;
    }

    @RequestMapping(value="/{dId}/dir/{newParentId}/move",method= RequestMethod.POST)
    public String moveDir(@PathVariable("dId")int dId, @PathVariable("newParentId")int newParentId,
                            HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        Dir dir=dirService.selectByPrimaryKey(dId);
        dir.setParent(newParentId);
        UpdateStateEnum updateState = dirService.update(dir);

        modelMap.addFlashAttribute("msg",updateState.getStateInfo());

        HttpSession session=request.getSession();
        int currentDir = (int)session.getAttribute("currentDir");
        return "forward:/home/"+currentDir;
    }

    @RequestMapping(value="{srcDId}/dir/{dId}/enter",method= RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String enterDir(@PathVariable("srcDId") int srcDId, @PathVariable("dId")int dId) {
        Dir dir=dirService.selectWithFirstChildrenById(dId);

        StringBuilder responseText=new StringBuilder();
        responseText.append("<div class=\"btn-group\"><button class=\"btn btn-default\" id=\"dir_move_btn\" d_id=\"");
        responseText.append(dId);
        responseText.append("\">移入</button><button class=\"btn btn-default\" id=\"dir_move_back_btn\" d_id=\"");
        if(dir.getParent()!=null){
            responseText.append(dir.getParent());
        }else {
            responseText.append(dir.getdId());
        }
        responseText.append("\">返回上一级</button><button type=\"button\" id=\"dir_move_form_close_btn\" class=\"btn btn-default\">取消</button></div>");

        List<Dir>childrenDirList=dir.getChildrenDirList();
        for (Dir child:childrenDirList){
            if(child.getdId()!=srcDId){
                responseText.append("<li class=\"list-group-item\"><button class=\"btn btn-default dir_move_enter_btn\" d_id=\"");
                responseText.append(child.getdId());
                responseText.append("\">打开</button>");
                responseText.append(child.getName());
                responseText.append("</li>");
            }
        }
        return responseText.toString();
    }

}
