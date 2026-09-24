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
  private static final String LAYOUT_SCRIPT="(function(){\nfunction formatNumber(v,d){if(v===''||v===null||v===undefined||v==='—')return '—';var n=Number(v);return Number.isFinite(n)?n.toLocaleString('fa-IR',{maximumFractionDigits:d||0}):'—'}\nfunction formatDate(v){if(!v)return '—';try{var raw=String(v).slice(0,10),dt=new Date(raw+'T12:00:00');return /^\\d{4}-\\d{2}-\\d{2}$/.test(raw)&&!isNaN(dt.getTime())?new Intl.DateTimeFormat('fa-IR-u-ca-persian',{year:'numeric',month:'2-digit',day:'2-digit'}).format(dt):'—'}catch(e){return '—'}}\nfunction fix(){\ntry{\nif(!document.querySelector('.mn-bottom'))return;\nif(!document.getElementById('morghban-layout-r38')){var st=document.createElement('style');st.id='morghban-layout-r38';st.textContent=\"html.morghban-mobile body:not(.mobile-native) .mobile-native-shell,html.morghban-mobile .mobile-native-shell.hidden{display:none!important}html.morghban-mobile body.mobile-native .mobile-native-shell:not(.hidden){display:block!important;overflow:visible!important}html.morghban-mobile .mn-drawer{z-index:1001!important;pointer-events:auto!important}html.morghban-mobile .mn-drawer.open{transform:translateX(0)!important;visibility:visible!important}html.morghban-mobile .mn-drawer-backdrop:not(.hidden){z-index:1000!important;pointer-events:auto!important}html.morghban-mobile .mn-menu-trigger,html.morghban-mobile #mnMoreBtn{pointer-events:auto!important;touch-action:manipulation!important}html.morghban-mobile .mn-log-meta{display:grid!important;grid-template-columns:repeat(2,minmax(0,1fr))!important;gap:8px!important;font-size:14px!important}html.morghban-mobile .mn-log-meta span{background:#f5f7f8!important;border-radius:10px!important;padding:8px!important;min-width:0!important}html.morghban-mobile .mn-cycle-card .mn-kpis{grid-template-columns:repeat(2,minmax(0,1fr))!important}html.morghban-mobile .mn-cycle-card .mn-kpi:nth-child(3){display:none!important}\";document.head.appendChild(st)}\nif(typeof window.nf==='function'&&!window.nf.__r38){window.nf=formatNumber;window.nf.__r38=true}\nvar logs=typeof HOME!=='undefined'&&HOME&&HOME.logs||[];\nvar rows=document.querySelectorAll('.mn-log-row');\nif(rows.length===logs.length)rows.forEach(function(row,i){var l=logs[logs.length-1-i],key=String(l.Log_ID);if(row.dataset.r38log===key)return;var w=l['وزن متوسط g'],hasW=w!==''&&w!=null&&Number(w)>0,a=l['حداقل دما °C'],b=l['حداکثر دما °C'],hasT=a!==''&&a!=null&&b!==''&&b!=null;row.innerHTML='<div class=\"topline\"><div><b>'+formatDate(l._date||l['تاریخ'])+'</b><div class=\"mn-card-sub\">روز '+formatNumber(l._ageDay)+'</div></div>'+(hasW?'<span class=\"mn-status\">⚖ وزن‌کشی</span>':'')+'</div><div class=\"mn-log-meta\"><span>تلفات: <b>'+formatNumber(l['تلفات امروز'])+'</b></span><span>حذف: <b>'+formatNumber(l['حذف امروز'])+'</b></span><span>دان: <b>'+formatNumber(l._feedTotal,1)+' کیلوگرم</b></span><span>آب: <b>'+formatNumber(l['آب مصرفی امروز L'],1)+' لیتر</b></span>'+(hasW?'<span>وزن: <b>'+formatNumber(w)+' گرم</b></span>':'')+(hasT?'<span>دما: <b>'+formatNumber(a,1)+' تا '+formatNumber(b,1)+' °C</b></span>':'')+'</div>';row.dataset.r38log=key});\ndocument.querySelectorAll('.mn-card-sub,.mn-log-row b,.mn-screen-title').forEach(function(el){if(/NaN|N\\/N\\/N/i.test(el.textContent||''))el.textContent=(el.textContent||'').replace(/NaN\\/?NaN\\/?NaN|N\\/N\\/N/gi,'—')});\n}catch(e){}\n}\nif(!window.__morghbanR38Click){window.__morghbanR38Click=true;document.addEventListener('click',function(e){var b=e.target.closest&&e.target.closest('.mn-menu-trigger,#mnMoreBtn');if(b&&typeof window.openMobileDrawer==='function'){e.preventDefault();e.stopImmediatePropagation();window.openMobileDrawer()}},true)}\nif(document.readyState==='loading')document.addEventListener('DOMContentLoaded',fix);else fix();\nnew MutationObserver(fix).observe(document,{childList:true,subtree:true});\n})();";
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