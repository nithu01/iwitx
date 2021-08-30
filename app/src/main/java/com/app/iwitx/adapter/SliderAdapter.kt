package com.app.iwitx.adapter

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class SliderAdapter(var mContext: Context,var sliderImageId: IntArray) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(sliderImageId.get(position))
        (container as ViewPager).addView(imageView, 0)
        return imageView
    }
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val itemView: View = mLayoutInflater.inflate(R.layout.linear_layout, container, false)
//
//        val photoView = itemView.findViewById<View>(R.id.image) as ImageView
//               Picasso.with(getContext()).load("" + imageModelList[position].getSliderimage()).placeholder(R.drawable.offer_default).into(photoView)
//             (container as ViewPager).addView(itemView)
//        if (itemView.parent != null) {
//            (itemView.parent as ViewGroup).removeView(itemView) // <- fix
//        }
//        container.addView(itemView)
//        return itemView
//    }

//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as LinearLayout)
//    }
//
//    //        int[] images;
//    init {
//        mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        this.imageModelList = imageModelList
//        //            this.images=images;
//        //notifyDataSetChanged();
//    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    (container as ViewPager).removeView(`object` as ImageView?)
    }

    override fun getCount(): Int {
        return sliderImageId.size
    }
}

