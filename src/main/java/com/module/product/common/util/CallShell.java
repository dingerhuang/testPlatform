package com.module.product.common.util;

public class CallShell {
    public static void callShell(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
