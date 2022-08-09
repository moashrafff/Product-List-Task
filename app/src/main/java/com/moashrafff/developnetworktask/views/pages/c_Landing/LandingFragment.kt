package com.moashrafff.developnetworktask.views.pages.c_Landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moashrafff.developnetworktask.R
import com.moashrafff.developnetworktask.data.model.Product
import com.moashrafff.developnetworktask.databinding.FragmentLandingBinding
import com.moashrafff.developnetworktask.views.adapters.ItemsAdapter
import com.moashrafff.developnetworktask.views.dialogs.ItemDetailsDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LandingFragment : Fragment() {


    private val itemsViewModel: ItemViewModel by  viewModels()
    private lateinit var _binding: FragmentLandingBinding
    private val binding get() = _binding


    @Inject
    lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLandingBinding.inflate(layoutInflater)
        itemsViewModel.getProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView(binding.itemsRecyclerView)
        itemsAdapter.onItemClick={
            showProductDetails(it)
        }
        itemsViewModel.products.observe(requireActivity(), Observer {
            itemsAdapter.setList(it)
        })



    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = itemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showProductDetails(product: Product){
        val dialog = ItemDetailsDialog(requireContext())
        dialog.show(product)
    }

}