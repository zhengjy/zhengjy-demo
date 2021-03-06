package com.zhengjy.test.thread.实战java高并发.fork_join.t1;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
/**
 * Created by Jiyang.Zheng on 2018/6/7 16:07.
 */
/**
 * RecursiveTask 表示有返回值的计算
 */
public class FileRecursiveTask extends RecursiveTask<Map<String, Integer>> {

    private static final long serialVersionUID = 1L;
    private final List<String> contents;

    public FileRecursiveTask(File file) {
        System.out.println("处理文件：" + file.getAbsolutePath());
        try {
            contents = Files.readAllLines(Paths.get(file.toURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 业务逻辑处理，相当于map
     */
    @Override
    protected Map<String, Integer> compute() {
        Map<String, Integer> map = new HashMap<>();
        for(String content : contents){
            String[] strs = content.split(" ");
            for(String str : strs){
                if(map.containsKey(str)){
                    int val = map.get(str);
                    map.put(str, val+1);
                } else {
                    map.put(str, 1);
                }
            }
        }
        return map;
    }
}
