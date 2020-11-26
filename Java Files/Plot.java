//Plot Data function under File tab

import javax.swing.*;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


class Plot extends JFrame{

    //creates the chart
    Plot(XYSeries[] series){
        //add all series
        var dataset = new XYSeriesCollection();
        for(int i = 0; i < series.length; i++){
            dataset.addSeries(series[i]);
        }

        //create chart
        JFreeChart chart = ChartFactory.createScatterPlot("Student Attendance",
                                                          "Percentage of Attendance",
                                                          "Number of Students",
                                                          dataset);
        XYPlot plot = chart.getXYPlot();
     
        //turn off gridlines
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.setBackgroundPaint(new Color(255,228,196));
        
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static XYSeries createData(int[] data, String date){
        XYSeries series = new XYSeries(date);
        int[] scatterData = new int[10];
        double percentage;

        for(int i = 0; i < data.length; i++){   //fill scatterData
            percentage = (data[i] / 75.0) * 100;
            if(percentage >= 100){  //attendance is 100% or more
                scatterData[9]++;
            }
            else if(percentage >= 90){
                scatterData[8]++;
            }
            else if(percentage >= 80){
                scatterData[7]++;
            }
            else if(percentage >= 70){
                scatterData[6]++;
            }
            else if(percentage >= 60){
                scatterData[5]++;
            }
            else if(percentage >= 50){
                scatterData[4]++;
            }
            else if(percentage >= 40){
                scatterData[3]++;
            }
            else if(percentage >= 30){
                scatterData[2]++;
            }
            else if(percentage >= 20){
                scatterData[1]++;
            }
            else{
                scatterData[0]++;
            }
        }

        for(int i = 0; i < 10; i++){   //fill xy series
            if(scatterData[i] != 0){
                series.add(i + 1, scatterData[i]);
            }
        }
        return series;
    }
}
