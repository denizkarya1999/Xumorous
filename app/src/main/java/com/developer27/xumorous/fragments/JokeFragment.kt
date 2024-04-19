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
import com.developer27.xumorous.databinding.FragmentInsultBinding
import com.developer27.xumorous.databinding.FragmentJokeBinding
import com.developer27.xumorous.viewModels.InsultViewModel
import com.developer27.xumorous.viewModels.JokeViewModel
import com.google.android.material.snackbar.Snackbar

class JokeFragment : Fragment() {
    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: JokeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(JokeViewModel::class.java)

        // Observe insult LiveData for both setup and punchline
        viewModel.setup.observe(viewLifecycleOwner, Observer { setup ->
            binding.setupText.text = setup
        })
        viewModel.punchline.observe(viewLifecycleOwner, Observer { punchline ->
            binding.punchlineText.text = punchline
        })

        binding.generateJokebutton.setOnClickListener{
            viewModel.getJokeDataAPI(requireContext())
            viewModel.clearCache(requireContext())
        }
    }

}