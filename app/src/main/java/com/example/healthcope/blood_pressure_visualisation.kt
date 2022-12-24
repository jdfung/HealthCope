package com.example.healthcope

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*

class blood_pressure_visualisation : AppCompatActivity() {
    lateinit var barChart: BarChart
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>

    lateinit var lineChart: LineChart
    lateinit var lineEntriesList: ArrayList<Entry>

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_pressure_visualisation)

        val actionBar = supportActionBar
        actionBar!!.title = "Blood Pressure Visualisation"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        barChart = findViewById(R.id.idBarChart)
        setBarChartData()

        lineChart = findViewById(R.id.idLineChart)
        setLineChartData()
    }

    private fun setBarChartData() {
        barEntriesList = ArrayList()
        barEntriesList.add(BarEntry(1f, 89f))
        barEntriesList.add(BarEntry(2f, 75f))
        barEntriesList.add(BarEntry(3f, 92f))
        barEntriesList.add(BarEntry(4f, 86f))
        barEntriesList.add(BarEntry(5f, 77f))

        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
        barData = BarData(barDataSet)
        barChart.data = barData
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.setColor(resources.getColor(R.color.blue))
        barDataSet.valueTextSize = 14f
        barChart.description.isEnabled = false
        barChart.animateXY(500, 500, Easing.EaseInCubic)
    }

    fun setLineChartData() {

        val linevalues = ArrayList<Entry>()
        linevalues.add(Entry(20f, 89f))
        linevalues.add(Entry(30f, 75f))
        linevalues.add(Entry(40f, 92f))
        linevalues.add(Entry(50f, 86f))
        linevalues.add(Entry(60f, 77f))


        val linedataset = LineDataSet(linevalues, "First")
        //We add features to our chart
        linedataset.color = resources.getColor(R.color.blue)

        linedataset.circleRadius = 10f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 14F
        linedataset.fillColor = resources.getColor(R.color.blue)
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //We connect our data to the UI Screen
        val data = LineData(linedataset)
        lineChart.data = data
        lineChart.setBackgroundColor(resources.getColor(R.color.white))
        lineChart.animateXY(500, 500, Easing.EaseInCubic)

    }
}