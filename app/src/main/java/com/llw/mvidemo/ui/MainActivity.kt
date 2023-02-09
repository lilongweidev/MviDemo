package com.llw.mvidemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.llw.mvidemo.network.NetworkUtils
import com.llw.mvidemo.databinding.ActivityMainBinding
import com.llw.mvidemo.data.intent.MainIntent
import com.llw.mvidemo.data.state.MainState
import com.llw.mvidemo.ui.adapter.WallpaperAdapter
import com.llw.mvidemo.ui.viewmodel.MainViewModel
import com.llw.mvidemo.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private var wallPaperAdapter = WallpaperAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //使用ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //绑定ViewModel
        mainViewModel = ViewModelProvider(this, ViewModelFactory(NetworkUtils.apiService))[MainViewModel::class.java]
        //初始化
        initView()
        //观察ViewModel
        observeViewModel()
    }

    /**
     * 观察ViewModel
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            //状态收集
            mainViewModel.state.collect {
                when(it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        binding.btnGetWallpaper.visibility = View.GONE
                        binding.pbLoading.visibility = View.VISIBLE
                    }
                    is MainState.Wallpapers -> {     //数据返回
                        binding.btnGetWallpaper.visibility = View.GONE
                        binding.pbLoading.visibility = View.GONE

                        binding.rvWallpaper.visibility = View.VISIBLE
                        it.wallpaper.let { paper ->
                            wallPaperAdapter.addData(paper.res.vertical)
                        }
                        wallPaperAdapter.notifyDataSetChanged()
                    }
                    is MainState.Error -> {
                        binding.pbLoading.visibility = View.GONE
                        binding.btnGetWallpaper.visibility = View.VISIBLE
                        Log.d("TAG", "observeViewModel: $it.error")
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    /**
     * 初始化
     */
    private fun initView() {
        //RV配置
        binding.rvWallpaper.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter  = wallPaperAdapter
        }
        //按钮点击
        binding.btnGetWallpaper.setOnClickListener {
            lifecycleScope.launch{
                //发送意图
                mainViewModel.mainIntentChannel.send(MainIntent.GetWallpaper)
            }
        }
    }
}