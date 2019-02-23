package com.jotschi.fahrplanmonitor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Properties;

public class AbfahrtsmonitorFragment extends Fragment {
    private PropertyReader propertyReader;
    private Context context;
    private Properties properties;
    private WebView webView1;
    private WebView webView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.abfahrtsmonitor, null);

        context = view.getContext();
        propertyReader = new PropertyReader(context);
        properties = propertyReader.getMyProperties("app.properties");

        webView1 = view.findViewById(R.id.activity_main_webview1);
        webView2 = view.findViewById(R.id.activity_main_webview2);

        initWebViewWithURL(webView1, properties.getProperty("fahrplanmonitor.url1"));
        initWebViewWithURL(webView2, properties.getProperty("fahrplanmonitor.url2"));

        return view;
    }

    private void initWebViewWithURL(WebView webView, String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(url);
        webView.setInitialScale(Integer.parseInt(properties.getProperty("fahrplanmonitor.scale")));
    }

    public void onStart() {
        super.onStart();
        webView1.reload();
        webView2.reload();
    }
}