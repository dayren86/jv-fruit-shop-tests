package core.basesyntax.strategy.operation;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.dao.FruitsDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReturnOperationTest {
    private FruitsDao fruitsDao;
    private OperationHandler returnOperation;

    @Before
    public void setUp() throws Exception {
        fruitsDao = new FruitsDaoImpl();
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 400));
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100));
        returnOperation = new ReturnOperation(fruitsDao);
    }

    @Test
    public void returnTransaction_ok() {
        returnOperation.transaction(
                new FruitTransaction(FruitTransaction.Operation.RETURN, "banana", 5));
        FruitTransaction fruitTransaction = fruitsDao.get(new FruitTransaction("banana"));
        Integer expected = 405;
        Assert.assertEquals(expected, fruitTransaction.getQuantity());
    }

    @After
    public void afterClass() {
        Storage.fruitsStorage.clear();
    }
}
