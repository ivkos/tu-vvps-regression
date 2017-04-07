package com.ivkos.tu.vvps.regression.data;

import com.ivkos.tu.vvps.regression.matrix.Matrix;

public class DataTableProcessor
{
   private final DataTable data;

   public DataTableProcessor(DataTable data)
   {
      this.data = data;
   }

   protected double getSumOfParams(DataPointCoefficientGetter paramGetter)
   {
      return data.getDataPoints().stream()
            .map(paramGetter)
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected double getSumOfProductsOfParams(DataPointCoefficientGetter coeff1getter,
                                             DataPointCoefficientGetter coeff2getter)
   {
      return data.getDataPoints().stream()
            .map(dp -> coeff1getter.apply(dp) * coeff2getter.apply(dp))
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   protected double getSumOfSquaredParams(DataPointCoefficientGetter paramGetter)
   {
      return data.getDataPoints().stream()
            .map(dp -> Math.pow(paramGetter.apply(dp), 2))
            .mapToDouble(Double::doubleValue)
            .sum();
   }

   public BetaResult process()
   {
      DataPointCoefficientGetter w = DataPoint::getW;
      DataPointCoefficientGetter x = DataPoint::getX;
      DataPointCoefficientGetter y = DataPoint::getY;
      DataPointCoefficientGetter z = DataPoint::getZ;

      Matrix lhs = new Matrix(new double[][] {
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

      Matrix rhs = new Matrix(new double[] {
            getSumOfParams(z),
            getSumOfProductsOfParams(w, z),
            getSumOfProductsOfParams(x, z),
            getSumOfProductsOfParams(y, z)
      });

      Matrix solution = lhs.solve(rhs);

      return new BetaResult(
            solution.get(0),
            solution.get(1),
            solution.get(2),
            solution.get(3)
      );
   }
}
