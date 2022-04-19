package ru.zar1official.hackathon_final.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.channels.consumeEach
import org.koin.androidx.compose.getViewModel
import ru.zar1official.hackathon_final.R
import ru.zar1official.hackathon_final.domain.models.MicroclimateType
import ru.zar1official.hackathon_final.presentation.components.CustomButton
import ru.zar1official.hackathon_final.presentation.screens.events.ScreenEvent
import ru.zar1official.hackathon_final.presentation.screens.work.WorkSpaceViewModel
import ru.zar1official.hackathon_final.presentation.screens.work.components.ButtonGroup

@Composable
fun WorkSpaceScreen(
    viewModel: WorkSpaceViewModel = getViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val isLoaded = viewModel.isLoaded.observeAsState(initial = false)
    val microclimateType =
        viewModel.microclimateType.observeAsState(initial = MicroclimateType.Comfortable)
    val busyStatus = viewModel.busyStatus.observeAsState(initial = false)
    val bright = viewModel.bright.observeAsState(initial = 60)
    val warm = viewModel.warm.observeAsState(initial = 3000)
    val snackBarErrorMessage = stringResource(id = R.string.error_message)
    val snackBarErrorLabel = stringResource(id = R.string.error_label)

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
                                viewModel.onGetWorkSpaceState()
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
                                    .height(60.dp)
                                    .background(color = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                elevation = 2.dp
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(end = 10.dp)
                                ) {
                                    Text(
                                        text = "Status: " + if (busyStatus.value) "busy" else "free",
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp,
                                    )
                                    Spacer(modifier = Modifier.width(25.dp))
                                    CustomButton(
                                        backgroundColor = if (busyStatus.value) Color.Red else Color.Green,
                                        contentDescription = "",
                                        contentPaddingValues = PaddingValues(10.dp)
                                    ) {
                                        viewModel.onChangeBusyStatus()
                                    }
                                }
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(45.dp))
                        }

                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .background(color = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                elevation = 2.dp
                            ) {
                                Text(
                                    text = stringResource(id = R.string.microclimate_control_label),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp,
                                )
                                ButtonGroup(
                                    modifier = Modifier.padding(
                                        top = 20.dp,
                                        start = 35.dp,
                                        end = 35.dp,
                                    ),
                                    buttons = listOf(
                                        MicroclimateType.Comfortable,
                                        MicroclimateType.Cooling,
                                        MicroclimateType.Heating
                                    ),
                                    onChange = { button ->
                                        viewModel.onChangeMicroclimateType(button)
                                    },
                                    selectedButton = microclimateType,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    contentPaddingValues = PaddingValues(30.dp),
                                    borderStrokeColor = Color.Black,
                                    borderStrokeWidth = 2.dp
                                )
                            }
                        }

                        item { Spacer(modifier = Modifier.height(45.dp)) }

                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .background(color = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                elevation = 2.dp
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(15.dp)
                                ) {

                                    Text(
                                        text = stringResource(id = R.string.light_control_label),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp,
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(15.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Slider(
                                                modifier = Modifier.requiredWidth(200.dp),
                                                value = bright.value.toFloat(),
                                                onValueChange = {
                                                    viewModel.onChangeBright(it.toInt())
                                                },
                                                colors = SliderDefaults.colors(
                                                    thumbColor = Color.Black,
                                                    activeTrackColor = Color.Black
                                                ),
                                                onValueChangeFinished = {
                                                    viewModel.onSaveBright(bright.value)
                                                },
                                                valueRange = 0f..100f
                                            )

                                            Spacer(modifier = Modifier.width(20.dp))

                                            Text(text = "Bright: ${bright.value}", fontSize = 20.sp)
                                        }

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Slider(
                                                modifier = Modifier.requiredWidth(200.dp),
                                                value = warm.value.toFloat(),
                                                onValueChange = {
                                                    viewModel.onChangeWarm(it.toInt())
                                                },
                                                colors = SliderDefaults.colors(
                                                    thumbColor = Color.Black,
                                                    activeTrackColor = Color.Black
                                                ),
                                                onValueChangeFinished = {
                                                    viewModel.onSaveWarm(warm.value)
                                                },
                                                valueRange = 1800f..6600f
                                            )

                                            Spacer(modifier = Modifier.width(20.dp))

                                            Text(text = "Warm: ${warm.value}", fontSize = 20.sp)
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
                viewModel.onGetWorkSpaceState()
            }
        }
}