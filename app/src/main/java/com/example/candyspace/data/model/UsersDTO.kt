package com.example.candyspace.data.model

import com.example.candyspace.domain.entity.BadgeCount
import com.example.candyspace.domain.entity.Users
import com.google.gson.annotations.SerializedName

/**
 * A class that helps to serialise and deserialize network data
 * And would eventually be mapped to our business logic
 * which has only the needed data
 * **/
data class UsersDTO(
    @SerializedName("badge_counts") val Badge:BadgeCountDTO,
    @SerializedName("reputation")   val Reputation:Long,
    @SerializedName("user_type")    val UserType:String,
    @SerializedName("user_id")      val UserID:Long,
    @SerializedName("accept_rate")  val AcceptRate:Long,
    @SerializedName("location")     val Location:String,
    @SerializedName("website_url")  val WebsiteUrl:String,
    @SerializedName("link")         val Link:String,
    @SerializedName("profile_image")val ProfileImage:String,
    @SerializedName("display_name") val DisplayName:String,
    @SerializedName("creation_date") val CreationDate:Long,
)

data class BadgeCountDTO(
    val bronze:String,
    val silver:String,
    val gold:String
)

fun UsersDTO.toUser()= Users(
    Reputation = Reputation,
    CreationDate = CreationDate,
    DisplayName = DisplayName,
    Location = Location,
    ProfileImage = ProfileImage,
    Badge = Badge.toBadgeCount()
)
fun BadgeCountDTO.toBadgeCount(): BadgeCount =
    BadgeCount(
        bronze,
        silver,
        gold
    )