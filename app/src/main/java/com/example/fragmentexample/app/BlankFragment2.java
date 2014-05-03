package com.example.fragmentexample.app;



import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class BlankFragment2 extends Fragment {


    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);

        FrameLayout frame = (FrameLayout) view.findViewById(R.id.fragment2);

        frame.setOnClickListener(viewListener);

        return view;
    }

    View.OnClickListener viewListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            Fragment fragment = new BlankFragment1();
            String nTag = fragment.getTag();

            // We replace the fragment
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.addToBackStack(nTag);
            fragmentTransaction.commit();
        }
    };


}
