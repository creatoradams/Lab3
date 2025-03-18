import java.awt.*;
import java.util.List;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class ChartsPanel extends JPanel
{
    private List<InflationCollection> data; // will be used to generate the scatter plot
    JFreeChart scatterPlot;

    // constructor
    public ChartsPanel(List<InflationCollection> data)
    {
        this.data = data;
        setPreferredSize(new Dimension(500, 500));

        // create x-y series for the scatterPlot requirements. a series holds x & y data points
        XYSeries series = new XYSeries("Inflation");
        // loop through the data
        for (InflationCollection i : data)
        {
            series.add(i.getYear(), i.getInflationRate()); // i.getYear = x values & i.getInflationRate = y value
        }

        // create the dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series); // add the series of x and y points to the dataset

        // build the scatter plot
           scatterPlot = ChartFactory.createScatterPlot
                   ("20 Year Inflation", // chart title
                        "X Axis", // x axis label
                        "Y Axis", // y axis label
                        dataset,     // data set
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                  );
           // create and display chartpanel
       ChartPanel chartPanel = new ChartPanel(scatterPlot);
       add(chartPanel);
    }
}
