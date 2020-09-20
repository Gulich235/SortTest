import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class FileDownload {
    private String fileName;
    private FileReader reader;
    private Scanner scan;
    private boolean endOfFile = false;
    private Vector buffer = new Vector();
    private int sortFile;
    private int size;
    private int sortType;
    private String dataType;

    FileDownload(String name, int sortFile, int sortType, String dataType)throws FileNotFoundException {
        fileName=name;
        this.sortType=sortType;
        this.dataType = dataType;
        reader = new FileReader(name);
        scan = new Scanner(reader);
        this.sortFile = sortFile;
        if (sortType>0){
        if (sortFile<0){
            while(scan.hasNextLine()){
                buffer.add(scan.nextLine());
                size++;
                try{
                reader.close();
                }catch (IOException ioException){System.out.println("IOException");}
            }
            reader = new FileReader(name);
            scan = new Scanner(reader);
        }
        }else{
            if (sortFile>0){
                while(scan.hasNextLine()){
                    buffer.add(scan.nextLine());
                    size++;
                    try{
                        reader.close();
                    }catch (IOException ioException){System.out.println("IOException");}
                }
                reader = new FileReader(name);
                scan = new Scanner(reader);
            }
        }
    }


    public void download (Vector buffer) throws IOException {
        int count=0;
        if (sortType>0){
        if (sortFile>0){
        while(scan.hasNextLine() && count<5){
            if(dataType.equals("Integer"))
            buffer.add(Integer.parseInt(scan.nextLine()));
            else
                buffer.add(scan.nextLine());
            count++;
        }
        if (!scan.hasNextLine()){
            endOfFile = true;
            reader.close();
        }
        }
        else{
            while(scan.hasNextLine() && size>0){
                if (count>size-6 & count<size)
                    if(dataType.equals("Integer"))
                        buffer.add(Integer.parseInt(scan.nextLine()));
                    else
                        buffer.add(scan.nextLine());
                else{
                    scan.nextLine();
                }
                count++;
            }
            size-=5;
            reader.close();
            if (size<0)
                endOfFile=true;
            else{
            reader = new FileReader(fileName);
            scan = new Scanner(reader);}
        }
    }
        else{
            if (sortFile<0){
                while(scan.hasNextLine() && count<5){
                    if(dataType.equals("Integer"))
                        buffer.add(Integer.parseInt(scan.nextLine()));
                    else
                        buffer.add(scan.nextLine());
                    count++;
                }
                if (!scan.hasNextLine()){
                    endOfFile = true;
                    reader.close();
                }
            }
            else{
                while(scan.hasNextLine() && size>0){
                    if (count>size-6 & count<size)
                        if(dataType.equals("Integer"))
                            buffer.add(Integer.parseInt(scan.nextLine()));
                        else
                            buffer.add(scan.nextLine());
                    else{
                        scan.nextLine();
                    }
                    count++;
                }

                size-=5;
                reader.close();
                if (size<1)
                    endOfFile=true;
                else{
                reader = new FileReader(fileName);
                scan = new Scanner(reader);}
            }
        }
    }


    public boolean getBuffer(Vector userBuffer){
        if (!endOfFile) {
            try{
           download(userBuffer);
            }catch (IOException ioException){
                userBuffer.clear();
            }
            return true;
        }
        else
            return false;
    }

    public int getSortFile(){
        return sortFile;
    }

}
