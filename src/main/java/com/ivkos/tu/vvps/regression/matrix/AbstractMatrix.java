package com.ivkos.tu.vvps.regression.matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public abstract class AbstractMatrix implements Matrix
{
   protected double[] flatData;

   private static boolean almostEquals(double[] a, double[] a2, int precision)
   {
      if (a == a2)
         return true;
      if (a == null || a2 == null)
         return false;

      int length = a.length;
      if (a2.length != length)
         return false;

      for (int i = 0; i < length; i++)
         if (Math.abs(a[i] - a2[i]) > Math.pow(10, -precision))
            return false;

      return true;
   }

   protected double[] getFlatData()
   {
      if (this.flatData != null) return this.flatData;

      this.flatData = Arrays.stream(getData())
            .flatMapToDouble(Arrays::stream)
            .toArray();

      return this.flatData;
   }

   @Override
   public int hashCode()
   {
      // reduce precision
      double[] truncated = Arrays.stream(this.getFlatData())
            .map(d -> BigDecimal.valueOf(d).setScale(10, RoundingMode.HALF_UP).doubleValue())
            .toArray();

      return Arrays.hashCode(truncated);
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof AbstractMatrix)) return false;

      AbstractMatrix that = (AbstractMatrix) o;
      return almostEquals(this.getFlatData(), that.getFlatData(), 10);
   }
}
