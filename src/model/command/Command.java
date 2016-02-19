package model.command;

import java.util.Deque;

/**
 * Created by igladush on 19.02.16.
 */
public interface Command {
    void apply(Deque<Double> sequence);
}
