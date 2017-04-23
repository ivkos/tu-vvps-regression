package com.ivkos.tu.vvps.regression.matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public abstract class AbstractMatrix implements Matrix
{
   public static final int PRECISION = 10;

   protected double[] flatData;

   /**
    * Checks whether the specified matrices (represented by double arrays) are almost equal within a precision range
    *
    * @param a
    * @param a2
    * @return
    */
   protected static boolean almostEquals(double[] a, double[] a2)
   {
      if (a == a2)
         return true;
      if (a == null || a2 == null)
         return false;

      int length = a.length;
      if (a2.length != length)
         return false;

      for (int i = 0; i < length; i++)
         if (Math.abs(a[i] - a2[i]) > Math.pow(10, -PRECISION))
            return false;

      return true;
   }

   /**
    * Returns the internal data flattened
    * @return a flat array of the internal data
    */
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
            .map(d -> BigDecimal.valueOf(d).setScale(PRECISION, RoundingMode.HALF_UP).doubleValue())
            .toArray();

      return Arrays.hashCode(truncated);
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof AbstractMatrix)) return false;

      AbstractMatrix that = (AbstractMatrix) o;
      return almostEquals(this.getFlatData(), that.getFlatData());
   }
}
