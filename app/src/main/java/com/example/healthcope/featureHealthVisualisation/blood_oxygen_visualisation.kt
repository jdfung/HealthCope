package com.example.healthcope.featureHealthVisualisation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.healthcope.R
import com.example.healthcope.featureHealthVisualisation.ongoing_health_report_page
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*

class blood_oxygen_visualisation : AppCompatActivity() {
    lateinit var barChart: BarChart
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>

    lateinit var avgBO: TextView

    lateinit var lineChart: LineChart
    lateinit var lineEntriesList: ArrayList<Entry>

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    var userHealthStats = ongoing_health_report_page()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_oxygen_visualisation)

        val dataList = intent.getIntegerArrayListExtra("bloodOxygenData")

        avgBO = findViewById(R.id.averageBO)
        avgBO.text = dataList?.average()?.toInt().toString()

        val actionBar = supportActionBar
        actionBar!!.title = "Blood Oxygen Visualisation"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        barChart = findViewById(R.id.idBarChart)
        setBarChartData(dataList)

        lineChart = findViewById(R.id.idLineChart)
        setLineChartData(dataList)
    }

    private fun setBarChartData(list: java.util.ArrayList<Int>?) {
        barEntriesList = ArrayList()
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
        barDataSet.setColor(resources.getColor(R.color.lightergreen))
        barDataSet.valueTextSize = 14f
        barChart.description.isEnabled = false
        barChart.animateXY(500, 500, Easing.EaseInCubic)
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

        val linedataset = LineDataSet(linevalues, "First")
        //We add features to our chart
        linedataset.color = resources.getColor(R.color.lightergreen)

        linedataset.circleRadius = 10f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 14F
        linedataset.fillColor = resources.getColor(R.color.lightergreen)
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER)

        //We connect our data to the UI Screen
        val data = LineData(linedataset)
        lineChart.data = data
        lineChart.setBackgroundColor(resources.getColor(R.color.white))
        lineChart.animateXY(500, 500, Easing.EaseInCubic)

    }
}