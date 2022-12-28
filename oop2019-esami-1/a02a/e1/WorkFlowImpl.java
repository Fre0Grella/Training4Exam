package a02a.e1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorkFlowImpl<T> implements Workflow<T> {

    Map<T,Pair<Boolean, Integer>> taskMultiMap = new HashMap<>();

    public WorkFlowImpl(Set<Pair<T,Integer>> tasks) {
        for (Pair<T,Integer> task : tasks) {
            taskMultiMap.put(task.getX(), new Pair<Boolean, Integer>(false, task.getY()));
        }
    }

    @Override
    public Set<T> getTasks() {
        return taskMultiMap.keySet();
    }

    @Override
    public Set<T> getNextTasksToDo() {
        int temp=(int) taskMultiMap.keySet().stream().count();
        Set<T> nextTask = new HashSet<>();
        for (T task : taskMultiMap.keySet()) {
            if (!taskMultiMap.get(task).getX()) {
                if (taskMultiMap.get(task).getY() < temp) {
                    temp = taskMultiMap.get(task).getY();
                }
            }
        }
        for (T t : taskMultiMap.keySet()) {
            if(taskMultiMap.get(t).getY() == temp && !taskMultiMap.get(t).getX()) {
                nextTask.add(t);
            }
        }
        return nextTask;
    }

    @Override
    public void doTask(T t) {
       taskMultiMap.put(t, new Pair<Boolean,Integer>(true, taskMultiMap.get(t).getY())) ;
    }

    @Override
    public boolean isCompleted() {
        for (boolean done : taskMultiMap.values().stream().map(x -> x.getX()).toList()) {
            if(!done) {
                return false;
            }
        }
        return true;
    }
    
}
