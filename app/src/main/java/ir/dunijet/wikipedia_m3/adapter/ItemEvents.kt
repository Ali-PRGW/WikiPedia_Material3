package ir.dunijet.wikipedia_m3.adapter

import ir.dunijet.wikipedia_m3.data.ItemPost

interface ItemEvents {

    fun onItemClicked( itemPost: ItemPost )

}