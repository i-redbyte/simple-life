package org.redbyte.simplelife.presentation.life

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat.requireViewById
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import org.redbyte.simplelife.R
import org.redbyte.simplelife.model.Cell
import org.redbyte.simplelife.model.Type.*

class LifeDelegate(context: Context) :
    AbsListItemAdapterDelegate<Cell, Any, LifeDelegate.Holder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean =
        item is Cell

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view = inflater.inflate(R.layout.item_cell_life, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(item: Cell, holder: Holder, payloads: List<Any>) = with(holder) {
        bind(item)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = requireViewById(itemView, R.id.tvCellTitle)
        private val tvSubtitle: TextView = requireViewById(itemView, R.id.tvCellSubtitle)
        private val vCircle: View = requireViewById(itemView, R.id.vCircle)
        private val ivState: ImageView = requireViewById(itemView, R.id.ivState)

        fun bind(item: Cell) {
            when (item.type) {
                LIFE -> {
                    tvTitle.text = getString(R.string.cell_life_title)
                    tvSubtitle.text = getString(R.string.cell_life_subtitle)
                    vCircle.background = getDrawable(R.drawable.circle_life)
                    ivState.setImageResource(R.drawable.ic_life)
                }
                DEAD -> {
                    tvTitle.text = getString(R.string.cell_dead_title)
                    tvSubtitle.text = getString(R.string.cell_dead_subtitle)
                    vCircle.background = getDrawable(R.drawable.circle_dead)
                    ivState.setImageResource(R.drawable.ic_skull)

                }
                LIVELY -> {
                    tvTitle.text = getString(R.string.cell_lively_title)
                    tvSubtitle.text = getString(R.string.cell_lively_subtitle)
                    vCircle.background = getDrawable(R.drawable.circle_lively)
                    ivState.setImageResource(R.drawable.ic_spark)
                }
            }
        }

        private fun getString(@StringRes id: Int): String =
            itemView.context.getString(id)

        private fun getDrawable(@DrawableRes id: Int): Drawable? = itemView.context.getDrawable(id)

    }
}