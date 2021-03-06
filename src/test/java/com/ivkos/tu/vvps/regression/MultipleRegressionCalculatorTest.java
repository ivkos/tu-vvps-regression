package com.ivkos.tu.vvps.regression;

import com.ivkos.tu.vvps.regression.data.BetaResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MultipleRegressionCalculatorTest
{
   private static final double PRECISION = 1e-1;

   @Test
   public void construct() throws Exception
   {
      MultipleRegressionCalculator calculator = new MultipleRegressionCalculator(new BetaResult(5, 6, 7, 8));
      assertNotNull(calculator);
   }

   @Test(expected = NullPointerException.class)
   public void constructWithNull() throws Exception
   {
      new MultipleRegressionCalculator(null);
   }

   @Test
   public void calculateZk() throws Exception
   {
      BetaResult betas = new BetaResult(5, 6, 7, 8);
      MultipleRegressionCalculator calculator = new MultipleRegressionCalculator(betas);

      double zk = calculator.calculateZk(2, 3, 4);
      assertEquals(70, zk, PRECISION);
   }

}
