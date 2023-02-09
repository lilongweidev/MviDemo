package com.llw.mvidemo.data.intent

/**
 * 页面意图
 */
sealed class MainIntent {
    /**
     * 获取壁纸
     */
    object GetWallpaper : MainIntent()
}