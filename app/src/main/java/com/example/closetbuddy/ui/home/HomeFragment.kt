package com.example.closetbuddy.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.closetbuddy.MainActivity
import com.example.closetbuddy.MyApplication
import com.example.closetbuddy.R
import com.example.closetbuddy.databinding.FragmentHomeBinding
import com.example.closetbuddy.models.ClothingViewModel
import com.example.closetbuddy.models.Season
import com.example.closetbuddy.ui.collection.CollectionFragment
import com.example.closetbuddy.ui.today.TodayFragment
import com.example.closetbuddy.utils.CalendarHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialFadeThrough

class HomeFragment : Fragment() {

    private lateinit var safeContext: Context
    private val season = CalendarHelper.getSeason()
    private val clothingViewModel: ClothingViewModel by activityViewModels {
        ClothingViewModel.provideFactory(MyApplication.clothesRepository)
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialFadeThrough()
        //reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeViewPager.adapter = TabViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager) { tab, position ->
            when (position) {
                0 -> {
                    //tab.text = getString(CalendarHelper.getSeasonName(season))
                    tab.text = getString(R.string.title_outfits)
                    tab.icon = ContextCompat.getDrawable(safeContext, R.drawable.ic_outfits_icon)
                }
                1 -> {
                    tab.text = getString(R.string.title_collection)
                    tab.icon = ContextCompat.getDrawable(safeContext, R.drawable.ic_closet_icon)
                }
            }
        }.attach()


        binding.homeFab.setOnClickListener {
            (activity as MainActivity).openDetails(null)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        safeContext = context
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun hideFab() {
        binding.homeFab.hide()
    }

    fun showFab() {
        binding.homeFab.show()
    }

    class TabViewPagerAdapter(fm: FragmentManager, lf: Lifecycle) : FragmentStateAdapter(fm, lf) {
        override fun getItemCount(): Int = PAGE_COUNT

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TodayFragment()
                1 -> CollectionFragment()
                else -> TodayFragment()
            }
        }
    }

    companion object {
        const val PAGE_COUNT = 2
    }
}