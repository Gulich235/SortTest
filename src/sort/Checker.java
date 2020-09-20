package sort;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Checker {
    private static SortType sortType;

    //Проверка файла на наличие строк, которые невохможно привести к целочисленному виду
    public boolean hasString(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()) {
            try {
                Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException numberFormatException) {
                return true;
            }
        }
        try {
            reader.close();
        } catch (IOException io) {
            System.out.println("Не удалось закрыть файл");
            System.exit(0);
        }
        return false;
    }

    //Проверка строковых элементов на сортированность
    public int sortedString(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        String buffer = scan.nextLine();
        String secondBuffer;
        while (scan.hasNextLine()) {
            secondBuffer = scan.nextLine();
            if (buffer.compareTo(secondBuffer) == 0)
                continue;
            if (buffer.compareTo(secondBuffer) < 0) {
                if (sortType == null) {
                    sortType = SortType.UP;
                } else if (sortType != SortType.UP)
                    return 0;
            } else if (sortType == null) {
                sortType = SortType.DOWN;
            } else if (sortType != SortType.DOWN)
                return 0;
            buffer = secondBuffer;
        }
        try {
            reader.close();
        } catch (IOException io) {
            System.out.println("Не удалось закрыть файл");
            System.exit(0);
        }
        if (sortType == SortType.UP)
            return 1;
        else
            return -1;
    }

    //Проверка целочисленных элементов на сортированность
    public int sortedInteger(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        int buffer = Integer.parseInt(scan.nextLine());
        int secondBuffer;
        while (scan.hasNextLine()) {
            secondBuffer = Integer.parseInt(scan.nextLine());
            if (buffer == secondBuffer)
                continue;
            if (buffer < secondBuffer) {
                if (sortType == null) {
                    sortType = SortType.UP;
                } else if (sortType != SortType.UP)
                    return 0;
            } else if (sortType == null) {
                sortType = SortType.DOWN;
            } else if (sortType != SortType.DOWN)
                return 0;
            buffer = secondBuffer;
        }
        try {
            reader.close();
        } catch (IOException io) {
            System.out.println("Не удалось закрыть файл");
            System.exit(0);
        }
        if (sortType == SortType.UP)
            return 1;
        else
            return -1;
    }

    //Проверка на наличие пробелов
    public boolean hasSpace(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()) {
            if (scan.nextLine().contains(" ")) {
                return true;
            }
        }
        return false;
    }


}
