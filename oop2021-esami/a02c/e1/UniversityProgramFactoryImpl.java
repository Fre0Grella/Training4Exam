package a02c.e1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    @Override
    public UniversityProgram flexible() {
        return new UniversityProgram() {

            Map<String, Pair<Set<Group>, Integer>> courses;


        @Override
        public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
            this.courses = new HashMap<>(courses);
        }

            @Override
            public boolean isValid(Set<String> courseNames) {
                return courses.entrySet().stream()
                    .filter(x -> courseNames.contains(x.getKey()))
                    .map(x -> x.getValue())
                    .map(x -> x.get2())
                    .reduce((a, b) -> a + b).get() == 48;
                
            }
        };
    }

    @Override
    public UniversityProgram fixed() {
        return new UniversityProgram() {

            Map<String, Pair<Set<Group>, Integer>> courses;


        @Override
        public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
            this.courses = new HashMap<>(courses);
        }

            @Override
            public boolean isValid(Set<String> courseNames) {

                List<Pair<Set<Group>, Integer>> list = courses.entrySet().stream()
                    .filter(x -> courseNames.contains(x.getKey()))
                    .map(x -> x.getValue()).toList();
            int opz=0;
            int mand=0;
            int thes=0;
            int tot=0;
                for (Pair<Set<Group>,Integer> pair : list) {
                    tot+=pair.get2();
                    if(pair.get1().contains(Group.OPTIONAL)) {
                        opz += pair.get2();
                    }
                    if(pair.get1().contains(Group.MANDATORY)) {
                        mand += pair.get2();
                    }
                    if(pair.get1().contains(Group.THESIS)) {
                        thes += pair.get2();
                    }
                }

                return tot == 60 && opz == 36 && thes == 12 && mand == 12;
            }
        };
    }

    @Override
    public UniversityProgram balanced() {
         return new UniversityProgram() {

            Map<String, Pair<Set<Group>, Integer>> courses;


        @Override
        public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
            this.courses = new HashMap<>(courses);
        }

            @Override
            public boolean isValid(Set<String> courseNames) {

                List<Pair<Set<Group>, Integer>> list = courses.entrySet().stream()
                    .filter(x -> courseNames.contains(x.getKey()))
                    .map(x -> x.getValue()).toList();
            int opz=0;
            int mand=0;
            int thes=0;
            int tot=0;
            int free =0;
                for (Pair<Set<Group>,Integer> pair : list) {
                    tot+=pair.get2();
                    if(pair.get1().contains(Group.OPTIONAL)) {
                        opz += pair.get2();
                    }
                    if(pair.get1().contains(Group.MANDATORY)) {
                        mand += pair.get2();
                    }
                    if(pair.get1().contains(Group.THESIS)) {
                        thes += pair.get2();
                    }
                    if(pair.get1().contains(Group.FREE)) {
                        free += pair.get2();
                    }
                }

                return tot == 60 && opz >= 24 && thes <= 12 && mand == 24 && free <= 12;
            }
        };
    }

    @Override
    public UniversityProgram structured() {
        return new UniversityProgram() {

            Map<String, Pair<Set<Group>, Integer>> courses;


        @Override
        public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
            this.courses = new HashMap<>(courses);
        }

            @Override
            public boolean isValid(Set<String> courseNames) {

                List<Pair<Set<Group>, Integer>> list = courses.entrySet().stream()
                    .filter(x -> courseNames.contains(x.getKey()))
                    .map(x -> x.getValue()).toList();
            int opzA=0;
            int opzB=0;
            int mand=0;
            int thes=0;
            int tot=0;
            int free =0;
                for (Pair<Set<Group>,Integer> pair : list) {
                    tot+=pair.get2();
                    if(pair.get1().contains(Group.OPTIONAL_A)) {
                        opzA += pair.get2();
                    }
                    if(pair.get1().contains(Group.OPTIONAL_B)) {
                        opzB += pair.get2();
                    }
                    if(pair.get1().contains(Group.MANDATORY)) {
                        mand += pair.get2();
                    }
                    if(pair.get1().contains(Group.THESIS)) {
                        thes += pair.get2();
                    }
                    if(pair.get1().contains(Group.FREE)) {
                        free += pair.get2();
                    }
                }

                return tot == 60 && opzA >= 6 && opzB >= 6 && opzA+opzB >= 30  && mand == 12 && free+thes == 18;
            }
        };
    }
    
}
