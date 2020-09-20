import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class MergeSort {
    private UploadingFile uploadingFile;

    public void sortString(Vector<FileDownload> fileData,int sortType, String outFile) {
        try{
            uploadingFile = new UploadingFile(outFile);
        }catch (IOException ioException){System.out.println("Лох");}
        ArrayList<String> temp = new ArrayList<>(fileData.size());
        ArrayList<String> finalBuffer = new ArrayList<>();
        ArrayList<Vector<String>> vecData = new ArrayList<>();
        String min;
        String max;
        int countEndFiles = fileData.size();
        for (int i=0; i<fileData.size();i++) {
            vecData.add(new Vector());
            fileData.elementAt(i).getBuffer(vecData.get(i));
        }
            for (int i = 0; i<vecData.size();i++) {
                if (sortType > 0)
                    if (fileData.elementAt(i).getSortFile() > 0)
                        temp.add(vecData.get(i).remove(0));
                    else
                        temp.add(vecData.get(i).remove(vecData.get(i).size()-1));
                else {
                    if (fileData.elementAt(i).getSortFile() > 0)
                        temp.add(vecData.get(i).remove(vecData.get(i).size()-1));
                    else
                        temp.add(vecData.get(i).remove(0));
                }

            }

        while (countEndFiles>0) {
            if (sortType > 0){
                int index = 0;
                min = temp.get(0);
                for (int i=1; i<temp.size();i++){
                    if (temp.get(i).compareTo(min)<0){
                        min = temp.get(i);
                        index = i;
                    }
            }
                finalBuffer.add(temp.remove (index));
                if (fileData.elementAt(index).getSortFile()>0){
                    try{
                        String tempString = vecData.get(index).remove(0);
                        temp.add(index, tempString);
                    }
                    catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                        if (!tempBoolean)
                        {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        }
                        else
                            temp.add(index, vecData.get(index).remove(0));
                    }

                }
                else{
                    try{
                        String tempString = vecData.get(index).remove(vecData.get(index).size()-1);
                    temp.add(index, tempString);
                }
                catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                    boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                    if (!tempBoolean)
                    {
                        countEndFiles--;
                        fileData.remove(index);
                        vecData.remove(index);
                    }
                    else
                        temp.add(index, vecData.get(index).remove(vecData.get(index).size()-1));
                }
                }

            }
            else {
                int index = 0;
                max = temp.get(0);
                for (int i=1; i<temp.size();i++){
                    if (temp.get(i).compareTo(max)>0){
                        max = temp.get(i);
                        index = i;
                    }
                }
                finalBuffer.add(temp.remove (index));
                if (fileData.elementAt(index).getSortFile()>0){
                    try{
                        String tempString = vecData.get(index).remove(vecData.get(index).size()-1);
                        temp.add(index, tempString);
                    }
                    catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                        if (!tempBoolean)
                        {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        }
                        else
                            temp.add(index, vecData.get(index).remove(vecData.get(index).size()-1));
                    }
                }
                else{
                    try{
                        String tempString = vecData.get(index).remove(0);
                        temp.add(index, tempString);
                    }
                    catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                        if (!tempBoolean)
                        {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        }
                        else
                            temp.add(index, vecData.get(index).remove(0));
                    }



                }

            }
            if (finalBuffer.size()==5){
            try{
                uploadingFile.upload(finalBuffer);
            }catch (IOException ioException){}
            finalBuffer.clear();
            }
        }
        try{
            uploadingFile.upload(finalBuffer);
            uploadingFile.closeWriter();
        }catch (IOException ioException){}
        finalBuffer.clear();

        }



    public void sortInteger(Vector<FileDownload> fileData,int sortType, String outFile){
        try{
            uploadingFile = new UploadingFile(outFile);
        }catch (IOException ioException){System.out.println("Лох");}
        ArrayList<Integer> temp = new ArrayList<>(fileData.size());
        ArrayList<Integer> finalBuffer = new ArrayList<>();
        ArrayList<Vector<Integer>> vecData = new ArrayList<>();
        int min;
        int max;
        int countEndFiles = fileData.size();
        for (int i=0; i<fileData.size();i++) {
            vecData.add(new Vector());
            fileData.elementAt(i).getBuffer(vecData.get(i));
        }
        for (int i = 0; i<vecData.size();i++) {
            if (sortType > 0)
                if (fileData.elementAt(i).getSortFile() > 0)
                    temp.add(vecData.get(i).remove(0));
                else
                    temp.add(vecData.get(i).remove(vecData.get(i).size()-1));
            else {
                if (fileData.elementAt(i).getSortFile() > 0)
                    temp.add(vecData.get(i).remove(vecData.get(i).size()-1));
                else
                    temp.add(vecData.get(i).remove(0));
            }

        }

        while (countEndFiles>0) {
            if (sortType > 0){
                int index = 0;
                min = temp.get(0);
                for (int i=1; i<temp.size();i++){
                    if (temp.get(i) < min){
                        min = temp.get(i);
                        index = i;
                    }
                }
                finalBuffer.add(temp.remove (index));
                if (fileData.elementAt(index).getSortFile()>0){
                    try{
                        int tempInteger = vecData.get(index).remove(0);
                        temp.add(index, tempInteger);
                    }
                    catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                        if (!tempBoolean)
                        {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        }
                        else
                            temp.add(index, vecData.get(index).remove(0));
                    }

                }
                else{
                    try{
                        int tempInteger = vecData.get(index).remove(vecData.get(index).size()-1);
                        temp.add(index, tempInteger);
                    }
                    catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                        if (!tempBoolean)
                        {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        }
                        else
                            temp.add(index, vecData.get(index).remove(vecData.get(index).size()-1));
                    }
                }

            }
            else {
                int index = 0;
                max = temp.get(0);
                for (int i=1; i<temp.size();i++){
                    if (temp.get(i) > max){
                        max = temp.get(i);
                        index = i;
                    }
                }
                finalBuffer.add(temp.remove (index));
                if (fileData.elementAt(index).getSortFile()>0){
                    try{
                        int tempInteger = vecData.get(index).remove(vecData.get(index).size()-1);
                        temp.add(index, tempInteger);
                    }
                    catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                        if (!tempBoolean)
                        {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        }
                        else
                            temp.add(index, vecData.get(index).remove(vecData.get(index).size()-1));
                    }
                }
                else{
                    try{
                        int tempInteger = vecData.get(index).remove(0);
                        temp.add(index, tempInteger);
                    }
                    catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        boolean tempBoolean = fileData.elementAt(index).getBuffer(vecData.get(index));
                        if (!tempBoolean)
                        {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        }
                        else
                            temp.add(index, vecData.get(index).remove(0));
                    }
                }

            }
            if (finalBuffer.size()==5){
                try{
                    uploadingFile.upload(finalBuffer);
                }catch (IOException ioException){}
                finalBuffer.clear();
            }
        }
        try{
            uploadingFile.upload(finalBuffer);
            uploadingFile.closeWriter();
        }catch (IOException ioException){}
        finalBuffer.clear();
    }
}
