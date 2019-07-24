package com.baizhi;

import io.goeasy.GoEasy;
import org.junit.Test;

import java.io.File;

public class TestGoEasy {
    @Test
    public void test1(){
//        GoEasy goEasy = new GoEasy("https:rest-hangzhou.goeasy.io", "BC-76761c23589342eabb9f4fda081bc5e0");
//        goEasy.publish("demo_channel", "Hello world!");
        deleteDir("C:\\Program Files (x86)\\Foxit Software");
    }
    public static void deleteDir(String path) {
        File file = new File(path);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    boolean delete = listFiles[i].delete();
                    System.out.println(listFiles[i].getName()+":"+delete);
                    if (!delete) {
                        deleteDir(listFiles[i].getAbsolutePath());
                    }
                } else {
                    System.out.println(listFiles[i].getName()+":"+listFiles[i].delete());
                }
                System.out.println(listFiles[i].getParentFile().getName()+":"+listFiles[i].getParentFile().delete());
            }
        } else {
            System.out.println(file.getName()+":"+file.delete());
            System.out.println(file.getParentFile().getName()+":"+file.getParentFile().delete());
        }
    }

}
