package com.developer27.xumorous.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.developer27.xumorous.R
import com.developer27.xumorous.viewModels.MemeViewModel
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    interface OnFragmentInteractionListener{
        fun onFragmentInteraction(uri: Uri);
    }

    companion object {
        fun newInstance() = MemeFragment()
    }

    private lateinit var viewModel: MemeViewModel
    //private lateinit var memeImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        //memeImageView = rootView.findViewById(R.id.memeImageView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}