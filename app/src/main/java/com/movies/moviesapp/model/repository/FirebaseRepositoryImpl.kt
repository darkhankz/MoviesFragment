package com.movies.moviesapp.model.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.movies.moviesapp.data.User

class FirebaseRepositoryImpl: FirebaseRepository {
    private var database: DatabaseReference = Firebase.database.reference

    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser)
    }

}