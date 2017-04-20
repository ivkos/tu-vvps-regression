package com.ivkos.tu.vvps.regression.matrix;

import java.util.Arrays;

public abstract class AbstractMatrix implements Matrix
{
   @Override
   public int hashCode()
   {
      return Arrays.deepHashCode(this.getData());
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof AbstractMatrix)) return false;

      AbstractMatrix that = (AbstractMatrix) o;
      return Arrays.deepEquals(this.getData(), that.getData());
   }
}
