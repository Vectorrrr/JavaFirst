package logic.service;

import model.TextSearchResult;
import view.reader.FileReader;
import view.reader.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by igladush on 19.02.16.
 *
 * Class implements CalcService
 * That class created for find some string in the some files
 *
 * @author Gladush Ivan
 * @see CalcService
 *
 */
public class SearchService implements CalcService {
    private final String LENGTH_WITH_WORDS = "\t\t\t";
    private String path;
    private String regex;
    private String startString;
    private File mainFile;
    private Pattern pattern;


    public SearchService() {

    }

    /**
     * Method take string, parse this string and than
     * find all file in the path and return string this file
     * which contain regex
     *
     * @param startString String need to be in this format ("path" "regex")
     * @value all files that contain regex
     */
    public String calculate(String startString) {
        this.startString = startString.trim();
        findPath();
        findRegex();
        mainFile = fileFactory(path);
        createPattern();
        StringBuilder sb = new StringBuilder();
        for (TextSearchResult tSR : doSearch(mainFile)) {
            sb.append(tSR.getFileName()).append(LENGTH_WITH_WORDS).append(tSR.getLine()).append("\n");
        }
        return sb.toString();
    }


    private List<TextSearchResult> doSearch(File mainFile) {
        if (!mainFile.exists()) {
            throw new IllegalStateException("You input incorrect file ");
        }
        if (mainFile.isDirectory()) {
            List<TextSearchResult> result = new ArrayList<>();
            String s[] = mainFile.list();
            if (s == null) {
                return result;
            }
            for (char i = 0; i < s.length; ++i) {
                File f = fileFactory(mainFile + "/" + s[i]);

                if (f.isDirectory()) {
                    result.addAll(doSearch(f));
                } else {
                    result.addAll(searchInFile(f));
                }
            }
            return result;

        } else {
            return searchInFile(mainFile);
        }

    }


    private void createPattern() {
        try {
            pattern = Pattern.compile(regex);
        } catch (Exception e) {
            throw new IllegalStateException("You input incorrect regex");
        }
    }

    private File fileFactory(String path) {
        String Os = getOs();
        if ("Linux".equals(Os) || "Mac OS X".equals(Os)) {
            path = path.replaceAll(":", ";");
            path = path.replaceAll("\\\\" + "\\\\", "/");
            //todo how make this easier
            return new File(path);
        } else if (Os != null && Os.contains("Windows")) {
            path = path.replaceAll(";", ":");
            path = path.replaceAll("\\\\" + "\\\\", "/");
            return new File(path);
        } else {
            throw new IllegalStateException("I don't know your OC");
        }
    }

    /**
     * Method highlights path from input string
     */
    private void findPath() {
        int temp = findFirstChar('(');
        if (temp < 8) {
            throw new IllegalStateException("You input incorrect data!!! If you wnat use findText function write her ubsent all sing before");
        }

        //delete findText and first open braket;
        startString = startString.substring(temp + 1, startString.length()).trim();
        temp = findFirstChar(1, '"');

        path = startString.substring(1, temp).trim();
        startString = startString.substring(temp + 1).trim();


    }

    /**
     * Method highlights regex from input string
     */
    private void findRegex() {
        int first = findFirstChar('"');
        int last = findLastChar('"');
        if (first == -1) {
            throw new IllegalStateException("You input incorrect data! You don't input regex");
        }
        if (first == last) {
            throw new IllegalStateException("You input incorrect data! You don't input regex");
        }
        regex = startString.substring(first + 1, last);
    }

    //return fist entry index ch or -1 if ch exist
    private int findFirstChar(char ch) {
        for (int i = 0; i < startString.length(); ++i) {
            if (ch == startString.charAt(i)) {
                return i;
            }
        }
        return -1;
    }

    private int findFirstChar(int startPosition, char ch) {
        for (; startPosition < startString.length(); ++startPosition) {
            if (ch == startString.charAt(startPosition)) {
                return startPosition;
            }
        }
        return -1;
    }

    //return last entry index ch or -1 if ch exist
    private int findLastChar(char ch) {
        for (int i = startString.length() - 1; i >= 0; --i) {
            if (ch == startString.charAt(i)) {
                return i;
            }
        }
        return -1;
    }

    private String getOs() {
        return System.getProperty("os.name");
    }

    /**
     * Method for search in specific  file
     * regex. Return all string where method find this regex.
     * If file NotFount method throw ne exception FileNotFound
     * @param file Certain file
     * @value All string that contain regex
     * @exception FileNotFoundException
     *
     * */
    private List<TextSearchResult> searchInFile(File file) {
        //todo where I need think about close file
        List<TextSearchResult> result = new ArrayList<>();
        if (!file.canRead()) {
            return result;
        }
        if (!file.exists()) {
            return result;
        }
        try (Reader read = new FileReader(file.getPath())) {
            if (read == null) {
                return result;
            }
            System.out.println("READ FILE " + file.getAbsolutePath());

            while (read.canRead()) {
                String string = read.getString();
                Matcher mch = pattern.matcher(string);
                if (mch.matches()) {
                    result.add(new TextSearchResult(file.getPath(), string));
                }
            }
            //  read.close();
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("CAN't READ!");
            return result;
        } catch (IOException e) {
            System.err.print(e + file.getAbsolutePath());
            throw new IllegalStateException("I can't read this file", e);
        }


    }
}
