package com.ivkos.tu.vvps.regression.matrix;

import org.ejml.simple.SimpleMatrix;

import static java.util.Objects.requireNonNull;

public class EjmlMatrix extends AbstractMatrix
{
   private final double[][] data;
   private final SimpleMatrix matrix;

   public EjmlMatrix(double[][] data)
   {
      requireNonNull(data, "data must not be null");

      this.data = data;
      this.matrix = new SimpleMatrix(this.data);
   }

   public EjmlMatrix(double[] data)
   {
      this(new SimpleMatrix(data.length, 1, true, data));
   }

   public EjmlMatrix(SimpleMatrix simpleMatrix)
   {
      requireNonNull("simpleMatrix must not be null");

      this.matrix = simpleMatrix;

      int rows = simpleMatrix.numRows();
      int cols = simpleMatrix.numCols();
      this.data = new double[rows][cols];

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            this.data[i][j] = simpleMatrix.get(i, j);
         }
      }
   }

   @Override
   public double[][] getData()
   {
      return data;
   }

   @Override
   public Matrix solve(Matrix other)
   {
      requireNonNull(other, "other matrix must not be null");

      SimpleMatrix otherSimple = new SimpleMatrix(other.getData());
      SimpleMatrix solution = this.matrix.solve(otherSimple);

      return new EjmlMatrix(solution);
   }

   @Override
   public double get(int index)
   {
      return this.matrix.get(index);
   }
}

