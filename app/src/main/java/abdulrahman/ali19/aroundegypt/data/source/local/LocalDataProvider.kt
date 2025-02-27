package abdulrahman.ali19.aroundegypt.data.source.local

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocalDataProvider(
    private val context: Context
) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("experience_prefs", Context.MODE_PRIVATE)

    fun <T> saveObject(key: String, value: KSerializer<T>) {
        sharedPreferences.edit()
            .putString(key, Json.encodeToString(value))
            .apply()
    }

    fun <T> getObject(key: String, serializer: KSerializer<T>): Flow<T?> {
        val json = sharedPreferences.getString(key, null)
        return flow { emit(json?.let { Json.decodeFromString(serializer, it) }) }
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
