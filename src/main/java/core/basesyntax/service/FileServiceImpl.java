package core.basesyntax.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {

    @Override
    public List<String> readFromFile(String filePath) {
        List<String> stringList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't find file by path: " + filePath, e);
        }

        return stringList;
    }

    @Override
    public void writeReportToFile(String report, String reportPath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportPath))) {
            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + reportPath, e);
        }
    }
}
