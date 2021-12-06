package com.example.ca1_makeup_arintoul

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ca1_makeup_arintoul.databinding.EditorFragmentBinding

class EditorFragment : Fragment() {

    private lateinit var viewModel: EditorViewModel
    private val args: EditorFragmentArgs by navArgs()
    private lateinit var binding: EditorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true) //enable the home button, pass through for it
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_backbutton)
            //Icon will display the button as an up button

        }
        //make the button do something
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
        binding = EditorFragmentBinding.inflate(inflater, container, false)
        binding.editor.setText("")

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    saveAndReturn()
                    //if the user enters a back gesture either through a button or an actual gesture
                    //ill handle that by calling my function saveAndReturn & this results in navigating up

                }


            }
        )

        viewModel.currentProduct.observe(viewLifecycleOwner, Observer {
            binding.editor.setText(it.name)
        })
        viewModel.getProductById(args.productId)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            //handle the home button
                android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun saveAndReturn(): Boolean {

        findNavController().navigateUp() //go back to the main fragment
        return true
    }



}