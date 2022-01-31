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
import androidx.navigation.fragment.navArgs
import com.rasenyer.schoolapp.R
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.data.local.models.Student
import com.rasenyer.schoolapp.databinding.FragmentAddStudentBinding
import com.rasenyer.schoolapp.ui.adapters.StudentAdapter
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModel
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModelFactory
import kotlinx.coroutines.launch

class AddStudentFragment : Fragment() {

    private lateinit var schoolViewModel: SchoolViewModel
    private val detailFragmentArgs: AddStudentFragmentArgs by navArgs()
    private lateinit var currentSubject: Subject

    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentSubject = detailFragmentArgs.subject!!

        val universityViewModelFactory = SchoolViewModelFactory(requireActivity().application)
        schoolViewModel = ViewModelProvider(this, universityViewModelFactory)[SchoolViewModel::class.java]

        schoolViewModel.getStudentsByIdSubject(currentSubject.idSubject).observe(viewLifecycleOwner) {
            binding.mRecyclerViewStudents.adapter = StudentAdapter(it)
        }

        binding.mImageViewSend.setOnClickListener { insertStudent() }

        setViews()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun insertStudent() {

        when {

            TextUtils.isEmpty(binding.mEditTextNameStudent.text.toString()) -> {
                binding.mEditTextNameStudent.error = resources.getString(R.string.enter_the_student_name)
            }

            else -> {

                lifecycleScope.launch {

                    val student = Student(
                        idStudent = 0,
                        nameStudent = binding.mEditTextNameStudent.text.toString(),
                        idSubject = currentSubject.idSubject
                    )

                    schoolViewModel.insertStudent(student)

                }

                Toast.makeText(context, R.string.added_successfully, Toast.LENGTH_SHORT).show()
                binding.mEditTextNameStudent.setText("")

            }

        }

    }

    private fun setViews() {

        binding.mTextViewIdCareer.text = currentSubject.idSubject.toString()
        binding.mTextViewNameCareer.text = currentSubject.nameSubject

    }

}