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

public class WetterFragment extends Fragment {
    private PropertyReader propertyReader;
    private Context context;
    private Properties properties;
    private WebView webView;

    private final String customHtml = "<html>\n" +
            "<header>\n" +
            "</header>\n" +
            "<body style='background-color: black'>\n" +
            "<script type='text/javascript' src='https://darksky.net/widget/default/48.2084,16.3725/ca12/en.js?width=100%&height=600&title=Wien&textColor=ffffff&bgColor=000000&transparency=false&skyColor=undefined&fontFamily=Default&customFont=&units=ca&htColor=ffffff&ltColor=a7a7a7&displaySum=yes&displayHeader=yes'></script>\n" +
            "</body>\n" +
            "</html>";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wetter, null);

        context = view.getContext();
        propertyReader = new PropertyReader(context);
        properties = propertyReader.getMyProperties("app.properties");

        webView = view.findViewById(R.id.activity_main_webview);

        this.initWebViewWithURL(webView, properties.getProperty("wetter.url"));
        return view;
    }

    private void initWebViewWithURL(WebView webView, String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setTextZoom(Integer.parseInt(properties.getProperty("wetter.text")));
        webView.loadUrl(url);
        webView.setInitialScale(Integer.parseInt(properties.getProperty("wetter.scale")));
    }

    public void onStart() {
        super.onStart();
        webView.reload();
    }
}
