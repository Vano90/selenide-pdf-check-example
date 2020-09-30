package com.vano;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static vano.com.PDFUtils.checkPDF;
import static vano.com.PDFUtils.waitFileDownload;

public class PDFTest {

    private static String URL =  "https://smallpdf.com/shared#pt=02fdd17c-5a41-4a2c-a381-e094422dca8d";
    private static SelenideElement buttonDownload = $x("//button/div[text()='Download']/..");

    @BeforeTest
    public void setUp(){
        Configuration.timeout=10000;
        Configuration.startMaximized=true;
        open(URL);
    }

    @Test
    public void pdfTest(){
         buttonDownload.click();
         //sleep(3000);
         waitFileDownload();
         checkPDF();
    }

}
