package view.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by igladush on 18.02.16.
 */
public class FileReader implements Reader {
    private final static String STRING_EMPTY = "";
    private Scanner sc;
    private String temp=null;
    public FileReader(String path) throws FileNotFoundException {
        if (!(new File(path).exists())) {
            throw new FileNotFoundException("Your file did't found!!!");
        }
        sc = new Scanner(new File(path));
    }

    @Override
    public String getString() {
        return temp;


    }

    @Override
    public boolean canRead() {
        if(!sc.hasNext()){
            return false;
        }
        temp= sc.nextLine();
        while(sc.hasNext() && (temp==null||STRING_EMPTY.equals(temp)) ){
            temp= sc.nextLine();
        }
        if(temp==null||STRING_EMPTY.equals(temp)){
            return false;
        }
        return true;
    }


    @Override
    public void close() {
        sc.close();
    }
}
