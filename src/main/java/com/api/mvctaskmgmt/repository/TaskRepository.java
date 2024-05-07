package com.api.mvctaskmgmt.repository;

import com.api.mvctaskmgmt.model.Task;

import java.util.List;

public interface TaskRepository {
    Task save(Task task);
    Task findById(Long id);
    List<Task> findAll();
    void deleteById(Long id);

}
