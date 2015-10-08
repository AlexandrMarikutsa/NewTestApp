package com.demo.develop.newtestapp;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.demo.develop.newtestapp.classes.Click;
import com.demo.develop.newtestapp.helper.DBHelper;
import com.demo.develop.newtestapp.helper.Dao;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Graph extends Activity {

    private Dao dao;

    private View mChart;
    private String[] timeClicking;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        dao = new Dao(new DBHelper(this));
        openChart();
    }

    private void openChart(){
        List<Click> clicks = dao.readAll();
        List<Integer> timeStamps = new ArrayList<>();
        List<Integer> ratings = new ArrayList<>();
        for (Click click: clicks){
            timeStamps.add(click.getTimeStamp());
            ratings.add(click.getRating());
        }
        timeClicking = new String[timeStamps.size()];
        for(int j = 0; j< timeClicking.length; j++)
            timeClicking[j] = makeDateAndTimeFormat(timeStamps.get(j));
        int [] rating = new int[ratings.size()];
        for(int j = 0; j<rating.length; j++)
            rating[j] = ratings.get(j);
        XYSeries incomeSeries = new XYSeries("Clicking");
        for(int i=0;i< timeClicking.length;i++){
            incomeSeries.add(i,rating[i]);
        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(incomeSeries);
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.CYAN);
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(2f);
        incomeRenderer.setDisplayChartValues(true);
        incomeRenderer.setDisplayChartValuesDistance(10);
        incomeRenderer.setPointStyle(PointStyle.CIRCLE);
        incomeRenderer.setStroke(BasicStroke.SOLID);
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Graph");
        multiRenderer.setXTitle("Time and date");
        multiRenderer.setYTitle("Rating");
        multiRenderer.setChartTitleTextSize(28);
        multiRenderer.setAxisTitleTextSize(24);
        multiRenderer.setLabelsTextSize(11);
        multiRenderer.setZoomButtonsVisible(false);
        multiRenderer.setPanEnabled(false, false);
        multiRenderer.setClickEnabled(false);
        multiRenderer.setZoomEnabled(false, false);
        multiRenderer.setShowGridY(true);
        multiRenderer.setShowGridX(true);
        multiRenderer.setFitLegend(true);
        multiRenderer.setShowGrid(true);
        multiRenderer.setZoomEnabled(false);
        multiRenderer.setExternalZoomEnabled(false);
        multiRenderer.setAntialiasing(true);
        multiRenderer.setInScroll(false);
        multiRenderer.setLegendHeight(30);
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        multiRenderer.setYLabels(10);
        multiRenderer.setYAxisMax(10);
        multiRenderer.setXAxisMin(-1);
        multiRenderer.setXAxisMax(11);
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);
        multiRenderer.setPointSize(4f);
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});
        for(int i=0; i< timeClicking.length;i++){
            multiRenderer.addXTextLabel(i, timeClicking[i]);
        }
        multiRenderer.addSeriesRenderer(incomeRenderer);
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
        chartContainer.removeAllViews();
        mChart = ChartFactory.getLineChartView(Graph.this, dataset, multiRenderer);
        chartContainer.addView(mChart);
    }

    private String makeDateAndTimeFormat(int timeStamp){
        long timeMilis = new Long(timeStamp);
        Date date = new java.util.Date(timeMilis);
        String dateFormat = new SimpleDateFormat("dd.MM\nHH:mm").format(date);
        return dateFormat;
    }
}