package pkg04_forward;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "StopOver", value = "/stopover")
public class StopOver extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * forward
         *
         * 1. 이동할 때 요청과 응답을 모두 전달한다.
         * 2. 이동 경로를 작성할 때 contextPath는 제외하고 작성해야 한다. (URLMapping만 작성한다.)
         * 3. 클라이언트는 forward 경로를 확인할 수 없다.
         * 4. forward 하는 경우
         *    1) 단순 이동
         *    2) 쿼리 select
         */

        request.getRequestDispatcher("/destination").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
