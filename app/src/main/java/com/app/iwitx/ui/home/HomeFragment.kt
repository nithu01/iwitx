package com.app.iwitx.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.app.iwitx.R
import com.app.iwitx.adapter.SliderAdapter
import com.app.iwitx.model.ImageData
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var viewPager: ViewPager? = null
    var sliderAdapter: SliderAdapter? = null
    private var currentPage = 0
    private val NUM_PAGES = 3
    var images_list: ArrayList<ImageData> = ArrayList<ImageData>()
    val sliderImageId = intArrayOf(
            R.drawable.aeps, R.drawable.aeps2, R.drawable.aeps
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = root.findViewById(R.id.view_pager)
        settimageslider()
        timer()
        return root
    }

    fun timer(){
        val handler = Handler()
        val Update = Runnable {
            if (currentPage === NUM_PAGES) {
                currentPage = 0
            }
            viewPager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)
    }

    private fun settimageslider() {
        sliderAdapter = SliderAdapter(requireContext(), sliderImageId)
        viewPager!!.adapter = sliderAdapter
    }

}