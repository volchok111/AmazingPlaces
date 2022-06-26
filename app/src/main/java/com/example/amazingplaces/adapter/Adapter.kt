package com.example.amazingplaces.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingplaces.R
import com.example.amazingplaces.model.place_model.PlacesModel
import com.example.amazingplaces.ui.details.DetailPresenter
import com.example.amazingplaces.utils.DistanceUtil
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var placesList = arrayListOf<PlacesModel>()

    /**
     * It updates the list of places in the adapter.
     *
     * @param list ArrayList<PlacesModel> - This is the list of places that you want to display in the
     * recycler view.
     */
    fun newList(list: ArrayList<PlacesModel>){
        placesList = list
        notifyDataSetChanged()
    }

    /**
     * It adds a place to the list and notifies the adapter that the data has changed.
     *
     * @param place PlacesModel - This is the data that will be added to the list.
     */
    fun addList(place: PlacesModel){
        placesList.add(place)
        notifyDataSetChanged()
    }

    /**
     * Sorts the placesList by name in ascending order.
     */
    fun doSortByTitleAz(){
        Collections.sort(placesList, PlacesModel.sortByNameAz)
        notifyDataSetChanged()
    }

    /**
     * It sorts the placesList by name in descending order.
     */
    fun doSortByTitleZa(){
        Collections.sort(placesList, PlacesModel.sortByNameZa)
        notifyDataSetChanged()
    }

    class PlaceViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val image = view.findViewById<ImageView>(R.id.item_iv)
        private val title = view.findViewById<TextView>(R.id.title_tv)
        private val nav = view.findViewById<TextView>(R.id.nav_tv)

        fun bind(place: PlacesModel){
            /* Setting the image, title and distance of the place. */
            Picasso.get().load(place.thumbnail_image).into(image)
            title.text = place.name
            DistanceUtil.distance(place.location.latitude,place.location.longitude).also {
                nav.text = when(it){
                    0 -> {"Less then 1 km"}
                    else -> "$it km"
                }
            }
            /* Setting the onClickListener for the itemView. When the itemView is clicked, it will
            navigate to the detailsFragment and pass the id of the place as an argument. */
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id",place.uuid)
                itemView.findNavController().navigate(
                    R.id.action_firstFragment_to_detailsFragment,bundle
                )
            }
        }
    }

    /**
     * This function is called when the RecyclerView needs a new RecyclerView.ViewHolder of the given
     * type to represent an item
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an
     * adapter position.
     * @param viewType This is the type of the view that will be created.
     * @return A RecyclerView.ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return PlaceViewHolder(view)
    }

    /**
     * It binds the data to the view holder.
     *
     * @param holder RecyclerView.ViewHolder - This is the ViewHolder that will be used to display the
     * data.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlaceViewHolder)
            holder.bind(placesList[position])
    }

    /**
     * The function returns the number of items in the placesList
     *
     * @return The number of items in the placesList
     */
    override fun getItemCount(): Int {
        return placesList.count()
    }
}