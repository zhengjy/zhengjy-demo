package com.zhengjy.test.design.中介者模式.t2;

public class Test {
	public static void main(String[] args) {
		AbstractColleague a1 =new ColleagueA();
		AbstractColleague a2 = new ColleagueB();
		AbstractMediator am = new Mediator(a1, a2);
		System.out.println("==========通过设置A影响B==========");  
		a1.setNumber(1000, am);  
        System.out.println("collA的number值为："+a1.getNumber());  
        System.out.println("collB的number值为A的10倍："+a2.getNumber());  
		
	}
}

abstract class AbstractColleague{
	protected int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	abstract void setNumber(int number,AbstractMediator am);
}

class ColleagueA extends AbstractColleague {
	@Override
	void setNumber(int number, AbstractMediator am) {
		super.number=number;
		am.affectA();
	}
	
}

class ColleagueB extends AbstractColleague {
	@Override
	void setNumber(int number, AbstractMediator am) {
		super.number=number;
		am.affectB();
	}
}

abstract class AbstractMediator{
	protected AbstractColleague a;
	protected AbstractColleague b;
	public AbstractMediator(AbstractColleague a, AbstractColleague b){
		this.a = a;
		this.b = b;
	}
	public abstract void affectA();
	public abstract void affectB();
}

class Mediator extends AbstractMediator {

	public Mediator(AbstractColleague a, AbstractColleague b) {
		super(a, b);
	}

	@Override
	public void affectA() {
		int number =super.a.getNumber();
		super.b.setNumber(number*100);
	}

	@Override
	public void affectB() {
		int number = super.b.getNumber();
		super.a.setNumber(number/100);
	}
	
}



