/*
 * (c) the authors Licensed under the Apache License, Version 2.0.
 */
package com.zhengjy.test;

import org.junit.Assert;
import org.junit.Test;
import org.roaringbitmap.RoaringBitmap;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Generic testing of the roaring bitmaps
 */
@SuppressWarnings({"static-method"})
public class TestRoaringBitmap {

    public static void main(String[] args) {
            RoaringBitmap rr =RoaringBitmap.bitmapOf(1,2,3,1000);
            RoaringBitmap rr2 =new RoaringBitmap();
            rr2.add(4000L,4255L);
            RoaringBitmap rror =RoaringBitmap.or(rr, rr2);// new bitmap rr.or(rr2); //in-place computationboolean equals = rror.equals(rr);// trueif(!equals) thrownewRuntimeException("bug");
            // number of values stored?long cardinality = rr.getLongCardinality();
            System.out.println();
            // a"forEach" is faster than this loop, but a loop is possible:for(int i : rr) {
    }

}
