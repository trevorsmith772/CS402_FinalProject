package com.example.closetbuddy.ui.today

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.closetbuddy.MainActivity
import com.example.closetbuddy.MyApplication
import com.example.closetbuddy.R
import com.example.closetbuddy.adapters.ClothingAdapter
import com.example.closetbuddy.databinding.FragmentTodayBinding
import com.example.closetbuddy.ui.home.HomeFragment
import com.example.closetbuddy.utils.SharedPrefsHelpers
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TodayFragment : Fragment() {

    private lateinit var adapter: ClothingAdapter
    private lateinit var todayViewModel: TodayViewModel
    private lateinit var safeContext: Context
    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!
    private var sort: String = "id"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        todayViewModel = ViewModelProvider(this,
            TodayViewModel.provideFactory(MyApplication.clothesRepository))
            .get(TodayViewModel::class.java)
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ClothingAdapter(safeContext,
            onClickListener = { _, item -> (activity as MainActivity).openDetails(item) }
        )
        binding.todayList.adapter = adapter
        binding.todayList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    //scroll down
                    (parentFragment as HomeFragment).hideFab()
                } else if (dy < 0) {
                    //scroll up
                    (parentFragment as HomeFragment).showFab()
                }
            }
        })

       // updateSort(sortId)
        todayViewModel.setOrder(sort)

        todayViewModel.clothes.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        safeContext = context
    }
}