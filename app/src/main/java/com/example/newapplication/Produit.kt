package com.example.newapplication


/** CECI EST LA CLASSE PRODUIT */
data class Produit(
    val id : Int,
    var nomProduit : String,
    var prixProduit : Double,
    var qttProduit: Double,
    var imageUrl : String,
    var descriptProduit : String
)


/** CECI EST UN OBJET SINGLETON C'EST-À-DIRE UN OBJET D'UNE CLASSE QUE L'ON DÉCLARE ET FORME EN MÊME TEMPS (L'OBJET ET LA CLASSE */
object ListProduit {
    //CECI ASSURERA L'INCRÉMENTATION DE L'IDPRODUIT
    private var currentId = 0
    private fun nextID() = ++currentId

    /**
     * CE VARIABLE EST L'ATTRIBUT DE LA CLASSE LISTPRODUIT QUI EST LA LISTE DE PRODUIT LUI-MÊME. DE TYPE MutableList<Produit>
     */
    var produitList: MutableList<Produit> = mutableListOf(
        Produit(
            nextID(),
            "Produit1",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            nextID(),
            "Produit2",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit ,f,ms4"
        ),
        Produit(
            nextID(),
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
            nextID(),
            "Produit5",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produidasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssst 4"
        ),
        Produit(
            nextID(),
            "Produit6",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            nextID(),
            "Produit7",
            300.2,
            10.0,
            "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
            "Produit 4"
        ),
        Produit(
            nextID(),
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
    fun selectProduit(
        idProduit: Int
    ): Produit
    {
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
    fun removeProduit(
        idProduit: Int
    ): MutableList<Produit>
    {
        val produit = selectProduit(idProduit)
        produit.let {
            produitList.remove(it)
        }
        return produitList
    }

    /**
     * FONCTION POUR MODIFIER LE PRODUIT
     * @param nomProduit : String
     * @param prixProduit : DOuble
     * @param qttProduit : Double
     * @param imageUrl : String
     * @param descriptProduit : String
     */
    fun upDateProduit(idProduit: Int,
                      nomProduit: String,
                      prixProduit : Double,
                      qttProduit: Double,
                      imageUrl : String,
                      descriptProduit : String
    )
    {
        ListProduit.removeProduit(idProduit)
        produitList.add(Produit(idProduit, nomProduit, prixProduit, qttProduit, imageUrl, descriptProduit))
    }

    /**
     * FONCTION POUR AJOUTER UN PRODUIT ET RETOURNE LE LISTEPRODUIT
     * @param nomProduit : String
     * @param prixProduit : DOuble
     * @param qttProduit : Double
     * @param imageUrl : String
     * @param descriptProduit : String
     * @return MutableList<Produit>
     */
    fun addProduit(
        nomProduit: String,
        prixProduit : Double,
        qttProduit: Double,
        imageUrl : String,
        descriptProduit : String
    ) : MutableList<Produit>
    {
        produitList.add(Produit(nextID(), nomProduit, prixProduit, qttProduit, imageUrl, descriptProduit))
        return produitList
    }
}