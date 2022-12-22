package com.example.superheroe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroe.model.Hero
import com.example.superheroe.model.HeroesRepository
import com.example.superheroe.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperHeroesApp()
                }
            }
        }
    }
}
/** Este bloque es donde definimos el bloque entero de la APP. Añadimos un Scaffold para poner una barra
    superior en la app que hemos definido en otro bloque */

@Composable
fun SuperHeroesApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar()
        }
    ) {
        val heroes = HeroesRepository.heroes
        HeroesList(heroes = heroes, Modifier.padding(it))
    }
}
/** Este bloque es donde ponemos la lista de heroes, lo hacemos con un Lazy column
que sirve para poder añadir más heroes si fuera necesario */

@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
){
    /** Dentro de lazy column, indicamos los items que vamos a añadir (itemsIndexed), en este caso
     son los heroes (hero)*/

    LazyColumn{
        itemsIndexed(heroes){ _, hero ->
            HeroListItem(
                hero = hero,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            )
        }
    }

}

/** En este bloque, creamos la barra superior que pondremos en la app. La introducimos en una Box, donde
    definiremos que ocupe el ancho máximo, un tamaño de 56.dp, y que esté centrada. El texto hará referencia
    al nombre de la app y usaremos una typography.h1, que previamente hemos definido en ui.theme>type */

@Composable
fun TopAppBar(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}
/** En este bloque definiremos la lista individual de cada heroe. Para poder ponerle forma al bloque,
    debemos primero meterlo en una Card, donde definimos una elevación (un sombreado)
 Importante: primero hemos definido hero: Hero, para luego poder hacer referencia en los strings y
    drawables que tenemos definidos en la clase Hero, que hemos creado al principio.
 También hacemos referencia al MaterialTheme, que hemos definido al principio en ui.theme>theme */

@Composable
fun HeroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
){
    Card(
        elevation = 2.dp,
        modifier = modifier,
    ){
    Row (
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
            .sizeIn(minHeight = 72.dp)
            ){
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(hero.nameRes),
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = stringResource(hero.descriptionRes),
                style = MaterialTheme.typography.body1
            )

        }
        Spacer(modifier = Modifier.width(16.dp)
        )
        Box(modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(hero.imageRes),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth


            )
        }
        }
    }
}

/** Este bloque sirve para previsualizar cómo quedaría la parte visual de la app, en este caso, vamos a
    previsualizar el bloque SuperHeroesApp, que sería el bloque que comprende toda la app. */

@Preview(showBackground = true)
@Composable
fun SuperheroesPreview(){
    SuperheroesTheme {
        SuperHeroesApp()

    }
}