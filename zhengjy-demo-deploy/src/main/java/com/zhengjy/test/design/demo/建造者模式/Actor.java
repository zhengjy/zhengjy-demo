package com.zhengjy.test.design.demo.建造者模式;

/**
 * Created by zhengjy on 2017/5/7.
 */
public class Actor {
    private  String type; //角色类型
    private  String sex; //性别
    private  String face; //脸型
    private  String costume; //服装
    private  String hairstyle; //发型

    public  void setType(String type) {
        this.type  = type;
    }
    public  void setSex(String sex) {
        this.sex  = sex;
    }
    public  void setFace(String face) {
        this.face  = face;
    }
    public  void setCostume(String costume) {
        this.costume  = costume;
    }
    public  void setHairstyle(String hairstyle) {
        this.hairstyle  = hairstyle;
    }
    public  String getType() {
        return  (this.type);
    }
    public  String getSex() {
        return  (this.sex);
    }
    public  String getFace() {
        return  (this.face);
    }
    public  String getCostume() {
        return  (this.costume);
    }
    public  String getHairstyle() {
        return  (this.hairstyle);
    }
}

//角色建造器：抽象建造者
abstract class ActorBuilder
{
    protected  Actor actor = new Actor();

    public  abstract void buildType();
    public  abstract void buildSex();
    public  abstract void buildFace();
    public  abstract void buildCostume();
    public  abstract void buildHairstyle();

    //工厂方法，返回一个完整的游戏角色对象
    public Actor createActor()
    {
        return actor;
    }
}

//英雄角色建造器：具体建造者
class HeroBuilder extends ActorBuilder
{
    public  void buildType()
    {
        actor.setType("英雄");
    }
    public  void buildSex()
    {
        actor.setSex("男");
    }
    public  void buildFace()
    {
        actor.setFace("英俊");
    }
    public  void buildCostume()
    {
        actor.setCostume("盔甲");
    }
    public  void buildHairstyle()
    {
        actor.setHairstyle("飘逸");
    }
}

//天使角色建造器：具体建造者
class AngelBuilder extends ActorBuilder
{
    public  void buildType()
    {
        actor.setType("天使");
    }
    public  void buildSex()
    {
        actor.setSex("女");
    }
    public  void buildFace()
    {
        actor.setFace("漂亮");
    }
    public  void buildCostume()
    {
        actor.setCostume("白裙");
    }
    public  void buildHairstyle()
    {
        actor.setHairstyle("披肩长发");
    }
}

//恶魔角色建造器：具体建造者
class DevilBuilder extends ActorBuilder
{
    public  void buildType()
    {
        actor.setType("恶魔");
    }
    public  void buildSex()
    {
        actor.setSex("妖");
    }
    public  void buildFace()
    {
        actor.setFace("丑陋");
    }
    public  void buildCostume()
    {
        actor.setCostume("黑衣");
    }
    public  void buildHairstyle()
    {
        actor.setHairstyle("光头");
    }
}

//游戏角色创建控制器：指挥者
class ActorController
{
    //逐步构建复杂产品对象
    public Actor construct(ActorBuilder ab)
    {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        ab.buildHairstyle();
        actor=ab.createActor();
        return actor;
    }
}

class Client
{
    public  static void main(String args[])
    {
        ActorBuilder ab; //针对抽象建造者编程
        ab =  Factory.getBean(1); //反射生成具体建造者对象

        ActorController ac = new  ActorController();
        Actor actor;
        actor = ac.construct(ab); //通过指挥者创建完整的建造者对象

        String  type = actor.getType();
        System.out.println(type  + "的外观：");
        System.out.println("性别：" + actor.getSex());
        System.out.println("面容：" + actor.getFace());
        System.out.println("服装：" + actor.getCostume());
        System.out.println("发型：" + actor.getHairstyle());
    }
}


class Factory{
    public static ActorBuilder getBean(int type){
        if(type == 1){
            //英雄角色建造器：具体建造者
            return new HeroBuilder();
        }else  if(type == 2){
            //天使角色建造器：具体建造者
            return new AngelBuilder();
        }else {
            //恶魔角色建造器
            return new DevilBuilder();
        }
    }

}