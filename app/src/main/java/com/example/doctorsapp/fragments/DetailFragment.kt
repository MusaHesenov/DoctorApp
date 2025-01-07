package com.example.doctorsapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import com.example.doctorsapp.Adapter.NearDoctorsAdapter
import com.example.doctorsapp.Model.DoctorModel
import com.example.doctorsapp.R
import com.example.doctorsapp.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var item: DoctorModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle()
    }

    private fun getBundle() {
        arguments?.let {
            item = it.getParcelable("object")!!

            binding.apply {
                specialTxt.text = item.Special
                patiensTxt.text = item.Patiens
                bioTxt.text = item.Biography
                addressTxt.text = item.Address
                timeTxt.text = item.Time
                dateTxt.text = item.Date

                experienceTxt.text = "${item.Expriense} Years"
                ratingTxt.text = "${item.Rating}"

                backBtn.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

                websiteBtn.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(item.Site)
                    startActivity(i)
                }

                messageBtn.setOnClickListener {
                    val uri = Uri.parse("smsto:${item.Mobile}")
                    val intent = Intent(Intent.ACTION_SENDTO, uri)
                    intent.putExtra("sms_body", "the SMS text")
                    startActivity(intent)
                }

                callBtn.setOnClickListener {
                    val uri = "tel:${item.Mobile.trim()}"
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
                    startActivity(intent)
                }

                directionBtn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.Location))
                    startActivity(intent)
                }

                shareBtn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_SUBJECT, item.Name)
                    intent.putExtra(
                        Intent.EXTRA_TEXT,
                        "${item.Name} ${item.Address} ${item.Mobile}"
                    )
                    startActivity(Intent.createChooser(intent, "Choose one"))
                }
            }

            Glide.with(this@DetailFragment)
                .load(item.Picture)
                .into(binding.img)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}