package com.harsh;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        // System.out.println( "Hello World!" );
        TaskManager taskManager = new TaskManager();
        if(args.length<1){
            System.out.println("Usage: task-cli <command>[arguments]");
            return;
        }
        String command = args[0];

        switch(command){
            case "add":
                if(args.length <2){
                    System.out.println("Usage: task-cli add <description>");
                }
                else{
                    String description= args[1];
                    taskManager.addTask(description);
                }
                break;
            
            case "list":
                String status = args.length > 1 ? args[1]:null;
                taskManager.listTasks(status);
            break;

            default:
                System.out.println("unknown command: "+command);
        }
    }
}