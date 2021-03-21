package id.ac.umn.projectuts.MusicPlayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import id.ac.umn.projectuts.R;

public class MusicPlayerAdapter extends RecyclerView.Adapter<MusicPlayerAdapter.ItemMusicViewHolder>{
    private LinkedList<MusicListExample> mDaftarMusik;
    private LayoutInflater mInflater;
    private Context mContext;
    public MusicPlayerAdapter(Context context, LinkedList<MusicListExample> daftarMusik){
        this.mContext = context;
        this.mDaftarMusik = daftarMusik;
        this.mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ItemMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.music_list_layout,
                parent , false);
        return new ItemMusicViewHolder(view, this);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemMusicViewHolder holder,
                                 int position) {
        MusicListExample mSumberMusik = mDaftarMusik.get(position);
        holder.tvJudul.setText(mSumberMusik.getJudul());
    }
    public int getItemCount() {
        return mDaftarMusik.size();
    }
    class ItemMusicViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView tvJudul;
        private TextView tvUri;
        private MusicPlayerAdapter mAdapter;
        private int mPosisi;
        private MusicListExample mSumberMusik;
        public ItemMusicViewHolder(@NonNull View itemView,
                                   MusicPlayerAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            mSumberMusik = mDaftarMusik.get(mPosisi);
            Intent detilInten = new Intent(mContext, MusicPlayerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("DetilMusik", mDaftarMusik);
            bundle.putSerializable("Posisi", mPosisi);
            detilInten.putExtras(bundle);
            mContext.startActivity(detilInten);
        }
    }
}
