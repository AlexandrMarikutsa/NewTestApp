package com.demo.develop.newtestapp;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    private void openChart() {
        List<Click> clicks = dao.readAll();
        if (clicks.size() != 0) {
            List<Long> timeStamps = new ArrayList<>();
            List<Integer> ratings = new ArrayList<>();
            for (Click click : clicks) {
                timeStamps.add(click.getTimeStamp());
                ratings.add(click.getRating());
            }
            timeClicking = new String[timeStamps.size()];
            for (int j = 0; j < timeClicking.length; j++)
                timeClicking[j] = makeDateAndTimeFormat(timeStamps.get(j));
            int[] rating = new int[ratings.size()];
            for (int j = 0; j < rating.length; j++)
                rating[j] = ratings.get(j);
            XYSeries incomeSeries = new XYSeries("Clicking");
            for (int i = 0; i < timeClicking.length; i++) {
                incomeSeries.add(i, rating[i]);
            }
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(incomeSeries);
            XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
            incomeRenderer.setColor(Color.BLUE);
            incomeRenderer.setFillPoints(true);
            incomeRenderer.setLineWidth(2f);
            incomeRenderer.setDisplayChartValues(true);
            incomeRenderer.setDisplayChartValuesDistance(10);
            incomeRenderer.setPointStyle(PointStyle.CIRCLE);
            incomeRenderer.setStroke(BasicStroke.SOLID);
            XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setXLabels(0);
            multiRenderer.setChartTitle("Graph");
            multiRenderer.setXTitle("\n\n\nTime and date");
            multiRenderer.setYTitle("Rating");
            multiRenderer.setChartTitleTextSize(28);
            multiRenderer.setAxisTitleTextSize(24);
            multiRenderer.setLabelsTextSize(11);//size of axis labels
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
            multiRenderer.setXLabelsColor(Color.WHITE);
            multiRenderer.setYLabelsColor(0, Color.WHITE);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT);
            multiRenderer.setTextTypeface("sans_serif", Typeface.BOLD);
            multiRenderer.setYLabels(11);
            multiRenderer.setYAxisMin(0);
            multiRenderer.setYAxisMax(10.5);
            multiRenderer.setXAxisMin(-1);
            multiRenderer.setXAxisMax(timeClicking.length);
            multiRenderer.setBackgroundColor(Color.TRANSPARENT);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
            multiRenderer.setApplyBackgroundColor(true);
            multiRenderer.setScale(2f);
            multiRenderer.setPointSize(4f);
            multiRenderer.setMargins(new int[]{30, 30, 30, 30});
            for (int i = 0; i < timeClicking.length; i++) {
                multiRenderer.addXTextLabel(i, timeClicking[i]);
            }
            multiRenderer.addSeriesRenderer(incomeRenderer);
            LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
            chartContainer.removeAllViews();
            mChart = ChartFactory.getLineChartView(Graph.this, dataset, multiRenderer);
            chartContainer.addView(mChart);
        }else {
            TextView textView = new TextView(Graph.this);
            View linearLayout =  findViewById(R.id.chart_relative);
            textView.setText("There is no data to show graph...");
//            textView.setId(5);
            textView. setGravity(Gravity.CENTER);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            ((RelativeLayout) linearLayout).addView(textView);
        }
    }

    private String makeDateAndTimeFormat(long timeStamp){
//        long timeMilis = new Long(timeStamp);
//        Date date = new java.util.Date(timeMilis);
//        String dateFormat = new SimpleDateFormat("yyyy dd.MM\nHH:mm").format(date);
//        return dateFormat;

        Date date = new Date(timeStamp);
        Format format = new SimpleDateFormat("dd.MM\nHH:mm");
        String dateFormat = format.format(date);

        return dateFormat;
    }
}