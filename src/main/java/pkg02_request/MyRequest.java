package pkg02_request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = "MyRequest", value = "/request")
public class MyRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF=8");
        String strNum1 = request.getParameter("number");
        int number = 0;
        if(strNum1 != null && !strNum1.isEmpty())
            number = Integer.parseInt(strNum1);
        System.out.println(number);

        String strNumber2 = request.getParameter("number2");
        Optional<String> opt = Optional.ofNullable(strNumber2);
        double number2 = Double.parseDouble(opt.orElse("0").isEmpty() ? "0" : opt.orElse("0"));
        System.out.println(number2);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobbies");
        String[] mobile = request.getParameterValues("mobile");

        System.out.println(name);
        System.out.println(email);
        System.out.println(gender);
        System.out.println(Arrays.toString(hobbies));
        System.out.println(Arrays.toString(mobile));
    }
}
