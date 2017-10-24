package ae.milch.testrb.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;

import ae.milch.testrb.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);

        getSupportFragmentManager().beginTransaction().add(R.id.content, new SearchFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_cards:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content, new SearchFragment()).commit();
                            return true;
                        case R.id.action_contacts:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content, new FavoritesFragment()).commit();
                            return true;
                    }
                    return false;
                }
            };
}
