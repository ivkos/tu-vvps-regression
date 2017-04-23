package com.ivkos.tu.vvps.regression.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Objects.requireNonNull;

/**
 * Represents a collection of data points
 */
public class DataTable
{
   private final Collection<DataPoint> dataPoints;

   /**
    * Creates an empty DataTable
    */
   public DataTable()
   {
      this.dataPoints = new HashSet<>();
   }

   /**
    * Creates a DataTable containing the supplied data points
    *
    * @param dataPoints
    */
   public DataTable(DataPoint... dataPoints)
   {
      requireNonNull(dataPoints, "dataPoints must not be null");
      List<DataPoint> dataPointsList = asList(dataPoints);
      if (dataPointsList.contains(null)) throw new NullPointerException("dataPoints must not contain null values");

      this.dataPoints = new HashSet<>(dataPointsList);
   }

   /**
    * Adds a data point to the DataTable
    *
    * @param dp the data point
    * @return the current DataTable instance
    */
   public DataTable addDataPoint(DataPoint dp)
   {
      this.dataPoints.add(dp);
      return this;
   }

   /**
    * Returns an unmodifiable collection of the data points this table contains
    *
    * @return an unmodifiable collection of data points
    */
   public Collection<DataPoint> getDataPoints()
   {
      return unmodifiableCollection(dataPoints);
   }

   /**
    * Returns the number of data points in this table
    *
    * @return the number of data points in this table
    */
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
