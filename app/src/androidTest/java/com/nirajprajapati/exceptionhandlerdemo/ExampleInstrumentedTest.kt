/*
 * Created by Niraj Prajapati (https://nirajprajapati.com)
 * Copyright (c) 2022. All rights reserved.
 * Last modified at 17/12/22, 11:44 AM
 */

package com.nirajprajapati.exceptionhandlerdemo

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nirajprajapati.exceptionhandlerdemo", appContext.packageName)
    }
}