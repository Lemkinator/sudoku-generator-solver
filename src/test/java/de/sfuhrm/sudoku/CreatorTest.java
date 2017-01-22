/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sfuhrm.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * @author Stephan Fuhrmann
 */
public class CreatorTest {
    @Test
    public void testGetSetBitOffsetWithNothingSet1() {
        int v = Creator.getSetBitOffset(0, 0);
        assertEquals(-1, v);
    }
    
    @Test
    public void testGetSetBitOffsetWithNothingSet2() {
        int v = Creator.getSetBitOffset(0, 1);
        assertEquals(-1, v);
    }
    
    @Test
    public void testGetSetBitOffsetWithTwoSet1() {
        int v = Creator.getSetBitOffset(3, 0);
        assertEquals(0, v);
    }    
    
    @Test
    public void testGetSetBitOffsetWithTwoSet2() {
        int v = Creator.getSetBitOffset(3, 1);
        assertEquals(1, v);
    }
    
    @Test
    public void testGetSetBitOffsetWithAll() {
        int v;
        
        v = Creator.getSetBitOffset(0xffffffff, 0);
        assertEquals(0, v);
        v = Creator.getSetBitOffset(0xffffffff, 1);
        assertEquals(1, v);
        v = Creator.getSetBitOffset(0xffffffff, 2);
        assertEquals(2, v);
        v = Creator.getSetBitOffset(0xffffffff, 3);
        assertEquals(3, v);
        v = Creator.getSetBitOffset(0xffffffff, 4);
        assertEquals(4, v);
        v = Creator.getSetBitOffset(0xffffffff, 5);
        assertEquals(5, v);
        v = Creator.getSetBitOffset(0xffffffff, 6);
        assertEquals(6, v);
        v = Creator.getSetBitOffset(0xffffffff, 7);
        assertEquals(7, v);
        v = Creator.getSetBitOffset(0xffffffff, 8);
        assertEquals(8, v);
    }
    
    
    @Test
    public void testCreateFull() {
        GameMatrix r = Creator.createFull();
        assertEquals(9*9, r.getSetCount());
        assertEquals(true, r.isValid());
    }
    
    @Test
    public void testCreateFullWithMultipleInvocations() {
//        for (int i=0; i < 100000; i++) {
        for (int i=0; i < 1000; i++) {
            GameMatrix r = Creator.createFull();
            assertEquals(9*9, r.getSetCount());
            assertEquals(true, r.isValid());
        }
    }
    
    @Test
    public void testCreateRiddle() {
        GameMatrix matrix = Creator.createFull();        
        Riddle riddle = Creator.createRiddle(matrix);
        for (int i=0; i < GameMatrix.SIZE; i++) {
            for (int j=0; j < GameMatrix.SIZE; j++) {
                if (riddle.get(i, j) != GameMatrix.UNSET) {
                    // all fields that are set are needed to be the same
                    assertEquals(matrix.get(i, j), riddle.get(i, j));
                    assertEquals(false, riddle.getWritable(i, j));
                } else {
                    assertEquals(true, riddle.getWritable(i, j));
                }
            }
        }
    }

    @Test
    public void testCreateNumbersToDistributeWithOnce() {
        byte v[] = Creator.createNumbersToDistribute(new Random(), 1);
        List<Integer> intList = Utility.toIntList(v);
        
        assertEquals(9, intList.size());
        assertEquals(Arrays.asList(1,2,3,4,5,6,7,8,9), intList);
    }
    
    @Test
    public void testCreateNumbersToDistributeWithTwice() {
        byte v[] = Creator.createNumbersToDistribute(new Random(), 2);
        List<Integer> intList = Utility.toIntList(v);
        
        assertEquals(2*9, intList.size());
        assertEquals(Arrays.asList(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9), intList);
    }
}
