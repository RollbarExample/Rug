package rug.demo.psq.demorug;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView mainImageIV,raplicaImageIV;
    private Context context;
    private ArrayList<Integer> imageList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupScreent();
    }

    private void setupScreent(){
        context=this;
        mainImageIV=(ImageView)findViewById(R.id.mainImageIV) ;
        raplicaImageIV=(ImageView)findViewById(R.id.raplicaImageIV) ;
        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        prepareImageList();
        refreshList(imageList);

    }

    private void prepareImageList(){
        imageList.add(R.drawable.item1);
        imageList.add(R.drawable.item2);
    }
    private void refreshList(ArrayList<Integer> list) {

        CustomAdapter   adapter = new CustomAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void refreshImage(int index){
        switch (index){
            case 0:
                raplicaImageIV.setBackgroundResource(R.drawable.mainimage2);
                break;
            case 1:
                raplicaImageIV.setBackgroundResource(R.drawable.mainimage1);
                break;
        }

    }
    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        private ArrayList<Integer> mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout familyItemLL;
            private ImageView carpetIV;


            public ViewHolder(View v) {
                super(v);
                carpetIV = (ImageView) v.findViewById(R.id.carpetIV);
                familyItemLL=(LinearLayout)v.findViewById(R.id.familyItemLL);
            }
        }


        public void add(int position, Integer item) {
            mDataset.add(position, item);
            notifyItemInserted(position);
        }

        public void remove(String item) {
            int position = mDataset.indexOf(item);
            mDataset.remove(position);
            notifyItemRemoved(position);
        }

        public void updateData(ArrayList<Integer> itemList) {
            mDataset.clear();
            mDataset.addAll(itemList);
            notifyDataSetChanged();
        }

        public CustomAdapter(ArrayList<Integer> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carpet_layout_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
          Integer imgId=mDataset.get(position);
          holder.carpetIV.setImageResource(imgId);
          holder.familyItemLL.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                refreshImage(position);
              }
          });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}
