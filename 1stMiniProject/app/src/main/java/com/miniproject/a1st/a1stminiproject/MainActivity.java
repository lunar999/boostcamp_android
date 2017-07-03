package com.miniproject.a1st.a1stminiproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    static final int MENU_BOARD_PAGE = 0;
    static final int MENU_FRIENDS_PAGE = 1;
    static final int MENU_NOTIFICATION_PAGE = 2;
    static final int MENU_MORE_PAGE = 3;
    static final int MENU_PAGE_SIZE = 4;

    @BindView(R.id.board_viewpager) ViewPager mMenuPager;
    @BindView(R.id.navigation) BottomNavigationView mNavigation;


    // ViewPager Listener
    private class MenuPagerAdapter extends FragmentPagerAdapter {
        public MenuPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case MENU_BOARD_PAGE: return new BoardFragment();
                case MENU_FRIENDS_PAGE: return new FriendsFragment();
                case MENU_NOTIFICATION_PAGE: return new NotificationFragment();
                case MENU_MORE_PAGE: return new MoreFragment();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return MENU_PAGE_SIZE;
        }
    }

    // BottomNavigationView Listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_board:
                    mMenuPager.setCurrentItem(MENU_BOARD_PAGE);
                    return true;
                case R.id.navigation_friends:
                    mMenuPager.setCurrentItem(MENU_FRIENDS_PAGE);
                    return true;
                case R.id.navigation_notification:
                    mMenuPager.setCurrentItem(MENU_NOTIFICATION_PAGE);
                    return true;
                case R.id.navigation_more:
                    mMenuPager.setCurrentItem(MENU_MORE_PAGE);
                    return true;
            }
            return false;
        }

    };

    // ViewPager 동작 시 BottomNavigationView 변화
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for(int i=0; i<mNavigation.getChildCount(); i++)
                mNavigation.getMenu().getItem(i).setChecked(false);
            mNavigation.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Action Bar -> Search Bar 변경
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams( // Search Bar's WIDTH <- MATCH_PARENT
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        actionBar.setCustomView(LayoutInflater.from(this).inflate(R.layout.searchbar, null), params);
        ButterKnife.bind(this);

        // BottomNavigationView & ViewPager 설정
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mMenuPager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager()));
        mMenuPager.addOnPageChangeListener(mOnPageChangeListener);
        mMenuPager.setCurrentItem(0);
    }



    // Search Bar 의 버튼 클릭
    @OnClick({R.id.searchbar_camera, R.id.searchbar_messenger, R.id.searchbar_info})
    void OnSearchBarButtonClick(View view) {
        switch (view.getId()) {
            case R.id.searchbar_camera :
                Toast.makeText(this, "카메라 클릭함.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.searchbar_messenger :
                Toast.makeText(this, "메신저 클릭함.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.searchbar_info :
                Toast.makeText(this, "회원정보 클릭함.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
