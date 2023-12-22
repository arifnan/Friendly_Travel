package com.example.friendly_travel.data

import com.example.friendly_travel.model.Wisata
import com.example.friendly_travel.model.WisataData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class WisataRepository {
    private val wisata = mutableListOf<Wisata>()

    init {
        if (wisata.isEmpty()){
            WisataData.wisata.forEach{
                wisata.add(it)
            }
        }
    }

    fun getWisataById(wisataId: Int): Wisata{
        return wisata.first{
            it.id == wisataId
        }
    }

    fun getFavoriteWisata(): Flow<List<Wisata>> {
        return flowOf(wisata.filter { it.isFavorite })
    }

    fun searchWisata(query: String) = flow {
        val data = wisata.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun updateWisata(wisataId: Int, newState: Boolean): Flow<Boolean> {
        val index = wisata.indexOfFirst { it.id == wisataId }
        val result = if (index >= 0) {
            val movie = wisata[index]
            wisata[index] = movie.copy(isFavorite = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: WisataRepository? = null

        fun getInstance():WisataRepository =
            instance ?: synchronized(this){
               WisataRepository().apply {
                    instance = this
                }
            }
    }
}

