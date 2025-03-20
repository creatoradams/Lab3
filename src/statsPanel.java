import java.awt.*;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.util.ArrayList;
public class statsPanel extends JPanel
{
    public statsPanel(List<InflationCollection> data)
    {
        setLayout(new GridLayout(4, 1));

        double min = Double.MAX_VALUE; //put max value, so when min is encountered it overwrites
        double max = Double.MIN_VALUE; // put min value, so when max is encountered it overwrites
        double total = 0.0;

        for (InflationCollection collection : data)
        {
            double rate = collection.getInflationRate();

            // store new minimum in min
            if (rate < min)
                min = rate;

            // store new max in max
            if (rate > max)
                max = rate;
            total += rate; // add all inflation values to one sum

        }
        // check if the list has elements, returns the average unless list returns zero elements
        double average = data.isEmpty() ? 0.0 : total / data.size(); // get the average inflation

        // create labels for results
        JLabel minLabel = new JLabel("Minimum Inflation Rate: " + String.format("%.2f", min));
        JLabel maxLabel = new JLabel("Maximum Inflation Rate: " + String.format("%.2f", max));
        JLabel averageLabel = new JLabel("Average Inflation Rate: " + String.format("%.2f", average));
        JLabel totalLabel = new JLabel("Total Inflation Rate: " + String.format("%.2f", total));

        // add to panel
        add(minLabel);
        add(maxLabel);
        add(averageLabel);
        add(totalLabel);
    }

}