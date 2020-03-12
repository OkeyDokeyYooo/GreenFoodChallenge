package co3.greenfoodchallenge;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class MyFragmentNaviBar extends Fragment {

    // Rewrite a new onCreateView() method for navigatiob bar.

    private String content;
    public MyFragmentNaviBar(String content) {
        this.content = content;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fg_content_navi_bar,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText(content);
        return view;
    }


}
