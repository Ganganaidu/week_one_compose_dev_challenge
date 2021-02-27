package com.example.androiddevchallenge.viewmodel

import android.content.res.TypedArray
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.PuppyModel

class PuppyViewModel : ViewModel() {

    var puppyList = MutableLiveData(listOf<PuppyModel>())

    fun getPuppyList(imageList: TypedArray) {
        var count = 0
        puppyList.value = List(50) {
            if(count > 9) { // reset count as we have only 9 images
                count = 0
            }
            count++
            PuppyModel(
                "Hola Puppy $it",
                imageList.getResourceId(count, R.drawable.puppy_image_01)
            )
        }
        // recycle it when no longer needed
        imageList.recycle()
    }
}