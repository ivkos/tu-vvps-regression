package com.ivkos.tu.vvps.regression.matrix.exceptions;

/**
 * Used to denote an illegal operation on matrices, e.g. solving for matrices with incompatible sizes
 */
public class IllegalMatrixOperationException extends RuntimeException
{
   public IllegalMatrixOperationException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
