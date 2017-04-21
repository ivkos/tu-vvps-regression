package com.ivkos.tu.vvps.regression.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Objects.requireNonNull;

public class DataTable
{
   private final Collection<DataPoint> dataPoints;

   public DataTable()
   {
      this.dataPoints = new HashSet<>();
   }

   public DataTable(DataPoint... dp)
   {
      requireNonNull(dp);
      List<DataPoint> dataPoints = asList(dp);
      if (dataPoints.contains(null)) throw new NullPointerException("dp must not contain null values");

      this.dataPoints = new HashSet<>(dataPoints);
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
