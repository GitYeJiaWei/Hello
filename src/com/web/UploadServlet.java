package com.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 上传各种文件图片及保存文件
 */
public class UploadServlet extends HttpServlet {
    //上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
    //上传配置
    private static final int MEMORY_THRESHOLD = 1024*1024*3; //3M
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    public UploadServlet(){
        super();
    }

    public void init() throws ServletException {
        // 初始化代码...
        System.out.println("初始化。。。");
    }

    //处理post请求,
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //检查是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)){
            //如果不是则停止,返回数据给前端
            PrintWriter writer = response.getWriter();
            System.out.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        //配置上传参数
        DiskFileItemFactory factory =new DiskFileItemFactory();
        //设置内存临界值 - - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        //设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录,File.separator为分隔符\\
        //String uploadPath = request.getServletContext().getRealPath("./")+File.separator+UPLOAD_DIRECTORY;
        String uploadPath = "E:"+File.separator+UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir =new File(uploadPath);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }

        try {
        // 解析请求的内容提取文件数据
        @SuppressWarnings("unchecked")
        List<FileItem> formItems =upload.parseRequest(request);

        if (formItems !=null && formItems.size()>0){
            //迭代表单数据
            for (FileItem item:formItems){
                //获取表单属性名字
                String name = item.getFieldName();
                //如果获取的表单信息是普通的文本信息，即通过页面表单形式传递来的字符串
                if (item.isFormField()){
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name,value);
                    System.out.println("name:"+name+"  value:"+value);
                }
                //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
                // 处理不在表单中的字段,
                else{
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File stroeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    item.write(stroeFile);
                    request.setAttribute("message","文件上传成功！");
                }
            }
        }
        } catch (Exception e) {
            //e.printStackTrace();
            request.setAttribute("message","错误信息："+e.getMessage());
        }

        //返回json串给前端
        PrintWriter writer = response.getWriter();
        writer.write("{}" );
        writer.flush();
        writer.close();

        //跳转到 message.jsp
        //request.getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
    }

    //处理get请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
