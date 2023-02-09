package com.llw.mvidemo.data.repository

import com.llw.mvidemo.network.ApiService

/**
 * 数据存储库
 */
class MainRepository(private val apiService: ApiService) {

    /**
     * 获取壁纸
     */
    suspend fun getWallPaper() = apiService.getWallPaper()
}