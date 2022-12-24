package com.example.healthcope

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
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

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bodily_measurement_visualisation)

        val actionBar = supportActionBar
        actionBar!!.title = "Bodily Measurements"
        actionBar.setDisplayHomeAsUpEnabled(true)

        lineChart1 = findViewById(R.id.idLineChart1)
        lineChart2 = findViewById(R.id.idLineChart2)
        lineChart3 = findViewById(R.id.idLineChart3)
        setLineChartData(lineChart1, R.color.grey)
        setLineChartData(lineChart2, R.color.blue)
        setLineChartData(lineChart3, R.color.orange)
    }

    fun setLineChartData(chart: LineChart, SpeciColor: Int) {

        val linevalues = ArrayList<Entry>()
        linevalues.add(Entry(20f, 89f))
        linevalues.add(Entry(30f, 75f))
        linevalues.add(Entry(40f, 92f))
        linevalues.add(Entry(50f, 86f))
        linevalues.add(Entry(60f, 77f))


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