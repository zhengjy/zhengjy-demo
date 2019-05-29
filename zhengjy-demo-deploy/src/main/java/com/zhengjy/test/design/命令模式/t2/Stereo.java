package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by zhengjy on 2019/5/27.
 */
public class Stereo {
    private String name;
    public Stereo(String name){
        this.name = name;
    }

    public void on(){
        System.out.println(name+"Stereo on");
    }

    public void off(){
        System.out.println(name+"Stereo off");
    }

    public void setCD(){
        System.out.println(name+"Stereo setCD");
    }

    public void setVolume(int num){
        System.out.println(name+"Stereo volume set to "+num);

    }

    public void onDefault(int num){
        on();
        setCD();
        setVolume(11);
    }
}
