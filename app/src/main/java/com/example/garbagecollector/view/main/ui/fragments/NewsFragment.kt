package com.example.garbagecollector.view.main.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.garbagecollector.R
import com.example.garbagecollector.domain.model.CatalogueItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catalogue_item.view.*
import kotlinx.android.synthetic.main.event_layout.view.*
import kotlinx.android.synthetic.main.fragment_catalogue.*

class NewsFragment : Fragment() {

    private lateinit var adapter: CatalogueItemsListAdapter
    private var dates = ArrayList<String>()
    private var messages = ArrayList<String>()


    private var catalogueItems = ArrayList<CatalogueItem>(9)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_catalogue, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dates.addAll(resources.getStringArray(R.array.dates))
        messages.addAll(resources.getStringArray(R.array.info))
        fillInItems()
        initRecyclerView()


    }

    private fun fillInItems() {
        var i = 1
        while (i < 9) {
            catalogueItems.add(
                    CatalogueItem(
                            messages[i],
                            i + 1,
                            dates[i]
                    )
            )
            i++
//            Log.e("item: ", catalogueItems[i].date + " | " + catalogueItems[i].message + " | " + catalogueItems[i].imgId)
        }
    }

    private fun initRecyclerView() {
        adapter = CatalogueItemsListAdapter(requireContext())
        fragCatalogue_rv.adapter = adapter
        adapter.updateItems(catalogueItems)
    }
}

class CatalogueItemsListAdapter(
        val context: Context,
        var catalogueItemsList: MutableList<CatalogueItem> = mutableListOf()
) : RecyclerView.Adapter<CatalogueItemsListAdapter.CatalogueItemViewHolder>() {

    fun updateItems(newItems: List<CatalogueItem>) {
        catalogueItemsList.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueItemViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.event_layout, parent, false)

        return CatalogueItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = catalogueItemsList.size

    override fun onBindViewHolder(holder: CatalogueItemViewHolder, position: Int) {
        holder.bind(catalogueItemsList[position])
    }

    inner class CatalogueItemViewHolder(val containerView: View) :
            RecyclerView.ViewHolder(containerView) {
        fun bind(catalogueItem: CatalogueItem) {
            containerView.name_eventLayout.text = catalogueItem.message
            containerView.description_eventLayout.text = catalogueItem.data
            when (catalogueItem.imgId) {
                1 -> {
                     containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img1))
                }
                2 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img2))
                }
                3 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img3))
                }
                4 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img4))
                }
                5 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img5))
                }
                6 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img6))
                }
                7 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img7))
                }
                8 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img8))
                }
                9 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img9))
                }
                10 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img10))
                }
            }
        }


    }
}