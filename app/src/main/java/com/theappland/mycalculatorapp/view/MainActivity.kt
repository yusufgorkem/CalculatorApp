package com.theappland.mycalculatorapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.theappland.mycalculatorapp.R
import com.theappland.mycalculatorapp.databinding.ActivityMainBinding
import com.theappland.mycalculatorapp.view.viewmodel.CalculateViewModel

class MainActivity : AppCompatActivity()
{
    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var calculateViewModel: CalculateViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        calculateViewModel = CalculateViewModel()

        mainBinding.calculateViewModel = calculateViewModel
    }
}