package com.developer27.xumorous.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developer27.xumorous.databinding.FragmentMemeBinding
import com.developer27.xumorous.viewModels.MemeViewModel

class MemeFragment : Fragment() {
    private var _binding: FragmentMemeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MemeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemeViewModel::class.java)

        binding.memeGeneratorButton.setOnClickListener {
            val memeImageView = binding.generatedMemeImage
            viewModel.getMemeData(requireContext(), memeImageView)
            viewModel.clearCache(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}