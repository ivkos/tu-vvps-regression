package com.ivkos.tu.vvps.regression.data;

import static java.lang.System.lineSeparator;

public class BetaResult
{
   private final double beta0, beta1, beta2, beta3;

   public BetaResult(double beta0, double beta1, double beta2, double beta3)
   {
      this.beta0 = beta0;
      this.beta1 = beta1;
      this.beta2 = beta2;
      this.beta3 = beta3;
   }

   public double getBeta0()
   {
      return beta0;
   }

   public double getBeta1()
   {
      return beta1;
   }

   public double getBeta2()
   {
      return beta2;
   }

   public double getBeta3()
   {
      return beta3;
   }

   @Override
   public String toString()
   {
      return "β₀ = " + beta0 + lineSeparator() +
            "β₁ = " + beta1 + lineSeparator() +
            "β₂ = " + beta2 + lineSeparator() +
            "β₃ = " + beta3;
   }
}
