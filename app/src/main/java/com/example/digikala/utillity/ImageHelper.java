package com.example.digikala.utillity;

import android.net.Uri;

import com.example.digikala.R;

import java.util.ArrayList;
import java.util.List;

public class ImageHelper {

    public static List<String> getAllImage(){
        List<String> stringList = new ArrayList<>();
        stringList.add(convertResourceIdToUrl(R.drawable.amazing_offer));
        stringList.add(convertResourceIdToUrl(R.drawable.atr_offer));
        stringList.add(convertResourceIdToUrl(R.drawable.digikala_offer));
        stringList.add(convertResourceIdToUrl(R.drawable.huawei));
        stringList.add(convertResourceIdToUrl(R.drawable.main_offer));
        return stringList;
    }

    public static String convertResourceIdToUrl(int resourceId) {
        return Uri.parse("android.resource://com.example.digikala/" + resourceId).toString();
    }
}
