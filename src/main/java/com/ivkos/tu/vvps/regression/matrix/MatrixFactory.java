package com.ivkos.tu.vvps.regression.matrix;

import static java.util.Objects.requireNonNull;

public class MatrixFactory implements AbstractMatrixFactory
{
   @Override
   public Matrix create(double[][] data)
   {
      requireNonNull(data, "data must not be null");
      return new EjmlMatrix(data);
   }

   @Override
   public Matrix create(double[] data)
   {
      requireNonNull(data, "data must not be null");
      return new EjmlMatrix(data);
   }
}
