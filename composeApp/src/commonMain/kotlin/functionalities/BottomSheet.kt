package functionalities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import playgroundkmp.composeapp.generated.resources.Res

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet() {
    val openDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(Modifier.fillMaxSize()){
        Button(modifier = Modifier.align(Alignment.Center),onClick = {
            openDialog.value = true
            scope.launch {
                sheetState.expand()
            }
        }){
            Text("Open Bottom Sheet")
        }

        if (openDialog.value) {


            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { openDialog.value = false },
                containerColor = Color(0xD9000000),
                dragHandle = {BottomSheetDefaults.DragHandle(
                    width = 82.dp,
                    height = 9.dp,
                    color = Color(0xFF54565A)
                )},
                modifier = Modifier.fillMaxHeight(0.5f)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        "OTP View",
                        fontSize = 25.sp,
                        color = Color.White,
                        modifier = Modifier.padding(18.dp)
                    )

                    OtpView()
                }

            }
        }
    }
}

@Composable
fun OtpView(
    boxWidth: Int = 50,
    boxHeight: Int = 53,
    boxSpacing: Int = 16,
) {

    val borderColor = Color(0xFFBFBFBF)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until 4) {
            OutlinedTextField(
                value = "",
                onValueChange = {
                },
                modifier = Modifier
                    .size(boxWidth.dp, boxHeight.dp)
                    .background(color = Color.Transparent),
                textStyle = TextStyle.Default.copy(
                    textAlign = TextAlign.Center
                ),
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(4.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    disabledBorderColor = borderColor,
                    errorBorderColor = borderColor,
                    focusedLabelColor = borderColor,
                    unfocusedLabelColor = borderColor,
                    disabledLabelColor = borderColor,
                    errorLabelColor = borderColor,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = VisualTransformation.None,
            )
            Spacer(modifier = Modifier.width(boxSpacing.dp))
        }
    }
}