package core.basesyntax.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FileServiceTest {
    private FileService fileService;
    private static final String INCORRECT_PATH = "";
    private static final String FILE_PATH = "src/test/resources/fruits.csv";

    @Before
    public void setUp() throws Exception {
        fileService = new FileServiceImpl();
    }

    @Test(expected = RuntimeException.class)
    public void readFromFile_incorrectPath_notOk() {
        fileService.readFromFile(INCORRECT_PATH);
    }

    @Test(expected = RuntimeException.class)
    public void writeFromFile_incorrectPath_notOk() {
        fileService.writeReportToFile("",INCORRECT_PATH);
    }

    @Test
    public void readFromFile() {
        List<String> expected = List.of("type,fruit,quantity",
                "b,banana,20", "b,apple,100",
                "s,banana,100", "p,banana,13", "r,apple,10",
                "p,apple,20", "p,banana,5", "s,banana,50");

        List<String> readFileList = fileService.readFromFile(FILE_PATH);

        Assert.assertEquals(expected, readFileList);
    }
}
