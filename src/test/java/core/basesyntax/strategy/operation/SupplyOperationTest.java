package core.basesyntax.strategy.operation;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.dao.FruitsDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SupplyOperationTest {
    private FruitsDao fruitsDao;
    private OperationHandler supplyOperation;

    @Before
    public void setUp() throws Exception {
        fruitsDao = new FruitsDaoImpl();
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 400));
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100));
        supplyOperation = new SupplyOperation(fruitsDao);
    }

    @Test
    public void supplyTransaction_ok() {
        supplyOperation.transaction(
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, "banana", 5));
        FruitTransaction fruitTransaction = fruitsDao.get(new FruitTransaction("banana"));
        Integer expected = 405;
        Assert.assertEquals(expected, fruitTransaction.getQuantity());
    }

    @After
    public void afterClass() {
        Storage.fruitsStorage.clear();
    }
}
