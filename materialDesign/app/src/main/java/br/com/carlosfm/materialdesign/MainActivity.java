package br.com.carlosfm.materialdesign;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.carlosfm.materialdesign.Http.AppHttp;
import br.com.carlosfm.materialdesign.adapter.CategoryAdapter;
import br.com.carlosfm.materialdesign.model.Category;


public class MainActivity extends ActionBarActivity
    implements CategoryAdapter.onClickInCategoryListener{

    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mLayoutManager = new LinearLayoutManager(this);
        } else {
            mLayoutManager = new GridLayoutManager(this, 2);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if(AppHttp.hasConect(this) == false){
            Toast.makeText(this, "Sem conexão com internet", Toast.LENGTH_LONG).show();
        }

        new CategoryTask().execute();

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(){
                // Tratar opçÕes de menu aqui
                return true;
            }
        });

        toolbar.setTitle(R.string.app_name);
        toolbar.setElevation(20);
        toolbar.inflateMenu(R.menu.menu_main);
        */

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Toast.makeText(MainActivity.this, "action:" + item, Toast.LENGTH_LONG);

        switch (item.getItemId()) {
            case R.id.action_home:
                Log.i("MENU", "onOptionsItemSelected - action_home");
                return true;
            case R.id.action_infos:
                Log.i("MENU", "onOptionsItemSelected - action_infos");
                return true;
            case R.id.action_delivery:
                Log.i("MENU", "onOptionsItemSelected - action_delivery");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClickInCategory(View v, int position, Category category) {
        Log.i("APPGUIA", "onClickInCategory - position:" + position);
    }

    class CategoryTask extends AsyncTask<Void, Void, List<Category>>{

        @Override
        protected List<Category> doInBackground(Void... strings){
            return Category.searchCatagoryJson();
        }

        @Override
        protected void onPostExecute(List<Category> categoryList){

            super.onPostExecute(categoryList);
            Log.i("APPGUIA", "categoryList - " + categoryList);
            if(categoryList != null){
                mAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                mAdapter.setOnClickInCategoryListener(MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                Toast.makeText(MainActivity.this, "Não foi possível objter as Categorias", Toast.LENGTH_LONG).show();
            }
        }
    }
}
