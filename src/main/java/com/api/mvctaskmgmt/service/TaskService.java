package com.api.mvctaskmgmt.service;

import com.api.mvctaskmgmt.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Task save(Task task);
    Task findById(Long id);
    List<Task> findAll();
    void deleteById(Long id);
}
