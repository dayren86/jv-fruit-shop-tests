package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.converter.DataConverter;
import core.basesyntax.service.converter.DataConverterImpl;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataConverterTest {
    private DataConverter dataConverter;
    private List<String> incorrectOperation;
    private List<String> listTransactions;
    private List<FruitTransaction> fruitTransactionList;

    @Before
    public void setUp() {
        dataConverter = new DataConverterImpl();
        incorrectOperation = List.of("type,fruit,quantity", "t, banana, -1");
        listTransactions = List.of("type,fruit,quantity",
                "b,banana,0", "s,banana,20", "p,banana,13",
                "r,banana,10");
        fruitTransactionList = List.of(
                new FruitTransaction(
                        FruitTransaction.Operation.BALANCE, "banana", 0),
                new FruitTransaction(
                        FruitTransaction.Operation.SUPPLY, "banana", 20),
                new FruitTransaction(
                        FruitTransaction.Operation.PURCHASE, "banana", 13),
                new FruitTransaction(
                        FruitTransaction.Operation.RETURN, "banana", 10));
    }

    @Test(expected = RuntimeException.class)
    public void convertTransaction_listNull_notOk() {
        dataConverter.convertFruitTransactions(null);
    }

    @Test(expected = IllegalStateException.class)
    public void convertOperation_incorrectValue_notOk() {
        dataConverter.convertFruitTransactions(incorrectOperation);
    }

    @Test(expected = RuntimeException.class)
    public void checkValidationSlitString_incorrectLength_notOk() {
        dataConverter.convertFruitTransactions(List.of("type,fruit,quantity", "b,bana,10,5"));
    }

    @Test(expected = RuntimeException.class)
    public void checkValidationSlitString_negativeQuantity_notOk() {
        dataConverter.convertFruitTransactions(List.of("type,fruit,quantity", "b,bana,-1"));
    }

    @Test
    public void convertFruitTransactions_correct_ok() {
        List<String> strings = List.of("type,fruit,quantity", "b,banana,10");
        List<FruitTransaction> expected = List.of(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 10));
        List<FruitTransaction> fruitTransactions = dataConverter.convertFruitTransactions(strings);
        Assert.assertEquals(expected, fruitTransactions);
    }

    @Test
    public void convertFruitTransactions_correctList_ok() {
        List<FruitTransaction> fruitTransactions
                = dataConverter.convertFruitTransactions(listTransactions);
        Assert.assertEquals(fruitTransactionList, fruitTransactions);
    }
}
