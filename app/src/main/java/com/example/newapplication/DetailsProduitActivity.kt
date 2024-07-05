package com.example.newapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.newapplication.ui.theme.NewApplicationTheme

class DetailsProduitActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ICI ON OBTIENT L'EXTRA ENVOYÉE DANS L'ACTIVITÉ PRÉCEDENTE
        val idProduit = intent.getIntExtra("idProduit", 0)

        //INSTANCE DE LA CLASSE LISTPRODUIT
        val produit = ListProduit.selectProduit(idProduit)

        //ICI NOUS SELECTIONS LE PRODUIT DE LA LISTEPRODUIT DE L'INSTANCE LISTPRODUIT, CORRESPONDANT À L'EXTRA RÉCUPÉRÉ
        setContent {
            NewApplicationTheme {
                MainDetailView(
                    produit,
                    //DELETE PRODUCT HERE
                    actionProduct = {
                        ListProduit.removeProduit(idProduit)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

/**
 * COMPOSABLE PRINCIPALE CHARGÉ DE MONTRER TOUS LES COMPOSANTS DE CETTE ACTIVITÉ
 * @param produit : Produit qui est le produit à montrer
 */
@Composable
fun MainDetailView(
    produit: Produit?,
    actionProduct:(Produit?) -> Unit
){
    Scaffold (
        topBar = { Header()},
        floatingActionButton = {
            CustomExtendedFloatingActionButton("SUPPRIMER", Color.Red, produit, actionProduct)
        }
    ){innerPadding->

        //NOUS LANÇONS ICI LE COMPOSABLE DISPLAYDETAIL EN ENVOYANT LE PADDING PAR DÉFAUT ET LE PRODUIT À MONTRER
        DisplayDetail(innerPadding, produit)
    }
}

/**
 * COMPOSABLE CHARGÉ DE MONTRER LES DÉTAILS DANS UN TYPE DE CONTENU OUTLINEDCARD
 * @param innerPadding : PaddingValues qui est la valeur par défaut du padding du container Scaffold
 * @param produit : Produit qui est le produit à montrer
 */
@Composable
fun DisplayDetail(
    innerPadding : PaddingValues,
    produit: Produit?
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 15.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .size(width = 350.dp, height = 670.dp)
                .padding(innerPadding)
        ){
            LazyVerticalGrid(columns = GridCells.Fixed(1))
            {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp, 15.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        produit?.nomProduit?.let {
                            Text(
                                text = it,
                                color = Color.Black,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        val painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current).data(data = produit?.imageUrl)
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                }).build()
                        )
                        Image(
                            painter = painter,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(300.dp)
                        )
                        DetailsText(title = "Prix", data = "${produit?.prixProduit}")
                        DetailsText(title = "Quantité", data = "${produit?.qttProduit}")
                        DetailsText(title = "Description", data = "${produit?.descriptProduit}")
                    }
                }
            }

        }
    }
}

/**
 * COMPOSABLE CHARGÉ DE MONTRÉ TOUS LES DONNÉES UN PAR UN
 * @param title : String qui est le titre du donnée
 * @param data : String qui est le donnée elle-même
 */
@Composable
fun DetailsText(
    title: String,
    data: String
){
    Column(
        modifier = Modifier
            .padding(16.dp, 5.dp, 0.dp, 0.dp)
    ){
        Text(
            text = "$title : ",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
        Column(
            modifier = Modifier
                .padding(16.dp, 5.dp, 0.dp, 0.dp)
        ){
            Text(
                text = data,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }
}

/**
 * TYPE DE BUTTON FLOATING MAIS CUSTOMISÉ
 */
@Composable
fun CustomExtendedFloatingActionButton(
    text : String,
    couleur : Color,
    produit: Produit?,
    actionProduct:(Produit?) -> Unit
)
{
    ExtendedFloatingActionButton(
        onClick = { actionProduct(produit)},
        containerColor = couleur,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(35.dp, 0.dp, 0.dp, 16.dp)
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Text(
            text,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayPreview(){
    ListProduit.selectProduit(5)
    NewApplicationTheme{
    }
}
