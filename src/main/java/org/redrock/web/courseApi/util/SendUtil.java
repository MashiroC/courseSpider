package org.redrock.web.courseApi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendUtil {
    public static String sendGet(String urlStr) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            con.setRequestProperty("Accept-Encoding", "gzip, deflate");
            con.setRequestProperty("Accept-Language", "zh-TW,zh-CN;q=0.9,zh;q=0.8");
            con.setRequestProperty("Cache-Control", "max-age=0");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Host", "jwzx.cqupt.congm.in");
            con.setRequestProperty("Upgrade-Insecure-Requests", "1");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
            con.connect();
            reader = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream(), "UTF-8"
                    )
            );
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = sendGet("http://jwzx.cqupt.congm.in/jwzxtmp/kebiao/kb_stu.php?xh=2017211573");
        Pattern getTbodyPattern = Pattern.compile("<tbody>(.*?)</tbody>");
        Matcher getTbodyMatcher = getTbodyPattern.matcher(str);
        while (getTbodyMatcher.find()) {
            Matcher matcher = Pattern.compile("<tr>(.*?)</tr>").matcher(getTbodyMatcher.group(1));
            while (matcher.find()){
                String rawCourseStr = matcher.group(1);
                Matcher replaceMatcher = Pattern.compile(">\\s<").matcher(rawCourseStr);
                rawCourseStr=replaceMatcher.replaceAll("");
                Matcher courseMatcher = Pattern.compile("<.*?>").matcher(rawCourseStr);
                rawCourseStr=courseMatcher.replaceAll("_");
                String[] courseStrArray =rawCourseStr.split("_+");
                    System.out.println(rawCourseStr);
                for (int i=0;i<courseStrArray.length;i++){
                    System.out.println(courseStrArray[i]);
                }
                    System.out.println();
            }
        }
    }
}
