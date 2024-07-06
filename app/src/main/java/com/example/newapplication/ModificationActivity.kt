package com.example.newapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newapplication.ui.theme.NewApplicationTheme

class ModificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idProduit = intent.getIntExtra("idProduit",0)
        setContent {
            NewApplicationTheme {
                ModificationMainView(
                    ListProduit.selectProduit(idProduit),
                    /**
                     * DÉFINIR LA FONCTION LAMBA QUI VA ASSURER LE RETOUR VERS L'ACTIVITÉ MAINACTIVITTY
                     */
                    goToMainActivity = {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    },
                    goToPreviousActivity = {
                        val intent = Intent(this, DetailsProduitActivity::class.java)
                            .apply { putExtra("idProduit", idProduit) }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}


/**
 * CE COMPOSANT EST LE COMPOSANT PRINCIPAL DE L'ACTIVITÉ MODIFICATION
 */
@Composable
fun ModificationMainView(
    produit: Produit,
    goToMainActivity: () -> Unit,
    goToPreviousActivity: () -> Unit
){
    var nameProduit by remember { mutableStateOf(produit.nomProduit) }
    var prixProduit by remember { mutableStateOf(produit.prixProduit.toString()) }
    var quantityProduit by remember { mutableStateOf(produit.qttProduit.toString()) }
    var imageUrlProduit by remember { mutableStateOf(produit.imageUrl) }
    var descriptionProduit by remember { mutableStateOf(produit.descriptProduit) }
    Scaffold (
        topBar = {
            Header(goToPreviousActivity)
        },
        floatingActionButton = {
            CustomExtendedFloatingActionButton(
                text = "MODIFIER",
                couleur = Color.Green,
                produit,
                //ADD NEW PRODUCT HERE
                actionProduct = { produit ->
                    // ICI NOUS APPELLER LA FONCTION UPDATEPRODUIT DE LA CLASSE LISTEPRODUIT POUR QU'IL MODIFIE LE PRODUIT DE LA LISTE
                    produit?.let {
                        ListProduit.upDateProduit(
                            it.id, nameProduit, prixProduit.toDouble(), quantityProduit.toDouble(), imageUrlProduit, descriptionProduit
                        )
                    }

                    // APRÈS ICI NOUS ALLONS FINIR L'ACTIVITÉ MODIFICATION ET RETOURNER VERS L'ACTIVITÉ LISTE PRODUIT
                    goToMainActivity()
                }
            )
        }
    ){
        innerPadding -> DisplayFields1(
        produit,
        innerPadding,
        "MODIFICATION",
        onNameChange = {nameProduit = it},
        onPrixChange = {prixProduit = it},
        onQttChange = {quantityProduit = it},
        onImageUrlChange = {imageUrlProduit = it},
        onDescriptionChange = {descriptionProduit = it}
    )

    }
}

/**
 * CE COMPOSANT EST LE CONTAINER DE TOUS LES ÉLÈMENTS
 */
@Composable
fun DisplayFields1(
    produit: Produit,
    paddingValues: PaddingValues,
    nomDelHeader: String,
    onNameChange: (String) -> Unit,
    onPrixChange: (String) -> Unit,
    onQttChange: (String) -> Unit,
    onImageUrlChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
){
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
                .padding(paddingValues)
        ){
            LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                item { Column {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Text(
                            text = nomDelHeader,
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    DisplayFieldText(name = "Nom", data = produit.nomProduit, onValueChange = onNameChange)
                    DisplayFieldNumeric(name = "Prix", data = produit.prixProduit.toString(), onValueChange = onPrixChange)
                    DisplayFieldNumeric(name = "Quantité", data = produit.qttProduit.toString(), onValueChange = onQttChange)
                    DisplayFieldText(name = "Description", data = produit.descriptProduit, onValueChange = onDescriptionChange)
                    DisplayFieldText(name = "UrlImage", data = produit.imageUrl, onValueChange = onImageUrlChange)
                } }
            }
        }
    }
}

/**
 * POUR VOIR LE DESIGN EN AVANCE
 */
@Preview (showBackground = true)
@Composable
fun Preview(){
    ModificationMainView(
        Produit(
        0,
        "test",
        0.00,
        0.00,
        "fsdkjfs",
        ",fmds,mfsm,dfm,s,dm"
        ),
        goToMainActivity = {/* TO DO*/},
        goToPreviousActivity = {/* TO DO */}
    )
}