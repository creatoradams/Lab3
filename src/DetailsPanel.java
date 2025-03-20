import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;

public class DetailsPanel extends JPanel
{
    private JLabel resultLabel; // used to display results
    private List<InflationCollection> data;

    public DetailsPanel(List<InflationCollection> data)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 300));

        // extract different countries from the data
        Set<String> diffCountry = new HashSet<>();

        // loop through the collection data
        for(InflationCollection i : data)
        {
            diffCountry.add(i.getCountry());
        }
        // convert the set of countries to an array
        String[] countries = diffCountry.toArray(new String[0]);

        // sort the array alphabetically, easier on the eye imo
        Arrays.sort(countries); // sort the countries alphabetically

        // create a dropdown for selecting a country
        JComboBox<String> country = new JComboBox<>(countries); // create combo box for countries

        // extract unique years from the data
        Set<Integer> yearSet = new HashSet<>();

        // loop through the data and add years to the set
        for(InflationCollection i : data)
        {
            yearSet.add(i.getYear());
        }
        // convert from set to a list
        List<Integer> allYears;
        allYears = new ArrayList<>(yearSet);

        // sort ascending
        Collections.sort(allYears);
        // convert to array
        Integer[] years = allYears.toArray(new Integer[0]);

        // create combobox for all the years
        JComboBox<Integer> year;
        year = new JComboBox<>(years);

        // create a button to show inflation
        JButton button = new JButton("Show Inflation");
        button.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // retrieve the selected country
                String selectedCountry = Objects.requireNonNull(country.getSelectedItem()).toString();
                // retrieve the selected year
                Integer selectedYear = (Integer) year.getSelectedItem();
                // call getInflation method to get the inflation rate for specific country and year
                double inflation = getInflation(selectedCountry, selectedYear);
                // display results
                resultLabel.setText(selectedCountry + ": " + selectedYear);
            }
        });

        // label the result
        resultLabel = new JLabel("Select a country and year to view inflation rate");

        // add panel components
        add(new JLabel("Select Country"));
        add(country);
        add(new JLabel("Select Year"));
        add(year);


    }
    // method to look up inflation data for a given country and year
    private double getInflation(String country, int year)
    {
        for (InflationCollection i : data)
        {
            if (i.getCountry().equals(country) && i.getYear() == year)
            {
                return i.getInflationRate();
            }
        }
        return 0.0; // return 0 if no matching record is found
    }
}
