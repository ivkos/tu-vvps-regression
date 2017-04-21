package com.ivkos.tu.vvps.regression.data;

public class MultipleRegressionCalculator
{
   private final BetaResult beta;

   public MultipleRegressionCalculator(BetaResult betaResult)
   {
      this.beta = betaResult;
   }

   public double calculate(double wk, double xk, double yk)
   {
      return beta.getBeta0() +
            wk * beta.getBeta1() +
            xk + beta.getBeta2() +
            yk + beta.getBeta3();
   }
}
