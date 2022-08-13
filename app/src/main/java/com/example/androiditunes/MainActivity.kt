package com.example.androiditunes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import com.example.androiditunes.databinding.ActivityMainBinding
import com.example.androiditunes.view.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val musicArray = arrayOf(
            "Rock",
            "Classic",
            "Pop"
        )
        val drawableArray = arrayOf(
            R.drawable.ic_home_rock,
            R.drawable.ic_dashboard_classic,
            R.drawable.ic_pop_notification
        )
        val tabLayout = binding.itunesTabLayout
        val viewPager2 = binding.itunesViewPager2

        val adapter = ViewPagerAdapter( supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.setIcon(drawableArray[position])
            tab.text = musicArray[position]
        }.attach()

    }

}