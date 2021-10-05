package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Defines methods for using the TableClass class with Room.
 */

@Dao
interface DatabaseDoa  {

    /**
     *In order to run these functions in background, we make use of the suspend function.Since these functions involve I/O operations which can lag the app.
     */


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(TableClassObject: TableClass)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param TableClassObject new value to write
     */

    @Update
    suspend  fun update(TableClassObject : TableClass)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key id to match
     */
    @Query("SELECT * from notes_table WHERE id = :key")
    suspend fun get(key: Int): TableClass?

    /**
     *Selects and returns all rows in the table, sorted by id in ascending order.
     */
    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<TableClass>>

    /**
     * Deletes all values from the table. This does not delete the table, only its contents.
     */
    @Delete
    suspend fun deleteAll(TableClassObject: TableClass)
}