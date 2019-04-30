package com.zhengjy.test.java8.asyn;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;

/**
 * Created by Jiyang.Zheng on 2018/10/17 15:22.
 */
public class Shop {
    List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    public Shop(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                '}';
    }

    /**

     */
    public double getPrice(String product){
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product){
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));

//        //创建CompletableFuture对象，他会包含计算结果
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        new Thread(() ->{
//            try {
//                //在另一个线程中以异步方式执行计算
//                double d = calculatePrice(product);
//                //需要长时间计算的任务结束并返回结果，设置future返回值
//                futurePrice.complete(d);
//            }catch (Exception e){
//                futurePrice.completeExceptionally(e);
//            }
//
//        }).start();
//        //无需等待直接还没结束的计算，直接返回futrue对象
//        return futurePrice;
    }

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    private double calculatePrice(String product){
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);

    }

    public static void delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop("s1");

        long start = System.nanoTime();

        Future<Double> futurePrice = shop.getPriceAsync("my shop as");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");

        //执行更多任务
        sleep();

        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

    }
    public static void  sleep(){
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
