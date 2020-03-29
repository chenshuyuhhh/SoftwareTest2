package xyz.chenshuyu.selenium;

import org.apache.poi.ss.usermodel.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


@RunWith(Parameterized.class)
public class Ex2 {

    private static WebDriver driver;
    private String expected;
    private String actual;

    public Ex2(String expected, String actual) {
        this.expected = expected;
        this.actual = actual;
    }

    @Before
    public void setUp() {
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {

        // create chromeDriver
        String driverPath = System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        // baseurl
        driver.get("http://103.120.226.190/selenium-demo/git-repo");

        ArrayList<Object[]> datas = new ArrayList<Object[]>();

        String filePath = System.getProperty("user.dir") + "/src/main/resources/info/Selenium+Lab2020.xlsx";
        File xlsFile = new File(filePath);
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(xlsFile);
            // first sheet
            Sheet sheet = workbook.getSheetAt(0);
            int row = 0;
            while (true) { // view every row
                Row r = sheet.getRow(row); // get this row
                String c1 = r.getCell(1).getStringCellValue(); // get username
                if (c1.equals("")) break; // if this row is null, then break out
                String c2 = r.getCell(2).getStringCellValue(); // get password

                // username
                driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[1]/input")).sendKeys(c1);
                // password
                driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[2]/input")).sendKeys(c2);
                // click query
                driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[3]/input")).click();
                // query text
                String text = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code")).getText();

                datas.add(new Object[]{text, c2});
                row++; // next row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datas;
    }

    @After
    public void tearDown() {
        driver.quit(); // close driver
    }


    @Test
    public void seleniumTest() {
        Assert.assertEquals(this.expected, this.actual);
    }
}
