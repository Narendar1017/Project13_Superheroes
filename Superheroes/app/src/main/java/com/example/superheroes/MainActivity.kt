package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                SuperheroesItems()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Pre() {
    SuperheroesItems()
}

@Composable
fun SuperheroesItems(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { SuperheroesTopBar(
            modifier = Modifier.height(64.dp).fillMaxWidth()
        ) }
    ) { it ->
        Column(modifier.padding(it)) {
            LazyColumn {
                items(heroes) { hero ->
                    SuperheroesCard(
                        hero = hero,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SuperheroesTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.displayLarge)
    }
}

@Composable
fun SuperheroesCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)) {
            Column(modifier = Modifier
                .weight(1f).padding(end = 4.dp)) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Image(
                painter = painterResource(hero.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .height(72.dp)
                    .width(72.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}