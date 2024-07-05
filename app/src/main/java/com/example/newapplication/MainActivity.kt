package com.example.newapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.newapplication.ui.theme.NewApplicationTheme

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(saDSAvedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ICI NOUS ASSIGNONS L'ATTRIBUT DE L'OBJET LISTPRODUIT DANS LE VAL PRODUITS


        //NOUS DÉMARRONS LE CONTENANT DE L'ACTIVITÉ
        setContent {
            NewApplicationTheme {
                //NOUS DÉMARRONS ICI LE COMPOSANT MAINVIEW
                MainView(
                    ListProduit.produitList,
                    seeDetails = {produit ->
                        val itent = Intent(this, DetailsProduitActivity::class.java)
                            .apply { putExtra("idProduit", produit.id) }
                        startActivity(itent)
                    },
                    goToRegistrationProduct = {
                        val intent = Intent(this, RegistrationActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

/**
 * CE COMPOSABLE EST LE COMPOSABLE PRINCIPALE QUI AFFICHER L'ENSEMBLE DES COMPOSANTS
 *
 * @param listProduits: List<Produit> qui est la liste du produit
 * @param seeDetails: Function c'est la méthode lambda pour changer d'activité DetailsProduitActivity
 * @param goToRegistrationProduct: Function c'est la méthode lambda pour changer d'activité RegistrationActivity
 * */
@Composable
fun MainView(
    listProduits: List<Produit>,
    seeDetails: (Produit) -> Unit,
    goToRegistrationProduct: () -> Unit
){
    Scaffold(
        topBar = {
                 Header()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { goToRegistrationProduct() },
                containerColor = Color.Blue

            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add new Products"
                )
            }
        }

    ){ innerPadding ->
        ContainerData(innerPadding, listProduits, seeDetails)
    }
}

/**
 * HEADER C'EST LE COMPOSABLE D'EN-TÊTE
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.purple_700),
            titleContentColor = Color.White
        ),
        title = {
            Text(
                text = "MY APP"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ },
            )
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Return to the previous activity",
                )
            }
        },
    )
}

/**
 * CONTAINERDATA C'EST LE COMPOSABLE CONTAINER DE TOUS LES PRODUITS DE LA LISTEPRODUIT QUI EST SCROLABLE
 *
 * @param paddingValues: PaddingValues qui est le padding par défaut géré par le type de contenu Scaffold
 * @param listProduits:  List<Produit> qui est la liste du produit
 * @param seeDetails: c'est la méthode lamba pour changer d'activité
 * */
@Composable
fun ContainerData(
    paddingValues: PaddingValues,
    listProduits: List<Produit>,
    seeDetails: (Produit) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = paddingValues,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxHeight()
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        items(listProduits){produit->
            run {
                ShowCard(produit, seeDetails)
            }
        }
    }
}

/**
 * CE COMPOSABLE EST CHARGÉ D'AFFICHER LE PRODUIT , EN UTILISANT LE TYPE DE CONTENU CARD QUI EST CLICKABLE
 * @param produit: Produit c'est le produit de la liste
 * @param seeDetails: c'est la méthode lamba pour changer d'activité
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCard(
    produit: Produit,
    seeDetails : (Produit) -> Unit
){
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(10.dp),
        onClick = {
            seeDetails(produit)
        },
        colors = CardDefaults.cardColors(Color.Gray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = produit.imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }).build()
            )
            Image(
                painter = painter ,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )

            Text(text = "${produit.nomProduit}")
        }
    }
}

