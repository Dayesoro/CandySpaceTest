package com.example.candyspace.domain.entity
/** Entity  Model */
data class Users (
    val Badge:BadgeCount,
    val Reputation:Long,
    val Location:String,
    val ProfileImage:String,
    val DisplayName:String,
    val CreationDate:Long
)

data class BadgeCount(
    val bronze:String,
    val silver:String,
    val gold:String
)
