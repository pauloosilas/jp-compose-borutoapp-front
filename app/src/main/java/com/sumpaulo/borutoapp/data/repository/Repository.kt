package com.sumpaulo.borutoapp.data.repository

import com.sumpaulo.borutoapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState() : Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }
}