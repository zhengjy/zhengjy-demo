package com.zhengjy.test.design.demo.命令模式.t3;

import com.zhengjy.test.design.demo.命令模式.t2.Command;

public class RemoteContorl {
	Command[] onCommand;
	Command[] offCommand;
	Command undoCommand;
	
	public RemoteContorl(){
		onCommand =new Command[7];
		offCommand = new Command[7];
	}
	
	public void setCommand(int slot, Command on, Command off){
		onCommand[slot] = on;
		offCommand[slot] =off;
	}
	
	public void onButtonWasPushed(int slot){
		onCommand[slot].execute();
		undoCommand =onCommand[slot];
	}
	
	public void offButtonWasPushed(int slot){
		offCommand[slot].execute();
		undoCommand = offCommand[slot];
	}
	
	public Command undo(){
		return undoCommand;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n----------------------\n");
		for(int i=0;i<onCommand.length;i++){
			sb.append("[slot"+i+"]"+onCommand[i].getClass().getName()+"          "+offCommand[i].getClass().getName()+"\n");
		}
		
		return sb.toString();
	}
	
}
