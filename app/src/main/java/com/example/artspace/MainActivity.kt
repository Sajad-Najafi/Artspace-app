package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ArtSpaceTheme {
        HomeScreen()
      }
    }
  }
}

@Composable
fun HomeScreen() {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    HomeContent(modifier = Modifier.padding(innerPadding))
  }
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
  val arts = 2
  var step by remember { mutableIntStateOf(1) }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Bottom,
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp)
  ) {
    Spacer(modifier = Modifier.height(20.dp))
    when (step) {
      1 -> {PhotoAndText(
        painterResourceId = R.drawable.cat,
        titleResourceId = R.string.first_title,
        artistResourceId = R.string.first_artist,
        yearResourceId = R.string.first_year
      )}
      2-> {
        PhotoAndText(
          painterResourceId = R.drawable.mountain,
          titleResourceId = R.string.second_title,
          artistResourceId = R.string.second_artist,
          yearResourceId = R.string.second_year
        )
      }
    }
    Spacer(modifier = Modifier.height(32.dp))
    NavigationButtons(
      previousOnClick = {
        if (step != 1) {
          step--
        }
      },
      nextOnClick = {
        if (step != arts) {
          step++
        } else {
          step = 1  
        }
      }
    )
  }
}

@Composable
fun PhotoAndText(
  painterResourceId: Int,
  titleResourceId: Int,
  artistResourceId: Int,
  yearResourceId: Int
) {
  Card(
    shape = RoundedCornerShape(0.dp),
    elevation = CardDefaults.cardElevation(4.dp),
    colors = CardDefaults.cardColors(Color.White),
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Image(
      painter = painterResource(painterResourceId),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .padding(32.dp)
        .aspectRatio(3f / 4f)
    )
  }
  Spacer(modifier = Modifier.height(42.dp))
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .background(Color(0xFFEBEDEF))
  ) {
    Column(
      modifier = Modifier
        .padding(16.dp)
    ) {
      Text(
        text = stringResource(titleResourceId),
        style = MaterialTheme.typography.titleLarge,
      )
      Spacer(modifier = Modifier.height(16.dp))
      Row(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = stringResource(artistResourceId),
          fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "(${stringResource(yearResourceId)})",
        )
      }
    }
  }
}
@Composable
fun NavigationButtons(previousOnClick: () -> Unit, nextOnClick: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Button(
      onClick = previousOnClick,
      modifier = Modifier
        .weight(1f)
    ) {
      Text(text = "Previous")
    }
    Spacer(modifier = Modifier.width(16.dp))
    Button(
      onClick = nextOnClick,
      modifier = Modifier
        .weight(1f)
    ) {
      Text(text = "Next")
    }
  }
}

@Preview(
  showBackground = true,
  showSystemUi = true
)
@Composable
fun AppPreview() {
  ArtSpaceTheme {
    HomeScreen()
  }
}