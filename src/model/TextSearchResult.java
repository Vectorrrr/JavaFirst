package model;

/**
 * Created by igladush on 19.02.16.
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

