package com.ignite.ignite.services;

import com.ignite.ignite.responseModel.WorkoutClass;
import com.ignite.ignite.repo.WorkoutClassRepository;
import com.ignite.ignite.request.WorkoutClassCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutClassService {
    @Autowired
    private WorkoutClassRepository classRepository;

    public WorkoutClass createClass(WorkoutClassCreateRequest request) {
        // Additional business logic validation
        validateClassCreation(request);

        WorkoutClass workoutClass = new WorkoutClass();
        workoutClass.setName(request.getName());
        workoutClass.setStartDate(request.getStartDate());
        workoutClass.setEndDate(request.getEndDate());
        workoutClass.setStartTime(request.getStartTime());
        workoutClass.setDurationMinutes(request.getDurationMinutes());
        workoutClass.setCapacity(request.getCapacity());

        return classRepository.save(workoutClass);
    }

    private void validateClassCreation(WorkoutClassCreateRequest request) {
        //For given date range class already exist(Business Logic can be added)
    
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }

    public List<WorkoutClass> getAllClasses() {
        return classRepository.findAll();
    }
}
