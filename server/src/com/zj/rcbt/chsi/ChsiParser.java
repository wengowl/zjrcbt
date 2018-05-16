package com.zj.rcbt.chsi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChsiParser {

    private Logger log = LogManager.getLogger(this.getClass());

    public Chsi getContentFromChsi(String code) {
        Chsi chsi = new Chsi();
        String url = "http://www.chsi.com.cn/xlcx/bg.do?vcode=" + code;
        try {

          Document document=  putCode(url,code);


            Element id = document.getElementById("fixedPart");

            if (id==null){
                Elements div = document.getElementsByClass("div2");
                id = div.get(0);
                Elements tables = id.select("table");
                Elements trs1 = tables.get(0).select("tr");
                chsi.setSex(trs1.get(1).select("td").get(0).text());
                String birth = trs1.get(1).select("td").get(1).text();

               String graduatedate=(trs1.get(2).select("td").get(1).text());
               chsi.setEducation(trs1.get(3).select("td").get(1).text());
                System.out.println(graduatedate);



                Elements trs2 = tables.get(1).select("tr");

                chsi.setSchool(trs2.get(0).select("td").get(0).text());

                chsi.setMajor(trs2.get(1).select("td").get(0).text());

                chsi.setXingshi(trs2.get(1).select("td").get(1).text());
//                String s = graduatedate.substring(8, 18);
//                System.out.println(s);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日");
                Date ss = null;
                try {
                    ss = format1.parse(graduatedate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
                String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
                chsi.setGraduatetime(time);
                Date a = null;

                    a = format1.parse(birth);



                chsi.setBirthdate(format0.format(a.getTime()));



            }else {
                Elements tables = id.select("table");
                Elements trs1 = tables.get(0).select("tr");
                chsi.setSex(trs1.get(1).select("td").get(1).text());
                chsi.setId(trs1.get(1).select("td").get(3).text());

                chsi.setMingzu(trs1.get(2).select("td").get(1).text());

                String birth = trs1.get(2).select("td").get(3).text();


                Elements trs2 = tables.get(1).select("tr");

                chsi.setSchool(trs2.get(0).select("td").get(1).getElementsByClass("cnt1").text());
                chsi.setEducation(trs2.get(0).select("td").get(3).text());

                chsi.setMajor(trs2.get(2).select("td").get(1).text());

                chsi.setXingshi(trs2.get(3).select("td").get(1).text());

                String graduation = trs2.get(4).select("td").get(3).getElementsByClass("cnt1").text();
                String s = graduation.substring(8, 18);
                System.out.println(s);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日");
                Date ss = null;

                    ss = format1.parse(s);


                SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
                String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
                chsi.setGraduatetime(time);


                Date a = null;

                    a = format1.parse(birth);



                chsi.setBirthdate(format0.format(a.getTime()));
            }


        } catch (Exception e) {
            chsi.setError(e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
        }

         return chsi;
    }


    public Document putCode(String url,String code) throws IOException {

        Connection.Response rs = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0").timeout(5000).method(Connection.Method.POST).execute();
        Document document = rs.parse();
        Element  chknum =document.getElementById("CHKNUM");
        if (chknum !=null){
            log.info("*******************开始检测验证码******************");
            Element getxueli=document.getElementById("getXueLi");
            Elements imgsrc = getxueli.select("img");
            System.out.println("imgsrc size "+imgsrc.size());
            String imgcode = imgsrc.get(0).attr("src");
            System.out.println("imgcode "+imgcode);
            String vcode = imgcode.substring(imgcode.lastIndexOf("=")+1);
            System.out.println("验证码 "+vcode);
            Map<String, String> datas=new HashMap<>();
            for(Element e:getxueli.getAllElements()){
                if(e.attr("name").equals("cap")) {
                    e.attr("value", vcode);//设置验证码
                    System.out.println((e.attr("name")+"     "+e.attr("value")));
                }

                if(e.attr("name").length()>0) {//排除空值表单属性
                    if (e.attr("name").equals("getXueLi")){
                        continue;
                    }
                    System.out.println((e.attr("name") +":"+ e.attr("value")));
                    datas.put(e.attr("name"), e.attr("value"));
                }
            }

            url = "http://www.chsi.com.cn/xlcx/yzm.do";
//            url ="http://www.chsi.com.cn/xlcx/yzm.jsp";

            Connection newconnection = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0").method(Connection.Method.POST).timeout(5000).ignoreContentType(true);
            Connection.Response response=newconnection.data(datas).cookies(rs.cookies()).execute();
            String url1="http://www.chsi.com.cn/xlcx/bg.do?vcode="+code+"&srcid=bgcx&Submit=查询";
            Connection.Response response1=Jsoup.connect(url1).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0").method(Connection.Method.GET).timeout(5000).ignoreContentType(true).cookies(response.cookies()).execute();
//            System.out.println(response1.body());
            document =response1.parse();

            return document;
        }

        return document;



    }
}
