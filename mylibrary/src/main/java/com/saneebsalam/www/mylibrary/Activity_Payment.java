package com.saneebsalam.www.mylibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Saneeb Salam
 * on 2/20/2017.
 */

public class Activity_Payment extends AppCompatActivity {

    WebView webview;
    String URL;
//            RedirectUrl;
//    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);

        getSupportActionBar().hide();
        webview = (WebView) findViewById(R.id.webview);

//        URL = getIntent().getStringExtra(Keys.paymenturl);
        URL = "https://www.iplayin.com/_checkout.html";
//        URL="http://www.iplayin.com/payresponse.aspx?referenceid=NV17082802155442401&success=True&TransactionID=QPTRN240282017822560&MobileNumber=97433300020&Amount=1&InvoiceNo=NPMP201728250225232399";
//        RedirectUrl = getIntent().getStringExtra(Keys.RedirectUrl);

        DisplayURL(URL);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void DisplayURL(String value) {
//        dialog = ProgressDialog.show(this, "Loading", "Wait while loading...");
//        System.out.println("DisplayURL: " + value);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");

//        webview.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress) {
//                // Activities and WebViews measure progress with different scales.
//                // The progress meter will automatically disappear when we reach 100%
////                MainActivity.this.setProgress(progress * 1000);
//                if (progress == 100) {
//                    dialog.dismiss();
//                }
//            }
//        });

        webview.setWebViewClient(new WebViewClient() {
            //            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(Activity_Payment.this, "Oh no! " + description, Toast.LENGTH_LONG).show();
//            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("url.........: " + url);

//                if(url.contains("success=True")){
//                    alert_Success("Transaction Success");
//                }else if(url.contains("success=False")){
//                    alert_Success("Transaction Failed");
//                }


//                if (url.equalsIgnoreCase(RedirectUrl))
//                    webview.loadUrl("javascript:HtmlViewer.showHTML" +
//                            "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });


        webview.loadUrl(value);
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            switch (keyCode) {
//                case KeyEvent.KEYCODE_BACK:
//                    if (webview.canGoBack()) {
//                        webview.goBack();
//                    } else {
//                        finish();
//                    }
//                    return true;
//            }
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    private class MyJavaScriptInterface {

        private Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(String html) {
            try {
                Document doc = getDomElement(html);
                NodeList nl = doc.getElementsByTagName("html");
                for (int temp = 0; temp < nl.getLength(); temp++) {
                    org.w3c.dom.Node nNode = nl.item(temp);

                    Element eElement = (Element) nNode;

                    html = eElement.getElementsByTagName("body")
                            .item(0).getTextContent();

                }
                JSONObject response = new JSONObject(html);
//                alert_success(response.getString("message"));

                Intent intent = new Intent();
//                intent.putExtra(Keys.paymentresult, response.getString("message"));
                setResult(Activity.RESULT_OK, intent);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(html);
        }
    }

    public Document getDomElement(String xml) {
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        // return DOM
        return doc;
    }
}
