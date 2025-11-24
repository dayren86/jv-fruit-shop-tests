package core.basesyntax.strategy.operation;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.dao.FruitsDaoImpl;
import core.basesyntax.model.FruitTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BalanceOperationTest {
    private FruitsDao fruitsDao;
    private OperationHandler balanceOperation;

    @Before
    public void setUp() throws Exception {
        fruitsDao = new FruitsDaoImpl();
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 400));
        fruitsDao.add(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100));
        balanceOperation = new BalanceOperation(fruitsDao);
    }

    @Test
    public void balanceTransaction_DoesntUpdateExistingBalance_notOk() {
        balanceOperation.transaction(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 100));
        FruitTransaction fruitTransaction = fruitsDao.get(new FruitTransaction("banana"));
        Integer expected = 100;
        Assert.assertNotEquals(expected, fruitTransaction.getQuantity());
    }
}
