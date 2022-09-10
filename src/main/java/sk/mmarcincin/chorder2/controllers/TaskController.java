package sk.mmarcincin.chorder2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.mmarcincin.chorder2.models.Task;
import sk.mmarcincin.chorder2.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/findAllTasks")
    public List<Task> findAllTasks(){
        return taskService.findAllTasks();
    }

    @GetMapping(value = "/findTaskById")
    public Task findTaskById(String taskId){
        return taskService.findTaskById(taskId);
    }

    @PostMapping(value = "/createTask")
    public void createTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @PutMapping(value = "/updateTask/{taskId}")
    public void updateTask(@PathVariable String taskId, @RequestBody Task task){
        taskService.updateTask(taskId, task);
    }

    @DeleteMapping(value = "/deleteTask/{taskId}")
    public void deleteTask(@PathVariable String taskId, @RequestBody Task task){
        taskService.deleteTask(taskId, task);
    }
}
