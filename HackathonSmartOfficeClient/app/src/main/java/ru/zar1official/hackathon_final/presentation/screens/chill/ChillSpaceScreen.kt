package ru.zar1official.hackathon_final.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.NavigateBefore
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.channels.consumeEach
import org.koin.androidx.compose.getViewModel
import ru.zar1official.hackathon_final.R
import ru.zar1official.hackathon_final.domain.models.MassageMode
import ru.zar1official.hackathon_final.presentation.screens.chill.ChillSpaceViewModel
import ru.zar1official.hackathon_final.presentation.screens.chill.components.DropDownMenu
import ru.zar1official.hackathon_final.presentation.screens.events.ScreenEvent

@Composable
fun ChillSpaceScreen(
    viewModel: ChillSpaceViewModel = getViewModel(),
    scaffoldState: ScaffoldState
) {
    val isLoaded = viewModel.isLoaded.observeAsState(initial = false)
    val snackBarErrorMessage = stringResource(id = R.string.error_message)
    val snackBarErrorLabel = stringResource(id = R.string.error_label)
    val massageMode = viewModel.massageMode.observeAsState(initial = MassageMode.None)
    val currentSong = viewModel.currentSong.observeAsState(initial = "")
    val isPlaying = viewModel.isPlaying.observeAsState(initial = false)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(true) {
            viewModel.event.consumeEach { event ->
                when (event) {
                    is ScreenEvent.Error -> {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = snackBarErrorMessage,
                            duration = SnackbarDuration.Short,
                        )
                    }
                    is ScreenEvent.LoadingError -> {
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            message = snackBarErrorMessage,
                            duration = SnackbarDuration.Indefinite,
                            actionLabel = snackBarErrorLabel
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onGetChillSpaceState()
                        }
                    }
                }
            }
        }
        if (isLoaded.value) {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(
                    top = 5.dp,
                    bottom = 5.dp,
                    start = 10.dp,
                    end = 10.dp
                ),
                content = {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(175.dp)
                                .background(color = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            elevation = 2.dp
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_chair),
                                        contentDescription = ""
                                    )

                                    DropDownMenu(
                                        suggestions = listOf(
                                            MassageMode.None,
                                            MassageMode.Vibration,
                                            MassageMode.AirCompression
                                        ),
                                        onChangeSelected = {
                                            viewModel.onChangeMassageMode(mode = it)
                                        },
                                        selected = massageMode
                                    )
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(45.dp)) }

                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(175.dp)
                                .background(color = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            elevation = 2.dp
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(

                                ) {

                                    IconButton(onClick = { viewModel.onPreviousMedia() }) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            imageVector = Icons.Outlined.NavigateBefore,
                                            contentDescription = ""
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(15.dp))


                                    IconButton(onClick = { viewModel.onPlayMedia() }) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            imageVector = if (!isPlaying.value) Icons.Filled.PlayCircle else Icons.Filled.StopCircle,
                                            contentDescription = ""
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(15.dp))

                                    IconButton(onClick = { viewModel.onNextMedia() }) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            imageVector = Icons.Outlined.NavigateNext,
                                            contentDescription = ""
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Slider(
                                    modifier = Modifier.requiredWidth(200.dp),
                                    value = 30f,
                                    onValueChange = {

                                    },
                                    colors = SliderDefaults.colors(
                                        thumbColor = Color.Black,
                                        activeTrackColor = Color.Black
                                    ),
                                    onValueChangeFinished = {

                                    },
                                    valueRange = 0f..100f
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = currentSong.value, fontSize = 18.sp)
                                    IconButton(onClick = { viewModel.onLoadSong() }) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            imageVector = Icons.Outlined.Download,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.size(75.dp),
                strokeWidth = 5.dp
            )
            viewModel.onGetChillSpaceState()
        }
    }
}