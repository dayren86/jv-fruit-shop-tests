package core.basesyntax.strategy.operation;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.dao.FruitsDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OperationHandlerTest {
    private FruitsDao fruitsDao;
    private OperationHandler balanceOperation;
    private OperationHandler purchaseOperation;
    private OperationHandler returnOperation;
    private OperationHandler supplyOperation;

    @Before
    public void setUp() {
        fruitsDao = new FruitsDaoImpl();
        fruitsDao.add(new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 400));
        fruitsDao.add(new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100));
        balanceOperation = new BalanceOperation(fruitsDao);
        purchaseOperation = new PurchaseOperation(fruitsDao);
        returnOperation = new ReturnOperation(fruitsDao);
        supplyOperation = new SupplyOperation(fruitsDao);
    }

    @After
    public void afterClass() {
        Storage.fruitsStorage.clear();
    }

    @Test
    public void balanceTransaction_DoesntUpdateExistingBalance_notOk() {
        balanceOperation.transaction(new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 100));
        FruitTransaction fruitTransaction = fruitsDao.get(new FruitTransaction("banana"));
        Integer expected = 100;
        Assert.assertNotEquals(expected, fruitTransaction.getQuantity());
    }

    @Test
    public void purchaseTransaction_correctValue_ok() {
        purchaseOperation.transaction(new FruitTransaction(FruitTransaction.Operation.PURCHASE, "banana", 20));
        FruitTransaction fruitTransaction = fruitsDao.get(new FruitTransaction("banana"));
        Integer expected = 380;
        Assert.assertEquals(expected, fruitTransaction.getQuantity());
    }

    @Test(expected = RuntimeException.class)
    public void purchaseTransaction_incorrectValue_notOk() {
        purchaseOperation.transaction(new FruitTransaction(FruitTransaction.Operation.PURCHASE, "banana", 500));
    }

    @Test
    public void returnTransaction_ok() {
        returnOperation.transaction(new FruitTransaction(FruitTransaction.Operation.RETURN, "banana", 5));
        FruitTransaction fruitTransaction = fruitsDao.get(new FruitTransaction("banana"));
        Integer expected = 405;
        Assert.assertEquals(expected, fruitTransaction.getQuantity());
    }

    @Test
    public void supplyTransaction_ok() {
        supplyOperation.transaction(new FruitTransaction(FruitTransaction.Operation.SUPPLY, "banana", 5));
        FruitTransaction fruitTransaction = fruitsDao.get(new FruitTransaction("banana"));
        Integer expected = 405;
        Assert.assertEquals(expected, fruitTransaction.getQuantity());
    }
}