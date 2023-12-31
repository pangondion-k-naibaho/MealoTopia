package com.mealotopia.client.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mealotopia.client.model.data_class.meal.DetailMealResponse

@Database(
    entities = [DetailMealResponse::class],
    version = 1
)

abstract class MealDatabase: RoomDatabase() {
    companion object{
        private var INSTANCE: MealDatabase?= null

        fun getDatabase(context: Context): MealDatabase?{
            if(INSTANCE == null){
                synchronized(MealDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MealDatabase::class.java,
                        "Meal Database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun bookmarkedMealDao(): BookmarkedMealDao
}