package com.llw.mvidemo.network

import com.llw.mvidemo.data.model.Wallpaper
import retrofit2.http.GET

/**
 * Api 接口
 */
interface ApiService {

    /**
     * 获取壁纸
     */
    @GET("v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    suspend fun getWallPaper(): Wallpaper
}