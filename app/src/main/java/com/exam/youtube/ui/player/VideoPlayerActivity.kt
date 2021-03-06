package com.exam.youtube.ui.player

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.exam.youtube.R
import com.exam.youtube.model.Item
import com.exam.youtube.utils.GOOGLE_API_KEY
import com.exam.youtube.utils.VIDEO_ITEM
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private  val TAG = VideoPlayerActivity::class.java.simpleName
    private var mVideoId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        // получение элемента видео и заполнение его данными в пользовательском интерфейсе
        val mItem = intent.getSerializableExtra(VIDEO_ITEM) as Item
        populateVideoData(mItem)

        // Инициализация и загрузка видео из API YouTube
        mVideoId = mItem.id
        youtubePlayer.initialize(GOOGLE_API_KEY,this)

    }

    private fun populateVideoData(mItem: Item) {
        tv_title.text = (mItem.video.title)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, p2: Boolean) {
        Log.i(TAG, "onInitializationSuccess: START LOADING VIDEO ...")

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) youTubePlayer?.setFullscreen(true)

        youTubePlayer?.loadVideo(mVideoId)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Log.i(TAG, "onInitializationFailure: FAILED TO INITIALIZE YOUTUBE PLAYER ...")
    }
}