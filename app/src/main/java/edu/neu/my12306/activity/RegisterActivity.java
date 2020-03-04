package edu.neu.my12306.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.my12306.R;
import edu.neu.my12306.database.UserDao;
import edu.neu.my12306.entity.Account;

public class RegisterActivity extends AppCompatActivity {
    private EditText t_username;
    private EditText t_name;
    private Spinner t_certificateType;
    private EditText t_certificateNumber;
    private EditText t_passengerType;
    private EditText t_phoneNumber;
    private EditText t_pw1,t_pw2;
    private Button bt_regist;
    private String certificateType;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        t_username = findViewById(R.id.regist_username);
        t_pw1 = findViewById(R.id.regist_pwd1);
        t_pw2 = findViewById(R.id.regist_pwd2);
        t_name = findViewById(R.id.regist_name);
        t_certificateType = findViewById(R.id.regist_certificateType);
        t_certificateNumber = findViewById(R.id.regist_certificateNumber);

        t_phoneNumber = findViewById(R.id.regist_phoneNumber);
        bt_regist = findViewById(R.id.regist_btn);
        userDao =new UserDao(RegisterActivity.this);

        t_certificateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] certificateTypes = getResources().getStringArray(R.array.certificateType_choice);
                certificateType = certificateTypes[(int) id];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = t_username.getText().toString();
                String name = t_name.getText().toString();
                String certificateNumber = t_certificateNumber.getText().toString();
                String passengerType = "普通乘客";

                String phoneNumber = t_phoneNumber.getText().toString();
                String pwd1 = t_pw1.getText().toString();
                String pwd2 = t_pw2.getText().toString();
                boolean flag =userDao.queryByName(username);
                if(flag){
                    Toast.makeText(RegisterActivity.this,"该用户名已经被注册过了",Toast.LENGTH_SHORT).show();
                }else{
                    if(!username.equals("")&&!pwd1.equals("")&&!pwd2.equals("")&&!name.equals("")&&!certificateNumber.equals("")
                            &&!passengerType.equals("")&&!phoneNumber.equals("")){
                        if(pwd1.equals(pwd2)){
                            Account account = new Account();
                            account.setUsername(username);
                            account.setName(name);
                            account.setCertificateType(certificateType);
                            account.setCertificateNumber(certificateNumber);
                            account.setPassengerType(passengerType);
                            account.setPhoneNumber(phoneNumber);
                            account.setPassword(pwd1);
                            if(userDao.register(account)){
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG)
                                        .show();
                                Intent intent = getIntent();
                                intent.setClass(RegisterActivity.this,LoginActivity.class);
                                intent.putExtra("username", account.getUsername());
                                startActivity(intent);
                            }else{
                                System.out.println("失败");
                            }

                        }else{
                            Toast.makeText(RegisterActivity.this, "密码输入不一致", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "以上输入框均为必填项，均不允许为空！", Toast.LENGTH_LONG)
                                .show();
                    }
                }


            }
        });

    }
}
