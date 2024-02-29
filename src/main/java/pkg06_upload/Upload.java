package pkg06_upload;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "Upload", value = "/upload")
public class Upload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 업로드 경로 (톰캣 내부경로)
        String uploadPath = request.getServletContext().getRealPath("upload");


        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }



        Collection<Part> parts = request.getParts();
        String originalFileName = null;
        String fileSystemName = null;
        String fileListHtml = "";

        for (Part part : parts
        ) {
            System.out.println(part.getName());
            if (part.getHeader("Content-Disposition").contains("filename")) {
                if (part.getSize() > 0) {
                    originalFileName = part.getSubmittedFileName();
                }
            }
            if (originalFileName != null) {
                int point = originalFileName.lastIndexOf(".");
                String fileType = originalFileName.substring(point);
                String fileName = originalFileName.substring(0, point);

                fileSystemName = fileName + "_" + System.currentTimeMillis() + fileType;

//                String[] splitStr= originalFileName.split(".");
//                for (String str: splitStr
//                     ) {
//                    System.out.println("str : "+ str);
//                }

                //fileSystemName = originalFileName.split(".")[0] + "_" + System.currentTimeMillis()+ originalFileName.split(".")[1];
            }
            if (fileSystemName != null) {
                part.write(uploadPath + File.separator + fileSystemName);
            }
        }
        List<File> fileList = List.of(uploadDir.listFiles());
         for (File file: fileList
                     ) {
             if(file.getName()!=null){
                    String savedFileName = file.getName();
                    int index1 = savedFileName.lastIndexOf("_");
                    int indext2 = savedFileName.lastIndexOf(".");
                    fileListHtml += "<div><a href =\"/servlet/download?filename="+savedFileName+"\">"
                            +savedFileName.substring(0,index1)+ savedFileName.substring(indext2)+"</a></div>";
                }
             }


        //응답
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"ko\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Insert title here</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div><a href =\"/servlet/pkg06_upload/newFile.html\">입력폼으로 돌아가기</a></div>");
        out.println("<hr>");
        out.println("<div>첨부파일명 : " + originalFileName + "</div>");
        out.println("<div>저장파일명 : " + fileSystemName + "</div>");
        out.println("<div>저장경로 : " + uploadPath + "</div>");
        out.println("<hr>");
        out.println(fileListHtml);
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
