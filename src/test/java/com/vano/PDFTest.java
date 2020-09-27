package com.vano;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class PDFTest {

    @BeforeTest
    public void setUp(){

    }

    @Test
    public void pdfTest(){
        open("https://smallpdf.com/shared#pt=02fdd17c-5a41-4a2c-a381-e094422dca8d");
        sleep(5000);
    }

}
