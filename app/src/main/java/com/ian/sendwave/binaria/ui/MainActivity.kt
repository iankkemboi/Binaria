package com.ian.sendwave.binaria.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.sendwave.binaria.databinding.ActivityMainBinding
import com.ian.sendwave.binaria.ui.SendMoneyFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}