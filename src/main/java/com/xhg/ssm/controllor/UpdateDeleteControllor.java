package com.xhg.ssm.controllor;

import com.xhg.ssm.entity.Standard;
import com.xhg.ssm.entity.Tool;
import com.xhg.ssm.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class UpdateDeleteControllor {

    @Autowired
    private StandardService service;

    /**
     * 跳转到修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {

        Standard standard = service.selectByPrimaryKey(id);
//        日期转换为字符串
        String date = Tool.formatDateString(standard.getImpl_date(),"yyyy-MM-dd");

        model.addAttribute("implDate", date);
        model.addAttribute("standard", standard);
        return "update";
    }

    /**
     * 修改标准
     *
     * @param multipartFile
     * @param standard
     * @param errors
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/updateStandard")
    public String updateStandard(@RequestParam("multipartFile") MultipartFile multipartFile,
                                 @Valid Standard standard, Errors errors,
                                 RedirectAttributes model,Model model1, HttpServletRequest request) {

//        获取日期并转换类型
        String date = request.getParameter("impl_date");

//        表单校验
        if (errors.hasErrors()) {
            model1.addAttribute("implDate",date);
            return "update";
        } else {
//        使用工具类转换日期
            Date implDate = Tool.formatDate(date,"yyyy-MM-dd");
            standard.setImpl_date(implDate);

//            上传文件并设置文件路径
            try {
                standard.setPackage_path(Tool.uploadFile(multipartFile,request));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (service.updateByPrimaryKeySelective(standard) > 0) {
                model.addFlashAttribute("result", "<script>alert('修改成功！');</script>");
            } else {
                model.addFlashAttribute("result", "<script>alert('修改失败！');</script>");
            }
            return "redirect:/list";
        }

    }

    /**
     * 批量删除功能
     *
     * @param ids
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/delete/{ids}")
    public String deleteStandard(@PathVariable("ids") String ids, HttpServletRequest request, Model model) {

//        将前端发来的数据转换为数组
        String[] ides = ids.split(",");
        int id;
        for (int i = 0; i < ides.length; i++) {

            id = Integer.parseInt(ides[i].trim());

            Standard standard = service.selectByPrimaryKey(id);
            if (standard.getPackage_path() != null) {
//               删除标准后并把对应的文件删除
                File file = new File(request.getSession().getServletContext().getRealPath("uploads/") + standard.getPackage_path());
                if (file.exists()) {
                    file.delete();
                }
            }

            if (service.deleteByPrimaryKey(id) > 0) {
                model.addAttribute("result", "<script>alert('您选择的标准已删除成功！');</script>");
            } else {
                model.addAttribute("result", "<script>alert('删除失败！');</script>");
            }

        }

        return "forward:/list";
    }

}
