package com.example.fragmentexample.app;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class BlankFragment1 extends Fragment {

    SpringSystem springSystem;
    Spring spring1;
    Spring spring2;
    String SPRINGID1;
    String SPRINGID2;
    TextView textview1;
    TextView textview2;
    private final ExampleSpringListener mSpringListener = new ExampleSpringListener();

    public BlankFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank1, container, false);
        FrameLayout frame = (FrameLayout) view.findViewById(R.id.fragment1);
        textview1 = (TextView) view.findViewById(R.id.textview1);
        textview2 = (TextView) view.findViewById(R.id.textview2);

        // Create a system to run the physics loop for a set of springs.
        springSystem = SpringSystem.create();

        // Add a spring to the system.
        spring1 = springSystem.createSpring();
        spring2 = springSystem.createSpring();
        SPRINGID1 = spring1.getId();
        SPRINGID2 = spring2.getId();

        // Add a listener to observe the motion of the spring.
        spring1.addListener(mSpringListener);
        spring2.addListener(mSpringListener);


        spring1.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(2, .5));
        spring2.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(1,.2));
        spring1.setCurrentValue(1);
        // Set the spring in motion; moving from 0 to 1
//        spring.setEndValue(1);

        textview1.setOnTouchListener(viewListener);
        textview2.setOnTouchListener(viewListener);

        return view;
    }


    View.OnTouchListener viewListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    spring2.setEndValue(.5);
                    spring1.setEndValue(0);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    spring2.setEndValue(0);
                    spring1.setEndValue(.5);
                    break;
            }
            return true;
        }
    };

//    FragmentTransaction nFragmentTransaction = getFragmentManager().beginTransaction();
//
//    nFragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
//    nFragmentTransaction.replace(R.id.login_frame, fragment_old_user);
//    nFragmentTransaction.addToBackStack(nTag);
//    nFragmentTransaction.commit();

    private class ExampleSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {

            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, .5);
                textview2.setAlpha(mappedValue);

            if (spring.getId().equals(SPRINGID1)){
                textview1.setScaleX(mappedValue);
                textview1.setScaleY(mappedValue);
            }
            else if( spring.getId().equals(SPRINGID2)){
                textview2.setScaleX(mappedValue);
                textview2.setScaleY(mappedValue);
            }

        }
    }

}
