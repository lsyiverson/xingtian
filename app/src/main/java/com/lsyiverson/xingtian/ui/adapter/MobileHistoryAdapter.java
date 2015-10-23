package com.lsyiverson.xingtian.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lsyiverson.xingtian.R;
import com.lsyiverson.xingtian.ui.MobileInfoItemBinding;

public class MobileHistoryAdapter extends RecyclerView.Adapter<MobileHistoryAdapter.MobileHistoryHolder> {
    private final Context context;

    public MobileHistoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MobileHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MobileInfoItemBinding itemBinding = DataBindingUtil.inflate(((Activity) context).getLayoutInflater(),
            R.layout.item_mobile_info, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(MobileHistoryHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MobileHistoryHolder extends RecyclerView.ViewHolder {

        public MobileHistoryHolder(View itemView) {
            super(itemView);
        }
    }
}
