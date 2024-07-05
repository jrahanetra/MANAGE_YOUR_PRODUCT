package com.example.newapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newapplication.ui.theme.NewApplicationTheme

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            NewApplicationTheme {
                RegistrationMainView()
            }
        }
    }
}

/**
 * CE COMPOSANT EST LE COMPOSANT PRINCIPAL DE L'APPLICATION
 */
@Composable
fun RegistrationMainView(){
    val produit = Produit(0,"",0.00,0.00,"","")
    Scaffold (
        topBar = {
            Header()
        },
        floatingActionButton = {
            CustomExtendedFloatingActionButton(
                text = "AJOUTER", couleur = Color.Green,
                produit,
                //ADD NEW PRODUCT HERE
                actionProduct = {
                    // Traitez les données des champs de texte ici
                    //Ajout de nouvelle catégories

                })
        }
    ){
        innerPadding -> DisplayFiels(innerPadding)
    }
}

/**
 * COMPOSANT DE CONTAINER DE TOUS LES TEXTFIELDS, LES INPUTS PLUS COURAMMENT DITS
 * @param paddingValues : PaddingValues qui est la valeur du padding par défaut du Scaffolf
 */
@Composable
fun DisplayFiels(
    paddingValues: PaddingValues
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
                            text = "REGISTRATION",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    DisplayField(name = "Nom", data = "", onValueChange = {/*TO DO*/ })
                    DisplayField(name = "Prix", data = "", onValueChange = {/*TO DO*/ })
                    DisplayField(name = "Quantité", data = "", onValueChange = {/*TO DO*/ })
                    DisplayField(name = "Description", data = "", onValueChange = {/*TO DO*/ })
                    DisplayField(name = "UrlImage", data = "", onValueChange = {/*TO DO*/ })
                } }
            }
        }
    }
}

/**
 * CONTAINER DE L'INPUT
 * @param name : String c'est le nom de l'input
 * @param data : String c'est le donnée de l'input par défaut "{chaîne de caractère vide}"
 * @param onValueChange : Lambda function qui contient un paramètre String qui est le donnée à envoeyer
 */
@Composable
fun DisplayField(
    name: String,
    data: String,
    onValueChange: (String) -> Unit
){
    Column(
        modifier = Modifier
            .padding(16.dp, 5.dp, 0.dp, 0.dp)
            .fillMaxSize()
    ){
        TextFieldWithIconsEdit(name, data, onValueChange)
    }
}

/**
 * L'INPUT LUI-MÊME
 * @param name : String c'est le nom de l'input
 * @param data : String c'est le donnée de l'input par défaut "{chaîne de caractère vide}"
 * @param onValueChange : Lambda function qui contient un paramètre String qui est le donnée à envoeyer
 */
@Composable
fun TextFieldWithIconsEdit(
    name: String,
    data: String,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue(data)) }
    OutlinedTextField(
        value = text,
        textStyle = LocalTextStyle.current,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "EditIcon",
                tint = Color.Black
            )
        },
        onValueChange = {
            text = it
            onValueChange(it.text)
        },
        modifier = Modifier.width(320.dp),
        label = { Text(
            text = name,
            fontWeight = FontWeight.Bold
        )
        },
        colors = OutlinedTextFieldDefaults.colors(Color.Black)
    )
}

@Preview (showBackground = true)
@Composable
fun ShowPreview(){
    RegistrationMainView()
}
