package com.ivkos.tu.vvps.regression.data;

import java.util.Objects;

import static java.lang.System.lineSeparator;

/**
 * Represents the result of the multiple regression
 */
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

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof BetaResult)) return false;
      BetaResult that = (BetaResult) o;
      return Double.compare(that.beta0, beta0) == 0 &&
            Double.compare(that.beta1, beta1) == 0 &&
            Double.compare(that.beta2, beta2) == 0 &&
            Double.compare(that.beta3, beta3) == 0;
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(beta0, beta1, beta2, beta3);
   }
}
