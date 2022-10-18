package com.myansis.android.myansishr.utils.moshi

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import io.realm.RealmList
import io.realm.RealmObject
import java.io.IOException

/**
 * Derived from this example:
 * https://github.com/square/moshi/blob/master/moshi/src/main/java/com/squareup/moshi/CollectionJsonAdapter.java
 */

internal class RealmListAdapter<T : RealmObject>(private val elementAdapter: JsonAdapter<T>) :
    JsonAdapter<RealmList<T>>() {

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, @NonNull value: RealmList<T>?) {
        writer.beginArray()
        for (element in value!!) {
            elementAdapter.toJson(writer, element)
        }
        writer.endArray()
    }

    @Nullable
    @Throws(IOException::class)
    override fun fromJson(@NonNull reader: JsonReader): RealmList<T>? {
        val result = RealmList<T>()
        reader.beginArray()
        while (reader.hasNext()) {
            result.add(elementAdapter.fromJson(reader))
        }
        reader.endArray()
        return result
    }
}
