package com.example.carrental.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.carrental.R;
import com.example.carrental.ui.main.fragment.ChooseCategoryFragment;
import com.example.carrental.ui.main.fragment.navigation.AboutFragment;
import com.example.carrental.ui.main.fragment.navigation.FavoriteListFragment;
import com.example.carrental.ui.main.fragment.navigation.HistoryFragment;
import com.example.carrental.ui.main.fragment.navigation.MyBookingFragment;
import com.example.carrental.ui.main.fragment.navigation.PrivacyPolicyFragment;
import com.example.carrental.ui.main.fragment.navigation.ProfileFragment;
import com.example.carrental.ui.main.fragment.navigation.SettingsFragment;
import com.example.carrental.utility.SessionManager;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class NavControllerActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, NavigationView.OnNavigationItemSelectedListener {

    /*
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeListAdapter homeListAdapter;
    private HomeFragment homeFragment;
    private ArrayList<HomeListItem> homeListItemArrayList;

    ActivityHomePageBinding activityHomePageBinding;
    */
    final int HOME = R.id.nav_home;
    final int FAVORITE_LIST = R.id.nav_favorite;
    final int MY_BOOKING = R.id.nav_my_booking;
    final int ACCOUNT = R.id.nav_view_account;
    final int LOG_OUT = R.id.nav_log_out;
    final int SETTINGS = R.id.nav_settings;
    final int PRIVACY_POLICY = R.id.nav_privacy;
    final int ABOUT = R.id.nav_about;
    final int CONTACT_US = R.id.nav_cont_us;
    final int HISTORY = R.id.nav_history;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    FrameLayout container;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBar actBar;
    FragmentManager fragmentManager;
    TextView headerSignUp;
    TextView headerSignIn;
    ImageView headerImage;

    boolean toolBarNavigationListenerIsRegistered = false;
    int currentNavigationDrawerItem = 0;

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
*/

    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Log.d("home", "action bar clicked");
            return true;
        } else {
            Log.d("home", "action bar clicked");
            return super.onOptionsItemSelected(item);
        }
    }*/
    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Log.d("opts", item + "opt home");
            return true;}
        Log.d("opts", item + "opt clicked");
        return super.onOptionsItemSelected(item);
    }
    */


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case HOME:
                if (currentNavigationDrawerItem != 0) {
                    fragmentManager.popBackStackImmediate();
                    currentNavigationDrawerItem = 0;
                    fragmentManager.beginTransaction()
                            .replace(R.id.navContent_frameLayout_container, new ChooseCategoryFragment()).commit();
                }
                break;
            case FAVORITE_LIST:
                if (!SessionManager.getInstance(NavControllerActivity.this).isLoggedIn()) {
                    Intent intent = new Intent(NavControllerActivity.this, EntryPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //overridePendingTransition(0, 0);
                    startActivity(intent);
                    //overridePendingTransition(0, 0);
                    getSupportFragmentManager().popBackStack();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                } else {
                    if (currentNavigationDrawerItem != 1) {
                        currentNavigationDrawerItem = 1;
                        fragmentManager.beginTransaction()
                                .replace(R.id.navContent_frameLayout_container, new FavoriteListFragment()).addToBackStack(null).commit();
                    }
                }
                break;
            case HISTORY:
                if (!SessionManager.getInstance(NavControllerActivity.this).isLoggedIn()) {
                    Intent intent = new Intent(NavControllerActivity.this, EntryPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //overridePendingTransition(0, 0);
                    startActivity(intent);
                    //overridePendingTransition(0, 0);
                    getSupportFragmentManager().popBackStack();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
                else {
                    if (currentNavigationDrawerItem != 2) {
                        currentNavigationDrawerItem = 2;
                        fragmentManager.beginTransaction()
                                .replace(R.id.navContent_frameLayout_container, new HistoryFragment()).addToBackStack(null).commit();
                    }
                }
                break;

            case MY_BOOKING:
                if (!SessionManager.getInstance(NavControllerActivity.this).isLoggedIn()) {
                    Intent intent = new Intent(NavControllerActivity.this, EntryPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //overridePendingTransition(0, 0);
                    startActivity(intent);
                    //overridePendingTransition(0, 0);
                    getSupportFragmentManager().popBackStack();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                } else {
                    if (currentNavigationDrawerItem != 3) {
                        currentNavigationDrawerItem = 3;
                        fragmentManager.beginTransaction()
                                .replace(R.id.navContent_frameLayout_container, new MyBookingFragment()).addToBackStack(null).commit();
                    }
                }
                break;

            case ACCOUNT:
                if (!SessionManager.getInstance(NavControllerActivity.this).isLoggedIn()) {
                    Intent intent = new Intent(NavControllerActivity.this, EntryPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //overridePendingTransition(0, 0);
                    startActivity(intent);
                    //overridePendingTransition(0, 0);
                    getSupportFragmentManager().popBackStack();
                } else {
                    if (currentNavigationDrawerItem != 4) {
                        currentNavigationDrawerItem = 4;
                        fragmentManager.beginTransaction()
                                .replace(R.id.navContent_frameLayout_container, new ProfileFragment()).addToBackStack(null).commit();
                    }
                }
                break;

            case LOG_OUT:
                if (currentNavigationDrawerItem != 5) {
                    currentNavigationDrawerItem = 5;
                    if (SessionManager.getInstance(NavControllerActivity.this).isLoggedIn()) {
                        SessionManager.getInstance(NavControllerActivity.this).removeLoginSession();
                        Intent intent = new Intent(NavControllerActivity.this, MainActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                        startActivity(intent);
                        finish();
                    }
                }
                break;

            case SETTINGS:
                if (currentNavigationDrawerItem != 6) {
                    currentNavigationDrawerItem = 6;
                    fragmentManager.beginTransaction()
                            .replace(R.id.navContent_frameLayout_container, new SettingsFragment()).addToBackStack(null).commit();
                }
                break;

            case CONTACT_US:
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                /* Fill it with Data */
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"ehabmohammed296@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Type title of your Message here...");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Type your Message here...");

                this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                getSupportFragmentManager().popBackStack();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
                //break;

            case PRIVACY_POLICY:
                if (currentNavigationDrawerItem != 8) {
                    currentNavigationDrawerItem = 8;
                    fragmentManager.beginTransaction()
                            .replace(R.id.navContent_frameLayout_container, new PrivacyPolicyFragment()).addToBackStack(null).commit();
                }
                break;

            case ABOUT:
                if (currentNavigationDrawerItem != 9) {
                    currentNavigationDrawerItem = 9;
                    fragmentManager.beginTransaction()
                            .replace(R.id.navContent_frameLayout_container, new AboutFragment()).addToBackStack("about").commit();
                }
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        Objects.requireNonNull(actBar).setTitle(item.getTitle());
        return true;

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            //navigationView.setCheckedItem(R.id.nav_home);
            //Objects.requireNonNull(getSupportActionBar()).setTitle("Choose your Vehicle");
            currentNavigationDrawerItem = 0;
            //MenuItem menuItem =navigationView.getMenu().getItem(currentNavigationDrawerItem).setChecked(true);
            onNavigationItemSelected(navigationView.getMenu().getItem(currentNavigationDrawerItem).setChecked(true));
        }
        if (getSupportFragmentManager().findFragmentById(R.id.navContent_frameLayout_container) instanceof ChooseCategoryFragment) {
            getSupportFragmentManager().popBackStackImmediate();
            super.onBackPressed();
            //finish();
        }
        super.onBackPressed();

    }

    @Override
    public void onBackStackChanged() {
    /*
        if (getSupportFragmentManager().findFragmentById(R.id.navContent_frameLayout_container) instanceof HomeFragment) {
            //actBar.setDisplayHomeAsUpEnabled(false);
            drawerToggle.setDrawerIndicatorEnabled(true);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            //drawerToggle.setToolbarNavigationClickListener(null);
            Log.d("test", "ins");
        } else {
            //actBar.setDisplayHomeAsUpEnabled(true);
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    */
        enableHomeUpOrHamburger();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activityHomePageBinding=ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_nav_controller);
        fragmentManager = getSupportFragmentManager();

        drawerLayout = findViewById(R.id.drawerBase_drawerLayout);
        container = findViewById(R.id.navContent_frameLayout_container);

        navigationView = findViewById(R.id.baseDrawer_navView);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        headerSignUp=headerView.findViewById(R.id.navHeader_txtView_title);
        headerSignIn=headerView.findViewById(R.id.navHeader_txtView_subTitle);
        headerImage=headerView.findViewById(R.id.navHeader_imageView);
        editNavItem();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actBar = getSupportActionBar();

        assert actBar != null : "ActBar is null";
        actBar.setDisplayHomeAsUpEnabled(true);
        //actBar.setHomeButtonEnabled(true);
        //Objects.requireNonNull(actBar).setTitle("Choose your Vehicle");

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        //actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        //actBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back);
        //invalidateOptionsMenu();
        //drawerToggle.syncState();
        fragmentManager.addOnBackStackChangedListener(this);
        enableHomeUpOrHamburger();


        if (savedInstanceState == null) {
            //fragmentManager.beginTransaction().replace(R.id.navContent_frameLayout_container, new HomeFragment()).commit();
            //navigationView.setCheckedItem(R.id.nav_home);
            currentNavigationDrawerItem = -1;
            MenuItem menuItem = navigationView.getMenu().getItem(0).setChecked(true);
            onNavigationItemSelected(menuItem);

        }

        //Bundle bundle2=getIntent().getExtras();
        //if(bundle2 !=null && bundle2.getString("user").equals("admin")) {
        //String test=bundle2.getString("aEmail");
        //Objects.requireNonNull(getSupportActionBar()).setTitle("Administrator (DEV)");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        //}
        //else
        //Objects.requireNonNull(getSupportActionBar()).setTitle("Choose your Vehicle");

        /*
        recyclerView = findViewById(R.id.homePage_recyclerView_main);
        homeListItemArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);

        //=======================================RV SETUP=======================================
        homeListAdapter = new HomeListAdapter(homeListItemArrayList, (id, homeListItem) -> {

            //======================================DEBUG=======================================
            //Log.e("click","HomePage onItemClick");
            //Log.e("ReceivedData",homeListItem.getCarModel());
            //======================================DEBUG=======================================

            //====================================SEND DATA=====================================
            Intent intent=new Intent(HomePageActivity.this, BookingActivity.class);
            intent.putExtra("listItemObject",homeListItem);
            intent.putExtra("id",id);
            startActivity(intent);
            //====================================SEND DATA=====================================
        });
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.setLayoutManager(layoutManager);
        //performance
        //homeListAdapter.setHasStableIds(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setNestedScrollingEnabled(true);
        //=======================================RV SETUP=======================================



        //======================================DUMMY DATA======================================
        HomeListItem homeListItem = new HomeListItem();
        HomeListItem homeListItem2 = new HomeListItem();

        homeListItem.setCarModel("Nissan Sunny");
        //String[] book = {"150 Km/period", "Min 1 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem.setBookDetails(book);
        homeListItem.setCompanyName("Rent Me");
        homeListItem.setPrice(600);
        homeListItem.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        String[] spec={"Silver","4","5","Automatic"};
        homeListItem.setSpecs(spec);
        homeListItem.setCarImg(R.drawable.img_logo_test);
        homeListItem.setCompanyAddress("Cairo,Egypt");

        homeListItem2.setCarModel("Peugeot 3008");
        //String[] book2 = {"150 Km/period", "Min 2 Days", "No Extra Fees", "Insurance Coverage"};
        //homeListItem2.setBookDetails(book2);
        homeListItem2.setCompanyName("1st for Rent");
        homeListItem2.setPrice(1200);
        String[] spec2={"Blue","4","5","Automatic"};
        homeListItem2.setSpecs(spec2);
        homeListItem2.setPriceLabel(PriceLabel.EGYPTIAN_POUND);
        homeListItem2.setCarImg(R.drawable.ic_car_default_black);
        homeListItem2.setCompanyAddress("Alex,Egypt");

        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem2);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem);
        homeListItemArrayList.add(homeListItem2);
        //======================================DUMMY DATA======================================
        */

        headerOnClick(headerSignUp);
        headerOnClick(headerSignIn);
    }

    private void headerOnClick(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v== headerSignIn|| v==headerSignUp) {
                        MenuItem menuItem = navigationView.getMenu().findItem(ACCOUNT);
                        onNavigationItemSelected(menuItem);
                }
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    /*
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    */


    private void enableHomeUpOrHamburger() {
        boolean hamburgerBtn = (getSupportFragmentManager().getBackStackEntryCount() == 0);
        if (hamburgerBtn) {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            drawerToggle.setDrawerIndicatorEnabled(true);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            drawerToggle.setToolbarNavigationClickListener(null);
            toolBarNavigationListenerIsRegistered = false;
        } else {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            if (!toolBarNavigationListenerIsRegistered) {
                drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                toolBarNavigationListenerIsRegistered = true;
            }
        }
    }


    private void editNavItem() {
        Menu navMenu = navigationView.getMenu();
        if (!SessionManager.getInstance(NavControllerActivity.this).isLoggedIn()) {
            navMenu.findItem(R.id.nav_log_out).setVisible(false);
            navMenu.findItem(R.id.nav_view_account).setVisible(false);
        } else {
            navMenu.findItem(R.id.nav_log_out).setVisible(true);
            navMenu.findItem(R.id.nav_view_account).setVisible(true);
            headerSignIn.setVisibility(View.INVISIBLE);
            headerSignUp.setText(SessionManager.getInstance(NavControllerActivity.this).getLoginSession().getFirstName());
        }
    }
}


