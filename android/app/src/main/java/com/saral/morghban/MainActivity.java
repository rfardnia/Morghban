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
    webView.setWebViewClient(new WebViewClient(){
      @Override public void onPageFinished(WebView view,String url){
        super.onPageFinished(view,url);
        String js="(function(){try{"+
          "var old=document.getElementById('morghban-apk-fix');if(old)old.remove();"+
          "var st=document.createElement('style');st.id='morghban-apk-fix';"+
          "st.textContent='html,body{height:auto!important;min-height:100%!important;overflow-y:auto!important;overflow-x:hidden!important;}body{touch-action:auto!important;}.app,.mobile-native-shell,.mn-content{height:auto!important;min-height:0!important;overflow:visible!important;}.mobile-native-shell{padding-bottom:118px!important;}.mn-content{padding-bottom:120px!important;}.mn-bottom{position:fixed!important;left:0!important;right:0!important;bottom:0!important;top:auto!important;width:100%!important;max-width:100%!important;height:auto!important;min-height:78px!important;max-height:104px!important;overflow:hidden!important;z-index:9999!important;display:grid!important;grid-template-columns:1fr 1fr 1.2fr 1fr!important;background:rgba(255,255,255,.98)!important;}.mn-bottom-btn{pointer-events:auto!important;touch-action:manipulation!important;}button,a,input,select,textarea,[onclick],[role=button]{pointer-events:auto!important;touch-action:manipulation!important;}.drawer-backdrop.hidden,.mn-drawer-backdrop.hidden{display:none!important;pointer-events:none!important;}#modal{overflow-y:auto!important;-webkit-overflow-scrolling:touch!important;}';"+
          "document.head.appendChild(st);document.documentElement.style.overflowY='auto';document.body.style.overflowY='auto';"+
          "}catch(e){}})();";
        view.evaluateJavascript(js,null);
      }
    });
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