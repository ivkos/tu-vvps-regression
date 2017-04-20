package com.ivkos.tu.vvps.regression;

import com.ivkos.tu.vvps.regression.data.BetaResult;
import com.ivkos.tu.vvps.regression.data.DataPoint;
import com.ivkos.tu.vvps.regression.data.DataTable;
import com.ivkos.tu.vvps.regression.data.DataTableProcessor;

public class Main
{
   public static void main(String[] args)
   {
      DataPoint[] dataPoints = {
            new DataPoint(345, 65, 23, 31.4),
            new DataPoint(168, 18, 18, 14.6),
            new DataPoint(94, 0, 0, 6.4),
            new DataPoint(187, 185, 98, 28.3),
            new DataPoint(621, 87, 10, 42.1),
            new DataPoint(255, 0, 0, 15.3)
      };

      DataTable data = new DataTable(dataPoints);
      DataTableProcessor processor = new DataTableProcessor(data);
      BetaResult result = processor.process();

      System.out.println(result.toString());
   }
}
