package com.saral.morghban;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
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
    webView.setClickable(true);
    webView.setFocusable(true);
    webView.setFocusableInTouchMode(true);
    webView.requestFocus(View.FOCUS_DOWN);
    WebSettings s=webView.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setDatabaseEnabled(true);
    s.setJavaScriptCanOpenWindowsAutomatically(true);
    s.setSupportMultipleWindows(false);
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
    webView.setWebChromeClient(new WebChromeClient());
    webView.setWebViewClient(new WebViewClient());
    webView.setOnTouchListener((v,event)->{
      if(event.getAction()==MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_UP){v.requestFocus();}
      return false;
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