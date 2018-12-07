package com.zj.rcbt.qrcode;


import com.zj.rcbt.chsi.ChsiParser;

public class Test {
    public static void main(String[] args){
        QRCode qrCode = new QRCode();
        String y="D:\\622223199607170334_qrcode.png";
//        String y="D:\\test.png";
        String x= null;
        try {
            x = qrCode.Decode(y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(x);
//        x=x.substring(x.indexOf("#")+1,x.lastIndexOf("#"));
        System.out.println(x);
        ChsiParser chsiParser = new ChsiParser();
//        System.out.println(chsiParser.getContentFromChsi(x).toString());




    }
}
