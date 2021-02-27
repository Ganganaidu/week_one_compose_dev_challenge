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
package com.example.androiddevchallenge

import android.content.res.TypedArray
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.PuppyModel
import com.example.androiddevchallenge.viewmodel.PuppyViewModel

object MainScreen {

    // Start building your app here!
    @Composable
    fun MainScreen(
        viewModel: PuppyViewModel,
        nameList: Array<String>,
        imageList: TypedArray,
        onItemClicked: (PuppyModel) -> Unit
    ) {
        Surface(color = MaterialTheme.colors.background) {
            PuppyScreen(
                "Puppy", viewModel, nameList,
                imageList, onItemClicked
            )
        }
    }

    @Composable
    fun PuppyScreen(
        title: String,
        viewModel: PuppyViewModel,
        nameList: Array<String>,
        imageList: TypedArray,
        onItemClicked: (PuppyModel) -> Unit
    ) {
        Scaffold(
            topBar = {
                Toolbar(title)
            },
            content = {
                Content(viewModel, nameList, imageList, onItemClicked)
            }
        )
    }

    @Composable
    fun Content(
        viewModel: PuppyViewModel,
        nameList: Array<String>,
        imageList: TypedArray,
        onItemClicked: (PuppyModel) -> Unit,
        modifier: Modifier = Modifier
    ) {
        viewModel.getPuppyList(imageList, nameList)
        viewModel.puppyList.observeAsState().value?.let {
            LazyColumn(modifier = modifier) {
                items(items = it) {
                    PuppyText(it, onItemClicked)
                }
            }
        }
    }

    @Composable
    fun Toolbar(title: String) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = Color.White
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = 12.dp
        )
    }

    @Composable
    fun PuppyText(
        puppyModel: PuppyModel,
        onItemClicked: (PuppyModel) -> Unit,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier
                .clickable { onItemClicked(puppyModel) }
                .padding(horizontal = 8.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(puppyModel.imageUrl),
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            puppyModel.name?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}
