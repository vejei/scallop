package io.github.zeleven.scallop.ui.modules.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.ui.base.BaseActivity;
import io.github.zeleven.scallop.ui.modules.main.home.HomeFragment;
import io.github.zeleven.scallop.ui.modules.main.more.MoreFragment;
import io.github.zeleven.scallop.ui.modules.main.pictures.PicturesFragment;
import io.github.zeleven.scallop.ui.modules.main.xiandu.XianDuFragment;
import io.github.zeleven.scallop.ui.modules.search.SearchActivity;

public class MainActivity extends BaseActivity implements MainContract.View {
    @Inject MainPresenter presenter;

    @BindView(R.id.bottom_nav) BottomNavigationViewEx bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 依赖注入
        activityComponent.inject(this);

        if (presenter != null) {
            presenter.attachView(this);
        }

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        bottomNav.enableAnimation(false);
        bottomNav.enableShiftingMode(false);
        bottomNav.enableItemShiftingMode(false);
        bottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                boolean enableElevation = false;
                switch (item.getItemId()) {
                    case R.id.home:
                        enableElevation = false;
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.xiandu:
                        enableElevation = false;
                        selectedFragment = new XianDuFragment();
                        break;
                    case R.id.pictures:
                        enableElevation = true;
                        selectedFragment = new PicturesFragment();
                        break;
                    case R.id.more:
                        enableElevation = true;
                        selectedFragment = new MoreFragment();
                        break;
                }
                enableAppBarElevation(enableElevation);
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }
                return true;

            }
        });
        bottomNav.setCurrentItem(0);
    }
     @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
