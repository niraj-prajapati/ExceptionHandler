/*
 * Created by Niraj Prajapati (https://nirajprajapati.com)
 * Copyright (c) 2022. All rights reserved.
 * Last modified at 17/12/22, 11:43 AM
 */

package com.nirajprajapati.exceptionhandlerdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nirajprajapati.exceptionhandler.ExceptionHandler
import com.nirajprajapati.exceptionhandlerdemo.databinding.ActivityCrashBinding

class CrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrashBinding.inflate(layoutInflater)
        val view = binding.root
        ExceptionHandler.getThrowableFromIntent(intent).let {
            Log.e("TAG", "Error Data: ", it)
            // TODO: send report to your server or firebase
            // Firebase.crashlytics.log(it)
        }
        setContentView(view)

        binding.apply {
            btnRestart.setOnClickListener {
                finishAffinity()
                startActivity(Intent(this@CrashActivity, MainActivity::class.java))
            }
        }
    }
}