package com.rasenyer.schoolapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rasenyer.schoolapp.R
import com.rasenyer.schoolapp.databinding.FragmentHomeBinding
import com.rasenyer.schoolapp.ui.adapters.SubjectAdapter
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModel
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var schoolViewModel: SchoolViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val universityViewModelFactory = SchoolViewModelFactory(requireActivity().application)
        schoolViewModel = ViewModelProvider(this, universityViewModelFactory)[SchoolViewModel::class.java]

        binding.mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(nameSubject: String?): Boolean {

                if (nameSubject != null) { getSubjectsByNameSubject(nameSubject) }
                return true

            }

            override fun onQueryTextChange(newNameSubject: String?): Boolean { return true }

        })

        binding.mSwipeRefreshLayout.setOnRefreshListener {
            getAllSubjectWithStudent()
            binding.mSwipeRefreshLayout.isRefreshing = false
        }

        binding.mFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_AddSubjectFragment)
        }

        getAllSubjectWithStudent()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAllSubjectWithStudent() {

        schoolViewModel.getAllSubjectWithStudent.observe(viewLifecycleOwner) {
            binding.mRecyclerView.adapter = SubjectAdapter(it)
        }

    }

    private fun getSubjectsByNameSubject(nameSubject: String) {

        schoolViewModel.getSubjectsByNameSubject(nameSubject).observe(viewLifecycleOwner) {
            binding.mRecyclerView.adapter = SubjectAdapter(it)
        }

    }

}