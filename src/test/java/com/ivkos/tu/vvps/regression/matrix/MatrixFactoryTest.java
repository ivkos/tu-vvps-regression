package com.ivkos.tu.vvps.regression.matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MatrixFactoryTest
{
   private AbstractMatrixFactory mf;

   @Before
   public void setUp()
   {
      mf = new MatrixFactory();
      assertNotNull(mf);
   }

   @Test
   public void create1D() throws Exception
   {
      Matrix mx1 = mf.create(new double[] { 1, 2, 3 });
      assertNotNull(mx1);
   }

   @Test
   public void create2D() throws Exception
   {
      Matrix mx2 = mf.create(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
      assertNotNull(mx2);
   }

   @Test(expected = NullPointerException.class)
   public void createWithNull1D() throws Exception
   {
      mf.create((double[]) null);
   }

   @Test(expected = NullPointerException.class)
   public void createWithNull2D() throws Exception
   {
      mf.create((double[][]) null);
   }
}
