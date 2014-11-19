package com.ingesup.android.labo.boite_a_meuh;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;

/**
 * Created by Mathea on 15/10/2014.
 */
public class SlideFragment extends Fragment {

    private int res;

    public SlideFragment(){
        super();
    }

    public SlideFragment(int res){
        this();
        this.res = res;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_slide, container, false);
        ImageView img = (ImageView)rootView.findViewById(R.id.imageView);
        img.setImageResource(res);
        return rootView;
    }
}
