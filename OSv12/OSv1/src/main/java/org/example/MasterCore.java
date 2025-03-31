package org.example;

import java.util.*;


import static org.example.ReadyQueue.ReadyQueue;

public class MasterCore extends Thread {

  static   SlaveCore slaveCore1=new SlaveCore("core1");
  static   SlaveCore slaveCore2=new SlaveCore("core2");

    public static void roundRobin(int quantum){
      Process process;
    int countCore1=0;
    int countCore2=0;
      while(!ReadyQueue.isEmpty()){
          showQueue(ReadyQueue);
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          if(!ReadyQueue.isEmpty()) {

              if (slaveCore1.isIdle) {
                  slaveCore1.currentProcess = ReadyQueue.poll();
                  slaveCore1.isIdle = false;
                  slaveCore1.run();
                  countCore1=1;
              }else{
                  if(countCore1>=quantum){

                  ReadyQueue.add(slaveCore1.currentProcess);
                  slaveCore1.currentProcess = ReadyQueue.poll();
                  slaveCore1.run();
                  countCore1=1;

              }else {
                  slaveCore1.run();
                  countCore1++;
              }
                }
          }
            if(!ReadyQueue.isEmpty()) {
                if (slaveCore2.isIdle) {
                    slaveCore2.currentProcess = ReadyQueue.poll();
                    slaveCore2.isIdle = false;
                    slaveCore2.run();
                    countCore2=1;
                }else{ if(countCore2>=quantum){
                    ReadyQueue.add(slaveCore2.currentProcess);
                    slaveCore2.currentProcess = ReadyQueue.poll();
                    slaveCore2.run();
                    countCore2=1;
                }else {
                    slaveCore2.run();
                    countCore2++;
                }
                }
            }

      }
      while (!slaveCore1.isIdle || !slaveCore2.isIdle) {
          if (!slaveCore1.isIdle) {
              slaveCore1.run();
          }
          if (!slaveCore2.isIdle) {
              slaveCore2.run();
          }
      }
  }
    public static void roundRobinSRT(int quantum){
        PriorityQueue<Process> q=new PriorityQueue<Process>();
       while (!ReadyQueue.isEmpty())
           q.add(ReadyQueue.poll());

        Process process;
        int countCore1=0;
        int countCore2=0;
        while(!q.isEmpty()){
            showQueue(q);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!q.isEmpty()) {

                if (slaveCore1.isIdle) {
                    slaveCore1.currentProcess = q.poll();
                    slaveCore1.isIdle = false;
                    slaveCore1.run();
                    countCore1=1;
                }else{ if(countCore1>=quantum){

                    ReadyQueue.add(slaveCore1.currentProcess);
                    slaveCore1.currentProcess = q.poll();
                    slaveCore1.run();
                    countCore1=1;

                }else {
                    slaveCore1.run();
                    countCore1++;
                }
                }
            }
            while (!ReadyQueue.isEmpty())
                q.add(ReadyQueue.poll());
            if(!q.isEmpty()) {
                if (slaveCore2.isIdle) {
                    slaveCore2.currentProcess = q.poll();
                    slaveCore2.isIdle = false;
                    slaveCore2.run();
                    countCore2=1;
                }else{ if(countCore2>=quantum){
                    ReadyQueue.add(slaveCore2.currentProcess);
                    slaveCore2.currentProcess = q.poll();
                    slaveCore2.run();
                    countCore2=1;
                }else {
                    slaveCore2.run();
                    countCore2++;
                }
                }
            }
            while (!ReadyQueue.isEmpty())
                q.add(ReadyQueue.poll());

        }
        while (!slaveCore1.isIdle || !slaveCore2.isIdle) {
            if (!slaveCore1.isIdle) {
                slaveCore1.run();
            }
            if (!slaveCore2.isIdle) {
                slaveCore2.run();
            }
        }
    }
    public static void ShortestJobFirst(){
        PriorityQueue<Process> SJF=new PriorityQueue<Process>();
        while(!ReadyQueue.isEmpty()){
            SJF.add(ReadyQueue.poll());
        }

        while(!SJF.isEmpty()){
            showQueue(SJF);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(slaveCore1.isIdle){
                slaveCore1.isIdle=false;
                slaveCore1.currentProcess=SJF.poll();
                slaveCore1.run();

            }
            else{
                slaveCore1.run();
            }

            if(slaveCore2.isIdle){
                slaveCore2.isIdle=false;
                slaveCore2.currentProcess=SJF.poll();
                slaveCore2.run();

            }
            else{
                slaveCore2.run();
            }

        }
        while(slaveCore1.isIdle==false||slaveCore2.isIdle==false){
            if(slaveCore1.isIdle==false){
                slaveCore1.run();
            }
            if(slaveCore2.isIdle==false){
                slaveCore2.run();
            }
  }



}


    public static void showQueue(Queue<Process> q){
		Queue<Process> temp=new LinkedList<Process>();
		int size=q.size();
		System.out.println("readyQueue");
		for(int i=0;i<size;i++){
			Process t=q.remove();
			System.out.print(t.pcb.getID());
			if(i!=size-1)
				System.out.print("->");
			temp.add(t);
		}
		while(!temp.isEmpty())
			q.add(temp.remove());
		System.out.println();
	}
public static void main(String[] args) {

}
}
