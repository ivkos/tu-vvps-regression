package com.ivkos.tu.vvps.regression.matrix;

import com.ivkos.tu.vvps.regression.matrix.exceptions.IllegalMatrixOperationException;
import com.ivkos.tu.vvps.regression.matrix.exceptions.SingularMatrixException;
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
   public Matrix solve(Matrix other) throws IllegalMatrixOperationException
   {
      requireNonNull(other, "other matrix must not be null");

      SimpleMatrix otherSimple = new SimpleMatrix(other.getData());

      SimpleMatrix solution;
      try {
         solution = this.matrix.solve(otherSimple);
      } catch (IllegalArgumentException e) {
         throw new IllegalMatrixOperationException("Illegal operation. Matrices are with incompatible sizes.", e);
      } catch (org.ejml.factory.SingularMatrixException e) {
         throw new SingularMatrixException("The resulting matrix is singular", e);
      }

      return new EjmlMatrix(solution);
   }

   @Override
   public double get(int index)
   {
      return this.matrix.get(index);
   }
}

