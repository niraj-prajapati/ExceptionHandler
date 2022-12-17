/*
 * Created by Niraj Prajapati (https://nirajprajapati.com)
 * Copyright (c) 2022. All rights reserved.
 * Last modified at 17/12/22, 11:43 AM
 */

package com.nirajprajapati.exceptionhandlerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nirajprajapati.exceptionhandlerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            btnCrash.setOnClickListener {
                throw Error("Test Crash!")
            }
        }
    }
}