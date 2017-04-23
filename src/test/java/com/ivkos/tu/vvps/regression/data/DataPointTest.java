package com.ivkos.tu.vvps.regression.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

   @Test
   public void equalsAndHashCode() throws Exception
   {
      DataPoint dp1 = new DataPoint(1, 2, 3, 4);
      DataPoint dp2 = new DataPoint(1, 2, 3, 4);
      assertTrue(dp1.equals(dp2));
      assertTrue(dp1.hashCode() == dp2.hashCode());

      DataPoint dp3 = new DataPoint(5, 6, 7, 8);
      assertFalse(dp1.equals(dp3));
      assertFalse(dp1.hashCode() == dp3.hashCode());
   }
}
