package com.example.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.data.db.entities.ShoppingItem

/* It is used to access the table */
@Dao
interface ShoppingDao {
    /* A mix of update and insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    /* Delete item */
    @Delete
    suspend fun delete(item: ShoppingItem)

    /* Make a list of all items */
    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}