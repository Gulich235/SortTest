import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class UploadingFile {
    FileWriter fileWriter;

    UploadingFile(String fileName) throws IOException{
        fileWriter = new FileWriter(fileName);
    }

    public void upload (ArrayList data) throws IOException {
        for (int i=0; i<data.size();i++){
            fileWriter.write(data.get(i).toString()+"\n");
            fileWriter.flush();
        }

    }

    public void closeWriter()throws IOException {
        fileWriter.close();
    }
}
