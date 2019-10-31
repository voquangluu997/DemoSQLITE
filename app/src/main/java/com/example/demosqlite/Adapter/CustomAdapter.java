package com.example.demosqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.demosqlite.R;
import com.example.demosqlite.model.Student;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private  Context context;
    private  int resource;
    private List<Student> listStudent;

    public CustomAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listStudent=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_list_student,parent,false);

            viewHolder=new ViewHolder();
            viewHolder.tvID=convertView.findViewById(R.id.tv_id);
            viewHolder.tvName=convertView.findViewById(R.id.tv_name);
            viewHolder.tvAdress=convertView.findViewById(R.id.tv_address);
            viewHolder.tvPhoneNumber=convertView.findViewById(R.id.tv_phoneNumber);
            viewHolder.tvEmail=convertView.findViewById(R.id.tv_email);
            convertView.setTag(viewHolder);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Student student=listStudent.get(position);
        viewHolder.tvID.setText(String.valueOf(position+1));
        viewHolder.tvName.setText(student.getmName());
        viewHolder.tvPhoneNumber.setText(student.getmNumber());
        viewHolder.tvAdress.setText(student.getmAddress());
        viewHolder.tvEmail.setText(student.getmEmail());
        return convertView;
    }

    public class ViewHolder{

        private TextView tvID;
        private TextView tvName;
        private TextView tvAdress;
        private TextView tvEmail;
        private TextView tvPhoneNumber;
    }
}
