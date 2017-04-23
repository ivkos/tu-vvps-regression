package com.ivkos.tu.vvps.regression;

import com.ivkos.tu.vvps.regression.data.BetaResult;

import static java.util.Objects.requireNonNull;

/**
 * Represents a calculator for predicting values based on multiple regression
 */
public class MultipleRegressionCalculator
{
   private final BetaResult beta;

   /**
    * Creates a new instance
    *
    * @param betaResult the result containing the beta coefficients
    */
   public MultipleRegressionCalculator(BetaResult betaResult)
   {
      requireNonNull(betaResult, "betaResult must not be null");
      this.beta = betaResult;
   }

   /**
    * Predicts a value (zk) based on the input values in the results of the multiple regression calculations
    *
    * @param wk
    * @param xk
    * @param yk
    *
    * @return the predicted value for zk
    */
   public double calculateZk(double wk, double xk, double yk)
   {
      return beta.getBeta0() +
            wk * beta.getBeta1() +
            xk * beta.getBeta2() +
            yk * beta.getBeta3();
   }
}
