package com.ivkos.tu.vvps.regression.matrix;

public class MatrixFactory implements AbstractMatrixFactory
{
   @Override
   public Matrix create(double[][] data)
   {
      return new EjmlMatrix(data);
   }

   @Override
   public Matrix create(double[] data)
   {
      return new EjmlMatrix(data);
   }
}
