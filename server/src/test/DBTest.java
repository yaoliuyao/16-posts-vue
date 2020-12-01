package test;

import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.utils.DBHelper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBTest {
    public static void main(String[] args) throws Exception {
        final Connection connection = DBHelper.getConnection();

        // 借助这个对象，做事情 jdbc 操作
        final QueryRunner runner = new QueryRunner();

        // 使用
        runner.execute(connection, "insert into xxx values (?, ?, ?)", "xxx", "111", 222);
        runner.execute(connection, "update xxx set x = ?, y = ?", "xxx", "111");
        runner.execute(connection, "delete from xxx where id = ?", 1);

        // 最后
        DbUtils.closeQuietly(connection);
    }

    public void testSelect() throws Exception {
        final Connection connection = DBHelper.getConnection();

        // 借助这个对象，做事情 jdbc 操作
        final QueryRunner runner = new QueryRunner();

        // 查询 10 条
        // [x, y, z]
        // arraylist
        // {id: 33, name: "44"}
        // List<Post>
        runner.query(connection, "select * from xxx where x = ?",
                (ResultSetHandler<Object>) resultSet -> {
                    List<Object> lst = new ArrayList<>();
                    while (resultSet.next()) {
                        // .....
                        lst.add(null);
                    }
                    return lst;
                }, 1);

        List<Post> posts = runner.query(connection, "select * from xxx where x = ?",
                new BeanListHandler<>(Post.class), 1);

        Post post = runner.query(connection, "select * from xxx where id = ?",
                new BeanHandler<>(Post.class), 1);

        // post [id title content created]
        // new Post() -> post.setXxx(ziduan)
        // POst [id title content created]

        List<Object[]> objects = runner.query(connection, "select * from xxx where x = ?",
                new ArrayListHandler(), 1);

        final List<Map<String, Object>> mapList = runner.query(connection, "select * from xxx where x = ?",
                new MapListHandler(), 1);
    }
}
