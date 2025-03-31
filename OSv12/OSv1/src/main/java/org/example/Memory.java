package org.example;

import java.util.*;
class Memory {
    static int memorySize=100;
    static int reLocatableRegester=0;
    static HashMap<Integer, Integer> memory= new HashMap<>();
    static HashMap<Integer, HashMap<String, String>> variables= new HashMap<>();
    public Memory(int size) {
        this.memorySize = size;
        memory = new HashMap<>();
        variables = new HashMap<>();
        reLocatableRegester =0;

    }
    public Memory() {
        this.memorySize = 200;
        memory = new HashMap<>();
        variables = new HashMap<>();
        reLocatableRegester =0;
    }
    public static void allocate(Process p ) {
        int start = reLocatableRegester;
        int end = p.noOfInstructions ;
        p.pcb.base= reLocatableRegester;
        p.pcb.limit=end;
        if (start >= 0 && start < memorySize && end >= 0 && end + reLocatableRegester < memorySize && start <= end + reLocatableRegester) {
            for (int i = start; i < end + reLocatableRegester; i++) {
                memory.put(i , p.pcb.ID);


            }
            HashMap<String, String> vars = new HashMap<>();
            variables.put(p.pcb.ID, vars);
        } else {
            System.out.println("Invalid memory allocation");
            System.out.println("Start:" + start + " End: " + end);
        }
        reLocatableRegester +=p.noOfInstructions;
    }
    // A method to store a variable and its value for a process
    public static void storeVariable(Process p, String var, String value) {
        // Check if the process ID exists in the variables map
        if (variables.containsKey(p.pcb.ID)) {
            // Get the variables HashMap for the process
            HashMap<String, String> vars = variables.get(p.pcb.ID);
            // Store the variable and its value in the HashMap
            vars.put(var, value);
        } else {
            // Print an error message
            System.out.println("Invalid process ID");
        }
    }
    public static void printMemory() {
        for (int i = 0; i < memorySize; i++) {
            System.out.println("Index: " + i + " " + memory.get(i));

        }
        System.out.println();
    }
    public static void printVariables() {
        System.out.println("Memory State:");
        for (Integer pid : variables.keySet()) {
            HashMap<String, String> vars = variables.get(pid);
            System.out.print("Process " + pid + ": ");
            for (String var : vars.keySet()) {
                String value = vars.get(var);
                System.out.print(var + " = " + value + ", ");
            }
            System.out.println();
        }
    }

}

