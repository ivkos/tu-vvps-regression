package com.ivkos.tu.vvps.regression.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BetaResultTest
{
   private static final double DOUBLE_DELTA = 1e-1;

   private static final double BETA_0 = 0;
   private static final double BETA_1 = 1;
   private static final double BETA_2 = 2;
   private static final double BETA_3 = 3;

   private BetaResult betaResult;

   @Before
   public void setUp() throws Exception
   {
      betaResult = new BetaResult(BETA_0, BETA_1, BETA_2, BETA_3);
   }

   @Test
   public void getBeta0() throws Exception
   {
      assertEquals(BETA_0, betaResult.getBeta0(), DOUBLE_DELTA);
   }

   @Test
   public void getBeta1() throws Exception
   {
      assertEquals(BETA_1, betaResult.getBeta1(), DOUBLE_DELTA);
   }

   @Test
   public void getBeta2() throws Exception
   {
      assertEquals(BETA_2, betaResult.getBeta2(), DOUBLE_DELTA);
   }

   @Test
   public void getBeta3() throws Exception
   {
      assertEquals(BETA_3, betaResult.getBeta3(), DOUBLE_DELTA);
   }
}
