
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.All;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class AllowanceDB {
    private String url;
    private String user;
    private String passwd;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        } catch (Exception ex) {
            throw new RuntimeException("database driver load error");
        }

    }


    public static Connection getConnection(String url, String user, String passwd) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, passwd);
        return connection;
    }
    public static void free( PreparedStatement preparedStatement, Connection conn) {

        try {
            if (preparedStatement != null) {

                preparedStatement.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (conn != null) {

                        conn.close();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }


    public void insertInto(List<AllowanceBean> allowanceBeans){
        try {
            Connection connection= AllowanceDB.getConnection(url,user,passwd);

            connection.setAutoCommit(false);
            String sql = "insert into allowance(id_num, begin_time, last_time, sum_money,  monthes, allowancetype, updatetime, bank, bank_card, phone, name, company,batch,graduatetime,education) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            for (AllowanceBean allowanceBean:allowanceBeans) {
                preparedStatement.setString(1, allowanceBean.getIdNum());
                System.out.print(allowanceBean.getIdNum()+"\t");
                preparedStatement.setString(2, allowanceBean.getBeginTime());
                System.out.print(allowanceBean.getBeginTime()+"\t");
                preparedStatement.setString(3, "");
                System.out.print(allowanceBean.getLastTime()+"\t");
                preparedStatement.setInt(4, allowanceBean.getSumMoney());
                System.out.print(allowanceBean.getSumMoney()+"\t");
                preparedStatement.setInt(5, allowanceBean.getMonthes());
                System.out.print(allowanceBean.getMonthes()+"\t");
                preparedStatement.setString(6, allowanceBean.getAllowancetype());
                System.out.print(allowanceBean.getIdNum()+"\t");
                preparedStatement.setString(7, allowanceBean.getUpdatetime());
                System.out.print(allowanceBean.getUpdatetime()+"\t");
                preparedStatement.setString(8, allowanceBean.getBank());
                System.out.print(allowanceBean.getBank()+"\t");
                preparedStatement.setString(9, allowanceBean.getBankCard());
                System.out.print(allowanceBean.getBankCard()+"\t");
                preparedStatement.setString(10, allowanceBean.getPhone());
                System.out.print(allowanceBean.getPhone()+"\t");
                preparedStatement.setString(11, allowanceBean.getName());
                System.out.print(allowanceBean.getName()+"\t");
                preparedStatement.setString(12, allowanceBean.getCompany());
                System.out.print(allowanceBean.getCompany()+"\t");
                preparedStatement.setString(13, allowanceBean.getBatch());
                System.out.print(allowanceBean.getBatch()+"\t");
                preparedStatement.setString(14, allowanceBean.getGraduatetime());
                System.out.print(allowanceBean.getGraduatetime()+"\t");
                preparedStatement.setString(15, allowanceBean.getEducation());
                System.out.print(allowanceBean.getEducation()+"\t");
                System.out.println();
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();

            free(preparedStatement,connection);



        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void update(List<AllowanceBean> allowanceBeans){
        try {
            Connection connection= AllowanceDB.getConnection(url,user,passwd);

            connection.setAutoCommit(false);
            String sql = "update allowance set batch=? where id_num=?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            for (AllowanceBean allowanceBean:allowanceBeans) {
                preparedStatement.setString(2, allowanceBean.getIdNum());
                System.out.print(allowanceBean.getIdNum()+"\t");
                preparedStatement.setString(1,allowanceBean.getBatch());
                System.out.print(allowanceBean.getBatch()+"\t");
                System.out.println();
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();

            free(preparedStatement,connection);



        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public void updatex(List<AllowanceBean> allowanceBeans){
        try {
            Connection connection= AllowanceDB.getConnection(url,user,passwd);

            connection.setAutoCommit(false);
            String sql = "update allowance set monthes=? , sum_money=? where id_num=?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            for (AllowanceBean allowanceBean:allowanceBeans) {
                preparedStatement.setString(3, allowanceBean.getIdNum());
                System.out.print(allowanceBean.getIdNum()+"\t");
                preparedStatement.setInt(2,allowanceBean.getSumMoney());
                System.out.print(allowanceBean.getSumMoney()+"\t");
                preparedStatement.setInt(1,allowanceBean.getMonthes());
                System.out.print(allowanceBean.getMonthes()+"\t");
                System.out.println();
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();

            free(preparedStatement,connection);



        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public AllowanceBean find(AllowanceBean allowanceBean){
        try {
            Connection connection= AllowanceDB.getConnection(url,user,passwd);

            String sql = "select * from allowance  where id_num=?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,allowanceBean.getIdNum());

            ResultSet resultSet = preparedStatement.executeQuery();
            AllowanceBean abc = null;
            while(resultSet.next()){
                abc = new AllowanceBean();
                abc.setIdNum(resultSet.getString("id_num"));
                abc.setMonthes(resultSet.getInt("monthes"));
                abc.setSumMoney(resultSet.getInt("sum_money"));
            }


            free(preparedStatement,connection);
            return abc;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;



    }


    public void importExcel(File file, List<AllowanceBean> allowanceBeans,String batch){

        if (!file.exists())
            return;
        boolean isE2007 = false;
        //判断是否是excel2007格式
        if(file.getName().endsWith("xlsx")){
            isE2007 = true;
        }

        FileInputStream is=null;
        try {
            is = new FileInputStream(file);
            Workbook wb;
            if(isE2007){
                wb = new XSSFWorkbook(is);
            }else{
                wb = new HSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数

            if (rowCount < 2) {
                System.out.println("sheet is null");
            }
            if (sheet == null)
                return;


            for (int i = 3; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                AllowanceBean allowanceBean = new AllowanceBean();
              /*  for(int j = 0;j<26;j++){
                    if(isMergedRegion(sheet,i,j)){
                        System.out.print(getMergedRegionValue(sheet,i,j)+"\t");
                    }else{
                        row = sheet.getRow(i);
                        System.out.print(row.getCell(j)+"\t");
                    }
                }*/
                Cell cella = row.getCell(0);
                if (getCellValue(cella) == null || getCellValue(cella).equals("")) {
                    continue;
                }
                System.out.println(getCellValue(cella));
                Cell cellb = row.getCell(1);
                if (isMergedRegion(sheet, i, 1)) {
                    System.out.println(getMergedRegionValue(sheet, i, 1) + "\t");
                    allowanceBean.setCompany(getMergedRegionValue(sheet, i, 1));
                } else {
                    allowanceBean.setCompany(getCellValue(cellb));
                }
                Cell cellc = row.getCell(2);
                allowanceBean.setName(getCellValue(cellc));
                Cell celld = row.getCell(3);
                allowanceBean.setIdNum(getCellValue(celld).trim().toUpperCase());
                Cell celle = row.getCell(4);
                System.out.println(getCellValue(celle) + "\t");
                String xueli = getCellValue(celle);
                if (xueli.startsWith("2")){
                    allowanceBean.setGraduatetime(xueli.substring(0, 4)+"-06");
                allowanceBean.setEducation(getCellValue(celle).substring(4));
            }else if (xueli.startsWith("1")){
                allowanceBean.setGraduatetime("20"+xueli.substring(0, 2)+"-06");
                allowanceBean.setEducation(xueli.substring(2));
            }else {
                    allowanceBean.setGraduatetime("2015"+"-06");
                    allowanceBean.setEducation(xueli);
                }
               allowanceBean.setBeginTime(" ");
                Cell cellf = row.getCell(5);
                allowanceBean.setBank(getCellValue(cellf));
                Cell cellg = row.getCell(6);
                allowanceBean.setBankCard(getCellValue(cellg));
                Cell cellh = row.getCell(7);
                allowanceBean.setBeginTime(getCellValue(cellh));

                Cell celli = row.getCell(8);
                allowanceBean.setMonthes(new Integer(getCellValue(celli)));
                Cell cellj = row.getCell(9);
                allowanceBean.setPhone(getCellValue(cellj));
                allowanceBean.setSumMoney(allowanceBean.getMonthes()*600);
//                allowanceBean.setLastTime("2017-12");
                allowanceBean.setUpdatetime("2017-12");
                allowanceBean.setBatch(batch);




                /*socialsecurityBean.setIdNum(getCellValue(row, 1));
                if (socialsecurityBean.getIdNum().equals("") || socialsecurityBean.getIdNum() == null)
                    continue;
                socialsecurityBean.setBeginTime(getCellValue(row, 2));
                socialsecurityBean.setLastTime(getCellValue(row, 3));
                socialsecurityBean.setMonthes(new Integer(getCellValue(row, 4)));
                socialsecurityBean.setStatus(Constants.socialsecurity_new);*/
                allowanceBean.setAllowancetype("600");
                allowanceBeans.add(allowanceBean);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void importExcelx(File file, List<AllowanceBean> allowanceBeans,String batch){

        if (!file.exists()) {
            System.out.println("file not exist");
            return;
        }
        boolean isE2007 = false;
        //判断是否是excel2007格式
        if(file.getName().endsWith("xlsx")){
            isE2007 = true;
        }

        FileInputStream is=null;
        try {
            is = new FileInputStream(file);
            Workbook wb;
            if(isE2007){
                wb = new XSSFWorkbook(is);
            }else{
                wb = new HSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数

            if (rowCount < 2) {
                System.out.println("sheet is null");
            }
            if (sheet == null)
                return;


            for (int i = 3; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                AllowanceBean allowanceBean = new AllowanceBean();
                Cell cella=row.getCell(0);
                if (getCellValue(cella)==null||getCellValue(cella).equals("")){
                    continue;
                }
                System.out.println(getCellValue(cella));
                Cell cellb=row.getCell(1);
                allowanceBean.setName(getCellValue(cellb));
                Cell cellc=row.getCell(2);
                allowanceBean.setCompany(getCellValue(cellc));
                Cell celld=row.getCell(3);
                allowanceBean.setIdNum(getCellValue(celld).trim().toUpperCase());
                Cell celle=row.getCell(4);

                Cell cellf = row.getCell(5);
                allowanceBean.setBeginTime(getCellValue(cellf));
                Cell cellg = row.getCell(6);
                allowanceBean.setMonthes(new Integer(getCellValue(cellg)));
                Cell cellh = row.getCell(7);
                allowanceBean.setSumMoney(new Integer(getCellValue(cellh)));
                Cell celli = row.getCell(8);
                allowanceBean.setBank(getCellValue(celli));
                Cell cellj = row.getCell(9);
                allowanceBean.setBankCard(getCellValue(cellj));
                Cell cellk = row.getCell(10);
                allowanceBean.setPhone(getCellValue(cellk));

                allowanceBean.setBatch(batch);
//                allowanceBean.setLastTime("2017-12");
                allowanceBean.setUpdatetime("2017-12");
                allowanceBean.setAllowancetype(allowanceBean.getSumMoney()/allowanceBean.getMonthes()+"");
                allowanceBeans.add(allowanceBean);



            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public  String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }

        return null ;
    }
    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public  String getCellValue(Cell cell){
        if(cell == null) return "";

        if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)){

                Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                return new SimpleDateFormat("yyyy-MM").format(date);
        }

        if (cell.getCellType()!=Cell.CELL_TYPE_STRING){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        return cell.getStringCellValue();
    }


    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private  boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
