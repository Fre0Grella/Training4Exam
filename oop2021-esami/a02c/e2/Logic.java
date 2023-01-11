package a02c.e2;

import java.util.List;

public interface Logic {

    List<Integer> init();

    List<String> returnMap();

    int returnUp();

    boolean isOver();

    int drop();

    int getCurrPos();
}
