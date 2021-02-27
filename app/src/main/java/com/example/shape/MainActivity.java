package com.example.shape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<CleanModel> cleanModels;
    private CleanAdapter cleanAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vp5);
        tabLayout = findViewById(R.id.tabLayout2);
        cleanModels = new ArrayList<>();
        cleanModels.add(new CleanModel(R.drawable.shape_test));
        cleanModels.add(new CleanModel(R.drawable.shape_test));
        cleanModels.add(new CleanModel(R.drawable.shape_test));
        cleanModels.add(new CleanModel(R.drawable.shape_test));
        cleanAdapter = new CleanAdapter(cleanModels, getApplicationContext());
        viewPager.setAdapter(cleanAdapter);
        viewPager.setPageTransformer(true, new CustomPageTransformer());
        tabLayout.setupWithViewPager(viewPager);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == cleanModels.size() - 1) {
                    doVisibilityOperation();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

     }

    private void doVisibilityOperation() {
          //skipButton.setVisibility(View.INVISIBLE);
    }

    class CleanModel {
        private int image;

        public CleanModel() {
        }

        public CleanModel(int image) {
            this.image = image;
         }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }


    }

    class CleanAdapter extends PagerAdapter {

        private List<CleanModel> cleanModelList;
        private Context context;

        public CleanAdapter(List<CleanModel> cleanModelList, Context context) {
            this.cleanModelList = cleanModelList;
            this.context = context;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layoutScreen2 = layoutInflater.inflate(R.layout.list, null);

            ImageView imageView = layoutScreen2.findViewById(R.id.wizImage2);
              imageView.setImageResource(cleanModelList.get(position).getImage());
            container.addView(layoutScreen2);
            return layoutScreen2;
        }

        @Override
        public int getCount() {
            return cleanModelList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    class CustomPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull View page, float position) {
            if (position <= 1) {
                page.setAlpha(1);
                page.setTranslationY(-180 * position);
            } else if (position < -1) {
                page.setAlpha(0f);
            } else {
                page.setAlpha(0f);
            }
        }
    }
}
