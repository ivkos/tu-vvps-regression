package com.ivkos.tu.vvps.regression.matrix.exceptions;

/**
 * Used to denote the resulting matrix is singular so cannot be operated on
 */
public class SingularMatrixException extends RuntimeException
{
   public SingularMatrixException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
