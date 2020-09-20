package sort;

import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainSort {
    private final List<FileDownload> files = new ArrayList<>();
    private static int sortType = 1;
    private static DataType dataType = DataType.INTEGER;
    private static final Options posixOptions = new Options();
    private static final Vector<String> infile = new Vector<>();
    private static String outfile = null;


    public static void main(String[] args) {
        MainSort sort = new MainSort();
        OptionGroup dataTypeGroup = new OptionGroup();
        dataTypeGroup.addOption(new Option("s", false, "String type"));
        dataTypeGroup.addOption(new Option("i", false, "Integer type"));
        OptionGroup sortTypeGroup = new OptionGroup();
        sortTypeGroup.addOption(new Option("d", false, "descending sort"));
        sortTypeGroup.addOption(new Option("a", false, "Ascending sort"));
        posixOptions.addOptionGroup(sortTypeGroup);
        posixOptions.addOptionGroup(dataTypeGroup);
        CommandLineParser commandLineParser = new DefaultParser();
        try {
            CommandLine cmd = commandLineParser.parse(posixOptions, args);
            if (cmd.hasOption("i"))
                dataType = DataType.INTEGER;
            if (cmd.hasOption("s"))
                dataType = DataType.STRING;
            if (cmd.hasOption("a"))
                sortType = 1;
            if (cmd.hasOption("d"))
                sortType = -1;

        } catch (ParseException pe) {
            System.out.println("Ошибка при вводе опций");
            System.exit(1);
        }
        for (String arg : args) {
            if (arg.contains(".txt")) {
                if (outfile == null)
                    outfile = arg;
                else
                    infile.add(arg);
            }
        }

        sort.start();
    }


    private void start() {
        if (dataType == null) {
            System.out.println("Ошибка ввода. Не указан тип данных");
            System.exit(1);
        }
        if (outfile == null) {
            System.out.println("Не указан результирующий файл");
            System.exit(1);
        }
        MergeSort mergeSort = new MergeSort();
        Checker check = new Checker();
        boolean temp;
        int sortFile;
        if (dataType == DataType.INTEGER) {
            for (int i = 0; i < infile.size(); i++) {
                try {
                    temp = check.hasString(infile.elementAt(i));
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Файл" + infile.elementAt(i) + " не найден");
                    infile.remove(i);
                    i--;
                    continue;
                }
                if (temp) {
                    System.out.println("Файл " + infile.get(i) + " убран из сортировки из-за того, что в нем имеются строки");
                    infile.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < infile.size(); i++) {
                try {
                    sortFile = check.sortedInteger(infile.elementAt(i));
                    if (sortFile == 0) {
                        System.out.println("Файл " + infile.get(i) + " убран из сортировки из-за того, что он не отсортирован");
                        infile.remove(i);
                        i--;
                    } else
                        files.add(new FileDownload(infile.elementAt(i), sortFile, sortType));
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Файл " + infile.elementAt(i) + " не найден");
                    infile.remove(i);
                    i--;
                }
            }

            mergeSort.sortInteger(files, sortType, outfile);
        } else {
            for (int i = 0; i < infile.size(); i++) {
                try {
                    temp = check.hasSpace(infile.elementAt(i));
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Файл " + infile.elementAt(i) + " не найден");
                    infile.remove(i);
                    i--;
                    continue;
                }
                if (temp) {
                    System.out.println("Файл " + infile.get(i) + " убран из сортировки из-за того, что в нем имеются пробелы");
                    infile.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < infile.size(); i++) {
                try {
                    sortFile = check.sortedString(infile.elementAt(i));
                    if (sortFile == 0) {
                        System.out.println("Файл " + infile.get(i) + " убран из сортировки из-за того, что он не отсортирован");
                        infile.remove(i);
                        i--;
                    } else
                        files.add(new FileDownload(infile.elementAt(i), sortFile, sortType));
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Файл " + infile.elementAt(i) + " не найден");
                    infile.remove(i);
                    i--;
                }
            }
            mergeSort.sortString(files, sortType, outfile);
        }


    }

}
