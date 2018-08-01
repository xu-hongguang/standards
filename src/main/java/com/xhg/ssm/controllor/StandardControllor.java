package com.xhg.ssm.controllor;

import com.xhg.ssm.entity.Page;
import com.xhg.ssm.entity.Standard;
import com.xhg.ssm.entity.Tool;
import com.xhg.ssm.service.StandardService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Controller
public class StandardControllor {

    @Autowired
    private StandardService service;

    /**
     * 分页显示数据
     *
     * @param likeName
     * @param pageNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String showSearch(@RequestParam(value = "std_num", required = false) String likeName,
                             @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                             Model model) {
//        获取分页工具类
        Page<Standard> pager = service.selectByLike(likeName, pageNo, 4);

        if (likeName != null) {
            model.addAttribute("likeName", likeName);
        }
        model.addAttribute("pager", pager);

        return "standards";
    }

    @RequestMapping(value = "isExists")
    @ResponseBody
    public Object stuNumExists(@RequestParam String stuNum) {
        int count = service.getCounts(stuNum);
        if (count > 0) {
            return "err";
        } else {
            return "ok";
        }
    }

    /**
     * 跳转到添加页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView addPage(ModelAndView modelAndView) {
        modelAndView.addObject("standard", new Standard());
        modelAndView.setViewName("/add");
        return modelAndView;
    }

    /**
     * 添加标准信息
     *
     * @param multipartFile
     * @param standard
     * @param errors
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/addStandard", method = RequestMethod.POST)
    public String addStandard(@RequestParam("multipartFile") MultipartFile multipartFile,
                              @Valid Standard standard, Errors errors,
                              HttpServletRequest request, RedirectAttributes model, Model model1) {

//        判断标准号是否重复
        List<Standard> standards = service.selectAllStandard();
        for (Standard standard1 : standards) {
            if (standard.getStd_num().equals(standard1.getStd_num())) {
                model1.addAttribute("result", "<script>alert('标准号已存在！');</script>");
                return "add";
            }
        }

//        判断是否选择了附件
        if (multipartFile.isEmpty()) {
            model1.addAttribute("file", "请选择附件！");
            return "add";
        }

        standard.setRelease_date(new Date());
//        获取日期并转换类型
        String date = request.getParameter("impl_date");
//        使用工具类转换日期
        Date implDate = Tool.formatDate(date, "yyyy-MM-hh");
        standard.setImpl_date(implDate);


//        表单校验
        if (errors.hasErrors()) {
            return "add";
        } else {

//            上传文件并设置文件路径
            try {
                standard.setPackage_path(Tool.uploadFile(multipartFile, request));
            } catch (IOException e) {
                model1.addAttribute("result", "<script>alert('文件上传失败！');</script>");
                e.printStackTrace();
                return "add";
            }

            if (service.insertSelective(standard) > 0) {
                model.addFlashAttribute("result", "<script>alert('保存成功！');</script>");
            } else {
                model.addFlashAttribute("result", "<script>alert('保存失败！');</script>");
            }
            return "redirect:/list";
        }

    }



    /**
     * 跳转到下载页面
     *
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/download/{id}")
    public String download(@PathVariable("id") int id, Model model, HttpSession session) {
        Standard standard = service.selectByPrimaryKey(id);
        String path1 = session.getServletContext().getRealPath("uploads/") + standard.getPackage_path();

        /*
        将下载附件的ID 和 源文件路径发送到前台页面
         */
        model.addAttribute("id", id);
        model.addAttribute("path1", path1);

        return "download";
    }

    /**
     * 下载附件
     *
     * @param id
     * @param path1
     * @param path2
     * @param model
     * @return
     */
    @RequestMapping("/downloadFile")
    public String downloadFile(@RequestParam("id") int id,
                               @RequestParam("path1") String path1,
                               @RequestParam("path2") String path2,
                               Model model) {
        System.out.println(path1);
        System.out.println(path2);
        Standard standard = service.selectByPrimaryKey(id);
        try {
            if (Tool.copyFile(path1, path2 + File.separator + standard.getPackage_path())) {
                model.addAttribute("result", "<script>alert('下载成功！');</script>");
                model.addAttribute("path2", path2);
            } else {
                model.addAttribute("result", "<script>alert('下载失败（文件夹中有重复名文件）！');</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "download";
    }



    /**
     * 新版下载功能
     *
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/download")
    public void downLoad(@RequestParam(value = "id") int id, HttpServletRequest request,
                         HttpServletResponse response) {
        Standard standard = service.selectByPrimaryKey(id);
//        文件全路径
        String filename = request.getSession().getServletContext().getRealPath("uploads/") +
                standard.getPackage_path();
        System.out.println("文件路径为:"   + filename);

//        调用工具类下载文件方法
        Tool.downloadFile(request, response, filename);
    }

}
