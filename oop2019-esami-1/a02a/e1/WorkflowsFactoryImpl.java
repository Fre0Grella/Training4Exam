package a02a.e1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkflowsFactoryImpl implements WorkflowsFactory {

    @Override
    public <T> Workflow<T> singleTask(T task) {
        return new WorkFlowImpl<>(Set.of(new Pair<T,Integer>(task, 0)));
    }

    @Override
    public <T> Workflow<T> tasksSequence(List<T> tasks) {
        Set<Pair<T,Integer>> wfc = new HashSet<>();
        int i =0;
        for (T task : tasks) {
            wfc.add(new Pair<T,Integer>(task, i));
            i++;
        }
        return new WorkFlowImpl<>(wfc);
    }

    @Override
    public <T> Workflow<T> tasksJoin(Set<T> initialTasks, T finalTask) {
        Set<Pair<T,Integer>> wfc = new HashSet<>();
        int i =0;
        for (T task : initialTasks) {
            wfc.add(new Pair<T,Integer>(task, i));
        }
        i++;
        wfc.add(new Pair<T,Integer>(finalTask, i));
        return new WorkFlowImpl<>(wfc);
    }

    @Override
    public <T> Workflow<T> tasksFork(T initialTask, Set<T> finalTasks) {
        Set<Pair<T,Integer>> wfc = new HashSet<>();
        int i =0;
        wfc.add(new Pair<T,Integer>(initialTask, i));
        i++;
        for (T task : finalTasks) {
            wfc.add(new Pair<T,Integer>(task, i));
        }
        return new WorkFlowImpl<>(wfc);
    }

    @Override
    public <T> Workflow<T> concat(Workflow<T> first, Workflow<T> second) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
