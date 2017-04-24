package com.ivkos.tu.vvps.regression;

import com.ivkos.tu.vvps.regression.data.BetaResult;
import com.ivkos.tu.vvps.regression.data.DataPoint;
import com.ivkos.tu.vvps.regression.data.DataTable;
import com.ivkos.tu.vvps.regression.data.DataTableProcessor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultipleRegressionIntegrationTest
{
   private static final double PRECISION = 1e-4;

   @Test
   public void table1TestCase() throws Exception
   {
      DataPoint[] dataPoints = {
            new DataPoint(345, 65, 23, 31.4),
            new DataPoint(168, 18, 18, 14.6),
            new DataPoint(94, 0, 0, 6.4),
            new DataPoint(187, 185, 98, 28.3),
            new DataPoint(621, 87, 10, 42.1),
            new DataPoint(255, 0, 0, 15.3)
      };

      DataTable data = new DataTable(dataPoints);
      DataTableProcessor processor = new DataTableProcessor(data);
      BetaResult result = processor.process();

      assertEquals(0.56645, result.getBeta0(), PRECISION);
      assertEquals(0.06533, result.getBeta1(), PRECISION);
      assertEquals(0.008719, result.getBeta2(), PRECISION);
      assertEquals(0.15105, result.getBeta3(), PRECISION);
   }

   @Test
   public void table3TestCase() throws Exception
   {
      DataPoint[] dataPoints = {
            new DataPoint(1142, 1060, 325, 201),
            new DataPoint(863, 995, 98, 98),
            new DataPoint(1065, 3205, 23, 162),
            new DataPoint(554, 120, 0, 54),
            new DataPoint(983, 2896, 120, 138),
            new DataPoint(256, 485, 88, 61)
      };

      DataTable data = new DataTable(dataPoints);
      DataTableProcessor processor = new DataTableProcessor(data);
      BetaResult result = processor.process();

      assertEquals(6.7013, result.getBeta0(), PRECISION);
      assertEquals(0.0784, result.getBeta1(), PRECISION);
      assertEquals(0.0150, result.getBeta2(), PRECISION);
      assertEquals(0.2461, result.getBeta3(), PRECISION);
   }

   @Test(expected = ArithmeticException.class)
   public void invalidData() throws Exception
   {
      DataPoint[] dataPoints = {
            new DataPoint(1, 2, 3, 4),
            new DataPoint(5, 6, 7, 8), // wow
            new DataPoint(9, 10, 11, 12),
            new DataPoint(13, 14, 15, 16)
      };

      DataTable data = new DataTable(dataPoints);
      DataTableProcessor processor = new DataTableProcessor(data);

      // will fail here
      processor.process();
   }
}
