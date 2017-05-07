package objects;

import java.util.ArrayList;

/**
 * Created by Work on 07.05.2017.
 */

public class DataForChart {
    String chartTitle;
    String xLabel;
    String yLabel;
    ArrayList<String> seriaTitles;
    ArrayList<String> dataTitles;
    ArrayList<ArrayList<Double>> seriaValues;

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }

    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    public ArrayList<String> getSeriaTitles() {
        return seriaTitles;
    }

    public void setSeriaTitles(ArrayList<String> seriaTitles) {
        this.seriaTitles = seriaTitles;
    }

    public ArrayList<String> getDataTitles() {
        return dataTitles;
    }

    public void setDataTitles(ArrayList<String> dataTitles) {
        this.dataTitles = dataTitles;
    }

    public ArrayList<ArrayList<Double>> getSeriaValues() {
        return seriaValues;
    }

    public void setSeriaValues(ArrayList<ArrayList<Double>> seriaValues) {
        this.seriaValues = seriaValues;
    }
}
