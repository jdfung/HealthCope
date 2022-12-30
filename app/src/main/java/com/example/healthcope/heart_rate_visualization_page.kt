package com.example.healthcope

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import com.example.healthcope.DialogWithData.Companion.TAG
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.activity_user_profile.*

class heart_rate_visualization_page : AppCompatActivity() {

    lateinit var barChart: BarChart
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>

    lateinit var avgBPM: TextView
    lateinit var highestBPM: TextView

    lateinit var lineChart: LineChart
    lateinit var lineEntriesList: ArrayList<Entry>

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_rate_visualization_page)

        val testList = intent.getIntegerArrayListExtra("heartRateData")
//        Log.d(TAG, testList?.get(0).toString())

        avgBPM = findViewById(R.id.averageBPM)
        highestBPM = findViewById(R.id.highestBPM)
        avgBPM.text = testList?.average()?.toInt().toString()
        highestBPM.text = testList?.maxOrNull().toString()


        val actionBar = supportActionBar
        actionBar!!.title = "Heart Rate Visualisation"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        barChart = findViewById(R.id.idBarChart)
        setBarChartData(testList)

        lineChart = findViewById(R.id.idLineChart)
        setLineChartData(testList)

    }

    private fun setBarChartData(list: java.util.ArrayList<Int>?) {
        barEntriesList = ArrayList()
        Log.d(TAG, list?.size.toString())
        if(list != null) {
            for (item in 0 until list.orEmpty().size) {

                barEntriesList.add(
                    BarEntry(
                        item.toFloat() + 1f,
                        list[item].toFloat()
                    )
                )
            }
        }

        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
        barData = BarData(barDataSet)
        barChart.data = barData
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.setColor(resources.getColor(R.color.red))
        barDataSet.valueTextSize = 14f
        barChart.description.isEnabled = false
        barChart.animateXY(500, 500,Easing.EaseInCubic)
    }

    fun setLineChartData(list: java.util.ArrayList<Int>?) {

        val linevalues = ArrayList<Entry>()
        if(list != null) {
            for (item in 0 until list.orEmpty().size) {

                linevalues.add(
                    Entry(
                        item.toFloat() + 1f,
                        list[item].toFloat()
                    )
                )
            }
        }
//        linevalues.add(Entry(20f, 89f))
//        linevalues.add(Entry(30f, 75f))
//        linevalues.add(Entry(40f, 92f))
//        linevalues.add(Entry(50f, 86f))
//        linevalues.add(Entry(60f, 77f))


        val linedataset = LineDataSet(linevalues, "First")
        //We add features to our chart
        linedataset.color = resources.getColor(R.color.red)

        linedataset.circleRadius = 10f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 14F
        linedataset.fillColor = resources.getColor(R.color.red)
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //We connect our data to the UI Screen
        val data = LineData(linedataset)
        lineChart.data = data
        lineChart.setBackgroundColor(resources.getColor(R.color.white))
        lineChart.animateXY(500, 500,Easing.EaseInCubic)

    }
}