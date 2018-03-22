package org.redrock.web.courseApi.been;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Course implements Comparable {
    @SerializedName("hash_day")
    private int hashDay;
    @SerializedName("hash_lesson")
    private int hashLesson;
    @SerializedName("begin_lesson")
    private int beginLesson;
    @SerializedName("course_num")
    private String courseNum;
    private String day;
    private String lesson;
    private String course;
    private String teacher;
    private String classroom;
    private String rawWeek;
    private String weekModel;
    private int weekBegin = 99;
    private int weekEnd = 0;
    private String type;
    private int period = 2;
    private ArrayList<Integer> week = new ArrayList<>();

    public Course() {

    }

    public Course(Course beforeCourse, String[] courseStrArray) {
        for(int i=0;i<courseStrArray.length;i++){
            System.out.println(courseStrArray[i]);
        }
        this.course = beforeCourse.getCourse();
        this.type = beforeCourse.getType();
        this.courseNum = beforeCourse.getCourseNum();
        this.teacher = courseStrArray[1];
        courseTime(courseStrArray[2]);
        this.classroom = courseStrArray[3];
    }

    public Course(String[] courseStrArray) {
        for(int i=0;i<courseStrArray.length;i++){
            System.out.println(courseStrArray[i]);
        }
        String[] courseInfo = courseStrArray[2].split("-");
        this.course = courseInfo[courseInfo.length-1];
        this.courseNum = courseInfo[0];
        this.type = courseStrArray[3];
        this.teacher = courseStrArray[6];
        courseTime(courseStrArray[7]);
        this.classroom = courseStrArray[8];
    }

    public void courseTime(String text) {
        //星期5第1-2节 1-6周,8-17周
        Pattern firstPattern = Pattern.compile("星期(\\d).*?第(.*?)节(.*)");
        Matcher firstMatcher = firstPattern.matcher(text);
        if (firstMatcher.find()) {
            int rawDay = Integer.parseInt(firstMatcher.group(1));
            this.day = getWeekChinese(rawDay);
            this.hashDay = rawDay - 1;
            lessonNum(firstMatcher.group(2));
            String rawWeekStr = firstMatcher.group(3);
            if (rawWeekStr.contains("单周")) {
                this.weekModel = "single";
                this.rawWeek = rawWeekStr.replaceAll("单周", "");
            } else {
                this.weekModel = "all";
                this.rawWeek = rawWeekStr;
            }
            if (!rawWeek.startsWith(" ")) {
                rawWeek = " " + rawWeek;
            }
            Pattern secondPattren = Pattern.compile(",(\\d+)周|(\\s\\d+)周|(\\d+-\\d+)周");
            Matcher secondMatcher = secondPattren.matcher(rawWeek);
            while (secondMatcher.find()) {
                String str = secondMatcher.group(0);
                Pattern thirdPattern = Pattern.compile("\\d+");
                if (str.contains("-")) {
                    Matcher thirdMatcher = thirdPattern.matcher(str);
                    if (thirdMatcher.find()) {
                        int begin = Integer.parseInt(thirdMatcher.group());
                        if (this.weekBegin > begin) {
                            this.weekBegin = begin;
                        }
                        if (thirdMatcher.find()) {
                            int end = Integer.parseInt(thirdMatcher.group());
                            if (this.weekEnd < end) {
                                this.weekEnd = end;
                            }
                            int model = 1;
                            if ("single".equals(weekModel)) {
                                model = 2;
                            }
                            for (int i = begin; i <= end; i += model) {
                                this.week.add(i);
                            }
                        }
                    }
                } else {
                    Matcher thirdMatcher = thirdPattern.matcher(str);
                    if (thirdMatcher.find()) {
                        int weekNum = Integer.parseInt(thirdMatcher.group());
                        if (this.weekBegin > weekNum) {
                            weekNum = weekBegin;
                        }
                        if (this.weekEnd < weekNum) {
                            weekNum = weekNum;
                        }
                        this.week.add(Integer.parseInt(thirdMatcher.group()));
                    }
                }
            }
            rawWeek = rawWeek.replaceFirst(" ", "");
            Collections.sort(week);
        }
    }

    private void lessonNum(String text) {
        switch (text) {
            case "1-2":
                this.hashLesson = 0;
                this.beginLesson = 1;
                this.lesson = "一二节";
                break;
            case "3-4":
                this.hashLesson = 1;
                this.beginLesson = 3;
                this.lesson = "三四节";
                break;
            case "5-6":
                this.hashLesson = 2;
                this.beginLesson = 5;
                this.lesson = "五六节";
                break;
            case "7-8":
                this.hashLesson = 3;
                this.beginLesson = 7;
                this.lesson = "七八节";
                break;
            case "9-10":
                this.hashLesson = 4;
                this.beginLesson = 9;
                this.lesson = "九十节";
                break;
            case "11-12":
                this.hashLesson = 5;
                this.beginLesson = 11;
                this.lesson = "十一十二节";
                break;
        }
    }

    public String getWeekChinese(int day) {
        switch (day) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期天";
        }
        return null;
    }

    @Override
    public int compareTo(Object object) {
        if (object instanceof Course) {
            Course course = (Course) object;
            if (this.hashDay > course.getHashDay()) {
                return 1;
            }
            if (this.hashDay < course.getHashDay()) {
                return -1;
            }
            if (this.hashLesson > course.getHashLesson()) {
                return 1;
            }
            if (this.hashLesson < course.getHashLesson()) {
                return -1;
            }
        }
        return 0;
    }
}
