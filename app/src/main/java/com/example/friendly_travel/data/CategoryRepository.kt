package com.example.friendly_travel.data

import com.example.friendly_travel.model.Wisata
import com.example.friendly_travel.model.WisataData
import com.example.friendly_travel.screen.category.CategoryData
import com.example.friendly_travel.screen.category.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class CategoryRepository {
    private val category= mutableListOf<Category>()

    init {
        if (category.isEmpty()){
            CategoryData.category.forEach{
                category.add(it)
            }
        }
    }

    fun getCategoryById(categoryId: Int): Category {
        return category.first{
            it.id == categoryId
        }
    }


    fun updateCategory(categoryId: Int, newState: Boolean): Flow<Boolean> {
        val index = category.indexOfFirst { it.id == categoryId }
        val result = if (index >= 0) {
            val categorywisata = category[index]
            category[index] = categorywisata.copy(isFavorite = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: CategoryRepository? = null

        fun getInstance():CategoryRepository =
            instance ?: synchronized(this){
                CategoryRepository().apply {
                    instance = this
                }
            }
    }
}
