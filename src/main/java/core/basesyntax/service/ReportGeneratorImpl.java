package core.basesyntax.service;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.model.FruitTransaction;

public class ReportGeneratorImpl implements ReportGenerator {
    private FruitsDao fruitsDao;

    public ReportGeneratorImpl(FruitsDao fruitsDao) {
        this.fruitsDao = fruitsDao;
    }

    @Override
    public String getReport() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("fruit,quantity\n");

        for (FruitTransaction fruitTransaction : fruitsDao.getAll()) {
            stringBuilder.append(fruitTransaction.getName())
                    .append(",")
                    .append(fruitTransaction.getQuantity())
                    .append('\n');
        }

        return stringBuilder.toString();
    }
}
