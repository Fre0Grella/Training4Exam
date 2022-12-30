package a03a.e1;

import java.util.ArrayList;
import java.util.List;

public class RWControllersFactoryImpl implements RWControllersFactory {

    @Override
    public RWController createPermissive() {
        return new RWController() {

            private int id = -1;
            private List<Boolean> accessList = new ArrayList<>();
            @Override
            public int enter() {
                accessList.add(false);
                return ++id;
            }

            @Override
            public void goRead(int id) {
                if (accessList.get(id)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, true);
                }
                
            }

            @Override
            public void goWrite(int id) {
                if (accessList.get(id)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, true);
                }
            }

            @Override
            public void exit(int id) {
                if (!accessList.get(id)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, false);
                }
            }
            
        };
    }

    @Override
    public RWController createOnlyRead() {
        return new RWController() {

            private int id = -1;
            private List<Boolean> accessList = new ArrayList<>();
            @Override
            public int enter() {
                accessList.add(false);
                return ++id;
            }

            @Override
            public void goRead(int id) {
                throw new IllegalStateException();
                /*if (accessList.get(id)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, true);
                }*/
                
            }

            @Override
            public void goWrite(int id) {
                if (accessList.get(id)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, true);
                }
            }

            @Override
            public void exit(int id) {
                if (!accessList.get(id)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, false);
                }
            }
            
        };
    }

    @Override
    public RWController createMutualExclusion() {
        return new RWController() {

            private int id = -1;
            private List<Boolean> accessList = new ArrayList<>();
            @Override
            public int enter() {
                accessList.add(false);
                return ++id;
            }

            @Override
            public void goRead(int id) {
                if (accessList.stream().anyMatch(x -> x == true)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, true);
                }
                
            }

            @Override
            public void goWrite(int id) {
                if (accessList.stream().anyMatch(x -> x == true)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, true);
                }
            }

            @Override
            public void exit(int id) {
                if (!accessList.get(id)) {
                    throw new IllegalStateException();
                } else {
                    accessList.set(id, false);
                }
            }
            
        };
    }

    @Override
    public RWController createManyReadersOrOneWriter() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
