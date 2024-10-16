import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CheckingFileZipTest {
    private ClassLoader cl = CheckingFileZipTest.class.getClassLoader();

    @DisplayName("Проверка pdf файла в test.zip")
    @Test
    void pdfFileParsingTest() throws Exception {
       PDF pdf = null;
       try (ZipInputStream zip = new ZipInputStream(
               cl.getResourceAsStream("test.zip")
       )) {
           ZipEntry entry;
           while ((entry = zip.getNextEntry()) != null) {
               if (entry.getName().equals("test.pdf")) {
                   pdf = new PDF(zip);
                   break;
               }
           }
           Assertions.assertEquals("Михаил Булгаков",pdf.author);
           }
       }

    @DisplayName("Проверка xlsx файла в test.zip")
    @Test
    void xslsFileParsingTest() throws Exception {
        XLS xsls = null;
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("sample-simple-1.xls")) {
                    xsls = new XLS(zip);
                    break;
                }
            }
            String actualValue = xsls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
            Assertions.assertTrue(actualValue.contains("test1"));
        }
    }

    @DisplayName("Проверка csv файла в test.zip")
    @Test
    void cvsFileParsingTest() throws Exception {
        CSVReader csv = null;
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("Test.csv")) {
                    csv = new CSVReader(new InputStreamReader(zip));
                    break;
                }
            }
            List<String[]> data = csv.readAll();
            Assertions.assertEquals(3, data.size());
            Assertions.assertArrayEquals(
                    new String[] {"Royal Canin"," Royal Canin"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[] {"GRANDORF"," GRANDORF"},
                    data.get(1)
            );
        }
    }
   }


