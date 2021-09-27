package com.jyzx.helper.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.jyzx.helper.R

class MediaPlay {


    companion object{
        lateinit var instance:MediaPlay
        lateinit var mediaPlayer: MediaPlayer
        @JvmName("getInstance1")
        fun getInstance(): MediaPlay{

                instance = MediaPlay()

            return instance
        }

        fun playMedia(context: Context,path: Int, listener:MediaPlayer.OnCompletionListener, preListener:MediaPlayer.OnPreparedListener ){
            mediaPlayer = MediaPlayer.create(context, path)
            mediaPlayer.setVolume(1.0f,1.0f)
            mediaPlayer.isLooping = false
            // mediaPlayer.setDataSource(path)
            // mediaPlayer.prepareAsync()
            mediaPlayer.setOnCompletionListener(listener)
            mediaPlayer.setOnPreparedListener(preListener)
            mediaPlayer.start()
        }

        fun relesaeMedia(){
            mediaPlayer?.let { mediaPlayer.release() }
        }
    }
    fun initMedia(){
        mediaPlayer = MediaPlayer()
        mediaPlayer.setVolume(1.0f,1.0f)
        mediaPlayer.isLooping = false
    }

}