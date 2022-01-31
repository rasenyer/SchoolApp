package com.rasenyer.schoolapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Subject(

    @PrimaryKey(autoGenerate = true)
    val idSubject: Int,

    val nameSubject: String?

): Serializable
