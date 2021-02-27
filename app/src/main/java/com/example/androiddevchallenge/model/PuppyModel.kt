package com.example.androiddevchallenge.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class PuppyModel(
    val name: String?,
    @DrawableRes val imageUrl: Int
) : Parcelable