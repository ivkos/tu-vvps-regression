package com.ivkos.tu.vvps.regression.matrix;

public interface AbstractMatrixFactory
{
   Matrix create(double[][] data);

   Matrix create(double[] data);
}
