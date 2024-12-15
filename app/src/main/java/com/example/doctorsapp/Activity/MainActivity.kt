package com.example.doctorsapp.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorsapp.Adapter.NearDoctorsAdapter
import com.example.doctorsapp.R
import com.example.doctorsapp.ViewModel.MainViewModel
import com.example.doctorsapp.databinding.ActivityMainBinding
import com.example.doctorsapp.databinding.ActivitySplashBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNearByDoctor()

    }

    private fun initNearByDoctor() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            viewModel.loadDoctors().observe(this@MainActivity) {
                topView.layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                topView.adapter = NearDoctorsAdapter(it)
                progressBar.visibility = View.GONE
            }
        }
    }
}