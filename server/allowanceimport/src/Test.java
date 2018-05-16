import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String args[]){
        AllowanceDB allowanceDB = new AllowanceDB();
//        123
        File file = new File("D:\\工作\\文档\\2015年上租房补贴兑现记录A1.xls");
        String batch = "2015-06";
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



        List<AllowanceBean> allowanceBeans = new ArrayList<>();
        allowanceDB.importExcel(file,allowanceBeans,batch);
        for (AllowanceBean allowanceBean:allowanceBeans){
            System.out.println(allowanceBean.getCompany());

        }


        allowanceDB.setPasswd("123456");
        allowanceDB.setUser("rcbt");
        allowanceDB.setUrl("jdbc:mysql://120.55.45.237:3306/rcbt?useUnicode=true&characterEncoding=utf-8&useSSL=false");
         System.out.println(allowanceBeans.size());
//        allowanceDB.insertInto(allowanceBeans);
        allowanceDB.update(allowanceBeans);
    }
}
