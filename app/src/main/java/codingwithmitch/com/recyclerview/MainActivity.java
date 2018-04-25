package codingwithmitch.com.recyclerview;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    private  String [] mDrawerTitle = {"Main", "Open", "Note", "Content", "Social", "Album", "History", "Buy","References" , "log out-->"};
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
            case R.id.mnuNew2:
                showNotification();
                return true;
            case R.id.showdialog:
                showdialog();
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

        mImageUrls.add("https://ae01.alicdn.com/kf/HTB1EA4YMFXXXXXxXFXXq6xXFXXX2/100-unids-flor-china-com-n-de-flores-rosas-rojas-semillas-jard-n-de-DIY-patio.jpg");
        mNames.add("Rose");

        mImageUrls.add("http://kaset-lifestyle.com/wp-content/uploads/2017/10/pink_lotus.jpg");
        mNames.add("Lotus");

        mImageUrls.add("http://www.ssimall.com/web/product/big/ssi33p9085_53.jpg");
        mNames.add("Forget me not");

        mImageUrls.add("https://cdn.pixabay.com/photo/2018/04/03/16/13/tulip-3287183_960_720.jpg");
        mNames.add("Tulip");

        mImageUrls.add("http://www.กล้วยไม้สาย4.com/index_files/3016s.jpg");
        mNames.add("Orchid");


        mImageUrls.add("http://mossymairu.appspot.com/images/mali2.jpg");
        mNames.add("Jasmine");

        mImageUrls.add("http://3.bp.blogspot.com/-lP-izIjLtSs/TfKvXbEeTcI/AAAAAAAAAlA/ltrU3AEQ2Eo/s1600/e0b8a5e0b8b1e0b988e0b899e0b897e0b8a1.jpg");
        mNames.add("Plumeria");

        mImageUrls.add("http://2.bp.blogspot.com/-WfpNhCgHOY4/UN7XT3ql5rI/AAAAAAAACaE/U6W_A6CSU3I/s1600/Sunflower7.bmp");
        mNames.add("Sunflower");

        mImageUrls.add("https://i.pinimg.com/originals/a2/7f/3b/a27f3bd3deb8eab7bc3a22809061c027.jpg");
        mNames.add("Spike flower");



        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void showNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/Montira009"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Read Me")
                        .setContentText("Hello Notification !!")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);
    }

    public void showdialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
        final EditText mEmail = (EditText) mView.findViewById(R.id.etEmail);
        final EditText mPassword = (EditText) mView.findViewById(R.id.etPassword);
        Button mLogin = (Button) mView.findViewById(R.id.btnLogin);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mEmail.getText ().toString ().isEmpty () && !mPassword.getText ().toString ().isEmpty ()) {
                    Toast.makeText (MainActivity.this,
                            R.string.success_login_msg,
                            Toast.LENGTH_SHORT).show ();
                    dialog.dismiss ();
                } else {
                    Toast.makeText (MainActivity.this,
                            R.string.error_login_msg,
                            Toast.LENGTH_SHORT).show ();
                }
            }


        });

    }
}

























