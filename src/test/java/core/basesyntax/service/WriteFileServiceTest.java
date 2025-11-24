package core.basesyntax.service;

import org.junit.Before;
import org.junit.Test;

public class WriteFileServiceTest {
    private WriteFileService writeFileService;

    @Before
    public void setUp() throws Exception {
        writeFileService = new WriteFileServiceImpl();
    }

    @Test(expected = RuntimeException.class)
    public void writeFile_incorrectPath_notOk() {
        String report = "fruit,quantity" + System.lineSeparator()
                + "banana,152" + System.lineSeparator()
                + "apple,90" + System.lineSeparator();
        writeFileService.writeReportToFile(report,"");
    }

    @Test(expected = RuntimeException.class)
    public void writeFile_emptyReport_notOk() {
        writeFileService.writeReportToFile("", "");
    }

    @Test(expected = RuntimeException.class)
    public void writeFile_reportNull_notOk() {
        writeFileService.writeReportToFile(null, "");
    }
}
