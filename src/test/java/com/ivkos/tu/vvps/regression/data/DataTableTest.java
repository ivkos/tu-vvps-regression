package com.ivkos.tu.vvps.regression.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataTableTest
{
   @Test
   public void emptyDataTable() throws Exception
   {
      DataTable dt = new DataTable();
      assertNotNull(dt);
   }

   @Test
   public void constructWithDataPoints() throws Exception
   {
      DataPoint dp1 = new DataPoint(1, 2, 3, 4);
      DataPoint dp2 = new DataPoint(5, 6, 7, 8);

      DataTable dt = new DataTable(dp1, dp2);
      assertNotNull(dt);
   }

   @Test
   public void addDataPoint() throws Exception
   {
      DataPoint dp1 = new DataPoint(1, 2, 3, 4);
      DataPoint dp2 = new DataPoint(5, 6, 7, 8);

      new DataTable()
            .addDataPoint(dp1)
            .addDataPoint(dp2);
   }

   @Test
   public void getDataPoints() throws Exception
   {
      DataPoint dp1 = new DataPoint(1, 2, 3, 4);

      DataTable dt1 = new DataTable(dp1);
      assertNotNull(dt1.getDataPoints());
      assertTrue(dt1.getDataPoints().contains(dp1));

      DataTable dt2 = new DataTable().addDataPoint(dp1);
      assertNotNull(dt2.getDataPoints());
      assertTrue(dt2.getDataPoints().contains(dp1));
   }

   @Test
   public void getDataPointsCount() throws Exception
   {
      assertTrue(new DataTable().getDataPointsCount() == 0);
      assertTrue(new DataTable().getDataPoints().size() == 0);


      DataPoint dp1 = new DataPoint(1, 2, 3, 4);
      DataPoint dp2 = new DataPoint(5, 6, 7, 8);

      DataTable dt1 = new DataTable(dp1, dp2);
      assertTrue(dt1.getDataPointsCount() == 2);
      assertTrue(dt1.getDataPoints().size() == 2);

      DataTable dt2 = new DataTable(dp1, dp2);
      assertTrue(dt2.getDataPointsCount() == 2);
      assertTrue(dt2.getDataPoints().size() == 2);
   }

   @Test
   public void equalDataPointsAreUnified() throws Exception
   {
      DataPoint dp1 = new DataPoint(1, 2, 3, 4);
      DataPoint dp2 = new DataPoint(1, 2, 3, 4);
      assertEquals(dp1, dp2);
      DataPoint dp3 = new DataPoint(5, 6, 7, 8);

      DataTable dt = new DataTable(dp1, dp2, dp3);
      assertTrue(dt.getDataPoints().size() == 2);
      assertTrue(dt.getDataPoints().contains(dp1));
      assertTrue(dt.getDataPoints().contains(dp2));
      assertTrue(dt.getDataPoints().contains(dp3));
   }

   @Test
   public void equivalentDataTables() throws Exception
   {
      DataPoint dp1 = new DataPoint(1, 2, 3, 4);
      DataPoint dp2 = new DataPoint(5, 6, 7, 8);

      DataTable dt1 = new DataTable(dp1, dp2);
      DataTable dt2 = new DataTable()
            .addDataPoint(dp1)
            .addDataPoint(dp2);

      assertTrue(dt1.equals(dt2));
      assertTrue(dt1.hashCode() == dt2.hashCode());
   }
}
