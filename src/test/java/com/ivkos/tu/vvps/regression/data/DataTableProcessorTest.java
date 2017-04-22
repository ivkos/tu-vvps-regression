package com.ivkos.tu.vvps.regression.data;

import com.ivkos.tu.vvps.regression.matrix.Matrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DataTableProcessorTest
{
   public static final double PRECISION = 1e-5;

   private DataTable table;
   private DataTableProcessor processor;

   @Before
   public void setUp() throws Exception
   {
      table = new DataTable(
            new DataPoint(1142, 1060, 325, 201),
            new DataPoint(863, 995, 98, 98),
            new DataPoint(1065, 3205, 23, 162),
            new DataPoint(554, 120, 0, 54),
            new DataPoint(983, 2896, 120, 138),
            new DataPoint(256, 485, 88, 61)
      );

      processor = new DataTableProcessor(table);
   }

   @Test(expected = NullPointerException.class)
   public void constructWithNull() throws Exception
   {
      new DataTableProcessor(null);
   }

   @Test
   public void process() throws Exception
   {
      BetaResult process = processor.process();
      assertNotNull(process);
   }

   @Test
   public void getSumOfParams() throws Exception
   {
      assertEquals(4863, processor.getSumOfCoefficients(DataPoint::getW), PRECISION);
      assertEquals(8761, processor.getSumOfCoefficients(DataPoint::getX), PRECISION);
      assertEquals(654, processor.getSumOfCoefficients(DataPoint::getY), PRECISION);
      assertEquals(714, processor.getSumOfCoefficients(DataPoint::getZ), PRECISION);
   }

   @Test
   public void getSumOfProductsOfParams() throws Exception
   {
      assertEquals(8519938, processor.getSumOfProductsOfCoefficients(DataPoint::getW, DataPoint::getX), PRECISION);
   }

   @Test
   public void buildLhsMatrix() throws Exception
   {
      Matrix result = processor.buildLhsMatrix();
      assertNotNull(result);
   }

   @Test
   public void buildRhsMatrix() throws Exception
   {
      Matrix result = processor.buildRhsMatrix();
      assertNotNull(result);
   }
}
