package org.redrock.web.courseApi.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.redrock.web.courseApi.been.Course;
import org.redrock.web.courseApi.been.CourseInfo;
import org.redrock.web.courseApi.util.SendUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CourseServlet", urlPatterns = "/course")
public class CourseServlet extends HttpServlet {

    private Gson gson = null;

    @Override
    public void init() throws ServletException {
        gson = new Gson();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        doGet((HttpServletRequest) req, (HttpServletResponse) res);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long date1 = new Date().getTime();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json; charset=utf-8");
        String result = null;
        String stuNum = request.getParameter("stu_num");
        CourseInfo courseInfo = null;
        if (stuNum != null) {
            courseInfo = new CourseInfo(stuNum);
        } else {
            courseInfo = CourseInfo.parameterError();
        }
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream(), "UTF-8"
                )
        );
        result = gson.toJson(courseInfo);
        writer.write(result);
        writer.flush();
        writer.close();
        long date2 = new Date().getTime();
        System.out.println(date2 - date1);
    }
}
