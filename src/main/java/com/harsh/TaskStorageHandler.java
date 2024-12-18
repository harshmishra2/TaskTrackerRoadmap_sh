package com.harsh;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskStorageHandler {
    private static final String FILE_PATH= "tasks.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Task> loadTasks() throws IOException{
        File file = new File(FILE_PATH);
        if(!file.exists()){
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
    }

    public void saveTasks(List<Task> tasks) throws IOException{
        objectMapper.writeValue(new File(FILE_PATH), tasks);
    }
}
