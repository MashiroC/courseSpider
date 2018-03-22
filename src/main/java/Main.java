import org.redrock.web.courseApi.been.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
//    public static void main(String[] args) {
//        ArrayList<Course> courses = new ArrayList<>();
//        String url = "http://jwzx.cqupt.congm.in/jwzxtmp/kebiao/kb_stu.php?xh=201721157";
//        String str = sendPost(url);
//        Pattern pattern = Pattern.compile("<tbody>(.*?)</tbody>");
//        Matcher matcher = pattern.matcher(str);
//        while (matcher.find()) {
//            Matcher matcher1 = Pattern.compile("<tr>(.*?)</tr>").matcher(matcher.group(1));
//            while (matcher1.find()) {
//                Course course = null;
//                if (matcher1.group(1).split("</td>").length > 3) {
//                    course = new Course(matcher1.group(1));
//                } else {
//                    course = new Course(courses.get(courses.size()-1),matcher1.group(1));
//                }
//                courses.add(course);
//            }
//        }
//        Collections.sort(courses);
//        for(int i=0;i<courses.size();i++){
//            System.out.println(courses.get(i));
//        }
//    }
//
//    public static String sendPost(String urlStr) {
////        StringBuilder parameter = new StringBuilder();
////        for (Map.Entry<String,String> entry : info.entrySet()) {
////            parameter.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
////        }
////        parameter.delete(parameter.length()-1,parameter.length());
//        StringBuilder sb = new StringBuilder();
//        try {
//            URL url = new URL(urlStr);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setDoInput(true);
//            con.setDoOutput(true);
//            con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
////            con.setRequestProperty("Accept-Encoding", "gzip, deflate");
//            con.setRequestProperty("Accept-Language", "zh-TW,zh-CN;q=0.9,zh;q=0.8");
//            con.setRequestProperty("Cache-Control", "max-age=0");
//            con.setRequestProperty("Connection", "keep-alive");
//            con.setRequestProperty("Host", "jwzx.cqupt.congm.in");
//            con.setRequestProperty("Upgrade-Insecure-Requests", "1");
//            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(
////                            con.getOutputStream(),"utf-8"
////                    )
////            );
//            con.connect();
////            writer.write(parameter.toString());
////            writer.flush();
////            writer.close();
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(
//                            con.getInputStream(), "UTF-8"
//                    )
//            );
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
public static void main(String[] args) {
//    String str ="<td>王婷</td>\n" +
//            "                        <td>星期5 第5-6节1-6周,8-16周</td>\n" +
//            "                        <td>4203</td>";
//    Matcher matcher = Pattern.compile("<.*?>").matcher(str);
//    str = matcher.replaceAll("_");
//    String[] a = str.split("_+");
//    System.out.println(str);
//    System.out.println(a.length);
//    for(int i=0;i<a.length;i++){
//        System.out.println(
//                a[i]
//        );
//    }
}
}
