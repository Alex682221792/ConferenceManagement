package com.fissa.conferencemanagement.model

import com.fissa.conferencemanagement.logic.interfaces.JSONConvertable
import com.google.gson.annotations.SerializedName

data class TopicVO(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("minsDuration")
    var minsDuration: Int,
    @SerializedName("startTime")
    var startTime: String,
    @SerializedName("dismissible")
    var dismissible: Boolean): JSONConvertable {
}