package sort;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileDownload {
    private final String fileName;
    private FileReader reader;
    private Scanner scan;
    private boolean endOfFile = false;
    private final int sortFile;
    private int size;
    private final int sortType;

    FileDownload(String name, int sortFile, int sortType) throws FileNotFoundException {
        fileName = name;
        this.sortType = sortType;
        reader = new FileReader(name);
        scan = new Scanner(reader);
        this.sortFile = sortFile;
        if (sortType > 0) {
            if (sortFile < 0) {
                while (scan.hasNextLine()) {
                    scan.nextLine();
                    size++;
                    try {
                        reader.close();
                    } catch (IOException ioException) {
                        System.out.println("Ошибка при закрытии файла");
                    }
                }
                reader = new FileReader(name);
                scan = new Scanner(reader);
            }
        } else {
            if (sortFile > 0) {
                while (scan.hasNextLine()) {
                    scan.nextLine();
                    size++;
                    try {
                        reader.close();
                    } catch (IOException ioException) {
                        System.out.println("Ошибка при закрытии файла");
                    }
                }
                reader = new FileReader(name);
                scan = new Scanner(reader);
            }
        }
    }


    public void downloadInteger(List<Integer> buffer) throws IOException {
        int count = 0;
        if (sortType > 0) {
            if (sortFile > 0) {
                while (scan.hasNextLine() && count < 5) {
                    buffer.add(Integer.parseInt(scan.nextLine()));
                    count++;
                }
                if (!scan.hasNextLine()) {
                    endOfFile = true;
                    reader.close();
                }
            } else {
                while (scan.hasNextLine() && size > 0) {
                    if (count > size - 6 & count < size) {
                        buffer.add(Integer.parseInt(scan.nextLine()));
                    } else {
                        scan.nextLine();
                    }
                    count++;
                }
                size -= 5;
                reader.close();
                if (size < 0)
                    endOfFile = true;
                else {
                    reader = new FileReader(fileName);
                    scan = new Scanner(reader);
                }
            }
        } else {
            if (sortFile < 0) {
                while (scan.hasNextLine() && count < 5) {
                    buffer.add(Integer.parseInt(scan.nextLine()));
                    count++;
                }
                if (!scan.hasNextLine()) {
                    endOfFile = true;
                    reader.close();
                }
            } else {
                while (scan.hasNextLine() && size > 0) {
                    if (count > size - 6 & count < size)
                        buffer.add(Integer.parseInt(scan.nextLine()));
                    else {
                        scan.nextLine();
                    }
                    count++;
                }

                size -= 5;
                reader.close();
                if (size < 1)
                    endOfFile = true;
                else {
                    reader = new FileReader(fileName);
                    scan = new Scanner(reader);
                }
            }
        }
    }

    public void downloadString(List<String> buffer) throws IOException {
        int count = 0;
        if (sortType > 0) {
            if (sortFile > 0) {
                while (scan.hasNextLine() && count < 5) {
                    buffer.add(scan.nextLine());
                    count++;
                }
                if (!scan.hasNextLine()) {
                    endOfFile = true;
                    reader.close();
                }
            } else {
                while (scan.hasNextLine() && size > 0) {
                    if (count > size - 6 & count < size)
                        buffer.add(scan.nextLine());
                    else {
                        scan.nextLine();
                    }
                    count++;
                }
                size -= 5;
                reader.close();
                if (size < 0)
                    endOfFile = true;
                else {
                    reader = new FileReader(fileName);
                    scan = new Scanner(reader);
                }
            }
        } else {
            if (sortFile < 0) {
                while (scan.hasNextLine() && count < 5) {
                    buffer.add(scan.nextLine());
                    count++;
                }
                if (!scan.hasNextLine()) {
                    endOfFile = true;
                    reader.close();
                }
            } else {
                while (scan.hasNextLine() && size > 0) {
                    if (count > size - 6 & count < size)
                        buffer.add(scan.nextLine());
                    else {
                        scan.nextLine();
                    }
                    count++;
                }

                size -= 5;
                reader.close();
                if (size < 1)
                    endOfFile = true;
                else {
                    reader = new FileReader(fileName);
                    scan = new Scanner(reader);
                }
            }
        }
    }


    public boolean getBufferInteger(List<Integer> userBuffer) {
        if (!endOfFile) {
            try {
                downloadInteger(userBuffer);
            } catch (IOException ioException) {
                userBuffer.clear();
            }
            return true;
        } else
            return false;
    }

    public boolean getBufferString(List<String> userBuffer) {
        if (!endOfFile) {
            try {
                downloadString(userBuffer);
            } catch (IOException ioException) {
                userBuffer.clear();
            }
            return true;
        } else
            return false;
    }

    public int getSortFile() {
        return sortFile;
    }

}
