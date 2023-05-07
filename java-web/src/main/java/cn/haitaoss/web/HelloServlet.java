package cn.haitaoss.web; /**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-01-19 16:32
 *
 */


import cn.haitaoss.web.servlet.BaserServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HelloServlet", value = "/HelloServlet")
public class HelloServlet extends BaserServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.get ...");
        request.setCharacterEncoding("ISO-8859-1");
        String name = request.getParameter("name");
        System.out.println("name = " + name);
        request.setCharacterEncoding("UTF-8");
        String name2 = request.getParameter("name");
        System.out.println("name2 = " + name2);


        response
                .getWriter()
                .println("hello world!!! haitaoss");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
