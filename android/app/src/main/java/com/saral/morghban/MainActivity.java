package com.saral.morghban;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
  private WebView webView;
  private static final String URL="https://script.google.com/macros/s/AKfycbxmjAbwy_h2cqUhCeaojDtOuBGJQd5gMsnIjOiZjfG5xMTV2436XYrP8DI5WZ95Zcff/exec";

  @Override public void onCreate(Bundle b){
    super.onCreate(b);

    webView=new WebView(this);
    setContentView(webView);
    fullscreen();

    WebSettings s=webView.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setDatabaseEnabled(true);
    s.setSupportZoom(false);
    s.setBuiltInZoomControls(false);
    s.setDisplayZoomControls(false);
    s.setTextZoom(100);
    s.setLoadWithOverviewMode(false);
    s.setUseWideViewPort(false);

    webView.setVerticalScrollBarEnabled(true);
    webView.setHorizontalScrollBarEnabled(false);
    webView.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
    webView.setNestedScrollingEnabled(true);
    webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

    CookieManager.getInstance().setAcceptCookie(true);
    CookieManager.getInstance().setAcceptThirdPartyCookies(webView,true);

    webView.setWebViewClient(new WebViewClient(){
      @Override public void onPageFinished(WebView view,String url){
        super.onPageFinished(view,url);
        view.evaluateJavascript(
          "(function(){"+
          "try{"+
          "document.documentElement.style.setProperty('overflow-y','auto','important');"+
          "document.documentElement.style.setProperty('height','auto','important');"+
          "document.body.style.setProperty('overflow-y','auto','important');"+
          "document.body.style.setProperty('height','auto','important');"+
          "document.body.style.setProperty('min-height','100vh','important');"+
          "document.body.style.setProperty('touch-action','pan-y','important');"+
          "var s=document.querySelector('.mobile-native-shell');"+
          "if(s){s.style.setProperty('overflow-y','visible','important');s.style.setProperty('height','auto','important');}"+
          "var c=document.querySelector('.mn-content');"+
          "if(c){c.style.setProperty('overflow','visible','important');c.style.setProperty('height','auto','important');}"+
          "}catch(e){}"+
          "})();",null);
      }
    });

    if(b==null) webView.loadUrl(URL); else webView.restoreState(b);
  }

  private void fullscreen(){
    if(android.os.Build.VERSION.SDK_INT>=30){
      WindowInsetsController c=getWindow().getInsetsController();
      if(c!=null){
        c.hide(WindowInsets.Type.statusBars()|WindowInsets.Type.navigationBars());
        c.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
      }
    } else {
      getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }
  }

  @Override protected void onResume(){super.onResume();fullscreen();}
  @Override public void onBackPressed(){if(webView.canGoBack())webView.goBack();else super.onBackPressed();}
  @Override protected void onSaveInstanceState(Bundle out){webView.saveState(out);super.onSaveInstanceState(out);}
}
