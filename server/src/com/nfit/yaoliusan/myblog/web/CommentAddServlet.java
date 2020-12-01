package com.nfit.yaoliusan.myblog.web;


import com.google.gson.Gson;
import com.nfit.yaoliusan.myblog.bean.Comment;
import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.dao.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment/add")
@MultipartConfig
public class CommentAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postid");
        String author = req.getParameter("author");
        String content = req.getParameter("content");
        try {
            Post post = new Post();
            post.setId(Long.parseLong(postId));
            Comment comment = new CommentDAO().addComment(new Comment(content, author, post));
            resp.getWriter().write(new Gson().toJson(comment));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加错误");
        }
    }
}
