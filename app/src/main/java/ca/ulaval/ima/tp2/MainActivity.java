package ca.ulaval.ima.tp2;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mydrawer;
    private ActionBarDrawerToggle myToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydrawer = (DrawerLayout)findViewById(R.id.drawer);

        myToggle = new ActionBarDrawerToggle(this,mydrawer,R.string.open,R.string.close);
        mydrawer.addDrawerListener(myToggle);
        myToggle.syncState();

        NavigationView nav = findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenu,new apropos()).commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nav);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(myToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectIterDrawer(MenuItem menuItem) {
        Fragment myFragment = null;
        Class frag;
        switch (menuItem.getItemId()){
            case R.id.statutnetwork:
                frag = statutnetwork.class;
                break;
            case R.id.apropos:
                frag = apropos.class;
                break;
            case R.id.form:
                frag = formulaire.class;
                break;
            default:
                frag = Description.class;
                break;
        }
        try{
            myFragment = (Fragment) frag.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        FragmentManager frgmng = getSupportFragmentManager();
        frgmng.beginTransaction().replace(R.id.contenu,myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mydrawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectIterDrawer(menuItem);
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        selectIterDrawer(menuItem);
        return true;
    }


}
