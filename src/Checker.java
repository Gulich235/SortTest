import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Checker {

    public Vector toInteger(Vector<String> data) throws NumberFormatException{
        Vector temp = new Vector();
        for (int i = 0; i < data.size(); i++) {
                temp.add(Integer.parseInt(data.elementAt(i)));
        }
       return temp;
    }

    public boolean hasString(String fileName) throws FileNotFoundException{
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()){
            try{
                Integer.parseInt(scan.nextLine());
            }catch (NumberFormatException numberFormatException){return true;}
        }
        try{
            reader.close();
        }catch (IOException io){
            System.out.println("Не удалось закрыть файл");
            System.exit(0);}
        return false;
    }

    public int sortedString(String fileName)throws FileNotFoundException {
        String sortType=null;
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        String buffer = scan.nextLine();
        String secondBuffer;
        while (scan.hasNextLine()) {
            secondBuffer = scan.nextLine();
            if (buffer.compareTo(secondBuffer)==0)
                continue;
            if(buffer.compareTo(secondBuffer)<0){
                if (sortType==null){
                    sortType = "Up";
                }
                else if(!sortType.equals("Up"))
                    return 0;
            }
            else
            if (sortType==null){
                sortType = "Down";
            }
            else if(!sortType.equals("Down"))
                return 0;
            buffer = secondBuffer;
        }
        try{
        reader.close();
        }catch (IOException io){
            System.out.println("Не удалось закрыть файл");
            System.exit(0);
        }
        if (sortType.equals("Up"))
            return 1;
        else
            return -1;
    }

    public int sortedInteger(String fileName) throws FileNotFoundException{
        String sortType=null;
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        int buffer = Integer.parseInt(scan.nextLine());
        int secondBuffer;
        while (scan.hasNextLine()) {
            secondBuffer = Integer.parseInt(scan.nextLine());
            if (buffer == secondBuffer)
                continue;
            if(buffer<secondBuffer){
                if (sortType==null){
                    sortType = "Up";
                }
                else if(!sortType.equals("Up"))
                    return 0;
            }
            else
            if (sortType==null){
                sortType = "Down";
            }
            else if(!sortType.equals("Down"))
                return 0;
            buffer = secondBuffer;
        }
        try{
            reader.close();
        }catch (IOException io){
            System.out.println("Не удалось закрыть файл");
            System.exit(0);
        }
        if (sortType.equals("Up"))
            return 1;
        else
            return -1;
    }

    public boolean hasSpace(String fileName) throws FileNotFoundException{
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()){
            if (scan.nextLine().indexOf(" ")!=-1){
                return true;
            }
        }
        return false;
    }

    public void sortString (Vector<String> data1){
        Collections.sort(data1);
    }

    public void sortInteger (Vector<Integer> data1){
        Collections.sort(data1);
    }


}
