package com.baizhi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPOI {
    @Test
    public void test1() throws Exception {
        //创建文件
        Workbook workbook = new HSSFWorkbook();
        //创建一张表
        Sheet sheet = workbook.createSheet("表一");
        //创建行
        Row row = sheet.createRow(0);
        //创建单元格
        Cell cell = row.createCell(0);
        //向单元格添加数据
        cell.setCellValue("编号");
        //更改样式
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
        //输出到硬盘
        File file = new File("f:/testPOI");
        if(!file.exists()){
            file.mkdir();
        }
        //创建单元格2
        Cell cell1 = row.createCell(1);
        //添加日期
        cell1.setCellValue(new Date());
        //创建一个样式
//        CellStyle dateStyle = workbook.createCellStyle();
//        DataFormat dataFormat = workbook.createDataFormat();
//        short format = dataFormat.getFormat("yyyy-MM-dd");
//        dateStyle.setDataFormat(format);
//        cell1.setCellStyle(dateStyle);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        cell1.setCellValue(date);
        workbook.write(new FileOutputStream("F:/testPOI/第一个Exceel.xls"));
        workbook.close();

    }
}
