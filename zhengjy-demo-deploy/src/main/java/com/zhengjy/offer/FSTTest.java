package com.zhengjy.offer;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRef;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util;

/**
 * Created by Jiyang.Zheng on 2018/7/20 9:41.
 */
public class FSTTest {

    public static void main(String[] args) {
        try {
            String inputValues[] = {"cat", "deep", "do", "dog", "dogs"};
            long outputValues[] = {5, 7, 17, 18, 21};
            PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton(true);
            Builder<Long> builder = new Builder<>(FST.INPUT_TYPE.BYTE1, outputs);
            BytesRef scratchBytes = new BytesRef();
            IntsRef scratchInts = new IntsRef();
            for (int i = 0; i < inputValues.length; i++) {
                scratchBytes.copyChars(inputValues[i]);
                builder.add(Util.toIntsRef(scratchBytes, scratchInts), outputValues[i]);
            }
            FST<Long> fst = builder.finish();
            Long value = Util.get(fst, new BytesRef("dog"));
            System.out.println(value); // 18
        } catch (Exception e) {
            ;
        }
    }
}
