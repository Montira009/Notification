package codingwithmitch.com.recyclerview;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    private  String [] mDrawerTitle = {"Home", "About", "Reference", "Detail","Log out"};
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView mListView;
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
        mDrawerLayout = findViewById(R.id.drawer_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
//                mDrawerLayout,
//                R.string.open_drawer,
//                R.string.close_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = findViewById(R.id.drawer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
                android.R.layout.simple_list_item_1,mDrawerTitle );
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) mListView.getItemAtPosition(position);

                mDrawerLayout.closeDrawers();
                Toast.makeText(getApplicationContext(),
                        "Go" + " to " + itemValue + " ...... ", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId()) {
            case R.id.mnuNew:
                Toast.makeText(this, "New Profile!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnuOpen:
                Toast.makeText(this, "UEFA Champions League!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnuHelp:
                Toast.makeText(this, "Help!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://vignette.wikia.nocookie.net/dbz-dokkanbattle/images/0/0f/24_Hours_Resurrection_Super_Saiyan_2_Goku_%28Angel%29.png/revision/latest?cb=20160825141919");
        mNames.add("Goku");

        mImageUrls.add("https://pre00.deviantart.net/0f1c/th/pre/i/2018/015/7/6/super_vegeta_ssj_blue_by_saodvd-dc044sk.png");
        mNames.add("Vegeta");

        mImageUrls.add("https://pre00.deviantart.net/666e/th/pre/i/2017/332/4/1/la_wea_golden_freezer__v_by_fradayesmarkers-dbsmyca.png");
        mNames.add("Freezer");

        mImageUrls.add("https://pre00.deviantart.net/b9d8/th/pre/i/2018/049/2/6/numero_18_estilo_dicasty_by_dicasty1-dc3lfsd.jpg");
        mNames.add("Android 18");

        mImageUrls.add("https://i.pinimg.com/originals/27/ac/3e/27ac3e5aa9c8b07d054c00946b9eb3a1.png");
        mNames.add("Gohan");


        mImageUrls.add("https://vignette.wikia.nocookie.net/dbz-dokkanbattle/images/3/38/False_SSB_Trunks.jpg/revision/latest?cb=20161220052756");
        mNames.add("Trunks");

        mImageUrls.add("https://i.pinimg.com/originals/30/bc/62/30bc62c430e7e79de8db84daa016487f.jpg");
        mNames.add("Caulifla ");

        mImageUrls.add("https://vignette.wikia.nocookie.net/dbz-dokkanbattle/images/3/3a/Card_1002400_bg.png/revision/latest?cb=20160907124733");
        mNames.add("Gotenks");

        mImageUrls.add("http://images6.fanpop.com/image/polls/1284000/1284712_1380361818904_full.png?v=1380361829");
        mNames.add("Bills");



        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}






















