package com.rasenyer.schoolapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rasenyer.schoolapp.data.local.dao.SchoolDao
import com.rasenyer.schoolapp.data.local.models.Subject
import com.rasenyer.schoolapp.data.local.models.Student

@Database(entities = [Subject::class, Student::class], version = 1)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {

        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context?): SchoolDatabase {

            synchronized(this) {

                return INSTANCE ?: Room.databaseBuilder(context!!.applicationContext, SchoolDatabase::class.java, "SchoolDatabase").build().also {
                    INSTANCE = it
                }

            }

        }

    }

}