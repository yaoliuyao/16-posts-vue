package com.nfit.yaoliusan.myblog.web;

import com.nfit.yaoliusan.myblog.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/del")
public class PostDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            new PostDAO().delete(id);
            resp.getWriter().print(id);
        } catch (Exception exception) {
            exception.printStackTrace();
            resp.getWriter().print("-1");
        }
    }
}
