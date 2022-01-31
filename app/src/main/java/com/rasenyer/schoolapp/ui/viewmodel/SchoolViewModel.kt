package com.rasenyer.schoolapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rasenyer.schoolapp.data.local.database.SchoolDatabase
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.data.local.models.Student

class SchoolViewModel(application: Application) : ViewModel() {

    private val schoolDatabase: SchoolDatabase = SchoolDatabase.getInstance(application)

    suspend fun insertSubject(subject: Subject) {
        schoolDatabase.schoolDao.insertSubject(subject)
    }

    suspend fun updateSubject(subject: Subject) {
        schoolDatabase.schoolDao.updateSubject(subject)
    }

    suspend fun insertStudent(student: Student) {
        schoolDatabase.schoolDao.insertStudent(student)
    }

    suspend fun updateStudent(student: Student) {
        schoolDatabase.schoolDao.updateStudent(student)
    }

    fun getSubjectsByNameSubject(nameSubject: String?): LiveData<List<Subject>> {
        return schoolDatabase.schoolDao.getSubjectsByNameSubject(nameSubject)
    }

    fun getStudentsByIdSubject(idSubject: Int?): LiveData<List<Student>> {
        return schoolDatabase.schoolDao.getStudentsByIdSubject(idSubject)
    }

    internal val getAllSubjectWithStudent: LiveData<List<Subject>> = schoolDatabase.schoolDao.getAllSubjectWithStudent()

}