package com.ignite.ignite.repo;

import com.ignite.ignite.responseModel.WorkoutClass;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkoutClassRepository {
    private List<WorkoutClass> classes = new ArrayList<>();

    public WorkoutClass save(WorkoutClass workoutClass) {
        classes.add(workoutClass);
        return workoutClass;
    }

    public List<WorkoutClass> findAll() {
        return new ArrayList<>(classes);
    }
}
