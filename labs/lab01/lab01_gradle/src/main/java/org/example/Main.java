package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        InputStream in = url.openStream();
        FileOutputStream fos = new FileOutputStream("download.pdf");

        int length;
        byte[] buffer = new byte[1024];
        while ((length = in.read(buffer)) > -1) {
            fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();

        System.out.println("PDF file downloaded");

        URL url1 = new URL("https://www.thealexandriazoo.com/images/animals/Capybara02.jpg");
        FileOutputStream fos1 = new FileOutputStream("capybara.jpg");
        InputStream in1 = url1.openStream();

        int length1;
        byte[] buffer1 = new byte[1024];
        while ((length1 = in1.read(buffer1)) > -1) {
            fos1.write(buffer1, 0, length1);
        }
        fos1.close();
        in1.close();

        System.out.println("JPG file downloaded");

        File file = new File("download.pdf");
        String photo = "capybara.jpg";
        PDDocument document = PDDocument.load(file);
        PDPage page = document.getPage(0);
        PDImageXObject pdPhoto = PDImageXObject.createFromFile(photo, document);
        PDPageContentStream stream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
        stream.drawImage(pdPhoto, 40, 300, 400, 275);
        stream.close();
        document.save("updated_pdf.pdf");
        document.close();
        System.out.println("Image successfully added.");
    }
}
