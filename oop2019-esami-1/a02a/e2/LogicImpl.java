package a02a.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicImpl implements Logic {
    private int size;
    private List<Integer> XPos = new ArrayList<>();
    private int first;
    private int mid;
    private int last;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public List<Integer> setXPosition() {
        if (XPos.isEmpty()) {
            first = 0;
            mid = size/2;
            last = size-1;
        } else{
            if (last == 0){
                int temp;
                temp = first;
                first = last;
                last = temp;
            }
            first++;
            last--;
        } 
        List<Integer> newXPos = new ArrayList<>(CreateNewXpos()); 
        XPos = newXPos;
        return newXPos;
    }

    private List<Integer> CreateNewXpos() {
        List<Integer> newXPos = new ArrayList<>();
        newXPos.add(first + size*first);
        newXPos.add(mid + size*first);
        newXPos.add(last + size*first);

        newXPos.add(first + size*mid);
        newXPos.add(last + size*mid);

        newXPos.add(first + size*last);
        newXPos.add(mid + size*last);
        newXPos.add(last + size*last);

        return newXPos;
    }

    
}
