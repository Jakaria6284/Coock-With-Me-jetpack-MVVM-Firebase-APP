package com.kamaljakaria.cookwithme.Data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class repository {

    private val db=FirebaseFirestore.getInstance();
    suspend fun fetchAllchefData():List<cheifdata>{
       return try {
           val result=db.collection("chef").get().await()
           result.documents.map { documentSnapshot ->
           cheifdata(
             image= documentSnapshot.getString("img")?:"",
             name = documentSnapshot.getString("name")?:"",
             id=documentSnapshot.getString("id")?:""
        )
           }

       }catch (e:Exception)
       {
           emptyList()
       }

    }


    suspend fun fetchRecipesForChef(chefId: String): List<Recipe> {
        return try {
            val querySnapshot = db.collection("chef").document(chefId)
                .collection("recepie").get().await()
            querySnapshot.documents.mapNotNull { document ->
                val recipeId = document.id
                val name = document.getString("name") ?: ""
                val image= document.getString("img")?:""
                Recipe(id = recipeId, name = name, image = image)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }



}