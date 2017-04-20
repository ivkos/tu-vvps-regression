package com.ivkos.tu.vvps.regression.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;

public class DataTable
{
   private final Collection<DataPoint> dataPoints;

   public DataTable()
   {
      this.dataPoints = new HashSet<>();
   }

   public DataTable(DataPoint... dp)
   {
      this.dataPoints = new HashSet<>(asList(dp));
   }

   public DataTable addDataPoint(DataPoint dp)
   {
      this.dataPoints.add(dp);
      return this;
   }

   public Collection<DataPoint> getDataPoints()
   {
      return unmodifiableCollection(dataPoints);
   }

   public int getDataPointsCount()
   {
      return this.dataPoints.size();
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof DataTable)) return false;
      DataTable dataTable = (DataTable) o;
      return Objects.equals(dataPoints, dataTable.dataPoints);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(dataPoints);
   }
}
