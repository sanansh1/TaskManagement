package com.api.mvctaskmgmt.repository;

import com.api.mvctaskmgmt.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class TaskRepositoryImpl implements TaskRepository{
    private Map<Long, Task> tasks = new HashMap<>();
    private Long idCounter = 0L;

    @Override
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(++idCounter);
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Task findById(Long id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteById(Long id) {
        tasks.remove(id);
    }

}
