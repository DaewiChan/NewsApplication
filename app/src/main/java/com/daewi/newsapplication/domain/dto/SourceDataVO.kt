package com.daewi.newsapplication.domain.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
@JsonClass(generateAdapter = true)
open class SourceDataVO(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null
): RealmModel

//    -{
//        -"source": {
//        "id": "the-verge",
//        "name": "The Verge"
//    },