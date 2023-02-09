package com.llw.mvidemo.data.model


import com.squareup.moshi.Json

data class Vertical(
    @Json(name = "atime")
    val atime: Double,
    @Json(name = "cid")
    val cid: List<String>,
    @Json(name = "cr")
    val cr: Boolean,
    @Json(name = "desc")
    val desc: String,
    @Json(name = "favs")
    val favs: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "img")
    val img: String,
    @Json(name = "ncos")
    val ncos: Int,
    @Json(name = "preview")
    val preview: String,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "rule")
    val rule: String,
    @Json(name = "source_type")
    val sourceType: String,
    @Json(name = "store")
    val store: String,
    @Json(name = "tag")
    val tag: List<String>,
    @Json(name = "thumb")
    val thumb: String,
    @Json(name = "url")
    val url: List<Any>,
    @Json(name = "views")
    val views: Int,
    @Json(name = "wp")
    val wp: String,
    @Json(name = "xr")
    val xr: Boolean
)