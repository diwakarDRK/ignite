package com.ignite.ignite.controllers;

import com.ignite.ignite.responseModel.WorkoutClass;
import com.ignite.ignite.services.WorkoutClassService;
import com.ignite.ignite.request.ClassCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class WorkoutClassController {

    @Autowired
    private WorkoutClassService classService;

    @PostMapping
    public ResponseEntity<WorkoutClass> createClass(
            @Valid @RequestBody ClassCreateRequest request) {
        WorkoutClass createdClass = classService.createClass(request);
        return ResponseEntity.ok(createdClass);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutClass>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }
}
