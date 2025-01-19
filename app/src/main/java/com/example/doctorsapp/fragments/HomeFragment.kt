package com.example.doctorsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorsapp.Adapter.NearDoctorsAdapter
import com.example.doctorsapp.Model.DoctorModel
import com.example.doctorsapp.R
import com.example.doctorsapp.ViewModel.MainViewModel
import com.example.doctorsapp.databinding.FragmentHomeBinding
import com.example.doctorsapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val nearDoctorsAdapter by lazy { NearDoctorsAdapter(mutableListOf()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNearByDoctor()
        setupNearDoctorsAdapter()

    }

    private fun setupNearDoctorsAdapter() {
        binding.topView.apply {
            adapter = nearDoctorsAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun  initNearByDoctor() {
        binding.apply {
            progressBar.visibility = View.VISIBLE

            viewModel.loadDoctors().observe(viewLifecycleOwner) { doctorList ->
                topView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                topView.adapter = NearDoctorsAdapter(doctorList)
                progressBar.visibility = View.GONE

            }
        }
    }


}