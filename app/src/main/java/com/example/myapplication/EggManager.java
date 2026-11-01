package com.example.myapplication;

public class EggManager
{
    private MainActivity ma;
    private int count;
    private String n;
    private String password;
    public EggManager(MainActivity ma)
    {
        this.ma = ma;
        this.count = 10;
        this.password = password;
    }
    public int getCount(){return count;}
    public void setCount(int count){this.count = count;}
    public String getName(){return n;}
    public void setName(String name){this.n = name;}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isItTime()
    {
        return count < 1;
    }
}
