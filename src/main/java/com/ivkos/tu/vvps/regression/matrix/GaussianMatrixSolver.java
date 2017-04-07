package com.ivkos.tu.vvps.regression.matrix;

public class GaussianMatrixSolver
{
   private static final double EPSILON = 1e-10;

   public static double[] solve(double[][] matrix1, double[] matrix2)
   {
      int numberOfVariables = matrix2.length;

      for (int coeffIdx = 0; coeffIdx < numberOfVariables; coeffIdx++) {
         int maxCoeffIdx = coeffIdx;

         for (int i = coeffIdx + 1; i < numberOfVariables; i++) {
            if (Math.abs(matrix1[i][coeffIdx]) > Math.abs(matrix1[maxCoeffIdx][coeffIdx])) {
               maxCoeffIdx = i;
            }
         }

         double[] temp = matrix1[coeffIdx];
         matrix1[coeffIdx] = matrix1[maxCoeffIdx];
         matrix1[maxCoeffIdx] = temp;

         double temp2 = matrix2[coeffIdx];
         matrix2[coeffIdx] = matrix2[maxCoeffIdx];
         matrix2[maxCoeffIdx] = temp2;

         if (Math.abs(matrix1[coeffIdx][coeffIdx]) <= EPSILON) {
            throw new RuntimeException("Matrix is singular or nearly singular");
         }

         for (int i = coeffIdx + 1; i < numberOfVariables; i++) {
            double alpha = matrix1[i][coeffIdx] / matrix1[coeffIdx][coeffIdx];

            matrix2[i] -= alpha * matrix2[coeffIdx];

            for (int j = coeffIdx; j < numberOfVariables; j++) {
               matrix1[i][j] -= alpha * matrix1[coeffIdx][j];
            }
         }
      }

      double[] result = new double[numberOfVariables];

      for (int i = numberOfVariables - 1; i >= 0; i--) {
         double sum = 0;

         for (int j = i + 1; j < numberOfVariables; j++) {
            sum += matrix1[i][j] * result[j];
         }

         result[i] = (matrix2[i] - sum) / matrix1[i][i];
      }

      return result;
   }
}
