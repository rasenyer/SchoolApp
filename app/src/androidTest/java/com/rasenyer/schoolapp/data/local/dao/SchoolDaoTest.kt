package com.rasenyer.schoolapp.data.local.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.rasenyer.schoolapp.data.local.database.SchoolDatabase
import com.rasenyer.schoolapp.data.local.models.Student
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SchoolDaoTest {

    private lateinit var schoolDao: SchoolDao
    private lateinit var schoolDatabase: SchoolDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDatabase() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        schoolDatabase = Room.inMemoryDatabaseBuilder(context, SchoolDatabase::class.java).build()
        schoolDao = schoolDatabase.schoolDao

    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {

        schoolDatabase.close()

    }

    @Test
    @Throws(Exception::class)
    fun insertSubject() = runBlocking {

        val subject = Subject(idSubject = 1, nameSubject = "English")
        schoolDao.insertSubject(subject)

        val getAllSubjectWithStudent = schoolDao.getAllSubjectWithStudent().getOrAwaitValue()
        assertThat(getAllSubjectWithStudent).contains(subject)

    }

    @Test
    @Throws(Exception::class)
    fun updateSubject() = runBlocking {

        val subject = Subject(idSubject = 1, nameSubject = "English")
        schoolDao.insertSubject(subject)

        val subjectUpdated = Subject(idSubject = 1, nameSubject = "History")
        schoolDao.updateSubject(subjectUpdated)

        assertThat(subject).isNotEqualTo(subjectUpdated)

    }

    @Test
    @Throws(Exception::class)
    fun insertStudent() = runBlocking {

        val subject = Subject(idSubject = 1, nameSubject = "English")
        schoolDao.insertSubject(subject)

        val student = Student(idStudent = 1, nameStudent = "Jorge", idSubject = 1)
        schoolDao.insertStudent(student)

        val getStudentsByIdSubject = schoolDao.getStudentsByIdSubject(idSubject = 1).getOrAwaitValue()
        assertThat(getStudentsByIdSubject).contains(student)

    }

    @Test
    @Throws(Exception::class)
    fun updateStudent() = runBlocking {

        val subject = Subject(idSubject = 1, nameSubject = "English")
        schoolDao.insertSubject(subject)

        val student = Student(idStudent = 1, nameStudent = "Jorge", idSubject = 1)
        schoolDao.insertStudent(student)

        val studentUpdated = Student(idStudent = 1, nameStudent = "Rasenyer", idSubject = 1)
        schoolDao.updateStudent(studentUpdated)

        assertThat(student).isNotEqualTo(studentUpdated)

    }

    @Test
    @Throws(Exception::class)
    fun getSubjectsByNameSubject() = runBlocking {

        val subject1 = Subject(idSubject = 1, nameSubject = "English")
        schoolDao.insertSubject(subject1)

        val subject2 = Subject(idSubject = 2, nameSubject = "History")
        schoolDao.insertSubject(subject2)

        val subject3 = Subject(idSubject = 3, nameSubject = "Programming")
        schoolDao.insertSubject(subject3)

        val getSubjectsByNameSubject = schoolDao.getSubjectsByNameSubject(nameSubject = "English").getOrAwaitValue()
        assertThat(getSubjectsByNameSubject).isNotEmpty()

    }

    @Test
    @Throws(Exception::class)
    fun getStudentsByIdSubject() = runBlocking {

        val subject = Subject(idSubject = 1, nameSubject = "English")
        schoolDao.insertSubject(subject)

        val student1 = Student(idStudent = 1, nameStudent = "Darwin", idSubject = 1)
        schoolDao.insertStudent(student1)

        val student2 = Student(idStudent = 2, nameStudent = "Jorge", idSubject = 1)
        schoolDao.insertStudent(student2)

        val student3 = Student(idStudent = 3, nameStudent = "Fabian", idSubject = 1)
        schoolDao.insertStudent(student3)


        val getStudentsByIdSubject = schoolDao.getStudentsByIdSubject(idSubject = 1).getOrAwaitValue()
        assertThat(getStudentsByIdSubject).isNotEmpty()

    }

    @Test
    @Throws(Exception::class)
    fun getAllSubjectWithStudent() = runBlocking {

        val subject1 = Subject(idSubject = 1, nameSubject = "English")
        schoolDao.insertSubject(subject1)

        val subject2 = Subject(idSubject = 2, nameSubject = "History")
        schoolDao.insertSubject(subject2)

        val subject3 = Subject(idSubject = 3, nameSubject = "Programming")
        schoolDao.insertSubject(subject3)

        val student1 = Student(idStudent = 1, nameStudent = "Darwin", idSubject = 1)
        schoolDao.insertStudent(student1)

        val student2 = Student(idStudent = 2, nameStudent = "Jorge", idSubject = 2)
        schoolDao.insertStudent(student2)

        val student3 = Student(idStudent = 3, nameStudent = "Fabian", idSubject = 3)
        schoolDao.insertStudent(student3)

        val getAllSubjectWithStudent = schoolDao.getAllSubjectWithStudent().getOrAwaitValue()
        assertThat(getAllSubjectWithStudent).isNotEmpty()

    }

}