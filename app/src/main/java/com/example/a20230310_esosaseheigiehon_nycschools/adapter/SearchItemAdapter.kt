package com.example.a20230310_esosaseheigiehon_nycschools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230310_esosaseheigiehon_nycschools.ScoreFragment
import com.example.a20230310_esosaseheigiehon_nycschools.databinding.LayoutSearchItemBinding
import com.example.a20230310_esosaseheigiehon_nycschools.model.SchoolModel
import com.example.a20230310_esosaseheigiehon_nycschools.viewmodel.SchoolViewModel

class SearchItemAdapter(val activity: AppCompatActivity, val viewModel: SchoolViewModel,  var schools : List<SchoolModel>) : RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val binding = LayoutSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return schools.size
    }

    fun changeData(newData : List<SchoolModel>){
        schools = newData
        notifyDataSetChanged()
    }

    inner class SearchItemViewHolder(val binding: LayoutSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvSchoolName.text = schools[position].schoolName
            binding.tvSchoolAddress.text = schools[position].addressString
            binding.clTop.setOnClickListener{
                //will launch a dialog fragment by our context
                viewModel.select(schools[position])
                ScoreFragment.newInstance().show(activity.supportFragmentManager, "SchoolDetailDialogFragment")


            }
        }
    }
}