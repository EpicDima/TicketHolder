package dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dima.ticketholder.model.Ticket

@Dao
interface TicketDao {
    @Query("SELECT * FROM tickets ORDER BY number ASC")
    fun selectLiveData(): LiveData<List<Ticket>>

    @Query("SELECT * FROM tickets ORDER BY number ASC")
    suspend fun select(): List<Ticket>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg tickets: Ticket)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(vararg tickets: Ticket)

    @Delete
    suspend fun delete(vararg tickets: Ticket)

//    @Query("SELECT * FROM tickets ORDER BY number ASC")
//    fun selectByNumber(): LiveData<List<Ticket>>
//
//    @Query("SELECT * FROM tickets ORDER BY number DESC")
//    fun selectByNumberDescending(): LiveData<List<Ticket>>
//
//    @Query("SELECT * FROM tickets ORDER BY usages ASC")
//    fun selectByUsages(): LiveData<List<Ticket>>
//
//    @Query("SELECT * FROM tickets ORDER BY usages DESC")
//    fun selectByUsagesDescending(): LiveData<List<Ticket>>
}