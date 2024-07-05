package com.example.newapplication


//CECI EST LA CLASSE PRODUIT
data class Produit(
    val id : Int,
    var nomProduit : String,
    var prixProduit : Double,
    var qttProduit: Double,
    var imageUrl : String,
    var descriptProduit : String
)



//CECI EST UN OBJET SINGLETON C'EST-À-DIRE UN OBJET D'UNE CLASSE QUE L'ON DÉCLARE ET FORME EN MÊME TEMPS (L'OBJET ET LA CLASSE)
object ListProduit {
    var produitList: MutableList<Produit> = mutableListOf(
        Produit(
            1,
            "Produit1",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            2,
            "Produit2",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit ,f,ms4"
        ),
        Produit(
            3,
            "Produit3",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            4,
            "Produit4",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            5,
            "Produit5",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produidasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssst 4"
        ),
        Produit(
            6,
            "Produit6",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            7,
            "Produit7",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            8,
            "Produit8",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        )
    )

    /**
     * FONCTION POUR SÉLÉCTIONNER UN PRODUIT PAR SON ID
     * @param idProduit : Int qui est l'id du produit
     * @return Produit cela retourne le produit qui a pour id, idProduit donné en paramètre
     */
    fun selectProduit(idProduit: Int): Produit {
        var product = Produit(0,"",0.00,0.00,"","")
        for(produit in produitList){
            if (produit.id == idProduit){
                product = produit
            }
        }
        return product
    }

    /**
     * FONCTION POUR SUPPRIMER UN PRODUIT PAR SON ID
     * @param idProduit : Int qui est l'id du produit à supprimer
     * @return MutableList<Produit> la liste mise à jour des produits
     */
    fun removeProduit(idProduit: Int): MutableList<Produit> {
        val produit = selectProduit(idProduit)
        produit.let {
            produitList.remove(it)
        }
        return produitList
    }
}