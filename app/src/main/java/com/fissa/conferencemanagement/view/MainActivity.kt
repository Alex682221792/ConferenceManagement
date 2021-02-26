package com.fissa.conferencemanagement.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.fissa.conferencemanagement.R
import com.fissa.conferencemanagement.view.info_app.InfoAppFragment
import com.fissa.conferencemanagement.view.result.ResultFragment
import com.fissa.conferencemanagement.view.topics.TopicsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var topicsFragment: TopicsFragment
    lateinit var infoFragment: InfoAppFragment
    lateinit var resultFragment: ResultFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.prepareTabBarItems()
        this.showDefaultOption()
    }

    private fun showDefaultOption(){
        topicsFragment = TopicsFragment.newInstance()
        topicsFragment.parentActivity = this
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, topicsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun prepareTabBarItems(){
        val bottomNav = findViewById<BottomNavigationView>(R.id.BottomNavMenu)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.topics -> {
                    topicsFragment = TopicsFragment.newInstance()
                    topicsFragment.parentActivity = this
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, topicsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.result -> {
                    resultFragment = ResultFragment.newInstance()
                    resultFragment.parentActivity = this
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, resultFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.info -> {
                    infoFragment = InfoAppFragment.newInstance()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, infoFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }

}