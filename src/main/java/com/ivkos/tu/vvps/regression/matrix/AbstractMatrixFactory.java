package com.ivkos.tu.vvps.regression.matrix;

public interface AbstractMatrixFactory
{
   /**
    * Creates a matrix
    *
    * @param data the data in the matrix
    *
    * @return a matrix
    */
   Matrix create(double[][] data);

   /**
    * Creates a matrix
    *
    * @param data the data in the matrix
    *
    * @return a matrix
    */
   Matrix create(double[] data);
}
