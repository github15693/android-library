package com.innoria.khanhduong.library.Example.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.innoria.khanhduong.library.R;
import com.innoria.khanhduong.library.Systems.Contacts.Contact;

import java.util.List;

/**
 * Created by KhanhD Duong on 9/6/2015.
 * Email: khanhduong@innoria.com
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context ctx;
    private List<Contact> contactList;
    private int resourceId;

    public ContactAdapter(Context context, int resource, List<Contact> contacts) {
        super(context, resource, contacts);
        this.ctx = context;
        this.contactList = contacts;
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Contact contact = getItem(position);
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(resourceId, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.tv_email = (TextView) convertView.findViewById(R.id.tv_email);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();

        holder.tv_name.setText(contact.getName());
        holder.tv_phone.setText("Phone no:" + (contact.getPhoneContacts().size() > 0 ? contact.getPhoneContacts().get(0).getPhone(): ""));
        holder.tv_email.setText("Email:" + (contact.getEmailContacts().size() > 0 ? contact.getEmailContacts().get(0).getEmail(): ""));
        return convertView;
    }

    private class ViewHolder{
        TextView tv_name, tv_phone, tv_email;
    }
}
