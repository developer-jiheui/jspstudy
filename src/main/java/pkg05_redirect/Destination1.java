package pkg05_redirect;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "Destination1", value = "/destination1")
public class Destination1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * redirect
         *
         * 1. 이동할 때 요청과 응답을 모두 전달하지 않는다.
         * 2. 이동 경로를 작성할 때 contextPath 부터 작성해야 한다.
         * 3. 클라이언트는 redirect 경로를 확인할 수 있다.
         * 4. redirect 하는 경우
         *    1) 쿼리 insert
         *    2) 쿼리 update
         *    3) 쿼리 delete
         */

        request.setCharacterEncoding("UTF-8");
        String luggage = request.getParameter("luggage");

        response.sendRedirect("/servlet/destination2?luggage=" + URLEncoder.encode(luggage, "UTF-8"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
