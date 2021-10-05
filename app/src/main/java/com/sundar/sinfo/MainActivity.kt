package com.sundar.sinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Array
import java.util.*
import kotlin.time.milliseconds

class MainActivity : AppCompatActivity() {
    private val TAG = "BuyAddressScreenTAG"
    private val activity = this@MainActivity
    var imageIndex = 0
    var isImage: Boolean = false
    var videoIndex: Int = 0
    var videoArray = arrayOf(R.raw.video_one, R.raw.video_two)
    var arrayImage = arrayOf(R.drawable.m_one, R.drawable.m_two, R.drawable.m_thre);
    var mediaController: android.widget.MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    fun init() {

        //Todo Marquee Textview use(Auto horizontal scroll)
        val text: String = activity.getString(R.string.nysun)
        txt_morque.text = text
        txt_morque.isSelected = true

        //Todo Load Webview data:
        /* var handler = Handler()
         handler.postDelayed({
             webview()
         },5000)*/

        webTiming()

        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (isImage) {
                    videoplay()
                } else {
                    showImage()
                }
                start()
            }
        }.start()

    }

    fun showImage() {
        isImage = true
        imgView.visibility = View.VISIBLE
        videoView.visibility = View.GONE
        imgView.setImageDrawable(
            activity.getResources().getDrawable(arrayImage.get(imageIndex))
        )
        Log.e("showing image", arrayImage.get(imageIndex).toString())
        imageIndex++
        if (imageIndex == arrayImage.size) imageIndex = 0

    }

    fun videoplay() {
        isImage = false
        imgView.visibility = View.GONE
        videoView.visibility = View.VISIBLE
        if (mediaController == null) {
            mediaController = android.widget.MediaController(activity)
            mediaController!!.setAnchorView(videoView)
        }
        videoView!!.setMediaController(mediaController)
        //   videoView!!.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video_one)
        videoView!!.setVideoURI(
            Uri.parse(
                "android.resource://" + getPackageName() + "/" + videoArray.get(
                    videoIndex
                )
            )
        )
        videoView!!.requestFocus()
        videoView!!.start()
        Log.e("showing video", videoArray.get(videoIndex).toString())

        videoIndex++
        if (videoIndex == videoArray.size) videoIndex = 0

    }

    fun webview() {
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.webViewClient = WebViewClient()
        // this will load the url of the website
        webView.loadUrl(activity.getString(R.string.com_url))
        // this will enable the javascript settings
        webView.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)

    }

    fun webTiming() {
        object : CountDownTimer(1000, 10000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                webview()
//
            }
        }.start()

    }

}