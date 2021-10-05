package com.sundar.sinfo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class VideoActivity : AppCompatActivity() {
    private val TAG = "VideoActivityTAG"
    private val activity = this@VideoActivity

    var mediaController: android.widget.MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        init()

    }
    fun init(){

        if (mediaController == null) {
            mediaController = android.widget.MediaController(activity)
            mediaController!!.setAnchorView(videoView)
        }
        videoView!!.setMediaController(mediaController)
        //   videoView!!.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video_one)
        videoView!!.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_one))
        videoView!!.requestFocus()
        videoView!!.start()



        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        },10000)
    }


}