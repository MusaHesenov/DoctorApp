package com.example.doctorsapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.doctorsapp.Model.DoctorModel
import com.example.doctorsapp.databinding.ViewholderNearbyDoctorBinding
import com.example.doctorsapp.fragments.DetailFragment

class NearDoctorsAdapter(val items:MutableList<DoctorModel>):RecyclerView.Adapter<NearDoctorsAdapter.Viewholder>() {
    private var context : Context? = null

    class Viewholder(val binding:ViewholderNearbyDoctorBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearDoctorsAdapter.Viewholder {
        context = parent.context
        return Viewholder(ViewholderNearbyDoctorBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: NearDoctorsAdapter.Viewholder, position: Int) {
        holder.binding.nameTxt.text = items[position].Name
        holder.binding.specialTxt.text = items[position].Special
        holder.binding.costTxt.text = items[position].Cost

        Glide.with(holder.itemView.context)
            .load(items[position].Picture)
            .apply { RequestOptions().transform(CenterCrop())}
            .into(holder.binding.img)

//        holder.binding.root.setOnClickListener{
//            val intent = Intent(context,DetailActivity::class.java)
//            intent.putExtra("object",items[position])
//            context?.startActivity(intent)
//        }

        holder.binding.root.setOnClickListener{
            val intent = Intent(context, DetailFragment::class.java)
            intent.putExtra("object",items[position])
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}