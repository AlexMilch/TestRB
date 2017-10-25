package ae.milch.testrb.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ae.milch.testrb.R;
import ae.milch.testrb.ui.favorites.FavoritesFragment;
import ae.milch.testrb.ui.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    public static final String FRAGMENT_SEARCH_TAG = "FRAGMENT_SEARCH_TAG";
    public static final String FRAGMENT_FAVORITES_TAG = "FRAGMENT_FAVORITES_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(getOnNavigationItemSelectedListener());
        navigation.setSelectedItemId(R.id.action_search);
    }

    @NonNull
    private BottomNavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return new
                BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        switch (item.getItemId()) {
                            case R.id.action_search:
                                if (fragmentManager.findFragmentByTag(FRAGMENT_SEARCH_TAG) != null) {
                                    SearchFragment searchFragment = (SearchFragment) fragmentManager.findFragmentByTag(FRAGMENT_SEARCH_TAG);
                                    fragmentManager.beginTransaction().show(searchFragment).commit();
                                    searchFragment.updateList();
                                } else {
                                    fragmentManager.beginTransaction().add(R.id.content, new SearchFragment(), FRAGMENT_SEARCH_TAG).commit();
                                }
                                if (fragmentManager.findFragmentByTag(FRAGMENT_FAVORITES_TAG) != null) {
                                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(FRAGMENT_FAVORITES_TAG)).commit();
                                }
                                break;
                            case R.id.action_favorites:
                                if (fragmentManager.findFragmentByTag(FRAGMENT_FAVORITES_TAG) != null) {
                                    FavoritesFragment favoriteFragment = (FavoritesFragment) fragmentManager.findFragmentByTag(FRAGMENT_FAVORITES_TAG);
                                    fragmentManager.beginTransaction().show(favoriteFragment).commit();
                                    favoriteFragment.updateList();
                                } else {
                                    fragmentManager.beginTransaction().add(R.id.content, new FavoritesFragment(), FRAGMENT_FAVORITES_TAG).commit();
                                }
                                if (fragmentManager.findFragmentByTag(FRAGMENT_SEARCH_TAG) != null) {
                                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(FRAGMENT_SEARCH_TAG)).commit();
                                }
                                break;
                        }
                        return true;
                    }
                };
    }
}
