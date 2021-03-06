package com.dots.piechartexample;

import java.util.Arrays;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainFragment extends Fragment {
  // save a reference so custom methods
  // can access views
  private View topLevelView;

  // save a reference to show the pie chart
  private WebView webview;

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
       super.onCreateView(inflater, container,
           savedInstanceState);

       boolean attachToRoot = false;
       topLevelView = inflater.inflate(
           R.layout.fragment_main,
           container,
           attachToRoot);

       // call now or after some condition is met
       initPieChart();

       return topLevelView;
  }

   // initialize the WebView and the pie chart
   public void initPieChart()
   {

           webview = (WebView)topLevelView.findViewById(
               R.id.pie_chart_webview);

           WebSettings webSettings =
               webview.getSettings();

           webSettings.setJavaScriptEnabled(true);


           webview.setWebChromeClient(
               new WebChromeClient());

           webview.setWebViewClient(new WebViewClient()
           {
               @Override
               public void onPageFinished(
                   WebView view,
                   String url)
               {

                   // after the HTML page loads,
                   // load the pie chart
                   loadPieChart();
               }
           });

           // note the mapping
           // from  file:///android_asset
           // to PieChartExample/assets
           webview.loadUrl("file:///android_asset/" +
               "html/piechart.html");

           webview.setBackgroundColor(Color.TRANSPARENT);
           webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
       }


   public void loadPieChart()
   {
       int dataset[] = new int[] {15,30,55};

//          use java.util.Arrays to format
//          the array as text
       String text = Arrays.toString(dataset);

//          pass the array to the JavaScript function
       webview.loadUrl("javascript:loadPieChart(" +
           text + ")");
   }
}
