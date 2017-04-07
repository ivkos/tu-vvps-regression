package com.ivkos.tu.vvps.regression.matrix;

import java.util.Arrays;

public class Matrix
{
   private final double[][] data;
   private double[] flatData = null; // flattened array cache

   public Matrix(double[][] data)
   {
      this.data = data;
   }

   public Matrix(double[] data)
   {
      this(new double[][] { data });
   }

   public Matrix solve(Matrix other)
   {
      return new Matrix(GaussianMatrixSolver.solve(this.data, other.data[0]));
   }

   public Matrix solve(double[] other)
   {
      return new Matrix(GaussianMatrixSolver.solve(this.data, other));
   }

   public double get(int index)
   {
      if (flatData == null) {
         flatData = Arrays.stream(data)
               .flatMapToDouble(Arrays::stream)
               .toArray();
      }

      return flatData[index];
   }
}
