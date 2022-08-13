package com.example.androiditunes.view.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androiditunes.R
import com.example.androiditunes.databinding.ItunesEachItemBinding
import com.example.androiditunes.model.ItunesItem
import com.squareup.picasso.Picasso

class ItunesAdapter():
    ListAdapter<ItunesItem, ItunesAdapter.ViewHolder>(ItunesItemDiffUtil) {

    class ViewHolder(private val binding: ItunesEachItemBinding):
            RecyclerView.ViewHolder(binding.root){
                fun onBind(dataItem: ItunesItem){


                    binding.itunesCollectionNameTitle.text = dataItem.collectionName

                    binding.itunesArtistNameTitle.text = dataItem.artistName

                    binding.itunesMusicPrice.text = dataItem.trackPrice.toString()

                    Picasso.get().load(dataItem.artworkUrl60).placeholder(R.drawable.ic_loading_error)
                        .error(R.drawable.ic_error).into(binding.itunesImageView)

                    binding.root.setOnClickListener {
                        val intent: Intent = Intent()
                        intent.action = android.content.Intent.ACTION_VIEW
                        intent.setDataAndType(Uri.parse(dataItem.previewUrl), "audio/*");
                        val bundle = Bundle()
                        startActivity(binding.root.context,intent, bundle);

                        Toast.makeText(itemView.context, "Play Music", Toast.LENGTH_SHORT).show()
                    }


                }


    }

    object ItunesItemDiffUtil: DiffUtil.ItemCallback<ItunesItem>(){
        override fun areItemsTheSame(oldItem: ItunesItem, newItem: ItunesItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItunesItem, newItem: ItunesItem): Boolean {
            return oldItem.collectionName == newItem.collectionName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder (
          ItunesEachItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

}