package com.example.jopa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jopa.CustomRecyclerAdapter
import com.example.jopa.R
import com.example.jopa.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var recyclerview: RecyclerView = binding.recyclerviewHome
        recyclerview.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerview.adapter = CustomRecyclerAdapter(getTitlesList(),getAboutsList())

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun getTitlesList():List<String> {
        return this.resources.getStringArray(R.array.titles).toList()
    }
    private fun getAboutsList():List<String> {
        return this.resources.getStringArray(R.array.abouts).toList()
    }
}