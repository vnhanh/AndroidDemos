package com.vnhanh.common.androidhelper.localData

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vnhanh.common.androidhelper.BuildConfig
import com.vnhanh.common.log.AppLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

data class LocalDbException(override val message: String = "You did not initialize LocalDb") :
    Throwable(message)

object LocalDb {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app")
    private var instance: DataStore<Preferences>? = null

    fun init(context: Context) {
        if (instance == null) {
            instance = context.applicationContext.dataStore
        }
    }

    @Throws(LocalDbException::class)
    suspend fun <T> saveData(
        key: String,
        value: T,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldThrowException: Boolean = BuildConfig.DEBUG,
    ) {
        dataStoreInstanceForSave(shouldThrowException, dispatcher) { dataStore ->
            dataStore.edit { preferences: MutablePreferences ->
                savePreference(preferences, key, value)
            }
        }
    }

    private fun <T> savePreference(preferences: MutablePreferences, key: String, value: T) {
        when (value) {
            is String -> preferences[stringPreferencesKey(key)] = value
            is Boolean -> preferences[booleanPreferencesKey(key)] = value
            is Int -> preferences[intPreferencesKey(key)] = value
            is Long -> preferences[longPreferencesKey(key)] = value
            is Float -> preferences[floatPreferencesKey(key)] = value
            is Double -> preferences[doublePreferencesKey(key)] = value
            else -> Unit
        }
    }

    @Throws(LocalDbException::class)
    private suspend inline fun dataStoreInstanceForSave(
        shouldThrowException: Boolean,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        crossinline block: suspend (DataStore<Preferences>) -> Unit,
    ) {
        val dataStore: DataStore<Preferences> = instance ?: run {
            if (shouldThrowException) {
                throw LocalDbException()
            }
            return
        }
        withContext(dispatcher) {
            block(dataStore)
        }
    }

    fun getString(
        key: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldLog: Boolean = BuildConfig.DEBUG,
        shouldThrowException: Boolean = BuildConfig.DEBUG
    ): Flow<String?>? =
        handlePreferences(
            dispatcher = dispatcher,
            shouldLog = shouldLog,
            shouldThrowException = shouldThrowException,
        ) { preferences ->
            preferences[stringPreferencesKey(key)]
        }

    fun getBoolean(
        key: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldLog: Boolean = BuildConfig.DEBUG,
        shouldThrowException: Boolean = BuildConfig.DEBUG
    ): Flow<Boolean?>? =
        handlePreferences(
            dispatcher = dispatcher,
            shouldLog = shouldLog,
            shouldThrowException = shouldThrowException,
        ) { preferences ->
            preferences[booleanPreferencesKey(key)]
        }

    fun getInt(
        key: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldLog: Boolean = BuildConfig.DEBUG,
        shouldThrowException: Boolean = BuildConfig.DEBUG,
    ): Flow<Int?>? =
        handlePreferences(
            dispatcher = dispatcher,
            shouldLog = shouldLog,
            shouldThrowException = shouldThrowException,
        ) { preferences ->
            preferences[intPreferencesKey(key)]
        }

    fun getLong(
        key: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldLog: Boolean = BuildConfig.DEBUG,
        shouldThrowException: Boolean = BuildConfig.DEBUG,
    ): Flow<Long?>? =
        handlePreferences(
            dispatcher = dispatcher,
            shouldLog = shouldLog,
            shouldThrowException = shouldThrowException,
        ) { preferences ->
            preferences[longPreferencesKey(key)]
        }

    fun getFloat(
        key: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldLog: Boolean = BuildConfig.DEBUG,
        shouldThrowException: Boolean = BuildConfig.DEBUG,
    ): Flow<Float?>? =
        handlePreferences(
            dispatcher = dispatcher,
            shouldLog = shouldLog,
            shouldThrowException = shouldThrowException,
        ) { preferences ->
            preferences[floatPreferencesKey(key)]
        }

    fun getDouble(
        key: String,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldLog: Boolean = BuildConfig.DEBUG,
        shouldThrowException: Boolean = BuildConfig.DEBUG,
    ): Flow<Double?>? =
        handlePreferences(
            dispatcher = dispatcher,
            shouldLog = shouldLog,
            shouldThrowException = shouldThrowException,
        ) { preferences ->
            preferences[doublePreferencesKey(key)]
        }

    private fun <T> handlePreferences(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        shouldLog: Boolean = BuildConfig.DEBUG,
        shouldThrowException: Boolean = BuildConfig.DEBUG,
        block: suspend (Preferences) -> T,
    ) =
        dateStoreInstanceForGet(shouldThrowException) { dataStore ->
            dataStore.data
                .catch { exception ->
                    if (shouldLog) {
                        AppLog.e("caught exception while get data from data store preference ${exception.message}")
                    }
                    emit(emptyPreferences())
                }
                .map { preferences: Preferences ->
                    block(preferences)
                }.flowOn(dispatcher)
        }

    @Throws(LocalDbException::class)
    private inline fun <T> dateStoreInstanceForGet(
        shouldThrowException: Boolean,
        crossinline block: (DataStore<Preferences>) -> Flow<T>,
    ): Flow<T>? {
        val dataStore: DataStore<Preferences> = instance ?: run {
            if (shouldThrowException) {
                throw LocalDbException()
            }
            return null
        }

        return block(dataStore)
    }

}
