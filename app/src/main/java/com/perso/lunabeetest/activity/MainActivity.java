package com.perso.lunabeetest.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.perso.lunabeetest.R;
import com.perso.lunabeetest.adapter.RecyclerAdapter;
import com.perso.lunabeetest.bean.UnsplashCard;
import com.perso.lunabeetest.listener.OnClickReceiverListener;
import com.perso.lunabeetest.listener.OnLoadMoreListener;
import com.perso.lunabeetest.manager.ApiManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<UnsplashCard> currentCards = new ArrayList<>();
    ProgressBar pb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.progressBar2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter(this.recyclerView, currentCards);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setOnClickReceiverListener(new OnClickReceiverListener() {
            @Override
            public void onItemClicked(UnsplashCard card) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("myCard", (Parcelable) card);
                startActivity(intent);
            }
        });


        ApiManager.getInstance().getPhotosByVolley(this, 1, new ApiManager.OnPhotoReceived() {
            @Override
            public void onReceived(List<UnsplashCard> newCards) {
                photoReceivedSuccess(newCards);
            }
        });

        recyclerAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(int current_page) {
                currentCards.add(null);
                recyclerAdapter.notifyItemInserted(currentCards.size() - 1);
                ApiManager.getInstance().getPhotosByVolley(MainActivity.this, current_page, new ApiManager.OnPhotoReceived() {
                    @Override
                    public void onReceived(List<UnsplashCard> newCards) {
                        photoReceivedSuccess(newCards);
                    }
                });

            }
        });
    }

    public void photoReceivedSuccess(List<UnsplashCard> newCards){
        if(currentCards.size()>0){
            currentCards.remove(currentCards.size() - 1);
            recyclerAdapter.notifyItemRemoved(currentCards.size());
        }
        currentCards.addAll(newCards);
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.setLoaded();
        pb.setVisibility(View.GONE);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.grid_button) {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        } else if (id == R.id.linear_button){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        return super.onOptionsItemSelected(item);
    }
}
