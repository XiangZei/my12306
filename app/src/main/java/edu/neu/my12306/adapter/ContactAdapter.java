package edu.neu.my12306.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.neu.my12306.entity.UserEntity;
import com.example.my12306.R;

import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * Created by YoKey on 16/10/8.
 */
public class ContactAdapter extends IndexableAdapter<UserEntity> {
    private LayoutInflater mInflater;

    public ContactAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        //渲染title
        View view = mInflater.inflate(R.layout.item_index_contact, parent, false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        //渲染内容
        View view = mInflater.inflate(R.layout.item_contact, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        //绑定viewholder
        IndexVH vh = (IndexVH) holder;
        vh.tv.setText(indexTitle);
    }

    @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, UserEntity entity) {
        //绑定内容viewholder
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(entity.getNick());
        vh.tvMobile.setText(entity.getMobile());
    }

    private class IndexVH extends RecyclerView.ViewHolder {
        TextView tv;

        public IndexVH(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_index);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView tvName, tvMobile;

        public ContentVH(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvMobile = (TextView) itemView.findViewById(R.id.tv_mobile);
        }
    }
}
