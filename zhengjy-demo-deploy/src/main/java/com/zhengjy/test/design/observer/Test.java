package com.zhengjy.test.design.observer;

public class Test {
	public static void main(String[] args) {
		Watched w = new ConcreteWatched();
		Watcher w1 = new ConcreteWatcher();
		Watcher w2 = new ConcreteWatcher();
		Watcher w3 = new ConcreteWatcher();
		w.addWatched(w1);
		w.addWatched(w2);
		w.addWatched(w3);
		w.removeWatched(w3);
		w.notifyWatched("xxx");
	}
}
