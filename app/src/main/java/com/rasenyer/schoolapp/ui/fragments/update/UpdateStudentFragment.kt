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
import androidx.navigation.fragment.navArgs
import com.rasenyer.schoolapp.R
import com.rasenyer.schoolapp.data.local.models.Student
import com.rasenyer.schoolapp.databinding.FragmentUpdateStudentBinding
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModel
import com.rasenyer.schoolapp.ui.viewmodel.SchoolViewModelFactory
import kotlinx.coroutines.launch

class UpdateStudentFragment : Fragment() {

    private lateinit var schoolViewModel: SchoolViewModel
    private val updateStudentFragmentArgs: UpdateStudentFragmentArgs by navArgs()
    private lateinit var currentStudent: Student

    private var _binding: FragmentUpdateStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentStudent = updateStudentFragmentArgs.student!!

        val universityViewModelFactory = SchoolViewModelFactory(requireActivity().application)
        schoolViewModel = ViewModelProvider(this, universityViewModelFactory)[SchoolViewModel::class.java]

        binding.mButtonUpdate.setOnClickListener { updateStudent() }

        setViews()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateStudent() {

        when {

            TextUtils.isEmpty(binding.mEditTextNameStudent.text.toString()) -> {
                binding.mEditTextNameStudent.error = resources.getString(R.string.enter_the_student_name)
            }

            else -> {

                lifecycleScope.launch {

                    val student = Student(
                        idStudent = currentStudent.idStudent,
                        nameStudent = binding.mEditTextNameStudent.text.toString(),
                        idSubject = currentStudent.idSubject
                    )
                    schoolViewModel.updateStudent(student)

                }

                Toast.makeText(context, R.string.updated_successfully, Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun setViews() {

        binding.mEditTextNameStudent.setText(currentStudent.nameStudent)

    }

}