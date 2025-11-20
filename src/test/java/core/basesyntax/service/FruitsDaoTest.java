package core.basesyntax.service;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.dao.FruitsDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FruitsDaoTest {
    private FruitsDao fruitsDao;
    private List<FruitTransaction> fruitTransactions;

    @Before
    public void setUp() {
        this.fruitsDao = new FruitsDaoImpl();
        fruitsDao.add(new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 400));
        fruitsDao.add(new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100));
        fruitTransactions = List.of(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 400),
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100));
    }

    @After
    public void afterClass() {
        Storage.fruitsStorage.clear();
    }

    @Test(expected = RuntimeException.class)
    public void fruitsDao_getIncorrectValue_notOk() {
        fruitsDao.get(new FruitTransaction(FruitTransaction.Operation.BALANCE, "paper", 50));
    }

    @Test
    public void fruitsDao_getCorrectValue_Ok() {
        FruitTransaction expectedBanana
                = new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 400);
        FruitTransaction fruitTransactionBanana = fruitsDao.get(
                new FruitTransaction("banana"));
        Assert.assertEquals(expectedBanana, fruitTransactionBanana);

        FruitTransaction expectedApple
                = new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 100);
        FruitTransaction fruitTransactionApple = fruitsDao.get(
                new FruitTransaction("apple"));
        Assert.assertEquals(expectedApple, fruitTransactionApple);
    }

    @Test
    public void fruitsDao_getAllFruits_Ok() {
        List<FruitTransaction> allTransactions = fruitsDao.getAll();
        Assert.assertEquals(fruitTransactions, allTransactions);
    }

    @Test
    public void fruitsDao_setFruits_Ok() {
        FruitTransaction fruitTransaction
                = new FruitTransaction(FruitTransaction.Operation.RETURN, "banana", 200);
        fruitsDao.set(fruitTransaction);
        Assert.assertEquals(fruitTransaction, fruitsDao.get(fruitTransaction));
    }
}
