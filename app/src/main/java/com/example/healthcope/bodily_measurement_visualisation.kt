package com.example.healthcope

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class bodily_measurement_visualisation : AppCompatActivity() {

    lateinit var lineChart1: LineChart
    lateinit var lineChart2: LineChart
    lateinit var lineChart3: LineChart
    lateinit var lineEntriesList: ArrayList<Entry>

    lateinit var avgWeight: TextView
    lateinit var highestWeight: TextView
    lateinit var avgFat: TextView
    lateinit var highestFat: TextView
    lateinit var avgTemp: TextView
    lateinit var highestTemp: TextView

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bodily_measurement_visualisation)

        val weightDataList = intent.getIntegerArrayListExtra("weightData")
        val bodyTempDataList = intent.getIntegerArrayListExtra("bodyTempData")
        val bodyFatDataList = intent.getIntegerArrayListExtra("bodyFatData")

        avgWeight = findViewById(R.id.averageWeight)
        avgWeight.text = weightDataList?.average()?.toInt().toString()
        highestWeight = findViewById(R.id.highestWeight)
        highestWeight.text = weightDataList?.maxOrNull().toString()

        avgFat = findViewById(R.id.averageFat)
        avgFat.text = bodyFatDataList?.average()?.toInt().toString()
        highestFat = findViewById(R.id.highestFat)
        highestFat.text = bodyFatDataList?.maxOrNull().toString()

        avgTemp = findViewById(R.id.averageTemp)
        avgTemp.text = bodyTempDataList?.average()?.toInt().toString()
        highestTemp = findViewById(R.id.highestTemp)
        highestTemp.text = bodyTempDataList?.maxOrNull().toString()

        val actionBar = supportActionBar
        actionBar!!.title = "Bodily Measurements"
        actionBar.setDisplayHomeAsUpEnabled(true)

        lineChart1 = findViewById(R.id.idLineChart1)
        lineChart2 = findViewById(R.id.idLineChart2)
        lineChart3 = findViewById(R.id.idLineChart3)
        setLineChartData(lineChart1, R.color.grey, weightDataList)
        setLineChartData(lineChart2, R.color.blue, bodyTempDataList)
        setLineChartData(lineChart3, R.color.orange, bodyFatDataList)
    }

    fun setLineChartData(chart: LineChart, SpeciColor: Int, list: java.util.ArrayList<Int>?) {

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


        val linedataset = LineDataSet(linevalues, "First")
        //We add features to our chart
        linedataset.color = SpeciColor

        linedataset.circleRadius = 10f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 14F
        linedataset.fillColor = resources.getColor(SpeciColor)
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //We connect our data to the UI Screen
        val data = LineData(linedataset)
        chart.data = data
        chart.setBackgroundColor(resources.getColor(SpeciColor))
        chart.animateXY(500, 500, Easing.EaseInCubic)

    }
}