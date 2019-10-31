package com.example.demosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demosqlite.Adapter.CustomAdapter;
import com.example.demosqlite.data.DBManager;
import com.example.demosqlite.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private Button btnSave;
    private Button btnUpdate;
    private ListView lvStudent;
    private DBManager dbManager;
    private CustomAdapter customAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager= new DBManager(this);
        initWidget();
        studentList=dbManager.getAllStudent();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student=createStudent();
                if(student!=null){
                    dbManager.addStudent(student);
                }
                updateListStudent();
                setAdapter();
            }
        });

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student=studentList.get(i);
                edtId.setText(String.valueOf(student.getmID()));
                edtName.setText(student.getmName());
                edtAddress.setText(student.getmAddress());
                edtPhoneNumber.setText(student.getmNumber());
                edtEmail.setText(student.getmEmail());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student=new Student();
                student.setmID(Integer.parseInt(String.valueOf(edtId.getText())));
                student.setmName(edtName.getText().toString());
                student.setmEmail(edtEmail.getText().toString());
                student.setmAddress(edtAddress.getText().toString());
                student.setmNumber(edtPhoneNumber.getText().toString());
                int result = dbManager.updateStudent(student);
                if(result>0){
                    btnSave.setEnabled(true);
                    btnUpdate.setEnabled(false);
                    updateListStudent();
                }else {
                    btnSave.setEnabled(false);
                    btnUpdate.setEnabled(true);
                }

            }
        });
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student= studentList.get(i);
                int result = dbManager.deleteStudent(student.getmID());
                if(result >0) {
                    Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                    updateListStudent();
                }
                else{
                    Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    public Student createStudent(){
        String name=edtName.getText().toString();
        String phoneNumber=edtPhoneNumber.getText().toString();
        String address=edtAddress.getText().toString();
        String email= edtEmail.getText().toString();

        Student student= new Student(name,phoneNumber,address,email);
        return student;
    }

    public void initWidget(){
        edtId= findViewById(R.id.edt_Id);
        edtAddress =findViewById(R.id.edt_address);
        edtName = findViewById(R.id.edt_name);
        edtPhoneNumber= findViewById(R.id.edt_number);
        edtEmail=findViewById(R.id.edt_email);
        btnSave = findViewById(R.id.btn_save);
        btnUpdate=findViewById(R.id.btn_update);
        lvStudent = findViewById(R.id.lv_student);
    }

    private  void setAdapter(){

        if(customAdapter==null){
            customAdapter=new CustomAdapter(this,R.layout.item_list_student,studentList);
            lvStudent.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount()-1);

        }

    }

    public  void updateListStudent(){
        studentList.clear();
        studentList.addAll(dbManager.getAllStudent());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }

    }
}
