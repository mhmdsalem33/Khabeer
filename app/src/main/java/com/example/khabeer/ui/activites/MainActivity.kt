package com.example.khabeer.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.khabeer.R
import com.example.khabeer.databinding.ActivityMainBinding
import com.example.khabeer.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    /*
      Technologies used
     * Kotlin
     * Mvvm
     * Coroutines
     * Dagger hilt
     * Retrofit
     * State flow
     * Live data

      * i have used SalamtakApi to make calling for api with Retrofit and Coroutines and i handle it with MainRepository and MainViewModel

      * we will call api request when internet connection is available if there is no internet connection we will not call api
        object NetworkStatus will take care about internet connection

   */


    private val mainMvvm : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        login()
        getInformation()





    }
    private fun login() {
        mainMvvm.userLogin()
        lifecycleScope.launchWhenStarted {
            mainMvvm.getUser.collect{ user ->
                user?.let {
                    Log.d("testApp" , it.Token)
                    mainMvvm.getInformation("Bearer "+it.Token)
                }
            }
        }
    }
    private fun getInformation() {
        mainMvvm.getInformation.observe(this){  data ->
            data?.let {
                binding.date.text =  it.Date

                data.Employee.forEach { employee ->
                    binding.apply {
                        userName.text = employee.EMP_DATA_AR
                        jobName.text  = employee.JOBNAME_AR
                    }

                    val one =    data.Allowences[0].SAL_VALUE + data.Allowences[1].SAL_VALUE   // الاستحقاقات
                    val two =    data.Deduction[0].SAL_VALUE  // الاستقطاعات

                    val total = "%.2f".format(one - two ) //  اجمالى الراتب النهائى لعدم تكرار الارقام الدبل
                    binding.salValue.text = StringBuffer().append(total).append(" ").append("ج")


                    binding.mySal.text  = StringBuffer().append(" $one ").append(resources.getString(R.string.pound)).append(" الاستحقاقات")
                    binding.cut.text    = StringBuffer().append(" $two ").append(resources.getString(R.string.pound)).append(" الاستقطاعات")
                    binding.total.text  = StringBuffer().append(" $total ").append(resources.getString(R.string.pound)).append(" اجمالى الراتب")


                    binding.basic.text    = StringBuffer().append(data.Allowences[0].SAL_VALUE.toString()).append(" ").append(resources.getString(R.string.pound)) // المرتب الاساسى
                    binding.normal.text   = StringBuffer().append(data.Allowences[1].SAL_VALUE.toString()).append(" ").append(resources.getString(R.string.pound))  // طبيعة العمل
                    binding.otherCut.text = StringBuffer().append(two.toString()).append(" ").append(resources.getString(R.string.pound))

                    configChartView( one , two )
                }
            }
        }

    }

    private fun configChartView(one : Double , two : Double ) {
        val salary   = listOf( one , two )
        val details  = listOf("الاستحقاقات"  , "الاستقطاعات")


        val pie : Pie = AnyChart.pie()
        val dataPieChart : MutableList<DataEntry>  = mutableListOf()
        for (index in  salary.indices)
        {
            dataPieChart.add(ValueDataEntry(details.elementAt(index) , salary.elementAt(index)))
        }
        pie.data(dataPieChart)
        pie.title("Salaries Overview")
        binding.chart.setChart(pie)
    }



}