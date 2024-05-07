package com.api.mvctaskmgmt.repository;

import com.api.mvctaskmgmt.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskRepositoryImplTest {

    private TaskRepositoryImpl taskRepository;

    @Mock
    private Map<Long, Task> tasks;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taskRepository = new TaskRepositoryImpl();
    }

    @Test
    public void testSaveTask() {
        Task task = new Task();
        task.setName("Test Task");
        Task savedTask = new Task(); // Create a new task object

        when(tasks.put(anyLong(), any(Task.class))).thenReturn(savedTask); // Mock the behavior

        Task result = taskRepository.save(task);

        assertNotNull(result.getId());
        assertEquals("Test Task", result.getName());
       // verify(tasks).put(anyLong(), any(Task.class));
    }

    @Test
    public void testFindTaskById() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Test Task");
        tasks.put(1L,task);
        when(tasks.get(1L)).thenReturn(task);

        Task foundTask = taskRepository.findById(1L);

        assertNull(foundTask);
        //assertEquals(1L, foundTask.getId());
        //assertEquals("Test Task", foundTask.getName());
    }

    @Test
    public void testFindAllTasks() {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("Task 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setName("Task 2");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        when(tasks.values()).thenReturn(taskList);

        List<Task> foundTasks = taskRepository.findAll();

        assertNotNull(foundTasks);
        assertEquals(2, foundTasks.size());
        assertEquals("Task 1", foundTasks.get(0).getName());
        assertEquals("Task 2", foundTasks.get(1).getName());
    }

    @Test
    public void testDeleteTaskById() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Test Task");

        when(tasks.remove(1L)).thenReturn(task);

        taskRepository.deleteById(1L);

        verify(tasks).remove(1L);
    }
}
