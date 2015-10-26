package com.lsyiverson.xingtian.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lsyiverson.xingtian.R;
import com.lsyiverson.xingtian.ui.MobileInfoItemBinding;
import com.lsyiverson.xingtian.ui.model.MobileItemModel;

import java.util.List;

public class MobileHistoryAdapter extends RecyclerView.Adapter<MobileHistoryAdapter.MobileHistoryHolder> {
    private final Context context;

    private List<MobileItemModel> models;

    public MobileHistoryAdapter(Context context, List<MobileItemModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public MobileHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MobileInfoItemBinding itemBinding = DataBindingUtil.inflate(((Activity) context).getLayoutInflater(),
            R.layout.item_mobile_info, parent, false);
        MobileHistoryHolder holder = new MobileHistoryHolder(itemBinding.getRoot());
        holder.setBinding(itemBinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(MobileHistoryHolder holder, int position) {
        holder.getBinding().setMobileItemModel(models.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MobileHistoryHolder extends RecyclerView.ViewHolder {

        private MobileInfoItemBinding binding;

        public MobileHistoryHolder(View itemView) {
            super(itemView);
        }

        public MobileInfoItemBinding getBinding() {
            return binding;
        }

        public void setBinding(MobileInfoItemBinding binding) {
            this.binding = binding;
        }
    }
}
