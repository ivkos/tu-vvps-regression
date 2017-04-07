package com.ivkos.tu.vvps.regression.data;

public class DataPoint
{
   // coefficients
   private final double w, x, y, z;

   public DataPoint(double w, double x, double y, double z) {
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
}
