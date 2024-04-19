package com.developer27.xumorous
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.developer27.xumorous.databinding.ActivityMainBinding
import com.developer27.xumorous.fragments.InsultFragment
import com.developer27.xumorous.fragments.JokeFragment
import com.developer27.xumorous.fragments.MainFragment
import com.developer27.xumorous.fragments.MemeFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(){

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(), "Home")
        adapter.addFragment(JokeFragment(), "Jokes")
        adapter.addFragment(MemeFragment(), "Memes")
        adapter.addFragment(InsultFragment(), "Insults")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        // Set the initial page to MainFragment
        viewPager.setCurrentItem(0, true)
    }

    inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTitleList = ArrayList<String>()

        override fun getCount(): Int = fragmentList.size

        override fun getItem(position: Int): Fragment = fragmentList[position]

        override fun getPageTitle(position: Int): CharSequence? = fragmentTitleList[position]

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }
}