package com.example.digikala.utillity

import android.net.Uri
import com.example.digikala.R
import java.util.*

class ImageConverterResource {
    fun getAllImage():List<String>{
        var mList = ArrayList<String>()
        mList.add(convertResourceToURL(R.drawable.amazing_offer))
        mList.add(convertResourceToURL(R.drawable.atr_offer))
        mList.add(convertResourceToURL(R.drawable.digikala_offer))
        mList.add(convertResourceToURL(R.drawable.huawei))
        mList.add(convertResourceToURL(R.drawable.main_offer))
        return mList
    }

    fun convertResourceToURL(res:Int):String{
        return Uri.parse("android.resource://com.example.digikala/" + res).toString()
    }
}