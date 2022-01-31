package com.rasenyer.schoolapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rasenyer.schoolapp.data.local.models.Student
import com.rasenyer.schoolapp.databinding.ItemStudentBinding
import com.rasenyer.schoolapp.ui.fragments.add.AddStudentFragmentDirections

class StudentAdapter(private val studentList: List<Student>) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {

        val student = studentList[position]

        myViewHolder.binding.mTextViewIdStudent.text = student.idStudent.toString()
        myViewHolder.binding.mTextViewNameStudent.text = student.nameStudent

        myViewHolder.binding.mImageViewEdit.setOnClickListener {
            val direction = AddStudentFragmentDirections.actionAddStudentFragmentToUpdateStudentFragment(student)
            it.findNavController().navigate(direction)
        }

    }

    override fun getItemCount(): Int { return studentList.size }

    class MyViewHolder(val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root)

}