package com.example.trk_5b;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.trk_5b.model.DataItem;
import java.util.List;
public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {

    private Context context;
    private List<DataItem> mahasiswaList;
    private OnItemClickListener onItemClickListener;

    public MahasiswaAdapter(Context context, List<DataItem> mahasiswaList) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false);
        return new MahasiswaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, int position) {
        DataItem mhs = mahasiswaList.get(position);

        // Menampilkan data mhs menggunakan Glide
        Glide.with(context)
                .load(mhs.getGambar())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageViewImg);

        // Menampilkan nama mhs
        holder.textViewTitle.setText(mhs.getNama());
        holder.textViewTitle.setSelected(true);

        // Set listener untuk item klik
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewImg;
        public TextView textViewTitle;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            imageViewImg = itemView.findViewById(R.id.imageViewImg);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }

    // Interface untuk mendeteksi klik item
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
