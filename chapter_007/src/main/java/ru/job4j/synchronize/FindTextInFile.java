package ru.job4j.synchronize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * class for search text in file system.
 */
public class FindTextInFile {
    /**
     * root for search begin.
     */
    private String root;
    /**
     * searching text.
     */
    private String text;
    /**
     * file extends, where we search text.
     */
    private List<String> exts;
    /**
     * Condition: if list is empty -> return from recursion.
     */
    private ArrayList<File> exitFromRecursion = new ArrayList<>();
    /**
     * list of files with right extendes, where we will search text.
     */
    private BlockingQueue<String> listOfFilesForSearch = new LinkedBlockingQueue<>();
    /**
     * result list with files, containing text.
     */
    private ArrayList<String> listOfContainingFiles = new ArrayList<>();
    /**
     * getter of listOfContainingFiles.
     * @return - listOfContainingFiles.
     */
    public ArrayList<String> getListOfContainingFiles() {
        return this.listOfContainingFiles;
    }

    /**
     * Constructor.
     * @param root - root.
     * @param text - text.
     * @param exts - extends.
     */
    FindTextInFile(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Chose files in root and in included directories with right file extends.
     * @param root - root.
     */
    synchronized void listOfFiles(String root) {
        File file = new File(root);
        String[] filesList = null;
        exitFromRecursion.add(file);
        while (!exitFromRecursion.isEmpty()) {
            if (file.isDirectory()) {
                filesList = file.list();
            }
            if (filesList != null && filesList.length != 0) {
                boolean exitIfNoDir = false;
                for (int i = 0; i < filesList.length; i++) {
                    File tempFile = new File(file.getAbsolutePath() + "/" + filesList[i]);
                    if (tempFile.isFile()) {
                        for (String nextExt : exts) {
                            if (filesList[i].contains(nextExt)) {
                                listOfFilesForSearch.add(file.getAbsolutePath() + "/" + filesList[i]);
                            }
                        }
                    }
                    if (tempFile.isDirectory()) {
                        listOfFiles(file.getAbsolutePath() + "/" + filesList[i]);
                        exitIfNoDir = true;
                    }
                }
                if (!exitIfNoDir) {
                    exitFromRecursion.clear();
                }
            }
        }
    }

    /**
     * Search text in list of files.
     * @param text - searching text.
     * @return - files, where text is contained.
     * @throws IOException - exception.
     */
    List<String> result(String text) throws IOException {
        if (listOfFilesForSearch.size() == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (listOfFilesForSearch.size() > 0) {
            String st = listOfFilesForSearch.poll();
            File fileToRead = new File(st);
            FileReader fr = new FileReader(fileToRead);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(text)) {
                    listOfContainingFiles.add(st);
                    break;
                }
            }
        }
        return listOfContainingFiles;
    }

}
