/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.viewmodel

import android.content.res.TypedArray
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.PuppyModel

class PuppyViewModel : ViewModel() {

    var puppyList = MutableLiveData(listOf<PuppyModel>())

    fun getPuppyList(imageList: TypedArray, nameList: Array<String>) {
        var count = 0
        puppyList.value = List(50) {
            if(count >= 9) { // reset count as we have only 9 images
                count = 0
            }
            count++
            PuppyModel(
                nameList[count],
                imageList.getResourceId(count, R.drawable.puppy_image_01)
            )
        }
        // recycle it when no longer needed
        imageList.recycle()
    }
}