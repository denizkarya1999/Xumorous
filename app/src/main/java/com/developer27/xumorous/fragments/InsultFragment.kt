package com.developer27.xumorous.fragments

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.developer27.xumorous.R
import com.developer27.xumorous.viewModels.InsultViewModel
import com.developer27.xumorous.databinding.FragmentInsultBinding
import com.developer27.xumorous.databinding.FragmentMemeBinding
import com.developer27.xumorous.viewModels.MemeViewModel
import com.google.android.material.snackbar.Snackbar

class InsultFragment : Fragment() {
    private var _binding: FragmentInsultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InsultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsultViewModel::class.java)

        // Observe insult LiveData
        viewModel.insult.observe(viewLifecycleOwner, Observer { insult ->
            binding.generatedInsult.text = insult
        })

        binding.generateInsultButton.setOnClickListener{
            viewModel.getInsultDataAPI(requireContext())
            viewModel.clearCache(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}