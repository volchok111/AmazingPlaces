package com.example.amazingplaces.ui.first

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amazingplaces.R
import com.example.amazingplaces.adapter.Adapter
import com.example.amazingplaces.databinding.FragmentFirstBinding
import com.example.amazingplaces.model.place_model.PlacesModel
import com.example.amazingplaces.ui.home.HomeFragment


class FirstFragment : HomeFragment(),FirstView {

    /* A way to avoid null pointer exception. */
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    /* A way to avoid null pointer exception. */
    private val adapter by lazy { Adapter() }

    /* A way to inject presenter into the view. */
    @InjectPresenter
    lateinit var firstPresenter: FirstPresenter


    /**
     * It inflates the layout and sets the adapter to the recycler view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container The parent that the fragment's UI should be attached to.
     * @param savedInstanceState A Bundle object containing the activity's previously saved state. If
     * the activity has never existed before, the value of the Bundle object is null.
     * @return The root view of the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentFirstBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    /**
     * A function that is called when the view is created.
     *
     * @param view View - the view that was created
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this
     * is the state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { firstPresenter.startInitialization(it) }
    }

    /**
     * This function inflates the menu resource file into the menu object
     *
     * @param menu The menu object that you want to inflate.
     * @param inflater The MenuInflater that can be used to inflate menu items from XML into the menu.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * When the user clicks on the menu item, the adapter will sort the list of movies by title in
     * ascending or descending order, and a toast message will appear to notify the user of the change
     *
     * @param item MenuItem - the item that was selected
     * @return The superclass method is being returned.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.sorting_title_az -> adapter.doSortByTitleAz()
                .also { Toast.makeText(context, "Sorted by titles A-Z", Toast.LENGTH_SHORT).show() }
            R.id.sorting_title_za -> adapter.doSortByTitleZa()
                .also { Toast.makeText(context, "Sorted by titles Z-A", Toast.LENGTH_SHORT).show() }

        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * It sets up the list of places.
     *
     * @param places List<PlacesModel> - This is the list of places that will be displayed in the list.
     */
    override fun onListSetup(places: List<PlacesModel>) {
        adapter.newList(places as ArrayList<PlacesModel> )
    }

    /**
     * The function takes a PlacesModel object as a parameter, and then calls the addList() function of
     * the adapter object, passing the PlacesModel object as a parameter
     *
     * @param place PlacesModel - This is the model that will be added to the list.
     */
    override fun addPlace(place: PlacesModel) {
        adapter.addList(place)
    }

    /**
     * It destroys the view and disposes the presenter.
     */
    override fun onDestroyView() {
        _binding = null
        firstPresenter.dispose()
        super.onDestroyView()
    }


}