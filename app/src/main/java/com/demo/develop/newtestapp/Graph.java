package com.demo.develop.newtestapp;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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

import static com.demo.develop.newtestapp.constants.GraphConstant.CHART_TITLE;
import static com.demo.develop.newtestapp.constants.GraphConstant.CHART_VALUES_DISTANCE;
import static com.demo.develop.newtestapp.constants.GraphConstant.DATE_FORMAT;
import static com.demo.develop.newtestapp.constants.GraphConstant.LEGEND_HEIGHT;
import static com.demo.develop.newtestapp.constants.GraphConstant.LINE_COLOR;
import static com.demo.develop.newtestapp.constants.GraphConstant.LINE_NAME;
import static com.demo.develop.newtestapp.constants.GraphConstant.LINE_WIDTH;
import static com.demo.develop.newtestapp.constants.GraphConstant.NOTHING_TO_SHOW;
import static com.demo.develop.newtestapp.constants.GraphConstant.NUMBER_OF_X_LABELS;
import static com.demo.develop.newtestapp.constants.GraphConstant.POINT_SIZE;
import static com.demo.develop.newtestapp.constants.GraphConstant.SCALE;
import static com.demo.develop.newtestapp.constants.GraphConstant.TEXT_TYPEFACE;
import static com.demo.develop.newtestapp.constants.GraphConstant.X_AXIS_TITLE;
import static com.demo.develop.newtestapp.constants.GraphConstant.Y_AXIS_MAX;
import static com.demo.develop.newtestapp.constants.GraphConstant.Y_AXIS_MIN;
import static com.demo.develop.newtestapp.constants.GraphConstant.Y_AXIS_MINI;
import static com.demo.develop.newtestapp.constants.GraphConstant.Y_AXIS_TITLE;
import static com.demo.develop.newtestapp.constants.GraphConstant.Y_LABELS;

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
            XYSeries incomeSeries = new XYSeries(LINE_NAME);
            for (int i = 0; i < timeClicking.length; i++) {
                incomeSeries.add(i, rating[i]);
            }
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(incomeSeries);
            XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
            incomeRenderer.setColor(LINE_COLOR);
            incomeRenderer.setFillPoints(true);
            incomeRenderer.setLineWidth(LINE_WIDTH);
            incomeRenderer.setDisplayChartValues(true);
            incomeRenderer.setDisplayChartValuesDistance(CHART_VALUES_DISTANCE);
            incomeRenderer.setPointStyle(PointStyle.CIRCLE);
            incomeRenderer.setStroke(BasicStroke.SOLID);
            XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setXLabels(NUMBER_OF_X_LABELS);
            multiRenderer.setChartTitle(CHART_TITLE);
            multiRenderer.setXTitle(X_AXIS_TITLE);
            multiRenderer.setYTitle(Y_AXIS_TITLE);
            multiRenderer.setChartTitleTextSize((int) getResources().getDimension(R.dimen.chart_title_text_size));
            multiRenderer.setAxisTitleTextSize((int) getResources().getDimension(R.dimen.axis_title_text_size));
            multiRenderer.setLabelsTextSize((int) getResources().getDimension(R.dimen.axis_labels_text_size));
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
            multiRenderer.setLegendHeight(LEGEND_HEIGHT);
            multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
            multiRenderer.setXLabelsColor(Color.WHITE);
            multiRenderer.setYLabelsColor(0, Color.WHITE);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT);
            multiRenderer.setTextTypeface(TEXT_TYPEFACE, Typeface.BOLD);
            multiRenderer.setYLabels(Y_LABELS);
            multiRenderer.setYAxisMin(Y_AXIS_MIN);
            multiRenderer.setYAxisMax(Y_AXIS_MAX);
            multiRenderer.setXAxisMin(Y_AXIS_MINI);
            multiRenderer.setXAxisMax(timeClicking.length);
            multiRenderer.setBackgroundColor(Color.TRANSPARENT);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
            multiRenderer.setApplyBackgroundColor(true);
            multiRenderer.setScale(SCALE);
            multiRenderer.setPointSize(POINT_SIZE);
            multiRenderer.setMargins(new int[]{(int) getResources().getDimension(R.dimen.graph_margin_top),
                    (int) getResources().getDimension(R.dimen.graph_margin_right),
                    (int) getResources().getDimension(R.dimen.graph_margin_bottom),
                    (int) getResources().getDimension(R.dimen.graph_margin_left)});
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
            textView.setText(NOTHING_TO_SHOW);
            textView. setGravity(Gravity.CENTER);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            ((RelativeLayout) linearLayout).addView(textView);
        }
    }

    private String makeDateAndTimeFormat(long timeStamp){
        Date date = new Date(timeStamp);
        Format format = new SimpleDateFormat(DATE_FORMAT);
        String dateFormat = format.format(date);

        return dateFormat;
    }
}