package a03a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic{

    private int size;
    private int curTargetPos;
    private int cordX;
    private int cordY;
    private Set<Integer> markedSet = new HashSet<>();

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public void markTheSpot(int pos) {
        markedSet.add(pos);
    }

    @Override
    public int moveTheX() {
        if(cordX < size-1 && cordY == 0) {
            cordX++;
        } else if (cordY < size-1 && cordX > 0) {
            cordY++;
        } else if (cordX > 0 ) {
            cordX--;
        } else if (cordY > 0) {
            cordY--;
        }

        curTargetPos = cordX + cordY*size;
        int temp = curTargetPos;
        System.out.println(cordX+" "+cordY);
        return temp;
    }

    @Override
    public boolean isOver() {
        return markedSet.stream().anyMatch(o -> o == curTargetPos);
    }

    @Override
    public int getLastXPos() {
        return curTargetPos;
    }

    
}
