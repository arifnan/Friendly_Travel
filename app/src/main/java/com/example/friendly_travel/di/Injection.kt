package com.example.friendly_travel.di

import com.example.friendly_travel.data.CategoryRepository
import com.example.friendly_travel.data.WisataRepository

object Injection {
    fun provideRepository(): WisataRepository {
        return WisataRepository.getInstance()
    }
}

object CategoryInjection {
    fun provideRepository(): CategoryRepository {
        return CategoryRepository.getInstance()
    }
}