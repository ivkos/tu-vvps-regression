package com.ivkos.tu.vvps.regression.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

   @Test
   public void equalsAndHashCode() throws Exception
   {
      BetaResult br1 = new BetaResult(1, 2, 3, 4);
      BetaResult br2 = new BetaResult(1, 2, 3, 4);
      assertTrue(br1.equals(br2));
      assertTrue(br1.hashCode() == br2.hashCode());

      BetaResult br3 = new BetaResult(5, 6, 7, 8);
      assertFalse(br1.equals(br3));
      assertFalse(br1.hashCode() == br3.hashCode());
   }

   @Test
   public void toAString() throws Exception
   {
      String str = betaResult.toString();
      assertNotNull(str);
      assertFalse(str.contains("@"));
   }
}
