package com.zhengjy.test.thread;

public class Demo1{
	public static void main(String[] args) {
		Depot depot=  new Depot(100);
		Producer procuder = new Producer(depot);
		Consumer consumer =new Consumer(depot);
		procuder.producer(60);
		procuder.producer(120);
		consumer.consumer(90);
		consumer.consumer(150);
		procuder.producer(30);
		
	}
}

class Depot {
   private int capacity;//仓库容量100个
   private int size;//仓库的实际数量

   public Depot(int capacity){
       this.capacity = capacity;
       this.size=0;
   }



   public synchronized void producer(int val) {
       try{
           //left 表示想要生产的数量
           int left = val;
           while(left >0){
               //库存已满
               while(size >=capacity){
                   wait();
               }
               /*
                * 获取实际生产的数量（即库存中新增的数量）
                * 如果库存+想要生产的数量>总的容量，则实际容量=总的容量-当前容量（此时填满仓库）
                * 否则实际增量=想要生产的数量
                */
               //
               int inc = (size+left) >capacity ? (capacity-size) :capacity;
               size += inc;
               left -= inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",  Thread.currentThread().getName(), val, left, inc, size);
               // 通知“消费者”可以消费了。
               notifyAll();
           }
       }catch(Exception e){
       }finally{

       }
   }

   public synchronized void consumer(int val) {
       try{
           //left表示客户要消费的数量
           int left = val;
           while(left > 0){
               //库存为0的时候等待生产者生产产品
               while(size <= 0){
                   wait();
               }
               /*
                * 获取实际消费的数量（即库存中实际减少的数量）
                * 如果库存小于客户要消费的数量，则实际消费数量=库存
                * 否则，实际消费数量=客户要消费的数量
                */
               int dec =(size<left) ?size:left;
               size -= dec;
               left -= dec;
               System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
               notifyAll();
           }
       }catch(Exception e){

       }
   }

   public String toString() {
       return "capacity:"+capacity+", actual size:"+size;
   }
}

/**
 * 生产者
 * @author zhengjy
 *
 */
class Producer{
	private Depot depot;
	public Producer(Depot depot){
		this.depot = depot;
	}
	//消费产品：新建线程向仓库中生产产品。
	public void producer(final int val){
		new Thread(){
			public void run(){
				depot.producer(val);
			}
		}.start();
	}
}

/**
 *  消费者
 * @author zhengjy
 *
 */
class Consumer{
	private Depot depot;
	
	public Consumer(Depot depot){
		this.depot = depot;
	}
	//消费产品：新建一个线程从仓库中消费产品
	public void consumer(final int val){
		new Thread(){
			public void run(){
				depot.consumer(val);
			}
		}.start();
	}
}


