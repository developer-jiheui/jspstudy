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
import java.net.URLEncoder;

@WebServlet(name = "GetData2", value = "/getData2")
public class GetData2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String traffic= "http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath";

        StringBuilder urlBuilder = new StringBuilder(traffic);

        //decoded key
        String serviceKey = "g+X/1yYy6x+57DuQzSPAR2KwbaHfyTbeauGW95yj4FXXtkXxsuAMyPbIr6yItghvJKtmnRch2A1mqVoo4UVWnQ==";

        //start building url
        urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey,"UTF-8"));
        urlBuilder.append("&searchYear=").append(URLEncoder.encode("2022","UTF-8"));
        urlBuilder.append("&siDo=").append(URLEncoder.encode("1100","UTF-8"));
        urlBuilder.append("&guGun=").append(URLEncoder.encode("1116","UTF-8"));
        urlBuilder.append("&type=").append(URLEncoder.encode("json","UTF-8"));
        urlBuilder.append("&numOfRows=").append(URLEncoder.encode("10","UTF-8"));
        urlBuilder.append("&pageNo=").append(URLEncoder.encode("1","UTF-8"));


        String specification = urlBuilder.toString();
        System.out.println(specification);
        URL url = new URL(specification);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //응답코드 확인
        int responseCode = connection.getResponseCode();
                        //!= httpURLConnection.OK
        if(responseCode !=200){
                throw new RuntimeException("API 응답 실패");
        }

        //문자 입력 스트림
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String outPut = null;

        while((outPut = input.readLine())!= null){
            builder.append(outPut);

            System.out.println(builder);
        }


        //응답데이터 타입 & 인코딩
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.print(builder.toString());
        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
