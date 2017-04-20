package com.ivkos.tu.vvps.regression.matrix;

public class GaussianMatrixSolver
{
   protected static final double SINGULARITY_LIMIT = 1e-10;

   public static double[] solve(double[][] matrix1, double[] matrix2)
   {
      int numberOfVariables = matrix2.length;

      for (int coeffIdx = 0; coeffIdx < numberOfVariables; coeffIdx++) {
         int maxCoeffIdx = coeffIdx;

         maxCoeffIdx = getMaxCoefficientIndex(matrix1, numberOfVariables, coeffIdx, maxCoeffIdx);
         swapMatrixRows(matrix1, matrix2, coeffIdx, maxCoeffIdx);

         checkMatrixForSingularity(matrix1, coeffIdx);

         transposeMatrix(matrix1, matrix2, numberOfVariables, coeffIdx);
      }

      return buildResult(matrix1, matrix2, numberOfVariables);
   }

   protected static void transposeMatrix(double[][] matrix1, double[] matrix2, int numberOfVariables, int coeffIdx)
   {
      for (int i = coeffIdx + 1; i < numberOfVariables; i++) {
         double alpha = matrix1[i][coeffIdx] / matrix1[coeffIdx][coeffIdx];

         matrix2[i] -= alpha * matrix2[coeffIdx];

         for (int j = coeffIdx; j < numberOfVariables; j++) {
            matrix1[i][j] -= alpha * matrix1[coeffIdx][j];
         }
      }
   }

   protected static int getMaxCoefficientIndex(double[][] matrix1, int numberOfVariables, int coeffIdx, int maxCoeffIdx)
   {
      for (int i = coeffIdx + 1; i < numberOfVariables; i++) {
         if (Math.abs(matrix1[i][coeffIdx]) > Math.abs(matrix1[maxCoeffIdx][coeffIdx])) {
            maxCoeffIdx = i;
         }
      }

      return maxCoeffIdx;
   }

   protected static void swapMatrixRows(double[][] matrix1, double[] matrix2, int coeffIdx, int maxCoeffIdx)
   {
      double[] temp = matrix1[coeffIdx];
      matrix1[coeffIdx] = matrix1[maxCoeffIdx];
      matrix1[maxCoeffIdx] = temp;

      double temp2 = matrix2[coeffIdx];
      matrix2[coeffIdx] = matrix2[maxCoeffIdx];
      matrix2[maxCoeffIdx] = temp2;
   }

   protected static double[] buildResult(double[][] matrix1, double[] matrix2, int numberOfVariables)
   {
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

   protected static void checkMatrixForSingularity(double[][] matrix, int coefficientIndex)
   {
      if (Math.abs(matrix[coefficientIndex][coefficientIndex]) <= SINGULARITY_LIMIT) {
         throw new ArithmeticException("Matrix is (nearly) singular");
      }
   }
}
