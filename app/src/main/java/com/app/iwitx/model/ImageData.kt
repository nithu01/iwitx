package com.app.iwitx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageData {
    @SerializedName("sliderimage")
    @Expose
    private var sliderimage: String? = null

    @SerializedName("pos")
    @Expose
    private var pos: String? = null

    fun getSliderimage(): String? {
        return sliderimage
    }

    fun setSliderimage(sliderimage: String?) {
        this.sliderimage = sliderimage
    }

    fun getPos(): String? {
        return pos
    }

    fun setPos(pos: String?) {
        this.pos = pos
    }
}