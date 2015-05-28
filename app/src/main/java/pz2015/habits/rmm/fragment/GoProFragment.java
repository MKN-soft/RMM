package pz2015.habits.rmm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pz2015.habits.rmm.R;


public class GoProFragment extends Fragment {

    public GoProFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_go_pro, container, false);

        //adding onclick listeners
        TextView text = (TextView) rootView.findViewById(R.id.txt_goPro);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billing();
            }
        });

        ImageView iView = (ImageView) rootView.findViewById(R.id.image_goPro);
        iView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billing();
            }
        });

        return rootView;
    }

    /**
     * manages micropayments (billing), is called when specific button or textview is pressed.
     * Called by onClick defined in onCreateView
     */
    private void billing() {
        int x = 5; // This is not the code you are looking for. Just for debugging purposes. You can go.
        //TODO: implement in-app billing
    }

}
