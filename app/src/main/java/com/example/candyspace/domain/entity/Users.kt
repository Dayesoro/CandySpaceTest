package com.example.candyspace.domain.entity
import java.io.Serializable

/** Entity  Model */
data class Users(
    val items:List<UsersData>
): Serializable

data class UsersData (
    val Badge:BadgeCount?,
    val Reputation:Long?,
    val Location:String?,
    val ProfileImage:String?,
    val DisplayName:String?,
    val CreationDate:Long?
): Serializable

data class BadgeCount(
    val bronze:String?,
    val silver:String?,
    val gold:String?
): Serializable