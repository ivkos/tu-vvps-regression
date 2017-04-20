package com.ivkos.tu.vvps.regression.matrix;

public interface Matrix
{
   double[][] getData();

   Matrix solve(Matrix other);

   double get(int index);
}
