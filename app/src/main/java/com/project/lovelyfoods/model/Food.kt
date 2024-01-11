package com.project.lovelyfoods.model

import com.google.firebase.firestore.DocumentId

data class Food(
    @DocumentId val id: String = "",
    val name: String = "",
    val url: String = "",
)
