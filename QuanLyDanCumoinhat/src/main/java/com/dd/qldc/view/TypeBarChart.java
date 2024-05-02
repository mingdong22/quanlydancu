package com.dd.qldc.view;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TypeBarChart extends JFrame {

    private static final String SPECIALPERSON_FILE_NAME = "SpecialPerson.xml";

    public TypeBarChart(String title) {
        super(title);
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ loại đối tương",
                "Loại đối tương",
                "Số lượng",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> typeCount = new HashMap<>();
        try {
            File xmlFile = new File(SPECIALPERSON_FILE_NAME);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                private String currentElement;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentElement = qName;
                }

                public void characters(char[] ch, int start, int length) throws SAXException {
                    if ("type".equals(currentElement)) {
                        String type = new String(ch, start, length);
                        typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
                    }
                }
            };
            saxParser.parse(xmlFile, handler);
            // Add type counts to the dataset
            for (Map.Entry<String, Integer> entry : typeCount.entrySet()) {
                dataset.addValue(entry.getValue(), "Số lượng", entry.getKey());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TypeBarChart chart = new TypeBarChart("Biểu đồ loại đối tượng");
            chart.pack();
            //chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chart.setLocationRelativeTo(null);
            chart.setVisible(true);
        });
    }
}
