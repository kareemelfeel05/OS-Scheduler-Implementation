package org.example;

import java.io.*;
import java.util.*;
public class Process  implements Comparable<Process> {
    String filepath;
    ArrayList<ArrayList<String>> instructions;
    PCB pcb;
    int noOfInstructions;
    static int noOfProgram;
    int burst;
    public Process(String filepath) {
        this.filepath = filepath;
        pcb = new PCB();

        try {
            ProgramParser(filepath);
        }
        catch (Exception e)
        {
            System.out.println("program file not found\n"+e.getMessage()+"\n");

        }

        ReadyQueue.ReadyQueue.add(this);

    }
    public void ProgramParser(String filePath) throws IOException {
        BufferedReader program = new BufferedReader(new FileReader(filePath));

         try
        {
            program = new BufferedReader(new FileReader(filePath));
        }
        catch(Exception e)
        {
            System.out.println("file not found");
        }
         instructions = new ArrayList<ArrayList<String>>();
         int i = 0;
        String line;
        while ((line = program.readLine()) != null) {
            ArrayList<String> words = new ArrayList<String>(Arrays.asList(line.split(" ")));
            if(words.size()>1){
            instructions.add(words);
            i++;


            System.out.println("Instruction " + i + ": " + words);
            }
        }
//if(instructions.getLast().isEmpty())
//    instructions.remove(instructions.size()-1);



        pcb.ID=noOfProgram++;
        noOfInstructions=instructions.size();
        burst=noOfInstructions;
        pcb.pc=0;
        pcb.base=0;
        pcb.limit=noOfInstructions;
        Memory.allocate(this); //doesnt work yet
        program.close();
    }
public static void main(String[] args) {

        Process p = new Process("file1.txt");
       // System.out.println(p.instructions);
    }

    @Override
    public int compareTo(Process o) {
        return this.burst-o.burst;
    }
}
