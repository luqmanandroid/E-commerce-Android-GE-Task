package com.app.ecom.data.appdb
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.app.ecom.data.model.CartItem
import com.app.ecom.data.model.CartItemDao

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ecommerce_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
