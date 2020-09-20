package sort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UploadingFile {
    private final FileWriter fileWriter;

    UploadingFile(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
    }

    //Выгрузка строковых элементов
    public void uploadString(List<String> data) throws IOException {
        for (String datum : data) {
            fileWriter.write(datum + "\n");
            fileWriter.flush();
        }
    }

    //Выгрузка целочисленных элементов
    public void uploadInteger(List<Integer> data) throws IOException {
        for (Integer datum : data) {
            fileWriter.write(datum.toString() + "\n");
            fileWriter.flush();
        }
    }

    //Закрытие файла
    public void closeWriter() throws IOException {
        fileWriter.close();
    }
}
