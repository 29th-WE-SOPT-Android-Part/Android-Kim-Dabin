package com.example.sopt_assignment_dabin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.ui.adapter.BackgroundViewPagerAdapter
import com.example.sopt_assignment_dabin.ui.viewpager_fragment.HomeFragment
import com.example.sopt_assignment_dabin.databinding.ActivityHomeMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeMainBinding
    private lateinit var viewPagerAdater: BackgroundViewPagerAdapter

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavigation()
    }

    //bottomnavigation연결
    private fun initBottomNavigation() {
        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bnHome.menu.getItem(position).isChecked = true
            }
        })

        binding.bnHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_profile -> {
                    binding.vpHome.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_home -> {
                    binding.vpHome.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpHome.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    //viewPager연결
    private fun initAdapter() {
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
        viewPagerAdater = BackgroundViewPagerAdapter(this)
        viewPagerAdater.fragments.addAll(fragmentList)
        binding.vpHome.adapter = viewPagerAdater
    }
}
