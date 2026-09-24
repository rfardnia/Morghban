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
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import java.util.HashSet;
import java.util.Arrays;

public class MainActivity extends Activity {
  private WebView webView;
  private static final String LAYOUT_SCRIPT="(function(){function fix(){try{var nav=document.querySelector('.mn-bottom');if(!nav)return;var style=document.getElementById('morghban-layout-r37');if(!style){style=document.createElement('style');style.id='morghban-layout-r37';style.textContent=\"html.morghban-mobile .mn-bottom{top:auto!important;bottom:0!important;height:calc(90px + env(safe-area-inset-bottom))!important;max-height:calc(90px + env(safe-area-inset-bottom))!important;min-height:0!important;grid-template-columns:1fr 1fr 1.2fr 1fr!important;grid-template-rows:72px!important;align-content:start!important;overflow:hidden!important;box-sizing:border-box!important;}html.morghban-mobile .mn-bottom-btn{height:72px!important;max-height:72px!important;min-height:0!important;pointer-events:auto!important;touch-action:manipulation!important;}html.morghban-mobile body:not(.mobile-native) .mobile-native-shell{display:none!important;}html.morghban-mobile .mn-log-meta{display:grid!important;grid-template-columns:1fr 1fr!important;gap:8px!important;font-size:14px!important;color:#3f4b50!important;}html.morghban-mobile .mn-log-meta span{background:#f5f7f8!important;border-radius:10px!important;padding:8px!important;}html.morghban-mobile .mn-drawer.open{pointer-events:auto!important;transform:translateX(0)!important;}html.morghban-mobile .mn-drawer-backdrop:not(.hidden){pointer-events:auto!important;}\";document.head.appendChild(style);}if(!document.getElementById('mnMoreBtn')){var b=document.createElement('button');b.id='mnMoreBtn';b.type='button';b.className='mn-bottom-btn';b.innerHTML='<span class=\\\"mn-bicon\\\">☰</span><span>بیشتر</span>';b.onclick=function(e){e.preventDefault();e.stopPropagation();if(window.openMobileDrawer)window.openMobileDrawer();};nav.appendChild(b);}var hb=document.querySelector('.mn-menu-trigger');if(hb&&!hb.dataset.r37){hb.dataset.r37='1';hb.type='button';hb.onclick=function(e){e.preventDefault();e.stopPropagation();if(window.openMobileDrawer)window.openMobileDrawer();};}if(window.showPage&&!window.__r37ShowPage){window.__r37ShowPage=window.showPage;window.showPage=function(id){if(['home','cycleDetail','manage','farmDetail','houseDetail','about'].indexOf(id)<0)document.body.classList.remove('mobile-native');return window.__r37ShowPage.apply(this,arguments);};}if(window.fa&&!window.__r37Fa){window.__r37Fa=window.fa;window.fa=function(date){if(!date)return '—';try{var raw=String(date).slice(0,10),dt=new Date(raw+'T12:00:00');if(!/^\\d{4}-\\d{2}-\\d{2}$/.test(raw)||isNaN(dt.getTime()))return '—';return new Intl.DateTimeFormat('fa-IR-u-ca-persian',{year:'numeric',month:'2-digit',day:'2-digit'}).format(dt);}catch(e){return '—';}};}document.querySelectorAll('.mn-card-sub,.mn-log-row b,.mn-screen-title,.sub').forEach(function(el){if(/NaN|N\\/N\\/N/.test(el.textContent||''))el.textContent=(el.textContent||'').replace(/NaN\\/?NaN\\/?NaN|N\\/N\\/N/g,'—');});}catch(e){}}if(document.readyState==='loading')document.addEventListener('DOMContentLoaded',fix);else fix();new MutationObserver(fix).observe(document,{childList:true,subtree:true});})();";
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
    if(WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)){
      WebViewCompat.addDocumentStartJavaScript(webView,LAYOUT_SCRIPT,
        new HashSet<>(Arrays.asList("https://script.google.com","https://script.googleusercontent.com","https://*.googleusercontent.com")));
    }
    webView.setWebViewClient(new WebViewClient(){
      @Override public void onPageFinished(WebView view,String url){
        super.onPageFinished(view,url);
        // Fallback for WebView versions without document-start injection.
        view.evaluateJavascript(LAYOUT_SCRIPT,null);
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