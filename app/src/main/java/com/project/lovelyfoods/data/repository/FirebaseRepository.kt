package com.project.lovelyfoods.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.project.lovelyfoods.model.Food
import java.util.UUID

class FirebaseRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Foods")

    fun addNewFood(name: String, url: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val uniqueId = UUID.randomUUID().toString()
        val newFood = hashMapOf(
            "name" to name,
            "url" to url
        )

        collection.document(uniqueId).set(newFood)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {exception ->
                onFailure.invoke(exception)
            }
    }

    fun getAllFoods(onSuccess: (List<Food>) -> Unit, onFailure: (Exception) -> Unit) {
        collection.get()
            .addOnSuccessListener { documents ->
                val foodsList = mutableListOf<Food>()

                for (document in documents) {
                    val food = document.toObject(Food::class.java)
                    foodsList.add(food)
                }

                onSuccess.invoke(foodsList)
            }
            .addOnFailureListener {exception ->
                onFailure.invoke(exception)
            }
    }

    fun getRandomFood(onSuccess: (Food) -> Unit, onFailure: (Exception) -> Unit) {
        collection.get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    onFailure.invoke(Exception("You don't have foods saved yet!"))
                } else {
                    val randomIndex = (0 until documents.size()).random()
                    val randomDocument = documents.documents[randomIndex]
                    val food = randomDocument.toObject(Food::class.java)

                    if (food == null) {
                        onFailure.invoke(Exception("Error to find a random food!"))
                    } else {
                        onSuccess.invoke(food)
                    }
                }
            }
            .addOnFailureListener(onFailure)
    }

    fun getFood(id: String, onSuccess: (Food) -> Unit, onFailure: (Exception) -> Unit) {
        collection.document(id).get()
            .addOnSuccessListener { document ->
                if (document == null) {
                    onFailure.invoke(Exception("Document don't exist!"))
                } else {
                    val food = document.toObject(Food::class.java)
                    food?.let(onSuccess)
                }
            }
            .addOnFailureListener(onFailure)
    }

    fun deleteFood(id: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        collection.document(id)
            .delete()
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener(onFailure)
    }
}