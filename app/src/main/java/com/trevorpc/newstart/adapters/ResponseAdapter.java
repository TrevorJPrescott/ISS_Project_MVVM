package com.trevorpc.newstart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trevorpc.newstart.R;
import com.trevorpc.newstart.model.object.Response;

import java.util.Date;
import java.util.List;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseViewHolder> {

    private Context context;
    private List<Response> responseList;


    public ResponseAdapter(Context context, List<Response> responseList) {
        this.context = context;
        this.responseList = responseList;


    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public ResponseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.response_layout,viewGroup,false);
        return new ResponseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseViewHolder holder, int position) {
        if(responseList != null)
        {

            // this is awkward place for data manipulation but I would have to create a new object to
            // pass it into the Response Adapter so I guess it is fine?
            Date date = new Date(Long.valueOf(responseList.get(position).getRisetime())*1000);
            Log.d("DATE", "Date: " + date.toString());

            holder.tvTime.setText(date.toString());
            holder.tvDuration.setText(String.valueOf(responseList.get(position).getDuration())+ " seconds");
        }


    }

    @Override
    public int getItemCount() {
        if(responseList == null)
            return 1;
        return responseList.size();

    }
}
