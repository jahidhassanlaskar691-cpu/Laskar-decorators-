package com.example.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.data.ShowroomData
import com.example.model.HotspotItem
import org.json.JSONObject

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Showroom3DView(
    modifier: Modifier = Modifier,
    onHotspotSelected: (HotspotItem) -> Unit,
    onBookHotspot: (HotspotItem) -> Unit,
    onWebViewReady: (WebView) -> Unit = {}
) {
    val context = LocalContext.current

    val jsBridge = remember {
        object {
            @JavascriptInterface
            fun onHotspotSelected(json: String) {
                Handler(Looper.getMainLooper()).post {
                    try {
                        val obj = JSONObject(json)
                        val id = obj.optString("id")
                        val matched = ShowroomData.hotspotList.find { it.id == id }
                            ?: HotspotItem(
                                id = id,
                                title = obj.optString("title", "Event Setup"),
                                category = obj.optString("category", "Decor"),
                                price = obj.optString("price", "Contact for price"),
                                description = obj.optString("desc", ""),
                                imageName = obj.optString("image", "img_6_1732114728754_c2314361.jpg")
                            )
                        onHotspotSelected(matched)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            @JavascriptInterface
            fun onBookHotspot(json: String) {
                Handler(Looper.getMainLooper()).post {
                    try {
                        val obj = JSONObject(json)
                        val id = obj.optString("id")
                        val matched = ShowroomData.hotspotList.find { it.id == id }
                            ?: HotspotItem(
                                id = id,
                                title = obj.optString("title", "Event Setup"),
                                category = obj.optString("category", "Decor"),
                                price = obj.optString("price", "Contact for price"),
                                description = obj.optString("desc", ""),
                                imageName = obj.optString("image", "img_6_1732114728754_c2314361.jpg")
                            )
                        onBookHotspot(matched)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            WebView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    cacheMode = WebSettings.LOAD_NO_CACHE
                    // Hardware accelerated WebGL
                    setSupportZoom(false)
                }
                webChromeClient = WebChromeClient()
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        if (view != null) {
                            onWebViewReady(view)
                        }
                    }
                }
                addJavascriptInterface(jsBridge, "AndroidBridge")
                loadUrl("file:///android_asset/showroom/index.html")
            }
        },
        update = { webView ->
            // Update hooks if needed
        }
    )
}
