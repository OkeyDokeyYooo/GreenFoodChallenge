package co3.greenfoodchallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import co3.greenfoodchallenge.R;
import co3.greenfoodchallenge.adapter.SlideAdapter;

public class SwapActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private SlideAdapter mSlideAdapter;
    private TextView[] mDots;

    private LinearLayout mDotLayout;
    private Button mNextButton;
    private Button mPrevButton;

    private int mCurrentPage;

    SharedPreferences mSharedPreferences;
    Boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);

        mSharedPreferences = getSharedPreferences("isFirstTime", MODE_PRIVATE);

        isFirstTime = mSharedPreferences.getBoolean("isFirstTime",true);

        if(isFirstTime){
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            isFirstTime = false;
            editor.putBoolean("isFirstTime", isFirstTime);
            editor.apply();

            mViewPager = (ViewPager) findViewById(R.id.viewpager);
            mSlideAdapter = new SlideAdapter(this);
            mDotLayout = (LinearLayout) findViewById(R.id.dotLayout);
            mNextButton = (Button) findViewById(R.id.next_button);
            mPrevButton = (Button) findViewById(R.id.prv_button);
            mViewPager.setAdapter(mSlideAdapter);

            addDotsIndicator(0);

            mViewPager.addOnPageChangeListener(viewListener);

            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(mCurrentPage + 1);
                }
            });
            mPrevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(mCurrentPage - 1);
                }
            });
        } else {
            Intent intent = LogInActivity.makeIntent(SwapActivity.this);
            startActivity(intent);
            finish();
        }


    }


    public void addDotsIndicator(int position) {
        mDots = new TextView[4];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0 ){
            mDots[position].setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;

            if(i == 0){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(false);
                mPrevButton.setVisibility(View.INVISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("");

            }else if (i == mDots.length - 1){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Finish");
                mPrevButton.setText("Back");

                mNextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent intent = LogInActivity.makeIntent(SwapActivity.this);
                    startActivity(intent);
                    finish();
                    }
                });
            }else {
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public static Intent makeIntent(Context context){
        return new Intent(context,SwapActivity.class);
    }

}
