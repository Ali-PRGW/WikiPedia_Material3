package ir.dunijet.wikipedia_m3.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.dunijet.wikipedia_m3.data.ItemPost
import ir.dunijet.wikipedia_m3.databinding.ItemExploreBinding

class ExploreAdapter(val data: List<ItemPost> , val itemEvents: ItemEvents) :
    RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {
    lateinit var binding: ItemExploreBinding

    inner class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(itemPost: ItemPost) {

            Glide
                .with(itemView.context)
                .load(itemPost.imgUrl)
                .into(binding.imgExploreMain)

            binding.txtExploreTitle.text = itemPost.txtTitle

            binding.txtExploreSubtitle.text = itemPost.txtSubtitle

            binding.txtExploreDetail.text = itemPost.txtDetail


            itemView.setOnClickListener {

                itemEvents.onItemClicked( itemPost )

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        binding = ItemExploreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ExploreViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {

        holder.bindViews(data[position])

        Log.v("testApp", position.toString())

    }

    override fun getItemCount(): Int {
        return data.size
    }


}