package com.zj.rcbt.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.zj.rcbt.common.utils.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QRCode {
    public String Decode(String path) throws Exception {
        BufferedImage image;


        image = ImageIO.read(new File(path));
        System.out.println(image.getType());
        image = toGrayImage(image);
        LuminanceSource source = new BufferedImageLuminanceSource(image);

        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "GBK");
//        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
//        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
        return result.getText();


    }


    /**
     * 将图片转成灰阶。
     *
     * @param image
     * @return
     */
    private static BufferedImage toGrayImage(BufferedImage image) {
        BufferedImage result = image;
        if (BufferedImage.TYPE_BYTE_GRAY != image.getType()) {
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            newImage.getGraphics().drawImage(image, 0, 0, null);
            result = newImage;
        }
        /*黑白处理
Raster raster = result.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = buffer.getData();
        for (int i = 0; i < data.length; i++) {
            byte value = 0;
            if (data[i] < 32) {
                value = -1;
            }
            buffer.setElem(i, value);
        }*/
        return result;
    }


}
