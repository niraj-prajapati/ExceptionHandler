/*
 * Created by Niraj Prajapati (https://nirajprajapati.com)
 * Copyright (c) 2022. All rights reserved.
 * Last modified at 17/12/22, 11:43 AM
 */

package com.nirajprajapati.exceptionhandler

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import kotlin.system.exitProcess

class ExceptionHandler private constructor(
    private val applicationContext: Context,
    private val defaultHandler: Thread.UncaughtExceptionHandler,
    private val activityToBeLaunched: Class<*>
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        try {
            launchActivity(applicationContext, activityToBeLaunched, p1)
            exitProcess(0)
        } catch (e: Exception) {
            defaultHandler.uncaughtException(p0, p1)
        }
    }

    private fun launchActivity(
        applicationContext: Context,
        activity: Class<*>,
        exception: Throwable
    ) {
        val crashedIntent = Intent(applicationContext, activity).also {
            it.putExtra(INTENT_DATA_NAME, Gson().toJson(exception))
        }
        crashedIntent.addFlags( // Clears all previous activities. So backstack will be gone
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        )
        crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        applicationContext.startActivity(crashedIntent)
    }

    companion object {
        private const val INTENT_DATA_NAME = "CrashData"
        private const val TAG = "CustomExceptionHandler"

        /**
         * [applicationContext]: Required for launching the activity.
         *
         * [activityToBeLaunched]: The activity that to be launched :D,
         * it will put Exception data as JSON string. It can be
         * easily converted back to [Throwable] with [getThrowableFromIntent].
         *
         * **Example usage at activity:**
         * ```
         * GlobalExceptionHandler.getThrowableFromIntent(intent).let {
         *   Log.e(TAG, "Error Data: ", it)
         *   // Send logs or do your stuff...
         * }
         * ```
         */
        fun initialize(
            applicationContext: Context,
            activityToBeLaunched: Class<*>
        ) {
            val handler = ExceptionHandler(
                applicationContext,
                Thread.getDefaultUncaughtExceptionHandler() as Thread.UncaughtExceptionHandler,
                activityToBeLaunched
            )
            Thread.setDefaultUncaughtExceptionHandler(handler)
        }

        /**
         * Gets throwable data from activity's intent. It'll return null if stringExtra has not been
         * found or another reasons.
         */
        fun getThrowableFromIntent(intent: Intent): Throwable? {
            return try {
                Gson().fromJson(intent.getStringExtra(INTENT_DATA_NAME), Throwable::class.java)
            } catch (e: Exception) {
                Log.e(TAG, "getThrowableFromIntent: ", e)
                null
            }
        }
    }
}