package org.example;

import static org.example.MasterCore.*;

public class Main {
    public static void main(String[] args) {

        Process p = new Process("C:\\Users\\ASUS\\Desktop\\OSv12\\OSv1\\src\\main\\java\\org\\example\\program_1.txt");
        Process p1 = new Process("C:\\Users\\ASUS\\Desktop\\OSv12\\OSv1\\src\\main\\java\\org\\example\\Program_2.txt");
        Process p2 = new Process("C:\\Users\\ASUS\\Desktop\\OSv12\\OSv1\\src\\main\\java\\org\\example\\Program_3.txt");

        //ShortestJobFirst();
        roundRobinSRT(2);
    }
}