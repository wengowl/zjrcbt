package com.zj.rcbt.qrcode;


import com.zj.rcbt.chsi.ChsiParser;

public class Test {
    public static void main(String[] args){
        QRCode qrCode = new QRCode();
        String y="D:\\610426199208160020_qrcode.jpg";
        String x= null;
        try {
            x = qrCode.Decode(y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        x=x.substring(x.indexOf("#")+1,x.lastIndexOf("#"));
        System.out.println(x);
        ChsiParser chsiParser = new ChsiParser();
        System.out.println(chsiParser.getContentFromChsi(x).toString());




    }
}
