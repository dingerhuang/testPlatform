package com.module.product.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ZXingUtil {
    public static boolean createQrCode(String url, String path, String fileName) {
        boolean flag=false;
        try {
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 400, 400, hints);
            File file = new File(path, fileName);
            if (file.exists() || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) && file.createNewFile())) {
                writeToFile(bitMatrix, "jpg", file);
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }


    /**
     * 自动生成plist文件方法
     * @param pName 项目名称
     * @param version 版本号
     * @param downLoadPath ipa下载地址
     * @param filePath plist文件存储地址
     * @throws IOException
     */
    public void createPlist(String pName,String version,String downLoadPath,String filePath) throws IOException {
        //plist文件链接服务器地址
        StringBuffer allPath=new StringBuffer();
        allPath.append(downLoadPath);
        // 创建文档类型
        DocType docType = new DocType("plist");
        docType.setPublicID("-//Apple//DTD PLIST 1.0//EN");
        docType.setSystemID("http://www.apple.com/DTDs/PropertyList-1.0.dtd");
        // 创建根节点 plist
        Element root = new Element("plist");
        root.setAttribute("version", "1.0");

        Element rootDict = new Element("dict");
        rootDict.addContent(new Element("key").setText("items"));
        Element rootDictArray = new Element("array");
        Element rootDictArrayDict = new Element("dict");
        rootDictArrayDict.addContent(new Element("key").setText("assets"));


        Element rootDictArrayDictArray = new Element("array");
        Element rootDictArrayDictArrayDict1 = new Element("dict");
        rootDictArrayDictArrayDict1.addContent(new Element("key")
                .setText("kind"));
        rootDictArrayDictArrayDict1.addContent(new Element("string")
                .setText("software-package"));
        rootDictArrayDictArrayDict1.addContent(new Element("key")
                .setText("url"));
        rootDictArrayDictArrayDict1.addContent(new Element("string")
                .setText(allPath.toString()));


        Element rootDictArrayDictArrayDict2 = new Element("dict");
        rootDictArrayDictArrayDict2.addContent(new Element("key")
                .setText("kind"));
        rootDictArrayDictArrayDict2.addContent(new Element("string")
                .setText("display-image"));
        rootDictArrayDictArrayDict2.addContent(new Element("key")
                .setText("needs-shine"));
        //true 有空格会导致解析不了plist文件
//        rootDictArrayDictArrayDict2.addContent(new Element("true"));
        rootDictArrayDictArrayDict2.addContent(new Element("key")
                .setText("url"));


        rootDictArrayDictArray.addContent(rootDictArrayDictArrayDict1);
        rootDictArrayDictArray.addContent(rootDictArrayDictArrayDict2);
        rootDictArrayDict.addContent(rootDictArrayDictArray);
        rootDictArrayDict.addContent(new Element("key").setText("metadata"));


        Element rootDictArrayDictDict = new Element("dict");
        rootDictArrayDictDict.addContent(new Element("key")
                .setText("bundle-identifier"));
        rootDictArrayDictDict.addContent(new Element("string")
                .setText("com.module.product"));
        rootDictArrayDictDict.addContent(new Element("key")
                .setText("bundle-version"));
        rootDictArrayDictDict.addContent(new Element("string").setText(version));
        rootDictArrayDictDict.addContent(new Element("key").setText("kind"));
        rootDictArrayDictDict.addContent(new Element("string")
                .setText("software"));
        rootDictArrayDictDict.addContent(new Element("key").setText("title"));
        rootDictArrayDictDict.addContent(new Element("string")
                .setText(pName));
                        rootDictArrayDict.addContent(rootDictArrayDictDict);


        rootDictArray.addContent(rootDictArrayDict);
        rootDict.addContent(rootDictArray);
        root.addContent(rootDict);
        // 根节点添加到文档中;
        Document Doc = new Document(root, docType);
        Format format = Format.getPrettyFormat();
        XMLOutputter XMLOut = new XMLOutputter(format);
        // 输出 user.xml 文件;
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        XMLOut.output(Doc, fos);
        // XMLOut.output(Doc);
    }
}
