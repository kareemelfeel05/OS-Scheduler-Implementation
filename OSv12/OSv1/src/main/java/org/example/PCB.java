package org.example;

public class PCB {
    int ID;
    int pc;
    int base;
    int limit;
    public PCB(){


    }
    public PCB(int ID,int pc,int base,int limit){
        this.ID=ID;
        this.pc=pc;
        this.base=base;
        this.limit=limit;

    }
    public int getID(){
        return ID;
    }
    public int getPC(){
        return pc;
    }
    public int getBase(){
        return base;
    }
    public int getLimit(){
        return limit;
    }

    public void setID(int ID){
        this.ID=ID;
    }
    public void setPC(int pc){
        this.pc=pc;
    }
    public void setBase(int base){
        this.base=base;
    }
    public void setLimit(int limit){
        this.limit=limit;
    }

}
