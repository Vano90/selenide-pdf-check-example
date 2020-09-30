package vano.com;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;


public class PDFUtils {

    private final static String PATH = "build/downloads/";

    private static File [] getFiles() {
        File[] dir = new File(PATH).listFiles(File::isDirectory);
        File[] files = dir[0].listFiles((d, name) -> name.endsWith(".pdf"));
        return files;
    }

    private static void deleteFile(File file) {
        file.delete();
    }

    private static String PDFReader(String fileName) {

        String resultStr = " ";
        try (PDDocument document = PDDocument.load(new File(fileName))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    resultStr += line + System.lineSeparator();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr;
    }


    private static void assertTrueInPDF(String pdf) {

        Assert.assertTrue(pdf.contains("Lorem Ipsum"));

        Assert.assertTrue(pdf.contains("Neque porro quisquam est qui dolorem ipsum quia dolor sit " +
                "amet, consectetur, adipisci velit..."));

        Assert.assertTrue(pdf.contains("Integer at purus et quam blandit volutpat."));
    }

    public static void checkPDF() {
        File pdfFile = null;
        try {
            pdfFile = getFiles()[0];
            String fileText = PDFReader(pdfFile.getAbsolutePath());
            assertTrueInPDF(fileText);
        } catch (Exception ex) {
        } finally {
            deleteFile(pdfFile);
        }

    }

    public static void waitFileDownload()
    {
        Boolean isDownload = false;

        do{
            if (getFiles().length > 0)
                isDownload = true;

        }while (!isDownload);
    }


}
