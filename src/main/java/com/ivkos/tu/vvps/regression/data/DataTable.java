package com.ivkos.tu.vvps.regression.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class DataTable
{
   private final List<DataPoint> dataPoints;

   public DataTable()
   {
      this.dataPoints = new ArrayList<>();
   }

   public DataTable(DataPoint... dp)
   {
      this.dataPoints = Arrays.asList(dp);
   }

   public DataTable addDataPoint(DataPoint dp)
   {
      this.dataPoints.add(dp);
      return this;
   }

   public List<DataPoint> getDataPoints()
   {
      return unmodifiableList(dataPoints);
   }

   public int getDataPointsCount()
   {
      return this.dataPoints.size();
   }
}
