//package com.demo.develop.newtestapp;
//
//
//import android.app.Activity;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//public class LineChartActivity extends Activity {
//    public static final String TYPE = "type";
//    private XYMultipleSeriesDataset mDataset = getDemoDataset();
//    private XYMultipleSeriesRenderer mRenderer = getDemoRenderer();
//    private GraphicalView mChartView;
//
//    @SuppressWarnings("deprecation")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_line_chart);
//
//        setRendererStyling();
//        if (mChartView == null) {
//            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
//            mChartView = ChartFactory.getLineChartView(this, mDataset,
//                    mRenderer);
//            mRenderer.setSelectableBuffer(100);
//            layout.addView(mChartView, new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
//        } else
//            mChartView.repaint();
//    }
//
//    private void setRendererStyling() {
//        mRenderer.setApplyBackgroundColor(true);
//        mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
//        mRenderer.setAxisTitleTextSize(16);
//        mRenderer.setChartTitleTextSize(20);
//        mRenderer.setLabelsTextSize(15);
//        mRenderer.setLegendTextSize(15);
//        mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
//        mRenderer.setZoomButtonsVisible(true);
//        mRenderer.setPointSize(10);
//    }
//
//    private XYMultipleSeriesDataset getDemoDataset() {
//        double[] seriesFirstY = {20,-20,67,180,-45,24,99,-34,-8};
//        double[] seriesSecondY = {10,80,-40,-20,135,24,199,-34,80};
//
//        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//
//        XYSeries firstSeries = new XYSeries("Sample series One");
//        for (int i = 0; i < 9; i++)
//            firstSeries.add(i, seriesFirstY[i]);
//        dataset.addSeries(firstSeries);
//
//        XYSeries secondSeries = new XYSeries("Sample series Two");
//        for (int j = 0; j < 9; j++)
//            secondSeries.add(j, seriesSecondY[j]);
//        dataset.addSeries(secondSeries);
//        return dataset;
//    }
//
//    private XYMultipleSeriesRenderer getDemoRenderer() {
//        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
//        renderer.setMargins(new int[] { 20, 30, 15, 0 });
//        XYSeriesRenderer r = new XYSeriesRenderer();
//        r.setColor(Color.BLUE);
//        r.setPointStyle(PointStyle.SQUARE);
//        r.setFillBelowLine(true);
//        r.setFillBelowLineColor(Color.WHITE);
//        r.setFillPoints(true);
//        renderer.addSeriesRenderer(r);
//        r = new XYSeriesRenderer();
//        r.setPointStyle(PointStyle.CIRCLE);
//        r.setColor(Color.GREEN);
//        r.setFillPoints(true);
//        renderer.addSeriesRenderer(r);
//        renderer.setAxesColor(Color.DKGRAY);
//        renderer.setLabelsColor(Color.LTGRAY);
//        return renderer;
//    }
//}
