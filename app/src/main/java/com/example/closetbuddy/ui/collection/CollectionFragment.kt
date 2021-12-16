package com.example.closetbuddy.ui.collection

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
import com.example.closetbuddy.databinding.FragmentCollectionBinding
import com.example.closetbuddy.ui.home.HomeFragment
import com.example.closetbuddy.utils.SharedPrefsHelpers
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CollectionFragment : Fragment() {

    private lateinit var adapter: ClothingAdapter
    private lateinit var collectionViewModel: CollectionViewModel
    private lateinit var safeContext: Context
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private var sort: String = "id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        collectionViewModel = ViewModelProvider(this,
                CollectionViewModel.provideFactory(MyApplication.clothesRepository))
                .get(CollectionViewModel::class.java)
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ClothingAdapter(safeContext,
            onClickListener = { _, item -> (activity as MainActivity).openDetails(item) }
        )

        binding.collectionList.adapter = adapter
        binding.collectionList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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


        collectionViewModel.setOrder(sort)

        collectionViewModel.clothes.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        safeContext = context
    }
}