package com.example.androiddevchallenge

import android.content.res.TypedArray
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
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
        imageList: TypedArray,
        onItemClicked: (PuppyModel) -> Unit
    ) {
        Surface(color = MaterialTheme.colors.background) {
            PuppyScreen("Puppy", viewModel, imageList, onItemClicked)
        }
    }

    @Composable
    fun PuppyScreen(
        title: String,
        viewModel: PuppyViewModel,
        imageList: TypedArray,
        onItemClicked: (PuppyModel) -> Unit
    ) {
        Scaffold(
            topBar = {
                Toolbar(title)
            }, content = {
                Content(viewModel, imageList, onItemClicked)
            })
    }

    @Composable
    fun Content(
        viewModel: PuppyViewModel,
        imageList: TypedArray,
        onItemClicked: (PuppyModel) -> Unit,
        modifier: Modifier = Modifier
    ) {
        viewModel.getPuppyList(imageList)
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