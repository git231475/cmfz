package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBetterPOI {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1() throws IOException {
        List<User> users = userDao.selectAllUser();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("用户表");
        Row row = sheet.createRow(0);
        String[] title ={"编号","电话","密码","法号","省"};
        Cell cell = null;
        for (int i = 0; i < title.length; i++) {
            String s = title[i];
            cell = row.createCell(i);
            cell.setCellValue(s);
        }
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Row row1 = sheet.createRow(i + 1);
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue(user.getId());
            Cell cell2 = row1.createCell(1);
            cell1.setCellValue(user.getPhone());
            Cell cell3 = row1.createCell(2);
            cell1.setCellValue(user.getPassword());
            Cell cell4 = row1.createCell(3);
            cell1.setCellValue(user.getDharmaName());
            Cell cell5 = row1.createCell(4);
            cell1.setCellValue(user.getProvince());

        }
        workbook.write(new FileOutputStream("F:/testPOI/第一个Exceel.xls"));
    }
}
