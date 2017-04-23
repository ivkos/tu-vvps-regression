package com.ivkos.tu.vvps.regression.matrix;

public interface Matrix
{
   /**
    * Returns the internal array representing the data in the matrix
    *
    * @return the data in the matrix
    */
   double[][] getData();

   /**
    * Solves for x in the equation this*x=other (Ax=b)
    *
    * @param other the other matrix
    *
    * @return the solution
    */
   Matrix solve(Matrix other);

   /**
    * Returns the element of the matrix at the specified index in row-major order
    *
    * @param index the index of the element of interest
    *
    * @return the element of the matrix
    */
   double get(int index);
}
