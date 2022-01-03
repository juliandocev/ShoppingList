package com.example.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities =[
        ShoppingItem::class
    ],
    version =1
)
/* It contains all the database */
abstract class ShoppingDatabase: RoomDatabase() {

    /* Let s access our Dao operations in our Database class */
    abstract fun getShoppingDao(): ShoppingDao

    // It is like a static object in c# and java
    companion object {
        /*
        * This allow just to one threat at a time is writing to that instance */
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val Lock = Any()

        /** it is called every time when we create  an instance of that database.
         * The synchronized block means that it will write just in one instance at a time.
         * But if the instance is null we  create a new one with createDatabase if not we set it to what ever the result is */
        operator  fun invoke(context: Context) = instance ?: synchronized(Lock){
            // If it is null...
            instance ?: createDatabase(context).also { instance = it}
        }

        /* Instantiates the database */
        private fun  createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java, "ShoppingDb.db").build()

    }

}