package com.zhengjy.test.java8;

import com.zhengjy.tag.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by zhengjy on 2017/6/19.
 */
public class Test2 {

    public static void main(String[] args) {
//        new Thread(() -> System.out.println("x")   ).start();
        List<Test> l = new ArrayList<>();
        l.add(new Test(1L,"xx"));
        l.add(new Test(5L,"xx2"));
        l.add(new Test(3L,"xx3"));
        l.add(new Test(7L,"xx4"));

       /* System.out.println("Languages which starts with J :");
        System.out.println(languages.stream().filter((str)-> str.startsWith("Jf")).collect(Collectors.toList()));

        List<Integer> values = Arrays.asList(4,5,6,7,8);
        values.stream().filter(i->{
            System.out.println("Hello");
            return false;
        });*/
        List<Long> ls = l.stream().map(Test::getId).collect(Collectors.toList());

        System.out.println(ls);



        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(6, 60, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(2, 20, Transaction.Type.A));
        transactions.add(new Transaction(4, 40, Transaction.Type.C));

        // JDK 8 如果发现type为grocery的所有交易, 然后返回以交易值降序排序的交易ID集合
        List<Integer> transactionsIds =
                transactions.parallelStream().filter(t -> t.getType() == Transaction.Type.GEOCERY)
                        .sorted(Comparator.comparing(Transaction::getValue).reversed())
                        .map(Transaction::getId)
                        .collect(Collectors.toList());
        System.out.println(transactionsIds);//[6, 5, 3, 1]

    }
    public static class Test{
        private Long id;
        private String name;

        public Test(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

class Transaction {
    private final int id;
    private final Integer value;
    private final Type type;

    public Transaction(int id, Integer value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public enum Type {
        A, B, C, D, GEOCERY
    }

    public int getId() {return id;}
    public Integer getValue() {return value;}
    public Type getType() {return type;}
}