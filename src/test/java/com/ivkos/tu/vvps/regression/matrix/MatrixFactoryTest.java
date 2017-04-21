package com.ivkos.tu.vvps.regression.matrix;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MatrixFactoryTest
{
   @Test
   public void create() throws Exception
   {
      MatrixFactory mf = new MatrixFactory();

      Matrix mx1 = mf.create(new double[] { 1, 2, 3 });
      assertNotNull(mx1);

      Matrix mx2 = mf.create(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
      assertNotNull(mx2);
   }

   @Test(expected = NullPointerException.class)
   public void createWithNull() throws Exception
   {
      MatrixFactory mf = new MatrixFactory();

      mf.create((double[]) null);
   }

   @Test(expected = NullPointerException.class)
   public void createWithNull2() throws Exception
   {
      MatrixFactory mf = new MatrixFactory();

      mf.create((double[][]) null);
   }
}
