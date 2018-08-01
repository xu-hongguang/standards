package com.xhg.ssm.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 工具类
 *
 * @author 常用的功能
 */
public class Tool {

    private Tool() {
    }

    /**
     * 字符串转日期+1
     *
     * @param date    要转换日期字符串
     * @param pattern 转化格式
     * @return 日期
     */
    public static Date formatDate(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }

    /**
     * 日期转字符串
     *
     * @param date    要转换日期
     * @param pattern 格式
     * @return 字符串
     */
    public static String formatDateString(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @param request       请求
     * @return 返回保存到数据库中的文件名（处理后的文件名）
     */
    public static String uploadFile(MultipartFile multipartFile, HttpServletRequest request) throws IOException {

        String image = null;

//            文件不为空时处理
        if (!multipartFile.isEmpty()) {

//            上传文件部分
            String filePath = request.getSession().getServletContext().getRealPath("uploads");
            File newFile = new File(filePath);
            if (!newFile.exists()) {
                newFile.mkdir();
            }

//            处理文件名重复
//            原始文件名
            String originalFilename = multipartFile.getOriginalFilename();
//            文件后缀名
            String nowFileName = originalFilename.substring(originalFilename.indexOf(".") - 1, originalFilename.length());
//            当前文件名
            image = UUID.randomUUID() + nowFileName;

            String imageName = filePath + "/" + image;
            File file = new File(imageName);
            multipartFile.transferTo(file);
        }
        return image;
    }

    /**
     * 复制文件
     *
     * @param path1 源文件
     * @param path2 复制目标文件
     * @throws IOException 抛出IOException异常
     */
    public static boolean copyFile(String path1, String path2) throws IOException {

        //输入源文件和目标文件
        String[] paths = new String[]{path1, path2};

        // 检查源文件是否存在
        File sourceFile = new File(paths[0]);
        if (!sourceFile.exists()) {
            System.out.println("源文件 " + paths[0] + " 不存在");
            return false;
        }

        //检查目标文件是否已经存在
        File targetFile = new File(paths[1]);
        if (targetFile.exists()) {
            System.out.println("目标文件 " + paths[1] + " 已经存在");
            return false;
        }

        try (
                // 创建输入流
                BufferedInputStream input =
                        new BufferedInputStream(new FileInputStream(sourceFile));

                // 创建输出流
                BufferedOutputStream output =
                        new BufferedOutputStream(new FileOutputStream(targetFile));
        ) {
            // 读出源文件内容并写入目标文件
            int r, numberOfBytesCopied = 0;
            while ((r = input.read()) != -1) {
                output.write((byte) r);
                numberOfBytesCopied++;
            }

            // 输出复制文件的大小
            System.out.println(numberOfBytesCopied + " 字节");

            return true;
        }
    }

    /**
     * 下载文件
     *
     * @param request  请求
     * @param response 响应
     * @param filename 要下载文件的全路径
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String filename) {

        ServletOutputStream out;
        // 得到要下载的文件
        File file = new File(filename);
        try {

//            根据文件名获取文件类型
            String name = request.getServletContext().getMimeType(filename);
            // 设置响应头，控制浏览器下载该文件
            response.setContentType(name);
            // 获得浏览器信息,并处理文件名
            String headerType = request.getHeader("User-Agent").toUpperCase();
            String fileName = null;
            if (headerType.indexOf("EDGE") > 0 || headerType.indexOf("MSIE") > 0 || headerType.indexOf("GECKO") > 0) {
                fileName = URLEncoder.encode(file.getName(), "UTF-8");
            } else {
                fileName = new String(file.getName().replaceAll(" ", "").getBytes("utf-8"), "iso8859-1");
            }

            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + file.length());
            FileInputStream inputStream = new FileInputStream(file);

            out = response.getOutputStream();

            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1) {
                b = inputStream.read(buffer);
                // 写到输出流(out)中
                if (b != -1) {
                    out.write(buffer, 0, b);
                }
            }
            inputStream.close();
            out.close();// 关闭输出流
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
