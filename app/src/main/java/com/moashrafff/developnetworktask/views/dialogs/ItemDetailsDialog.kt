package com.moashrafff.developnetworktask.views.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.moashrafff.developnetworktask.R
import com.moashrafff.developnetworktask.data.model.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ItemDetailsDialog(context: Context) : AlertDialog.Builder(context) {

    fun show(product: Product) {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.product_details_dialog, null)
        val builder = AlertDialog.Builder(context)
        val itemImage = itemView.findViewById<ImageView>(R.id.item_iv)

        Picasso.get().load(product.images[0]).placeholder(R.drawable.ic_launcher_background)
            .fit().noFade().centerInside().into(itemImage, object : Callback {
                override fun onSuccess() {
                    itemImage.alpha = 0f
                    itemImage.animate().setDuration(150).alpha(1f).start()
                }

                override fun onError(e: Exception) {}
            })

        val priceTextView: TextView = itemView.findViewById(R.id.dialog_price_tv)
        val titleTextView: TextView = itemView.findViewById(R.id.dialog_title_tv)
        val descriptionTextView: TextView = itemView.findViewById(R.id.dialog_description_tv)

        priceTextView.text = product.price.toString()
        titleTextView.text = product.title
        descriptionTextView.text = product.description
        val alertDialog: AlertDialog = builder.create()

        alertDialog.setView(itemView)

        alertDialog.show()


    }
}