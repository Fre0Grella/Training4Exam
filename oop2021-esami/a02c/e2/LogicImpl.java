package a02c.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {

    int size;
    int xPos;
    List<Integer> map = new ArrayList<>();
    Set<Integer> rock = new HashSet<>();

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public int returnUp() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isOver() {
        if(xPos%size == 0 && map.get(xPos+1) != 0 && map.get(xPos+size) != 0) {
            return true;
        }
        if((xPos-(size-1))%size == 0 && map.get(xPos-1) != 0 && map.get(xPos+size) != 0) {
            return true;
        }
        if(map.get(xPos-1) != 0 && map.get(xPos+1) != 0 && map.get(xPos+size) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public int drop() {
        map.set(xPos, 0);
        if(map.get(xPos+size) != 0) {
            var dor = new Random().nextBoolean();
            if(dor && map.get(xPos+1) == 0 || (xPos-(size-1))%size == 0) {
                xPos++;
                map.set(xPos, 1);
                return xPos;
            }
            if(map.get(xPos-1) == 0 || xPos%size == 0) {
                xPos--;
                map.set(xPos, 1);
                return xPos;
            }
        }
        while(map.get(xPos+size) == 0) {
            xPos=xPos+size;
            
        }
        map.set(xPos, 1);
        return xPos;
    }

    @Override
    public List<Integer> init() {
        while(rock.size()<20) {
            rock.add(new Random().nextInt(10, 101));
            
        }
        int x = new Random().nextInt(10);

        for(int i = 0; i<size*size; i++) {
            if(x == i) {
                map.add(1);
                xPos = x;
            } else
            if (rock.contains(i)) {
                map.add(-1);
            } else {
                map.add(0);
            }
            }

        
        return map;
    }

    @Override
    public List<String> returnMap() {
        List<String> stringMap = new ArrayList<>();
        for (Integer x : map) {
            if(x == 0) {
                stringMap.add(" ");
            }
            if(x<0) {
                stringMap.add("o");
            }
            if(x>0) {
                stringMap.add("*");
            }
        }
        return stringMap;
    }

    @Override
    public int getCurrPos() {
        int pos = xPos;
        return pos;
    }
    
}
