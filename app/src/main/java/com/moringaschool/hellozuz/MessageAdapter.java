package com.moringaschool.hellozuz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private List<MessageData> mMessages;
    public MessageAdapter (Context context, List<MessageData> messages){
        this.mContext = context;
        this.mMessages = messages;
    }
    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public MessageData getItem(int i) {
        return mMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MessageAdapter.MyViewHolder mViewHolder;
        if (convertView == null) {
            // get layout from xml file
            convertView = inflater.inflate(R.layout.message_adapter_activity, null);
            mViewHolder = new MessageAdapter.MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MessageAdapter.MyViewHolder) convertView.getTag();
        }
        mViewHolder.messageView.setText(mMessages.get(position).getMessage());
        mViewHolder.nameView.setText(mMessages.get(position).getSender());
        return convertView;
    }

    // create a view holder just to have clean refactored code to reduce complexity
    private class MyViewHolder {
        TextView nameView, messageView;

        public MyViewHolder(View item) {
            nameView = (TextView) item.findViewById(R.id.senderName);
            messageView = (TextView) item.findViewById(R.id.messageContent);
        }
    }
}

