package model;

/**
 * Created by igladush on 19.02.16.
 * This class is container for answer in SearchService
 *
 * @see logic.service.SearchService
 * @author Gladush Ivan
 */

public class TextSearchResult {


    private String fileName;
    private String line;

    public TextSearchResult(String filePath, String line) {
        this.fileName = filePath;
        this.line = line;
    }

    public String getFileName() {
        return fileName;
    }

    public String getLine() {
        return line;
    }
}

