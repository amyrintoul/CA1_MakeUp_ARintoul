package com.example.ca1_makeup_arintoul

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ca1_makeup_arintoul.databinding.MainFragmentBinding
import kotlin.math.log

class MainFragment : Fragment(),
    ProductsListAdapter.ListItemListener{


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: ProductsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //makes the back button disappear when back on the main fragment
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true) //if you want to use the options menu you need to call it
        //and pass in a value of true.

        (activity as AppCompatActivity).supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_home)
            //displays home icon


        }


        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView){
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
            //Creating a visual divider in between each of the rows.
        }
        viewModel.productsList?.observe(viewLifecycleOwner, Observer {
            Log.i("productLogging", it.toString())
            adapter = ProductsListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuId =
        if (this::adapter.isInitialized &&
                adapter.selectedProducts.isNotEmpty()
        ) {
            R.menu.menu_main_selected_items
        } else {
            R.menu.menu_main
        }
        inflater.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_sample_data -> sampleData()
            R.id.action_delete -> deleteSelectedProducts()
            R.id.action_delete_all -> deleteAllProducts()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllProducts(): Boolean {
        viewModel.deleteAllProducts()
        return true

    }

    private fun deleteSelectedProducts(): Boolean {
        viewModel.deleteProducts(adapter.selectedProducts)
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.selectedProducts.clear()
            requireActivity().invalidateOptionsMenu()
        }, 100)
        //after its done the work on the database wait a tenth of a second and then clear the state
        return true
    }

    private fun sampleData(): Boolean {
        viewModel.sampleData()
        return true

    }

    override fun onItemClick(productId: Int) {
        Log.i(TAG, "onItemClick: recieved product on id $productId")
        val action = MainFragmentDirections.actionEditProduct(productId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChanged() {
        //reset the options menu
        requireActivity().invalidateOptionsMenu()
    }

}