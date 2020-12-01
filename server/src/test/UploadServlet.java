package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/upload")
@MultipartConfig(
        maxFileSize = -1,
        maxRequestSize = -1,
        fileSizeThreshold = 100000000
)
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/test/upload/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part shuaige = req.getPart("shuaige");
        // req.getParts()
        // 保存为啥名字？
        // 保存到哪里？
        // 怎么限制大小，保证安全？
        System.out.println(
                "Name: " + shuaige.getName() + "\n" +  // 临时名字
                "Type: " + shuaige.getContentType() + "\n" +
                "Size: " + shuaige.getSize() + "\n" +
                "SubName: " + shuaige.getSubmittedFileName() + "\n" + // 源文件的名字
                "Headers: " + shuaige.getHeaderNames()
        );

        // 保存为什么名字
        String savedName =
                new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date())
                + "-"
                + shuaige.getSubmittedFileName();
        System.out.println("saved as: " + savedName);

        // 保存到哪里
        String savedDir = getServletContext().getRealPath("/img/");
        new File(savedDir).mkdir();
        System.out.println("SavedDir Dir: " + savedDir);

        shuaige.write(savedDir + savedName);
        resp.sendRedirect(req.getContextPath() + "/test/upload/upload_success.jsp?path=" + savedName);
    }
}
