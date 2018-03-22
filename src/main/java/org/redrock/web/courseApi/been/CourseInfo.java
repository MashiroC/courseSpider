package org.redrock.web.courseApi.been;

import com.google.gson.annotations.Expose;
import lombok.Data;
import org.redrock.web.courseApi.util.SendUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class CourseInfo {
    private int status;
    private String version;
    private String term;
    private String stuNum;
    private String success;
    private String info;
    private ArrayList<Course> data;
    private int nowWeek;
    private static long termBegin = 1520179200L;
    private static long oneWeek=604800;

    public CourseInfo(){

    }

    public CourseInfo(String num){
        this.status=200;
        this.version="99.99.99";
        this.term="2017-2018学年第2学期";
        this.stuNum=num;
        data = new ArrayList<>();
        String url = "http://jwzx.cqupt.congm.in/jwzxtmp/kebiao/kb_stu.php?xh="+stuNum;
        String str = SendUtil.sendGet(url);
        Pattern getTbodyPattern = Pattern.compile("<tbody>(.*?)</tbody>");
        Matcher getTbodyMatcher = getTbodyPattern.matcher(str);
        while (getTbodyMatcher.find()) {
            Matcher matcher = Pattern.compile("<tr>(.*?)</tr>").matcher(getTbodyMatcher.group(1));
            while (matcher.find()) {
                Course course;
                String rawCourseStr=matcher.group(1);
                Matcher courseMatcher = Pattern.compile("<.*?>").matcher(rawCourseStr);
                rawCourseStr=courseMatcher.replaceAll("_");
                String[] courseStrArray =rawCourseStr.split("_+");
                if (courseStrArray.length>5) {
                    course = new Course(courseStrArray);
                } else {
                    course = new Course(data.get(data.size() - 1), courseStrArray);
                }
                data.add(course);
            }
        }
        Collections.sort(data);

        long nowTime = (long) Math.ceil(new Date().getTime()/1000);
        this.nowWeek= (int) ((nowTime-termBegin)/oneWeek)+1;
    }

    public static CourseInfo parameterError(){
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setSuccess("false");
        courseInfo.setInfo("stuNum not allowed");
        return courseInfo;
    }

}
