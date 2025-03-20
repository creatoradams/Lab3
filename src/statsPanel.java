import java.awt.*;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.util.ArrayList;
public class statsPanel extends JPanel
{
    public statsPanel(List<InflationCollection> data)
    {
        setLayout(new BorderLayout());

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
            double average = total/collection.getInflationRate(); // get the average inflation
        }
        // create labels for results
        JLabel minLabel = new JLabel("Mininmum Inflation Rate: " + min);
        JLabel maxLabel = new JLabel("Maxinmum Inflation Rate: " + max);
        JLabel averageLabel = new JLabel("Average Inflation Rate: " + total);
        JLabel totalLabel = new JLabel("Total Inflation Rate: " + total);

        // add to panel
        add(minLabel);
        add(maxLabel);
        add(averageLabel);
        add(totalLabel);
    }

}
