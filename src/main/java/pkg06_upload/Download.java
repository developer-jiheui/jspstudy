package pkg06_upload;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet(name = "Download", value = "/download")
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String fileName = request.getParameter("filename");

        //upload path 알아내기
        String uploadPath = request.getServletContext().getRealPath("upload");

        /*File Download from website */
        File file = new File(uploadPath, fileName);
        System.out.println(file.exists());

        //원본 파일(서버) 입력스트림 생성
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

        //다운로드용 응답 헤더("Content-Disposition: attachment;")
        String savedFileName = fileName;
        int index1 = savedFileName.lastIndexOf("_");
        int indext2 = savedFileName.lastIndexOf(".");
        String originalName = savedFileName.substring(0,index1) + savedFileName.substring(indext2);

        response.setHeader("Content-Disposition", "attachment"); // 다운로드 대화상자 나옴
        response.setHeader("Content-Disposition", "attachment; filename = " + originalName); //다운로드 대화상자 안나옴

        //복사 파일(클라이언트) 출력 스트림 생성
        BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());

        //복사 (서버 -> 클라이언트)
        byte[] b = new byte[1024];
        int readByte = 0;
        while ((readByte = input.read(b)) != -1){
            output.write(b,0,readByte);
        }

        //스트림 닫기
        output.close();
        input.close();

        if (file.exists()) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"ko\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Insert title here</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<hr>");
            out.println("<div>저장파일명 : " + originalName + "이 있습니다.</div>");
            out.println("<div>저장경로 : " + uploadPath + "</div>");
            out.println("<hr>");
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
