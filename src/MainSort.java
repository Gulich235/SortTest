import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.util.Vector;

public class MainSort {
    private Checker check;
    private Vector<Vector<String>> bufferString = new Vector<>();
    private Vector<Vector<Integer>> bufferInteger;
    private Vector <FileDownload> files = new Vector<FileDownload>();
    private static int sortType = 1;
    private static String dataType = "Integer";
    private static OptionGroup sortTypeGroup;
    private static OptionGroup dataTypeGroup;
    private static CommandLine cmd;
    private static Options posixOptions = new Options();
    private static Vector<String> infiles = new Vector<>();
    private static String outfile = null;
    private boolean temp;
    private int sortFile;


    public static void main (String[] args){
        MainSort kek = new MainSort();
        dataTypeGroup = new OptionGroup();
        dataTypeGroup.addOption(new Option("s",false, "String type"));
        dataTypeGroup.addOption(new Option("i", false, "Integer type"));
        sortTypeGroup = new OptionGroup();
        sortTypeGroup.addOption(new Option("d", false, "descending sort"));
        sortTypeGroup.addOption(new Option("a", false, "Ascending sort"));
        posixOptions.addOptionGroup(sortTypeGroup);
        posixOptions.addOptionGroup(dataTypeGroup);
        CommandLineParser commandLineParser = new DefaultParser();
        try {
            cmd = commandLineParser.parse(posixOptions, args);
        if (cmd.hasOption("i"))
            dataType = "Integer";
        if (cmd.hasOption("s"))
            dataType = "String";
        if (cmd.hasOption("a"))
            sortType = 1;
        if(cmd.hasOption("d"))
            sortType = -1;

        }catch (ParseException pe){System.out.println("хуй");}
        for (int i=0; i<args.length;i++){
            if (args[i].indexOf(".txt")!=-1){
                if (outfile==null)
                    outfile = args[i];
                else
                    infiles.add(args[i]);
            }
        }

        kek.start();
    }


    private void start() {
        System.out.println(infiles);
        outfile = "out.txt";
        infiles.add("lol.txt");
        infiles.add("lol2.txt");
        infiles.add("lol3.txt");
        if (dataType == null){
            System.out.println("Ошибка ввода. Не указан тип данных");
            System.exit(0);
        }
        if (outfile==null){
            System.out.println("Не указан результирующий файл");
            System.exit(0);
        }
        MergeSort mergeSort = new MergeSort();
        check = new Checker();
        if (dataType.equals("Integer")){
            for (int i = 0; i<infiles.size();i++){
                try{
                    temp = check.hasString(infiles.elementAt(i));
                }catch (FileNotFoundException fileNotFoundException){
                    System.out.println("Файл" + infiles.elementAt(i)+ " не найден");
                    infiles.remove(i);
                    i--;
                    continue;}
                if (temp){
                    infiles.remove(i);
                    i--;
                }
            }
            for (int i=0; i<infiles.size();i++){
                try{
                 sortFile = check.sortedInteger(infiles.elementAt(i));
                    if (sortFile==0){
                        infiles.remove(i);
                        i--;
                    }
                    else
                files.add(new FileDownload(infiles.elementAt(i), sortFile, sortType, dataType));
                }catch (FileNotFoundException fileNotFoundException){
                    System.out.println("Файл " + infiles.elementAt(i)+ " не найден");
                    infiles.remove(i);
                    i--;
                    continue;
                }
            }

            mergeSort.sortInteger(files, sortType, outfile);
        }else{
        for (int i = 0; i<infiles.size();i++){
            try{
                temp = check.hasSpace(infiles.elementAt(i));
            }catch (FileNotFoundException fileNotFoundException){
                System.out.println("Файл " + infiles.elementAt(i)+ " не найден");
                infiles.remove(i);
                i--;
                continue;}
            if(temp){
                infiles.remove(i);
                i--;
            }
        }
        for (int i=0; i<infiles.size();i++){
            try{
                sortFile = check.sortedString(infiles.elementAt(i));
                if (sortFile==0){
                    infiles.remove(i);
                    i--;
                }
                else
                files.add(new FileDownload(infiles.elementAt(i), sortFile, sortType, dataType));
            }catch (FileNotFoundException fileNotFoundException){
                System.out.println("Файл " + infiles.elementAt(i)+ " не найден");
                infiles.remove(i);
                i--;
                continue;
            }
        }
            mergeSort.sortString(files, sortType, outfile);
        }



    }

}
