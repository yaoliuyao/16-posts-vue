package com.nfit.yaoliusan.myblog.dao;

import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.utils.DBHelper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class PostDAO {

    /**
     * 获取所有文章
     *
     * @return 所有的 Posts
     * @throws Exception 所有异常
     */
    public List<Post> getAllPosts() throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "select id, title, content, author, cover, created from post order by created Desc";
        try {
            return new QueryRunner().query(
                    conn, sql, new BeanListHandler<Post>(Post.class));
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public List<Post> getPostsByPage(int currentPage, int limit) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "select * from " +
                "(select row_number() over (order by created desc) as rn, id, title, content, author, cover, created from post) as s " +
                "where rn between ? and ?";
        try {
            return new QueryRunner().query(
                    conn, sql, new BeanListHandler<Post>(Post.class),
                    (currentPage - 1) * limit + 1,
                    currentPage * limit);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public int getPostsMount() throws Exception {
        Connection conn = DBHelper.getConnection();
        try {
            String sql = "select count(*) from post";
            return (int) new QueryRunner().query(conn, sql, new ScalarHandler<>());
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 根据 PostId 获取文章
     *
     * @param id Post 的主键
     * @return 某篇 Post
     */
    public Post getPostById(int id) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "select id, title, content, cover, author, likes, created from post where id = ?";
        try {
            return new QueryRunner().query(
                     conn, sql, new BeanHandler<Post>(Post.class), id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 添加文章
     *
     * @param post 要插入的文章
     * @return 带 Id 的文章
     */
    public Post addPost(Post post) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "insert into post (title, content, author, cover) values (?, ?, ?, ?)";
        Object[] params = {
                post.getTitle(), post.getContent(), post.getAuthor(), post.getCover()
        };
        try {
            QueryRunner run = new QueryRunner();
            BigDecimal res = run.insert(conn, sql, new ScalarHandler<BigDecimal>(), params);
            post.setId(res.longValue());
            return post;
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public int like(String id) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "update post set likes = likes + 1 where id = ?";
        try {
            QueryRunner runner = new QueryRunner();
            runner.update(conn, sql, id);
            sql = "select likes from post where id = ?";
            return (int) runner.query(conn, sql, new ScalarHandler<>(), id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public void delete(String id) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "delete from post where id = ?";
        try {
            new QueryRunner().update(conn, sql, id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    public static void main(String[] args) throws Exception {
        PostDAO postDAO = new PostDAO();
        System.out.println(postDAO.getPostsMount());
    }
}
