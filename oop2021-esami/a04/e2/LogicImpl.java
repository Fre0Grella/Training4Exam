package a04.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class LogicImpl implements Logic {

    int size;
    List<String> clickedValues = new ArrayList<>();
    int lastPos=-1;

    

    public LogicImpl(int size) {
        this.size = size;
    }

    

    @Override
    public boolean isPossible(int pos, String value) {
        String operator = "+-*";

        

        if(lastPos == -1 || pos+1 == lastPos || pos-1 == lastPos || pos+size == lastPos || pos-size == lastPos) {

            if (clickedValues.isEmpty()) {
                if(!operator.contains(value)) {
                    lastPos = pos;
                    return true;
                }
                return false;
            }
            if (operator.contains(clickedValues.get(clickedValues.size()-1)) && !operator.contains(value)) {
                lastPos = pos;
                return true;
            }
            if (!operator.contains(clickedValues.get(clickedValues.size()-1)) && operator.contains(value)) {
                lastPos = pos;
                return true;
            }
        }
        return false;
    }

    @Override
    public int calcResult() {
        if(clickedValues.isEmpty()) {
            return 0;
        }

        int usefulSign = clickedValues.size();
        int result=Integer.parseInt(clickedValues.get(0));
        if(usefulSign%2 == 0) {
            usefulSign--;
        }

        for(int i = 1; i < usefulSign; i=i+2) {
            if (clickedValues.get(i).contains("+")) {
                result = result + Integer.parseInt(clickedValues.get(i+1));
            }
            if (clickedValues.get(i).contains("-")) {
                result = result - Integer.parseInt(clickedValues.get(i+1));
            }
            if (clickedValues.get(i).contains("*")) {
                result = result * Integer.parseInt(clickedValues.get(i+1));
            }
        }
        
        return result;
    }



    @Override
    public List<String> init() {
        List<String> valueList = new ArrayList<>();
        int grid = (size*size);
        int split = grid/2;
        int num = 0;
        for(int i=0; i<grid; i++) {
            if(RandomGenerator.getDefault().nextBoolean() && num < split) {
                valueList.add(String.valueOf(new Random().nextInt(10)));
                num++;
            } else {
               var a = RandomGenerator.getDefault().nextBoolean();
               if(a) {
                valueList.add("+");
               } else if (new Random().nextBoolean()) {
                valueList.add("-");
               } else {
                valueList.add("*");
               }
            }

        }
        return valueList;
    }



    @Override
    public void addValue(String value) {
        clickedValues.add(value);  
    }
    
}
