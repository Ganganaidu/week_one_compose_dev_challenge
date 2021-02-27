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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.PuppyModel

object DetailScreen {

    @Composable
    fun DetailScreen(puppyModel: PuppyModel?) {
        MaterialTheme {
            val typography = MaterialTheme.typography
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                puppyModel?.imageUrl?.let {
                    Image(
                        painter = painterResource(it),
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(Modifier.height(10.dp))
                puppyModel?.name?.let {
                    Text(it, style = typography.h6)
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    stringResource(id = R.string.puppy_desc),
                    style = typography.body1
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    "Gangahdar, California",
                    modifier = Modifier
                        .align(Alignment.End),
                    color = Color.LightGray,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    style = typography.caption
                )
            }
        }
    }
}