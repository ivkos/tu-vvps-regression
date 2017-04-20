package com.ivkos.tu.vvps.regression.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataPointTest
{
   private static final double DOUBLE_DELTA = 1e-1;

   private static final double W = 0;
   private static final double X = 1;
   private static final double Y = 2;
   private static final double Z = 3;

   private DataPoint dp;

   @Before
   public void setUp() throws Exception
   {
      dp = new DataPoint(W, X, Y, Z);
   }

   @Test
   public void getW() throws Exception
   {
      assertEquals(W, dp.getW(), DOUBLE_DELTA);
   }

   @Test
   public void getX() throws Exception
   {
      assertEquals(X, dp.getX(), DOUBLE_DELTA);
   }

   @Test
   public void getY() throws Exception
   {
      assertEquals(Y, dp.getY(), DOUBLE_DELTA);
   }

   @Test
   public void getZ() throws Exception
   {
      assertEquals(Z, dp.getZ(), DOUBLE_DELTA);
   }
}
