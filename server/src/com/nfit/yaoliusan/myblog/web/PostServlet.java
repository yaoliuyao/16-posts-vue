package com.nfit.yaoliusan.myblog.web;


import com.google.gson.Gson;
import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.dao.PostDAO;
import com.nfit.yaoliusan.myblog.vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.getPostById(id);
            resp.getWriter().print(ResultVO.ok(post).toJSON());
        } catch (Exception e) {
            e.printStackTrace();
            String err = ResultVO.err(111, e.getLocalizedMessage()).toJSON();
            resp.getWriter().print(err);
        }
    }
}
