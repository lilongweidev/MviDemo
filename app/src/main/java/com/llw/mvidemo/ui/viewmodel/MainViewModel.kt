package com.llw.mvidemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llw.mvidemo.data.repository.MainRepository
import com.llw.mvidemo.data.intent.MainIntent
import com.llw.mvidemo.data.state.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/**
 * @link MainActivity
 */
class MainViewModel(private val repository: MainRepository) : ViewModel() {

    //创建意图管道，容量无限大
    val mainIntentChannel = Channel<MainIntent>(Channel.UNLIMITED)

    //可变状态数据流
    private val _state = MutableStateFlow<MainState>(MainState.Idle)

    //可观察状态数据流
    val state: StateFlow<MainState> get() = _state

    init {
        viewModelScope.launch {
            //收集意图
            mainIntentChannel.consumeAsFlow().collect {
                when (it) {
                    //发现意图为获取壁纸
                    is MainIntent.GetWallpaper -> getWallpaper()
                }
            }
        }
    }

    /**
     * 获取壁纸
     */
    private fun getWallpaper() {
        viewModelScope.launch {
            //修改状态为加载中
            _state.value = MainState.Loading
            //网络请求状态
            _state.value = try {
                //请求成功
                MainState.Wallpapers(repository.getWallPaper())
            } catch (e: Exception) {
                //请求失败
                MainState.Error(e.localizedMessage ?: "UnKnown Error")
            }
        }
    }
}