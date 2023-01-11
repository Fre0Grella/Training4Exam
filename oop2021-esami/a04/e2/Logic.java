package a04.e2;

import java.util.List;

public interface Logic {

    List<String> init();

    boolean isPossible(int pos, String value);

    void addValue(String value);

    int calcResult();
}
