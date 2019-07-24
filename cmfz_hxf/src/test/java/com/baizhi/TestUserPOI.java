package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.export.ExcelBatchExportService;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
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
public class TestUserPOI {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1() throws IOException {
        List<User> users = userDao.selectAllUser();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户", "用户表"),User.class,users);
        workbook.write(new FileOutputStream("f:/testPOI/第一个EasyPOIExcel文档.xls"));
    }
}
