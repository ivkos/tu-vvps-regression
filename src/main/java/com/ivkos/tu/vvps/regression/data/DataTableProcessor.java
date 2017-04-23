package com.ivkos.tu.vvps.regression.data;


import com.ivkos.tu.vvps.regression.matrix.AbstractMatrixFactory;
import com.ivkos.tu.vvps.regression.matrix.Matrix;
import com.ivkos.tu.vvps.regression.matrix.MatrixFactory;
import org.ejml.factory.SingularMatrixException;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class DataTableProcessor
{
   private static final Function<DataPoint, Double> w = DataPoint::getW;
   private static final Function<DataPoint, Double> x = DataPoint::getX;
   private static final Function<DataPoint, Double> y = DataPoint::getY;
   private static final Function<DataPoint, Double> z = DataPoint::getZ;

   private final DataTable data;
   private final AbstractMatrixFactory matrixFactory = new MatrixFactory();

   public DataTableProcessor(DataTable dataTable)
   {
      requireNonNull(dataTable, "dataTable must not be null");
      this.data = dataTable;
   }

   public BetaResult process()
   {
      Matrix A = buildLhsMatrix();
      Matrix b = buildRhsMatrix();

      Matrix solution;
      try {
         solution = A.solve(b);
      } catch (SingularMatrixException e) {
         throw new ArithmeticException("Matrix is singular");
      }

      return new BetaResult(
            solution.get(0),
            solution.get(1),
            solution.get(2),
            solution.get(3)
      );
   }

   protected double getSumOfCoefficients(Function<DataPoint, Double> coefficientGetter)
   {
      requireNonNull(coefficientGetter, "coefficientGetter must not be null");

      return data.getDataPoints().stream()
            .map(coefficientGetter)
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected double getSumOfProductsOfCoefficients(Function<DataPoint, Double> coefficient1Getter,
                                                   Function<DataPoint, Double> coefficient2Getter)
   {
      requireNonNull(coefficient1Getter, "coefficient1Getter must not be null");
      requireNonNull(coefficient2Getter, "coefficient2Getter must not be null");

      return data.getDataPoints().stream()
            .map(dp -> coefficient1Getter.apply(dp) * coefficient2Getter.apply(dp))
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected Matrix buildLhsMatrix()
   {
      return matrixFactory.create(new double[][] {
            {
                  data.getDataPointsCount(),
                  getSumOfCoefficients(w),
                  getSumOfCoefficients(x),
                  getSumOfCoefficients(y)
            },

            {
                  getSumOfCoefficients(w),
                  getSumOfProductsOfCoefficients(w, w),
                  getSumOfProductsOfCoefficients(w, x),
                  getSumOfProductsOfCoefficients(w, y)
            },

            {
                  getSumOfCoefficients(x),
                  getSumOfProductsOfCoefficients(w, x),
                  getSumOfProductsOfCoefficients(x, x),
                  getSumOfProductsOfCoefficients(x, y)
            },

            {
                  getSumOfCoefficients(y),
                  getSumOfProductsOfCoefficients(w, y),
                  getSumOfProductsOfCoefficients(x, y),
                  getSumOfProductsOfCoefficients(y, y)
            }
      });
   }

   protected Matrix buildRhsMatrix()
   {
      return matrixFactory.create(new double[] {
            getSumOfCoefficients(z),
            getSumOfProductsOfCoefficients(w, z),
            getSumOfProductsOfCoefficients(x, z),
            getSumOfProductsOfCoefficients(y, z)
      });
   }
}
