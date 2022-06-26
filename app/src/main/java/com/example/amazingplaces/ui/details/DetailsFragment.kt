package com.example.amazingplaces.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amazingplaces.R
import com.example.amazingplaces.databinding.FragmentDetailsBinding
import com.example.amazingplaces.model.detail_model.Details
import com.example.amazingplaces.ui.home.HomeFragment
import com.example.amazingplaces.utils.DistanceUtil
import com.squareup.picasso.Picasso

class DetailsFragment : HomeFragment(), DetailView {

    /* A way to avoid null pointer exception. */
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    /* A Moxy annotation that injects the presenter into the view. */
    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    /**
     * `onCreateView` is a function that returns a View
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState A Bundle object containing the activity's previously saved state. If
     * the activity has never existed before, the value of the Bundle object is null.
     * @return The root view of the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    /**
     * The function is called when the fragment is created and it gets the id from the arguments and
     * passes it to the presenter
     *
     * @param view View - The View returned by onCreateView(LayoutInflater, ViewGroup, Bundle)
     * @param savedInstanceState This is the bundle that contains the state of the fragment.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id","")
        if (id != null)
            detailPresenter.getInfo(id)
    }

    /**
     * `onLoading()` is called when the `ViewModel` is loading data
     */
    override fun onLoading() {}

    /**
     * It shows a toast message when an error occurs.
     */
    override fun onError() {
        context?.let { Toast.makeText(it,
            getString(R.string.error), Toast.LENGTH_LONG).show() }
    }

    /**
     * It loads the details of the place into the view.
     *
     * @param details Details - The details of the place.
     */
    override fun onLoaded(details: Details) {

        Picasso.get().load(details.image).into(binding.fragmentDetailsIv)
        binding.apply {
            fragmentDetailsTitle.text = details.name

            /* Calculating the distance between the user and the place. */
            DistanceUtil.distance(details.location.latitude,details.location.longitude).also {
                fragmentDetailsNav.text = when(it){
                    0 -> {"Less then 1 km"}
                    else -> "$it km"
                }
            }
            fragmentDetailsCity.text = details.address.country
            fragmentDetailsZip.text = details.address.zip
            fragmentDetailsCityZip.text = details.address.city
            fragmentDetailsDesc.text = details.description

            binding.navFab.setOnClickListener {
                details.location.apply {
                    onShowMap(latitude.toString(),longitude.toString())
                }
            }
        }
    }
}