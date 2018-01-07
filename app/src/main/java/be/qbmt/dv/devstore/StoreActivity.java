package be.qbmt.dv.devstore;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import be.qbmt.dv.devstore.db.DataManager;
import be.qbmt.dv.devstore.db.DbHelper;
import be.qbmt.dv.devstore.db.DevStoreDbContract;

public class StoreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,StoreFragmentListener {

    private FrameLayout storeFrame;

    private StoreFragment storeFragment;

    private DevStoreDbContract.User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (currentUser == null) {
            getUserFromDatabase();
        }

        if (storeFragment == null) {
            makeStoreFragment();
        }

    }

    private void getUserFromDatabase() {
        int userId = getSharedPreferences(LoginActivity.PREFS_NAME,MODE_PRIVATE).getInt("userid",-1);
        DbHelper helper = new DbHelper(this);
        DataManager manager = new DataManager(helper);

        try {
            currentUser = manager.getUserById(userId);
            View headerLayout = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0); // 0-index header
            ((TextView)headerLayout.findViewById(R.id.usernameslidemenu)).setText(currentUser.username);

        } catch (DataManager.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void makeStoreFragment() {
        storeFragment = new StoreFragment();
        storeFrame = (FrameLayout)findViewById(R.id.storeframe);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.storeframe,storeFragment, "store").commit();
    }

    public void toStoreFrame() {
        ;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.store, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.overview:
                //Load fragment
                break;
            case R.id.account:
                //Load fragment
                break;
            default:
                throw new RuntimeException("onNavigationItemSelected got passed an illegal ID.");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void storeItemTouched(ProductAdapter.ProductViewHolder vh) {
        //Open detail view
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        DevStoreDbContract.Product p = vh.getProduct();

        args.putString(DetailFragment.Arguments.NAME,p.name);
        args.putString(DetailFragment.Arguments.DESCRIPTION,p.description);
        args.putInt(DetailFragment.Arguments.PRICE,p.price);
        args.putString(DetailFragment.Arguments.IMAGE,p.imageResource);

        detailFragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.storeframe,detailFragment,"detailfragment")
                .addToBackStack("replacedetail")
                .commit();

    }

    public void showBuyDialog(int productId) {

    }


}
