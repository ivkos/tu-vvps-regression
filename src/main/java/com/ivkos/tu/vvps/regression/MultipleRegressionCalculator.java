package com.ivkos.tu.vvps.regression;

import com.ivkos.tu.vvps.regression.data.BetaResult;

import static java.util.Objects.requireNonNull;

public class MultipleRegressionCalculator
{
   private final BetaResult beta;

   public MultipleRegressionCalculator(BetaResult betaResult)
   {
      requireNonNull(betaResult, "betaResult must not be null");
      this.beta = betaResult;
   }

   public double calculateZk(double wk, double xk, double yk)
   {
      return beta.getBeta0() +
            wk * beta.getBeta1() +
            xk * beta.getBeta2() +
            yk * beta.getBeta3();
   }
}
