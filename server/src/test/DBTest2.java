package test;

import com.nfit.yaoliusan.myblog.utils.DBHelper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://mvnrepository.com/artifact/org.antlr/antlr4-runtime/4.7.2

public class DBTest2 {
    public static void main(String[] args) throws Exception {
        Connection conn = DBHelper.getConnection();

        String sql = "insert into post (title, content, author) values (?, ?, ?)";
        int r = new QueryRunner().update(conn,
                sql,
                "helloworld",
                "many years ago,......",
                "xxyz");
        System.out.println(r);

        DbUtils.closeQuietly(conn);
    }
    public void deleteDemo () throws Exception {
        Connection conn = DBHelper.getConnection();

        int r = new QueryRunner().update(conn, "delete from post where id = ?", 3);
        System.out.println(r);

        DbUtils.closeQuietly(conn);
    }
    public void queryDemo () throws Exception {
        final Connection conn = DBHelper.getConnection();

        final QueryRunner runner = new QueryRunner();
        final List<Map<String, Object>> posts =
                runner.query(conn, "select * from post where id > ?", new MapListHandler(), 2);

        DbUtils.closeQuietly(conn);

        for (Map<String, Object> post : posts) {
            System.out.println("Title: " + post.get("title"));
        }
    }
    public void jdbcDiff () {
        List<Map<String, Object>> posts = new ArrayList<>();
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from post where id > ?")) {
            ps.setInt(1, 2);
            try (ResultSet res = ps.executeQuery()) {
                while (res.next()) {
                    Map<String, Object> post = new HashMap<>();
                    post.put("title", res.getString(2));
                    post.put("title", res.getString(2));
                    post.put("title", res.getString(2));
                    post.put("title", res.getString(2));
                    post.put("title", res.getString(2));
                    post.put("title", res.getString(2));
                }
            }
        } catch (Exception ex) {

        }
    }
}
