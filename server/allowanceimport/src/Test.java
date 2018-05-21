import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String args[]){
        AllowanceDB allowanceDB = new AllowanceDB();
        allowanceDB.setPasswd("123456");
        allowanceDB.setUser("rcbt");
//        allowanceDB.setUrl("jdbc:mysql://127.0.0.1:3306/rcbt?useUnicode=true&characterEncoding=utf-8&useSSL=false");
//        allowanceDB.setUrl("jdbc:mysql://192.168.0.143:3306/rcbt?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        allowanceDB.setUrl("jdbc:mysql://120.55.45.237:3306/rcbt?useUnicode=true&characterEncoding=utf-8&useSSL=false");
//        123
//        File file = new File("D:\\工作\\文档\\2015年上租房补贴兑现记录A1.xls");
//        String batch = "2015-06";
//781
//        File file = new File("D:\\工作\\文档\\2015下租房补贴兑现记录B.xls");
//        String batch = "2015-12";
// 645
//        File file = new File("D:\\工作\\文档\\2016年下租房补兑现记录表B1.xlsx");
//        String batch = "2016-12";
//201
//        File file = new File("D:\\工作\\文档\\2016上租房补贴兑现记录表.xls");
//        String batch = "2016-06";
//        201
//        File file = new File("D:\\工作\\文档\\2017年第一期本科生租房补贴兑现记录表.xlsx");
//        String batch = "2017-06";
//        859
//        File file = new File("D:\\工作\\文档\\2017年第二期本科生租房补贴兑现记录表.xlsx");
//        String batch = "2017-12";
//                File file = new File("D:\\工作\\文档\\2017年.xlsx");

//        File file = new File("D:\\工作\\文档\\shuoshi\\2016_12_01.xlsx");
//        81
//        File file = new File("D:\\工作\\文档\\shuoshi\\2016_12_02.xlsx");
//        79
//        File file = new File("D:\\工作\\文档\\shuoshi\\2016_12_03.xlsx");
//        65
//        String batch = "2016-12";
//        File file = new File("D:\\工作\\文档\\shuoshi\\2017_06_01.xlsx");
//        21
//        File file = new File("D:\\工作\\文档\\shuoshi\\2017_06_02.xlsx");
//        21
//        String batch = "2017-06";
                File file = new File("D:\\工作\\文档\\shuoshi\\2017_12.xlsx");
////                133
        String batch = "2017-12";


        List<AllowanceBean> allowanceBeans = new ArrayList<>();
//        allowanceDB.importExcel(file,allowanceBeans,batch);
        allowanceDB.importExcelx(file,allowanceBeans,batch);
        for (AllowanceBean allowanceBean:allowanceBeans){
            AllowanceBean x = allowanceDB.find(allowanceBean);
            if (x!=null){
                allowanceBean.setSumMoney(allowanceBean.getSumMoney()+x.getSumMoney());
                allowanceBean.setMonthes(allowanceBean.getMonthes()+x.getMonthes());
            }

        }




         System.out.println(allowanceBeans.size());
        allowanceDB.updatex(allowanceBeans);
       allowanceDB.insertInto(allowanceBeans);
//        allowanceDB.update(allowanceBeans);
    }
}
