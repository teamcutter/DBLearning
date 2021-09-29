package com.example.dblearning.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dblearning.model.User

// contains the database holder and servers as the main access point for the underlying
// connection to your app's persisted, relational data
// entities - все таблицы, которые будут в базе

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    // means our database will have only one instance
    companion object {
        // @Volatile - значит, что поле видимо для других потоков

        @Volatile
        private var INSTANCE: UserDatabase? = null

        // Context – это объект, который предоставляет доступ к базовым функциям приложения:
        // доступ к ресурсам, к файловой системе, вызов активности и т.д.
        fun getDatabase(context: Context) : UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            // synchronized - защищает от одновоременного исполнения в разных потоках
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}