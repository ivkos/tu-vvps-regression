package com.ivkos.tu.vvps.regression.matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest
{
   private static double[][] ARR_2D = {
         { 9, 3, 4 },
         { 4, 3, 4 },
         { 1, 1, 1 }
   };

   private static double[] ARR_1D = {
         7,
         8,
         3
   };

   private static double[] ARR_RESULT = {
         -1.0 / 5,
         4,
         -4.0 / 5
   };

   private AbstractMatrixFactory matrixFactory;
   private Matrix m2d;
   private Matrix m1d;

   @Before
   public void setUp()
   {
      matrixFactory = new MatrixFactory();

      m2d = matrixFactory.create(ARR_2D);
      assertNotNull(m2d);

      m1d = matrixFactory.create(ARR_1D);
      assertNotNull(m1d);
   }

   @Test
   public void getData() throws Exception
   {
      Matrix matrix = matrixFactory.create(ARR_2D);
      assertEquals(ARR_2D, matrix.getData());
   }

   @Test
   public void solve() throws Exception
   {
      Matrix solution = m2d.solve(m1d);
      Matrix expected = matrixFactory.create(ARR_RESULT);

      assertEquals(expected, solution);
      assertTrue(expected.hashCode() == solution.hashCode());
   }

   @Test
   public void get() throws Exception
   {
      Matrix matrix = matrixFactory.create(ARR_RESULT);
      assertEquals(4, matrix.get(1), 1e-10);
   }

   @Test
   public void equalsAndHashCode() throws Exception
   {
      Matrix mx1 = matrixFactory.create(ARR_2D);
      Matrix mx2 = matrixFactory.create(ARR_2D);

      assertTrue(mx1.equals(mx2));
      assertTrue(mx1.hashCode() == mx2.hashCode());
   }
}