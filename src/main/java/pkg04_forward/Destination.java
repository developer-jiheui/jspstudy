package pkg04_forward;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Destination", value = "/destination")
public class Destination extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 요청
    request.setCharacterEncoding("UTF-8");

    String luggage = request.getParameter("luggage");

    // 응답
    response.setContentType("text/html; charset=UTF-8");

    PrintWriter out = response.getWriter();

    out.println("<script>");
    out.println("alert('목적지에 도착했습니다. 수화물 " + luggage + " 도착했습니다.');");
    out.println("</script>");
    out.flush();
    out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
