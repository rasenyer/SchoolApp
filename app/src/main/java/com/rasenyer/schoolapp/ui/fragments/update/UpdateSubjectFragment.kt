package com.rasenyer.schoolapp.ui.fragments.update

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
import androidx.navigation.fragment.navArgs
import com.rasenyer.schoolapp.R
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.databinding.FragmentUpdateSubjectBinding
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModel
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModelFactory
import kotlinx.coroutines.launch

class UpdateSubjectFragment : Fragment() {

    private lateinit var schoolViewModel: SchoolViewModel
    private val updateSubjectFragmentArgs: UpdateSubjectFragmentArgs by navArgs()
    private lateinit var currentSubject: Subject

    private var _binding: FragmentUpdateSubjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentSubject = updateSubjectFragmentArgs.subject!!

        val universityViewModelFactory = SchoolViewModelFactory(requireActivity().application)
        schoolViewModel = ViewModelProvider(this, universityViewModelFactory)[SchoolViewModel::class.java]

        binding.mButtonUpdate.setOnClickListener { updateSubject() }

        setViews()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateSubject() {

        when {

            TextUtils.isEmpty(binding.mEditTextNameCareer.text.toString()) -> {
                binding.mEditTextNameCareer.error = resources.getString(R.string.enter_the_subject_name)
            }

            else -> {

                lifecycleScope.launch {

                    val subject = Subject(idSubject = currentSubject.idSubject, nameSubject = binding.mEditTextNameCareer.text.toString())
                    schoolViewModel.updateSubject(subject)

                }

                Toast.makeText(context, R.string.updated_successfully, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_UpdateCareerFragment_to_HomeFragment)

            }

        }

    }

    private fun setViews() {

        binding.mEditTextNameCareer.setText(currentSubject.nameSubject)

    }

}