package core.basesyntax.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileServiceTest {
    private FileService fileService;

    @Before
    public void setUp() throws Exception {
        fileService = new FileServiceImpl();
    }

    @Test(expected = RuntimeException.class)
    public void readFromFile_incorrectPath_notOk() {
        fileService.readFromFile("");
    }

    @Test(expected = RuntimeException.class)
    public void writeFromFile_incorrectPath_notOk() {
        fileService.writeReportToFile("","");
    }

    @Test
    public void readFromFile() {
        List<String> expected = List.of("type,fruit,quantity",
                "b,banana,20", "b,apple,100",
                "s,banana,100", "p,banana,13", "r,apple,10",
                "p,apple,20", "p,banana,5", "s,banana,50");

        String filePath = "src/test/resources/fruits.csv";
        List<String> readFileList = fileService.readFromFile(filePath);

        Assert.assertEquals(expected, readFileList);
    }
}
