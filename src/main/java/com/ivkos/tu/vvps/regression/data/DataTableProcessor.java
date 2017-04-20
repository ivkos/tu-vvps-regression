package com.ivkos.tu.vvps.regression.data;


import com.ivkos.tu.vvps.regression.matrix.AbstractMatrixFactory;
import com.ivkos.tu.vvps.regression.matrix.Matrix;
import com.ivkos.tu.vvps.regression.matrix.MatrixFactory;

import java.util.Objects;
import java.util.function.Function;

public class DataTableProcessor
{
   private final DataTable data;
   private final AbstractMatrixFactory matrixFactory = new MatrixFactory();

   public DataTableProcessor(DataTable data)
   {
      this.data = data;
   }

   public BetaResult process()
   {
      Function<DataPoint, Double> w = DataPoint::getW;
      Function<DataPoint, Double> x = DataPoint::getX;
      Function<DataPoint, Double> y = DataPoint::getY;
      Function<DataPoint, Double> z = DataPoint::getZ;

      Matrix lhs = buildLhsMatrix(w, x, y);
      Matrix rhs = buildRhsMatrix(w, x, y, z);
      Matrix solution = lhs.solve(rhs);

      return new BetaResult(
            solution.get(0),
            solution.get(1),
            solution.get(2),
            solution.get(3)
      );
   }

   protected double getSumOfParams(Function<DataPoint, Double> coefficientGetter)
   {
      return data.getDataPoints().stream()
            .map(coefficientGetter)
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected double getSumOfSquaredParams(Function<DataPoint, Double> coefficientGetter)
   {
      return getSumOfParams(coefficientGetter.andThen(d -> d * d));
   }

   protected double getSumOfProductsOfParams(Function<DataPoint, Double> coeff1Getter,
                                             Function<DataPoint, Double> coeff2Getter)
   {
      return data.getDataPoints().stream()
            .map(dp -> coeff1Getter.apply(dp) * coeff2Getter.apply(dp))
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected Matrix buildLhsMatrix(Function<DataPoint, Double> w,
                                   Function<DataPoint, Double> x,
                                   Function<DataPoint, Double> y)
   {
      return matrixFactory.create(new double[][] {
            {
                  data.getDataPointsCount(),
                  getSumOfParams(w),
                  getSumOfParams(x),
                  getSumOfParams(y)
            },

            {
                  getSumOfParams(w),
                  getSumOfSquaredParams(w),
                  getSumOfProductsOfParams(w, x),
                  getSumOfProductsOfParams(w, y)
            },

            {
                  getSumOfParams(x),
                  getSumOfProductsOfParams(w, x),
                  getSumOfSquaredParams(x),
                  getSumOfProductsOfParams(x, y)
            },

            {
                  getSumOfParams(y),
                  getSumOfProductsOfParams(w, y),
                  getSumOfProductsOfParams(x, y),
                  getSumOfSquaredParams(y)
            }
      });
   }

   protected Matrix buildRhsMatrix(Function<DataPoint, Double> w,
                                   Function<DataPoint, Double> x,
                                   Function<DataPoint, Double> y,
                                   Function<DataPoint, Double> z)
   {
      return matrixFactory.create(new double[] {
            getSumOfParams(z),
            getSumOfProductsOfParams(w, z),
            getSumOfProductsOfParams(x, z),
            getSumOfProductsOfParams(y, z)
      });
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof DataTableProcessor)) return false;
      DataTableProcessor that = (DataTableProcessor) o;
      return Objects.equals(data, that.data);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(data);
   }
}
