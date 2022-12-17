/*
 * Created by Niraj Prajapati (https://nirajprajapati.com)
 * Copyright (c) 2022. All rights reserved.
 * Last modified at 17/12/22, 11:43 AM
 */

package com.nirajprajapati.exceptionhandlerdemo

import android.app.Application
import com.nirajprajapati.exceptionhandler.ExceptionHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ExceptionHandler.initialize(this, CrashActivity::class.java)
    }

}