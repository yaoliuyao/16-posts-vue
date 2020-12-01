package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/uploadkaoshi")
@MultipartConfig
public class UploadKaoshi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/test/kaoshi.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Part kaoshi = req.getPart("kaoshi");
        String path = "E:/kaoshi/" + kaoshi.getSubmittedFileName();
        kaoshi.write(path);
        resp.sendRedirect("/uploadkaoshi");
    }
}
