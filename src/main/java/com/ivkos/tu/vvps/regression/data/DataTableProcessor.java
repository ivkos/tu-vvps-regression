package com.ivkos.tu.vvps.regression.data;

import com.ivkos.tu.vvps.regression.matrix.Matrix;

import java.util.Objects;

public class DataTableProcessor
{
   private final DataTable data;

   public DataTableProcessor(DataTable data)
   {
      this.data = data;
   }

   protected double getSumOfParams(DataPointCoefficientGetter coefficientGetter)
   {
      return data.getDataPoints().stream()
            .map(coefficientGetter)
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected double getSumOfProductsOfParams(DataPointCoefficientGetter coeff1Getter,
                                             DataPointCoefficientGetter coeff2Getter)
   {
      return data.getDataPoints().stream()
            .map(dp -> coeff1Getter.apply(dp) * coeff2Getter.apply(dp))
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected double getSumOfSquaredParams(DataPointCoefficientGetter coefficientGetter)
   {
      return data.getDataPoints().stream()
            .map(dp -> Math.pow(coefficientGetter.apply(dp), 2))
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   public BetaResult process()
   {
      DataPointCoefficientGetter w = DataPoint::getW;
      DataPointCoefficientGetter x = DataPoint::getX;
      DataPointCoefficientGetter y = DataPoint::getY;
      DataPointCoefficientGetter z = DataPoint::getZ;

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

   protected Matrix buildRhsMatrix(DataPointCoefficientGetter w,
                                   DataPointCoefficientGetter x,
                                   DataPointCoefficientGetter y,
                                   DataPointCoefficientGetter z
   )
   {
      return new Matrix(new double[] {
            getSumOfParams(z),
            getSumOfProductsOfParams(w, z),
            getSumOfProductsOfParams(x, z),
            getSumOfProductsOfParams(y, z)
      });
   }

   protected Matrix buildLhsMatrix(DataPointCoefficientGetter w,
                                   DataPointCoefficientGetter x,
                                   DataPointCoefficientGetter y
   )
   {
      return new Matrix(new double[][] {
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
