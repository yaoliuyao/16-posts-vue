package com.nfit.yaoliusan.myblog.web;

import com.google.gson.Gson;
import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

@WebServlet("/post/add")
@MultipartConfig
public class PostAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Part cover = req.getPart("cover");
        try {
            // 将图片保存，并获取其路径名
            String coverPath = "";
            if (cover != null) {
                coverPath = "/img/" + System.currentTimeMillis() + "-" + cover.getSubmittedFileName();
                cover.write(getServletContext().getRealPath("/") + coverPath);
            }

            // 所有信息入库
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.addPost(new Post(title, content, author, coverPath));

            // 返回数据
            post.setCreated(new Date());
            resp.getWriter().print(new Gson().toJson(post));
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print("-1");
        }
    }
}
