package com.movies.moviesapp.model.repository

import com.movies.moviesapp.data.User


interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}