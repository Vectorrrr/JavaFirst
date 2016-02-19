package view.reader;

import java.io.Closeable;

/**
 * Created by igladush on 17.02.16.
 */
public interface Reader extends AutoCloseable,Closeable{
    String getString();
    boolean canRead();
    void close();
}
