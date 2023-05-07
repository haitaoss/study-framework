package cn.haitaoss.web.servlet;
/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-01-19 16:29
 *
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BaserServlet", value = "/BaserServlet")
public class BaserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        // 设置响应头信息
        resp.setHeader("customerHeader", "hello world");
        System.out.println();
    }
}
