package core.basesyntax.service;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReadFileServiceTest {
    private static final String FILE_PATH = "src/test/resources/fruits.csv";
    private ReadFileService readFileService;

    @Before
    public void setUp() throws Exception {
        readFileService = new ReadFileServiceImpl();
    }

    @Test(expected = RuntimeException.class)
    public void readFromFile_incorrectPath_notOk() {
        readFileService.readFromFile("");
    }

    @Test
    public void readFromFile() {
        List<String> expected = List.of("type,fruit,quantity",
                "b,banana,20", "b,apple,100",
                "s,banana,100", "p,banana,13", "r,apple,10",
                "p,apple,20", "p,banana,5", "s,banana,50");

        List<String> readFileList = readFileService.readFromFile(FILE_PATH);

        Assert.assertEquals(expected, readFileList);
    }
}
