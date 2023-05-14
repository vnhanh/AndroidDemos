package com.alanvo.androiddemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alanvo.androiddemo.databinding.RvItemFeatureBinding

class FeaturesAdapter: RecyclerView.Adapter<FeaturesAdapter.VH>() {
    private val diffCallback = object: DiffUtil.ItemCallback<FeatureModel>() {
        override fun areItemsTheSame(oldItem: FeatureModel, newItem: FeatureModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FeatureModel, newItem: FeatureModel): Boolean {
            return oldItem.name == newItem.name
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layout = RvItemFeatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(layout)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(newList: ArrayList<FeatureModel>) {
        differ.submitList(newList)
    }

    class VH(private val binding: RvItemFeatureBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FeatureModel) {
            binding.apply {
                tvActivityName.text = data.name
                tvActivityName.setOnClickListener { data.onClick() }
            }
        }
    }
}