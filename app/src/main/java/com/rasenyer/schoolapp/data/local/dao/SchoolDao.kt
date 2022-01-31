package com.rasenyer.schoolapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.data.local.models.Student

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Update
    suspend fun updateSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM subject WHERE nameSubject LIKE :nameSubject")
    fun getSubjectsByNameSubject(nameSubject: String?): LiveData<List<Subject>>

    @Transaction
    @Query("SELECT * FROM student WHERE idSubject = :idSubject")
    fun getStudentsByIdSubject(idSubject: Int?): LiveData<List<Student>>

    @Transaction
    @Query("SELECT * FROM subject ORDER BY idSubject ASC")
    fun getAllSubjectWithStudent(): LiveData<List<Subject>>

}