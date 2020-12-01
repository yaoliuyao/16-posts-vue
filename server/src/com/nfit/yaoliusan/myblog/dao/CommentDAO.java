package com.nfit.yaoliusan.myblog.dao;

import com.nfit.yaoliusan.myblog.bean.Comment;
import com.nfit.yaoliusan.myblog.utils.DBHelper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class CommentDAO {
    /**
     * 获取所有评论
     *
     * @return 所有的评论列表
     */
    public List<Comment> getCommentsByPostId(int id) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "select id, content, author, created from comment where postid  = ? order by created desc";
        try {
            return new QueryRunner().query(
                    conn, sql, new BeanListHandler<Comment>(Comment.class), id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 添加评论
     *
     * @param comment 要插入的对象
     * @return 返回一个带主键的 Comment 对象
     */
    public Comment addComment(Comment comment) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "insert into comment(postid , author, content) values(?, ?, ?)";
        Object[] params = {
                comment.getPost().getId(), comment.getAuthor(), comment.getContent()
        };
        try {
            // 添加的第三个参数，表名需要返回一个 Long 类型的主键
            // 所以，它返回的就是主键
            QueryRunner runner = new QueryRunner();
            BigDecimal res = runner.insert(conn, sql, new ScalarHandler<BigDecimal>(), params);
            sql = "select id, postid, author, content, created from comment where id = ?";
            return runner.query(conn, sql, new BeanHandler<>(Comment.class), res.longValue());
        } finally {
            // 千万不要忘记关闭
            // 要放到 finally 里，因为 finally 的语句不管有没有异常都会被执行
            DbUtils.closeQuietly(conn);
        }
    }

    public void delete(String id) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "delete from comment where id = ?";
        try {
            new QueryRunner().update(conn, sql, id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }
}
