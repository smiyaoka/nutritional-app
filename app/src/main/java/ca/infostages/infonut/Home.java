package ca.infostages.infonut;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

/**
 * Represents the container of all user navigation related tasks.
 */
public class Home extends AppCompatActivity {

    private static final String TAG_NUTRIENT_DIALOG = "NUTRIENT_DIALOG";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return loadFragment(HomeFragment.newInstance());
                case R.id.navigation_camera:
                    intent = new Intent(Home.this, BarcodeReader.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_plans:
                    return loadFragment(ChoosePlanFragment.newInstance());
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Sets the home fragment as the current view.
        loadFragment(HomeFragment.newInstance());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Replaces the content of the current fragment with a new one.
     * @param fragment - a fragment that has been selected through the bottom navigation.
     * @return a boolean indicating success or failure.
     */
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    /**
     * Opens the MakePlanFragment upon button press from the ChoosePlanFragment
     * @param view - view
     */
    public void makePlan(View view) {
        loadFragment(MakePlanFragment.newInstance());
    }

    /**
     * Shows a dialog which has a list of available nutrients that users can pick from.
     * @param view - view
     */
    public void addNutrientOrIngredient(View view) {
        DialogFragment dialogFragment = new NutrientDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), TAG_NUTRIENT_DIALOG);
    }
}
