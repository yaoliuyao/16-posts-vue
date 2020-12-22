package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ctest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("session: " + session.getId());

        if (session.getAttribute("aaa") == null) {
            System.out.println("Setting session...");
            session.setAttribute("aaa", "2222");
        } else {
            System.out.println("Already... " + session.getAttribute("aaa"));
        }
    }
}
