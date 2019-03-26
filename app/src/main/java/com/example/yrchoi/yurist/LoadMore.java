package com.example.yrchoi.yurist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.yrchoi.yurist.LoadMoreListener.PostRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LoadMore extends AppCompatActivity {

    ArrayList<HashMap<String,String>> getDatalist;
    private RecyclerView mrecyclerView;
    PostRecyclerAdapter mAdapter;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);

        random = new Random();

        getDatalist = new ArrayList<>();
        for(int aind = 0 ; aind < 20; aind++){
            HashMap<String,String> map = new HashMap<>();
            map.put("KEY_EMAIL","android" + aind + "@gmail.com");
            map.put("KEY_PHONE",phoneNumberGenerating());
            getDatalist.add(map);
        }

        mrecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(LoadMore.this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new PostRecyclerAdapter(this, getDatalist, mrecyclerView);
        mrecyclerView.setAdapter(mAdapter);

        // set RecyclerView on item click listner
        mAdapter.setOnItemListener(new PostRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HashMap<String, String> item) {
                String mEmail = "";
                String mPhone = "";
                try{
                    mEmail = item.get("KEY_EMAIL");
                    mPhone = item.get("KEY_PHONE");
                }catch (Exception ev){
                    System.out.print(ev.getMessage());
                }
                Toast.makeText(LoadMore.this,"Clicked row: \nEmail: "+mEmail+", Phone: "+mPhone,Toast.LENGTH_LONG).show();
            }
        });


        //set load more listener for the RecyclerView adapter
        mAdapter.setOnLoadMoreListener(new PostRecyclerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (getDatalist.size() <= 40) {
                    getDatalist.add(null);
                    mAdapter.notifyItemInserted(getDatalist.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getDatalist.remove(getDatalist.size() - 1);
                            mAdapter.notifyItemRemoved(getDatalist.size());

                            //Generating more data
                            int index = getDatalist.size();
                            int end = index + 20;
                            for (int i = index; i < end; i++) {
                                HashMap<String,String> map = new HashMap<>();
                                map.put("KEY_EMAIL","android" + i + "@gmail.com");
                                map.put("KEY_PHONE",phoneNumberGenerating());
                                getDatalist.add(map);
                            }
                            mAdapter.notifyDataSetChanged();
                            mAdapter.setLoaded();
                        }
                    }, 2000);
                } else {
                    Toast.makeText(LoadMore.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String phoneNumberGenerating() {
        int low = 100000000;
        int high = 999999999;
        int randomNumber = random.nextInt(high - low) + low;

        return "0" + randomNumber;
    }
}
