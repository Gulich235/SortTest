package sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    private UploadingFile uploadingFile;
    private int countEndFiles;

    public void sortString(List<FileDownload> fileData, int sortType, String outFile) {
        try {
            uploadingFile = new UploadingFile(outFile);
        } catch (IOException ioException) {
            System.out.println("Лох");
        }
        List<String> temp = new ArrayList<>(fileData.size());
        List<String> finalBuffer = new ArrayList<>();
        List<List<String>> data = new ArrayList<>();
        String min;
        String max;
        countEndFiles = fileData.size();
        for (int i = 0; i < fileData.size(); i++) {
            data.add(new ArrayList<>());
            fileData.get(i).getBufferString(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            if (sortType > 0)
                if (fileData.get(i).getSortFile() > 0)
                    temp.add(data.get(i).remove(0));
                else
                    temp.add(data.get(i).remove(data.get(i).size() - 1));
            else {
                if (fileData.get(i).getSortFile() > 0)
                    temp.add(data.get(i).remove(data.get(i).size() - 1));
                else
                    temp.add(data.get(i).remove(0));
            }

        }

        while (countEndFiles > 0) {
            int index = 0;
            if (sortType > 0) {
                min = temp.get(0);
                for (int i = 1; i < temp.size(); i++) {
                    if (temp.get(i).compareTo(min) < 0) {
                        min = temp.get(i);
                        index = i;
                    }
                }
                finalBuffer.add(temp.remove(index));
                if (fileData.get(index).getSortFile() > 0) {
                    try {
                        String tempString = data.get(index).remove(0);
                        temp.add(index, tempString);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferString(data.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            data.remove(index);
                        } else
                            temp.add(index, data.get(index).remove(0));
                    }

                } else {
                    try {
                        String tempString = data.get(index).remove(data.get(index).size() - 1);
                        temp.add(index, tempString);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferString(data.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            data.remove(index);
                        } else
                            temp.add(index, data.get(index).remove(data.get(index).size() - 1));
                    }
                }

            } else {
                max = temp.get(0);
                for (int i = 1; i < temp.size(); i++) {
                    if (temp.get(i).compareTo(max) > 0) {
                        max = temp.get(i);
                        index = i;
                    }
                }
                finalBuffer.add(temp.remove(index));
                if (fileData.get(index).getSortFile() > 0) {
                    try {
                        String tempString = data.get(index).remove(data.get(index).size() - 1);
                        temp.add(index, tempString);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferString(data.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            data.remove(index);
                        } else
                            temp.add(index, data.get(index).remove(data.get(index).size() - 1));
                    }
                } else {
                    try {
                        String tempString = data.get(index).remove(0);
                        temp.add(index, tempString);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferString(data.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            data.remove(index);
                        } else
                            temp.add(index, data.get(index).remove(0));
                    }


                }

            }
            if (finalBuffer.size() == 5) {
                try {
                    uploadingFile.uploadString(finalBuffer);
                } catch (IOException ioException) {
                    System.out.println("Ошибка выгрузки результирующих данных");
                }
                finalBuffer.clear();
            }
        }
        try {
            uploadingFile.uploadString(finalBuffer);
            uploadingFile.closeWriter();
        } catch (IOException ioException) {
            System.out.println("Ошибка выгрузки результирующих данных");
        }
        finalBuffer.clear();

    }


    public void sortInteger(List<FileDownload> fileData, int sortType, String outFile) {
        try {
            uploadingFile = new UploadingFile(outFile);
        } catch (IOException ioException) {
            System.out.println("Невозможно открыть файл");
        }
        List<Integer> temp = new ArrayList<>(fileData.size());
        List<Integer> finalBuffer = new ArrayList<>();
        List<List<Integer>> vecData = new ArrayList<>();
        int min;
        int max;
        countEndFiles = fileData.size();
        for (int i = 0; i < fileData.size(); i++) {
            vecData.add(new ArrayList<>());
            fileData.get(i).getBufferInteger(vecData.get(i));
        }
        for (int i = 0; i < vecData.size(); i++) {
            if (sortType > 0)
                if (fileData.get(i).getSortFile() > 0)
                    temp.add(vecData.get(i).remove(0));
                else
                    temp.add(vecData.get(i).remove(vecData.get(i).size() - 1));
            else {
                if (fileData.get(i).getSortFile() > 0)
                    temp.add(vecData.get(i).remove(vecData.get(i).size() - 1));
                else
                    temp.add(vecData.get(i).remove(0));
            }

        }

        while (countEndFiles > 0) {
            int index = 0;
            if (sortType > 0) {
                min = temp.get(0);
                for (int i = 1; i < temp.size(); i++) {
                    if (temp.get(i) < min) {
                        min = temp.get(i);
                        index = i;
                    }
                }
                finalBuffer.add(temp.remove(index));
                if (fileData.get(index).getSortFile() > 0) {
                    try {
                        int tempInteger = vecData.get(index).remove(0);
                        temp.add(index, tempInteger);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferInteger(vecData.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        } else
                            temp.add(index, vecData.get(index).remove(0));
                    }

                } else {
                    try {
                        int tempInteger = vecData.get(index).remove(vecData.get(index).size() - 1);
                        temp.add(index, tempInteger);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferInteger(vecData.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        } else
                            temp.add(index, vecData.get(index).remove(vecData.get(index).size() - 1));
                    }
                }

            } else {
                max = temp.get(0);
                for (int i = 1; i < temp.size(); i++) {
                    if (temp.get(i) > max) {
                        max = temp.get(i);
                        index = i;
                    }
                }
                finalBuffer.add(temp.remove(index));
                if (fileData.get(index).getSortFile() > 0) {
                    try {
                        int tempInteger = vecData.get(index).remove(vecData.get(index).size() - 1);
                        temp.add(index, tempInteger);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferInteger(vecData.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        } else
                            temp.add(index, vecData.get(index).remove(vecData.get(index).size() - 1));
                    }
                } else {
                    try {
                        int tempInteger = vecData.get(index).remove(0);
                        temp.add(index, tempInteger);
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        boolean tempBoolean = fileData.get(index).getBufferInteger(vecData.get(index));
                        if (!tempBoolean) {
                            countEndFiles--;
                            fileData.remove(index);
                            vecData.remove(index);
                        } else
                            temp.add(index, vecData.get(index).remove(0));
                    }
                }

            }
            if (finalBuffer.size() == 5) {
                try {
                    uploadingFile.uploadInteger(finalBuffer);
                } catch (IOException ioException) {
                    System.out.println("Ошибка выгрузки результирующих данных");
                }
                finalBuffer.clear();
            }
        }
        try {
            uploadingFile.uploadInteger(finalBuffer);
            uploadingFile.closeWriter();
        } catch (IOException ioException) {
            System.out.println("Ошибка выгрузки результирующих данных");
        }
        finalBuffer.clear();
    }
}
