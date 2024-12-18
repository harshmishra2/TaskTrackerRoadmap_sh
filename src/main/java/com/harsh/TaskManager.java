package com.harsh;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    private List<Task> tasks;
    private TaskStorageHandler storageHandler;

    public TaskManager() throws IOException{
        storageHandler = new TaskStorageHandler();
        tasks = storageHandler.loadTasks();
    }

    public void addTask(String description) throws IOException{
        int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() -1 ).getId() +1;
        Task newTask = new Task(id, description, "todo", LocalDateTime.now(), LocalDateTime.now());
        tasks.add(newTask);
        storageHandler.saveTasks(tasks);
        System.out.println("Task has been added successfully, task ID: "+ id+ ".");
    }

    public void updateTask(int id, String newDescription) throws IOException{
        Optional<Task> taskOptional  = tasks.stream().filter(task -> task.getId() ==id).findFirst();
        if(taskOptional.isPresent()){
            Task task = taskOptional.get();
            task.setDescription(newDescription);
            task.setUpdatedAt(LocalDateTime.now());
            storageHandler.saveTasks(tasks);
            System.out.println("Task updated successfully.");
        }else{
            System.out.println("No task found with the listed ID.");
        }
    }

    public void listTasks(String status){
        String finalStatus = status;
        tasks.stream().filter(task -> finalStatus == null || task.getStatus().equalsIgnoreCase(finalStatus))
        .forEach(System.out::println);
    }
}