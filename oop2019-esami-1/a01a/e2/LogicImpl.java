package a01a.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class LogicImpl implements Logic {
    private final static int MAXTRY = 5;

    int size;
    List<Integer> boat = new ArrayList<>();
    List<Integer> shootHistory = new ArrayList<>();

    public LogicImpl(int size) {
        this.size = size;  
    }

    @Override
    public void placeBoat(int boat) {
        int cordX = RandomGenerator.getDefault().nextInt(size-1-boat);
        int cordY = RandomGenerator.getDefault().nextInt(size-1);
        int pos = cordX + cordY*size;
        for (int i = 0; i < boat; i++) {
            this.boat.add(pos+i);
            System.out.println((cordX+i)+" "+cordY+"\n");
        }
    }

    @Override
    public boolean isHit(int pos) {
        shootHistory.add(pos);
        for (Integer boatPos : boat) {
            if (boatPos == pos) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOver() {

        return shootHistory.containsAll(boat) || shootHistory.stream().filter(x -> !boat.contains(x)).toList().size() >= MAXTRY;
    }
    //
}
