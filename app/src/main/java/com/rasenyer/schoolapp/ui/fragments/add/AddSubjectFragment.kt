package com.rasenyer.schoolapp.ui.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rasenyer.schoolapp.R
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.databinding.FragmentAddSubjectBinding
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModel
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModelFactory
import kotlinx.coroutines.launch

class AddSubjectFragment : Fragment() {

    private lateinit var schoolViewModel: SchoolViewModel

    private var _binding: FragmentAddSubjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val universityViewModelFactory = SchoolViewModelFactory(requireActivity().application)
        schoolViewModel = ViewModelProvider(this, universityViewModelFactory)[SchoolViewModel::class.java]

        binding.mButtonSave.setOnClickListener {
            insertSubject()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun insertSubject() {

        when {

            TextUtils.isEmpty(binding.mEditTextNameCareer.text.toString()) -> {
                binding.mEditTextNameCareer.error = resources.getString(R.string.enter_the_subject_name)
            }

            else -> {

                lifecycleScope.launch {

                    val subject = Subject(idSubject = 0, nameSubject = binding.mEditTextNameCareer.text.toString())
                    schoolViewModel.insertSubject(subject)

                }

                Toast.makeText(context, R.string.added_successfully, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_AddSubjectFragment_to_HomeFragment)

            }

        }

    }

}