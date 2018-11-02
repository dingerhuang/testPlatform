package com.module.product;

import com.module.product.common.util.ZXingUtil;

import java.io.IOException;

public class ZXingUtilTest {
    public static void main(String[] args) throws IOException {
        ZXingUtil zXingUtil = new ZXingUtil();
        zXingUtil.createPlist("小凡分期","1.1.1.1",
                "http://172.29.0.138:8081/download/2018/10/24/test.ipa","/data/ftp/download/2018/10/24/test.plist");
    }
}
