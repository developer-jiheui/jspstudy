package pkg07_Cookie;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "SaveCookie", value = "/saveCookie")
public class SaveCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //요청 및 요청 파라미터
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name"); //아무것도 입력안하고 넘기면 ""이 넘어감 null 아님
        String email = request.getParameter("email");

        //쿠키 만들기 (쿠키 이름, 유지시간, 저장 경로)
        Cookie cookie1 = new Cookie("name", URLEncoder.encode(name,"UTF-8"));       //쿠키 값은 String 타입이다. 공백 등 쿠키값으로 사용할 수 없는 문자가 있어서 인코딩 후 저장한다.
        Cookie cookie2 = new Cookie("email", URLEncoder.encode(email,"UTF-8"));


        //쿠키 유지 시간
        cookie1.setMaxAge(60*60*24*15); //15일간 유지됨; 초단위
        //cookie2.setMaxAge(0);                 //생략하면 세션 쿠키가 된다(브라우저를 닫으면 지워지는 크키)

        //쿠키 저장경로; 주소에따라 다름; 생략가능. 생략하면 컨텍스트 패스가 졍로로 사용된다.
        cookie1.setPath("/servlet");
        cookie2.setPath("/servlet/saveCookie"); //URL mapping
        //쿠키 저장 (응답)
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        //readCookie 서블릿으로 이동하기
        //response.sendRedirect("/servlet/readCookie");
        response.sendRedirect(request.getContextPath() + "/readCookie");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request, response);
    }
}
