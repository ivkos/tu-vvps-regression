package com.ivkos.tu.vvps.regression.data;

import java.util.Objects;

public class DataPoint
{
   // coefficients
   private final double w, x, y, z;

   public DataPoint(double w, double x, double y, double z)
   {
      this.w = w;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public double getW()
   {
      return w;
   }

   public double getX()
   {
      return x;
   }

   public double getY()
   {
      return y;
   }

   public double getZ()
   {
      return z;
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof DataPoint)) return false;
      DataPoint dataPoint = (DataPoint) o;
      return Double.compare(dataPoint.w, w) == 0 &&
            Double.compare(dataPoint.x, x) == 0 &&
            Double.compare(dataPoint.y, y) == 0 &&
            Double.compare(dataPoint.z, z) == 0;
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(w, x, y, z);
   }
}
