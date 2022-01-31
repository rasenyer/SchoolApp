package com.rasenyer.schoolapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.databinding.ItemCareerBinding
import com.rasenyer.schoolapp.ui.fragments.home.HomeFragmentDirections

class SubjectAdapter(private val subjectList: List<Subject>): RecyclerView.Adapter<SubjectAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCareerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {

        val career = subjectList[position]

        myViewHolder.binding.mTextViewIdCareer.text = career.idSubject.toString()
        myViewHolder.binding.mTextViewNameCareer.text = career.nameSubject

        myViewHolder.binding.mImageViewEdit.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateSubjectFragment(career)
            it.findNavController().navigate(direction)
        }

        myViewHolder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToAddStudentFragment(career)
            it.findNavController().navigate(direction)
        }

    }

    override fun getItemCount(): Int { return subjectList.size }

    class MyViewHolder(val binding: ItemCareerBinding): RecyclerView.ViewHolder(binding.root)

}