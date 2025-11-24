package core.basesyntax.service;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.dao.FruitsDaoImpl;
import core.basesyntax.model.FruitTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReportGeneratorTest {
    private ReportGenerator reportGenerator;
    private FruitsDao fruitsDao;

    @Before
    public void setUp() throws Exception {
        fruitsDao = new FruitsDaoImpl();
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE,"banana", 152));
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 90));
        reportGenerator = new ReportGeneratorImpl(fruitsDao);
    }

    @Test
    public void reportGenerator_checkValidReport_ok() {
        String expected = "fruit,quantity" + System.lineSeparator()
                + "banana,152" + System.lineSeparator()
                + "apple,90" + System.lineSeparator();
        String report = reportGenerator.getReport();

        Assert.assertEquals(expected, report);
    }
}
