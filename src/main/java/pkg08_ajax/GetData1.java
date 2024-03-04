package pkg08_ajax;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "GetData1", value = "/getData1")
public class GetData1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1시간마다 갱신되는 전국 날씨 정보
        String spec = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";

        //url 객체
        URL url = new URL(spec);

        //http URL Connection Object
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String line = null;

        while ((line = in.readLine()) != null) {
            builder.append(line);

            System.out.println(builder.toString());
        }

        response.setContentType("application/xml; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.print(builder.toString());
        out.flush();
        out.close();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
