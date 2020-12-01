package test;

import com.google.gson.Gson;
import com.nfit.yaoliusan.myblog.bean.Post;
import com.nfit.yaoliusan.myblog.dao.PostDAO;

import java.util.List;

public class GsonTest {
    public static void main(String[] args) throws Exception {
        List<Post> posts = new PostDAO().getAllPosts();
        String json = new Gson().toJson(posts);
        System.out.println(json);
    }
}
