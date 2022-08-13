package com.example.androiditunes.view

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiditunes.R
import com.example.androiditunes.databinding.ItunesListBinding
import com.example.androiditunes.model.ItunesItem
import com.example.androiditunes.view.adapter.ItunesAdapter
import com.example.androiditunes.viewmodel.ItunesViewModel


class ItunesDetailsFragment(private val position: Int): Fragment() {


    private lateinit var binding: ItunesListBinding

    private lateinit var adapter: ItunesAdapter


    private val itunesViewModel: ItunesViewModel by lazy {
        ViewModelProvider(this)[ItunesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ItunesListBinding.inflate(inflater, container, false)

        when(position) {
            0 ->  {
                itunesViewModel.getRockItunes()
                binding.itunesList.setBackgroundResource(R.color.red)}
            1 -> {
                itunesViewModel.getClassicItunes()
                binding.itunesList.setBackgroundResource(R.color.blue)
            }
            2 -> {
                itunesViewModel.getPopItunes()
                binding.itunesList.setBackgroundResource(R.color.Pink)
            }
        }

        initObservables()

        adapter = ItunesAdapter()
        binding.itunesList.adapter = adapter
        binding.itunesList.layoutManager = LinearLayoutManager(context)

        binding.swipeMe.setOnRefreshListener {
            binding.swipeMe.isRefreshing = false
        }

        return binding.root
    }


    private fun initObservables() {

        when(position) {
            0 -> itunesViewModel.itunesRockResult.observe(viewLifecycleOwner, Observer { updateAdapter(it)
                Toast.makeText(context, "Found ${itunesViewModel.resultCount} Result", Toast.LENGTH_SHORT).show()})
            1 -> itunesViewModel.itunesClassicResult.observe(viewLifecycleOwner, Observer { updateAdapter(it)
                Toast.makeText(context, "Found ${itunesViewModel.resultCount} Result", Toast.LENGTH_SHORT).show()})
            2 -> itunesViewModel.itunesPopResult.observe(viewLifecycleOwner, Observer { updateAdapter(it)
                Toast.makeText(context, "Found ${itunesViewModel.resultCount} Result", Toast.LENGTH_SHORT).show()})
        }
    }

    private fun updateAdapter(dataSet: List<ItunesItem>) {
        adapter.submitList(dataSet)
    }

}