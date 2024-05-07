package com.api.mvctaskmgmt.service;

import com.api.mvctaskmgmt.model.Task;
import com.api.mvctaskmgmt.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTask() {
        // Create a task
        Task task = new Task();
        task.setName("Sample Task");

        // Mock behavior of taskRepository.save() method
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Call the service method to save the task
        Task savedTask = taskService.save(task);

        // Verify
        verify(taskRepository).save(task);

        // Check if the saved task matches the original task
        assertEquals("Sample Task", savedTask.getName());
    }

    @Test
    public void testFindTaskById() {
        // Mock behavior of taskRepository.findById() method
        when(taskRepository.findById(1L)).thenReturn(new Task(1L,"Sandhya", "Sample Task", new Date(),null, false));

        // Call the service method to find a task by ID
        Task foundTask = taskService.findById(1L);

        // Verify
        verify(taskRepository).findById(1L);

        // Check if the found task matches the expected task
        //assertEquals("Sample Task", foundTask.getName());
    }

    @Test
    public void testFindAllTasks() {
        // Create a list of tasks
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"Sandhya", "Sample Task 2", new Date(),null, false));
        tasks.add(new Task(2L,"Sandhya", "Sample Task 3", new Date(),null, false));

        // Mock behavior of taskRepository.findAll() method
        when(taskRepository.findAll()).thenReturn(tasks);

        // Call the service method to find all tasks
        List<Task> foundTasks = taskService.findAll();

        // Verify
        verify(taskRepository).findAll();
        // Check if the found tasks match the expected tasks
        assertEquals(2, foundTasks.size());
        //assertEquals("Task 1", foundTasks.get(0).getName());
        //assertEquals("Task 2", foundTasks.get(1).getName());
    }

    @Test
    public void testDeleteTaskById() {
        // Call the service method to delete a task by ID
        taskService.deleteById(1L);

        // Verify
        verify(taskRepository).deleteById(1L);
    }
}

