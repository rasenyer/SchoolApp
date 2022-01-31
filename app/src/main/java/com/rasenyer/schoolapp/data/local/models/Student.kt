package com.rasenyer.schoolapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Student(

    @PrimaryKey(autoGenerate = true)
    val idStudent: Int,

    val nameStudent: String,

    val idSubject: Int

): Serializable
