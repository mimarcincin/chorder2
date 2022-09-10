package sk.mmarcincin.chorder2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.mmarcincin.chorder2.models.Task;
import sk.mmarcincin.chorder2.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;


    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(String taskId) {
        return taskRepository.findTaskById(taskId);
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(String taskId, Task task) {
        task.setId(taskId);
        taskRepository.save(task);

    }

    public void deleteTask(String taskId, Task task) {
        taskRepository.deleteById(taskId);
    }
}
