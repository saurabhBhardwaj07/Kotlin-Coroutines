package com.saurabhbhardwaj.kotlincoroutines.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.saurabhbhardwaj.kotlincoroutines.R
import com.saurabhbhardwaj.kotlincoroutines.ui.basic.BasicActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.errorhandling.exceptionhandler.ExceptionHandlerActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.errorhandling.supervisor.IgnoreErrorAndContinueActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.errorhandling.trycatch.TryCatchActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.retrofit.parallel.ParallelNetworkCallsActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.retrofit.series.SeriesNetworkCallsActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.retrofit.single.SingleNetworkCallActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.room.RoomDBActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.task.onetask.LongRunningTaskActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.task.twotasks.TwoLongRunningTasksActivity
import com.saurabhbhardwaj.kotlincoroutines.ui.timeout.TimeoutActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
    }

    fun startSeriesNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
    }

    fun startParallelNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
    }

    fun startRoomDatabaseActivity(view: View) {
        startActivity(Intent(this@MainActivity, RoomDBActivity::class.java))
    }

    fun startTimeoutActivity(view: View) {
        startActivity(Intent(this@MainActivity, TimeoutActivity::class.java))
    }

    fun startTryCatchActivity(view: View) {
        startActivity(Intent(this@MainActivity, TryCatchActivity::class.java))
    }

    fun startExceptionHandlerActivity(view: View) {
        startActivity(Intent(this@MainActivity, ExceptionHandlerActivity::class.java))
    }

    fun startIgnoreErrorAndContinueActivity(view: View) {
        startActivity(Intent(this@MainActivity, IgnoreErrorAndContinueActivity::class.java))
    }

    fun startLongRunningTaskActivity(view: View) {
        startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
    }

    fun startTwoLongRunningTasksActivity(view: View) {
        startActivity(Intent(this@MainActivity, TwoLongRunningTasksActivity::class.java))
    }

    fun startBasicActivity(view: View) {
        startActivity(Intent(this@MainActivity, BasicActivity::class.java))
    }

}
